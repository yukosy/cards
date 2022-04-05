import com.cards.controller.Executor;
import com.cards.entitty.Card;
import com.cards.logger.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.cards.repository.CardDB;

import java.util.Scanner;

public class ExecutorTest {

    Logger logger = new Logger();
    Scanner scanner = new Scanner(System.in);
    Executor executor = new Executor(logger, scanner);
    CardDB db = new CardDB();

    @BeforeEach
    public void setUpCards() {

        db.getCards().add(new Card("Paris","Capital of France",0));
        db.getCards().add(new Card("London","Capital of Grate Britain",0));
        db.getCards().add(new Card("Moscow","Capital of Russia",0));
        db.getCards().add(new Card("Minsk","Capital of Belarus",0));
        db.getCards().add(new Card("Yerevan","Capital of Armenia",0));
    }

    @Test
    public void checkDefinition() {
        String actual = executor.checkDefinition("Capital of France");

        Assertions.assertEquals("Paris", actual);
    }

    @Test
    public void checkTerm() {
        String actual = executor.checkTerm("Paris");

        Assertions.assertEquals("Capital of France", actual);
    }

    @Test
    public void replace_TERM() {
        Card card = new Card("Piter", "Capital of Russia", 0);

        executor.replace(card);
        String actual = db.getCards().get(2).getTerm();

        Assertions.assertEquals("Piter", actual);
    }

    @Test
    public void replace_DEFENITION() {
        Card card = new Card("Piter", "Cultural Capital of Russia", 0);

        executor.replace(card);
        String actual = db.getCards().get(2).getDefinition();

        Assertions.assertEquals("Cultural Capital of Russia", actual);
    }

    @Test
    public void resetStats() {
        db.getCards().get(0).setError(5);
        executor.resetStats();

        Assertions.assertEquals(0, db.getCards().get(0).getError());
    }
}