package com.cards.logger;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private final List<String> loggerList;

    public Logger() {
        this.loggerList = new ArrayList<>();
    }

    public List<String> getLogger() {
        return loggerList;
    }

    public void msg(String string) {
        System.out.println(string);
        this.loggerList.add(string);
    }

    public void inputMsg(String string) {
        this.loggerList.add(string);
    }
}
