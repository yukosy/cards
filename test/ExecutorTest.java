import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ExecutorTest {

    Logger logger = new Logger();
    Scanner scanner = new Scanner(System.in);
    Executor executor = new Executor(logger, scanner);

    @Before
    public void setUpCards() {

        Card.cards.add(new Card("Paris","Capital of France",0));
        Card.cards.add(new Card("London","Capital of Grate Britain",0));
        Card.cards.add(new Card("Moscow","Capital of Russia",0));
        Card.cards.add(new Card("Minsk","Capital of Belarus",0));
        Card.cards.add(new Card("Yerevan","Capital of Armenia",0));
    }

    @Test
    public void checkDefinition() {
        String actual = executor.checkDefinition("Capital of France");

        Assert.assertEquals("Paris", actual);
    }

    @Test
    public void checkTerm() {
        String actual = executor.checkTerm("Paris");

        Assert.assertEquals("Capital of France", actual);
    }

    @Test
    public void replace_TERM() {
        Card card = new Card("Piter", "Capital of Russia", 0);

        executor.replace(card);
        String actual = Card.cards.get(2).getTerm();

        Assert.assertEquals("Piter", actual);
    }

    @Test
    public void replace_DEFENITION() {
        Card card = new Card("Piter", "Cultural Capital of Russia", 0);

        executor.replace(card);
        String actual = Card.cards.get(2).getDefinition();

        Assert.assertEquals("Cultural Capital of Russia", actual);
    }

    @Test
    public void resetStats() {
        Card.cards.get(0).setError(5);
        executor.resetStats();

        Assert.assertEquals(0, Card.cards.get(0).getError());
    }
}