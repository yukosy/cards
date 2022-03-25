import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Executor {
    private final Logger logger;
    private final Scanner scanner;

    public Executor(Logger logger, Scanner scanner) {
        this.logger = logger;
        this.scanner = scanner;
    }
    public String checkDefinition(String search) {
        for (Card card : Card.cards) {
            if (card.getDefinition().equals(search)) {
                return card.getTerm();
            }
        }
        return null;
    }

    public String checkTerm(String search) {
        for (Card card : Card.cards) {
            if (card.getTerm().equals(search)) {
                return card.getDefinition();
            }
        }
        return null;
    }

    public void replace(Card card) {
        for (int i = 0; i < Card.cards.size(); i++) {
            if (Card.cards.get(i).getTerm().equals(card.getTerm())) {
                Card.cards.add(i, card);
                break;
            } else if (Card.cards.get(i).getDefinition().equals(card.getDefinition())) {
                Card.cards.add(i, card);
                break;
            }
        }
    }

    public void giveCard(Card card) {
        logger.msg("Print the definition of \"" + card.getTerm() + "\" :");
        String definitionInput = scanner.nextLine();
        logger.inputMsg(definitionInput);
        if (definitionInput.equals(card.getDefinition())) {
            logger.msg("Correct!");
        } else if (checkDefinition(definitionInput) != null) {
            String correctValue = checkDefinition(definitionInput);
            card.upError();
            String wrongRightMsg = "Wrong. The right answer is \"" + card.getDefinition() + "\", " +
                    "but your definition is correct for \"" + correctValue + "\"";
            logger.msg(wrongRightMsg);
        } else {
            card.upError();
            logger.msg("Wrong. The right answer is \"" + card.getDefinition() + "\".");
        }
    }

    public void add() {
        String term;
        String definition;
        logger.msg("The card:");
        term = scanner.nextLine();
        logger.inputMsg(term);
        if (checkTerm(term) != null) {
            logger.msg("The card \"" + term + "\" already exists");
        } else {
            logger.msg("The definition of the card:");
            definition = scanner.nextLine();
            logger.inputMsg(definition);
            String message;
            if (checkDefinition(definition) != null) {
                message = "The definition \"" + definition + "\" already exists.";
            } else {
                Card.cards.add(new Card(term, definition, 0));
                message = "The pair (\"" + term + "\":\"" + definition + "\") has been added";
            }
            logger.msg(message);
        }
    }

    public void remove() {
        String term;
        logger.msg("Which card?");
        term = scanner.nextLine();
        logger.inputMsg(term);
        String message = "Can't remove \"" + term + "\": there is no such card.";
        for (int i = 0; i < Card.cards.size(); i++) {
            if (Card.cards.get(i).getTerm().equals(term)) {
                Card.cards.remove(i);
                message = "The card has been removed.";
                break;
            }
        }
        logger.msg(message);
    }

    public void importFile() {
        logger.msg("File name:");
        String filePath = scanner.nextLine();
        logger.inputMsg(filePath);
        importFile(filePath);
    }

    public void importFile(String filePath) {
        List<String> array = new ArrayList<>();
        File file = new File(filePath);
        String term;
        String definition;
        if (file.exists()) {
            try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {
                array = in.lines().collect(Collectors.toList());
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
            if (array.size() == 0) {
                logger.msg("Import failed. Empty file");
            } else {
                for (String s : array) {
                    String[] split = s.split(":");
                    term = split[0];
                    definition = split[1];
                    int error = Integer.parseInt(split[2]);
                    Card card = new Card(term, definition, error);
                    if (checkTerm(term) != null || checkDefinition(definition) != null) {
                        replace(card);
                    } else {
                        Card.cards.add(card);
                    }
                }
                logger.msg(array.size() + " cards have been loaded.");
            }
        } else {
            logger.msg("File not found.");
        }
    }

    public void export() {
        logger.msg("File name:");
        String filePath = scanner.nextLine();
        logger.inputMsg(filePath);
        export(filePath);
    }

    public void export(String filePath) {
        int count = 0;
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                logger.msg("File is created!");
            } else {
                logger.msg("File already exists.");
            }
            FileWriter writer = new FileWriter(filePath, false);
            for (Card card : Card.cards) {
                writer.write(card.getTerm() + ":" + card.getDefinition() + ":" + card.getError());
                writer.append('\n');
                count++;
            }
            writer.flush();
        } catch (IOException ex) {
            logger.msg(ex.getMessage());
        }
        logger.msg(count + " cards have been saved.");
    }

    public void ask() {
        logger.msg("How many times to ask?");
        int count = scanner.nextInt();
        logger.inputMsg(count + "");
        int i = 0;
        while (i < count) {
            for (Card card : Card.cards) {
                giveCard(card);
                i++;
                if (i >= count) {
                    break;
                }
            }
        }
    }

    public void log() {
        logger.msg("File name:");
        String fileName = scanner.nextLine();
        logger.inputMsg(fileName);
        File file = new File(fileName);
        try(FileWriter writer = new FileWriter(file)) {
            for(String str : logger.getLogger()) {
                writer.write(str);
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.msg("The log has been saved.");

    }

    public void getHardestCard() {
        int max = Card.getMaxNumError();
        if (Card.cards.size() == 0 || max == 0) {
            logger.msg("There are no cards with errors");
        } else {
            ArrayList<String> hardestCard = new ArrayList<>();
            for (Card card : Card.cards) {
                if (card.getError() == max) {
                    hardestCard.add(card.getTerm());
                }
            }
            if (hardestCard.size() == 1) {
                String oneCardMsg = "The hardest card is \"" + hardestCard.get(0)
                        + "\". You have \"" + max
                        + "\" errors answering it.";
                logger.msg(oneCardMsg);
            } else {
                StringBuilder out = new StringBuilder("The hardest cards are ");
                for (String str : hardestCard) {
                    out.append("\"").append(str).append("\"");
                    if (hardestCard.size() != (hardestCard.indexOf(str) + 1)) {
                        out.append(", ");
                    }
                }
                out.append(". You have ").append(max).append(" errors answering them.");
                logger.msg(out.toString());
            }
        }
    }

    public void resetStats() {
        for (Card card : Card.cards) {
            card.setError(0);
        }
        logger.msg("Card statistics have been reset.");
    }

}

