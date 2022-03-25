import java.util.ArrayList;
import java.util.List;

public class Logger {
    private final List<String> logger;

    public Logger() {
        this.logger = new ArrayList<>();
    }

    public List<String> getLogger() {
        return logger;
    }

    public void msg(String string) {
        System.out.println(string);
        this.logger.add(string);
    }

    public void inputMsg(String string) {
        this.logger.add(string);
    }
}
