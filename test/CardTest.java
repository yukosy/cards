import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void getMaxNumError() {
        Card.cards.add(new Card("first","second",5));
        Card.cards.add(new Card("third","forth",0));
        Card.cards.add(new Card("fifth","sixth",7));

        int num = Card.getMaxNumError();
        Assert.assertEquals(num, 7);
    }
}