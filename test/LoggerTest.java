import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoggerTest {

    Logger logger = new Logger();

    @Test
    public void msg() {
        String message = "message";
        logger.msg(message);

        Assert.assertEquals(logger.getLogger().get(0),"message");
    }

    @Test
    public void msgTest() {
        String message = "message";
        for(int i =0; i < 10000; i++) {
            logger.msg(message);
        }

        Assert.assertEquals(logger.getLogger().size(),10000);
    }
}