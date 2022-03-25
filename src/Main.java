import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger();
        Scanner scanner = new Scanner(System.in);
        Executor executor = new Executor(logger, scanner);

        String variableImport = "-import";
        String variableExport = "-export";
        String fileImport = "";
        String fileExport = "";
        for (int i = 0; i < args.length; i = i + 2) {
            if(variableImport.equals(args[i])) {
                fileImport = args[i + 1];
            } else if(variableExport.equals(args[i])) {
                fileExport = args[i + 1];
            }
        }
        logger.msg(" ");
        logger.msg(" ");
        if(fileImport.length()>4) {
            executor.importFile(fileImport);
        }
        while (true) {
            String inputMsg = "Input the action " +
                    "(add, remove, import, export, ask, exit, log, hardest card, reset stats):";
            logger.msg(inputMsg);
            String input = scanner.nextLine();
            logger.inputMsg(input);
            switch (input) {
                case "add" -> executor.add();
                case "remove" -> executor.remove();
                case "import" -> executor.importFile();
                case "export" -> executor.export();
                case "ask" -> executor.ask();
                case "log" -> executor.log();
                case "hardest card" -> executor.getHardestCard();
                case "reset stats" -> executor.resetStats();
                case "exit" -> {
                    String byeMsg = "Bye bye!";
                    logger.msg(byeMsg);
                    if (fileExport.length() > 4) {
                        executor.export(fileExport);
                    }
                    System.exit(0);
                }
            }
        }
    }
}
