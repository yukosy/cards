
import com.cards.entitty.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.cards.repository.CardDB;


public class CardTest {
    CardDB db = new CardDB();

    @Test
    public void getMaxNumError() {
        db.getCards().add(new Card("first","second",5));
        db.getCards().add(new Card("third","forth",0));
        db.getCards().add(new Card("fifth","sixth",7));

        int num = db.getMaxNumError();
        Assertions.assertEquals(num, 7);
    }
}