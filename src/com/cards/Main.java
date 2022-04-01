package com.cards;

import com.cards.controller.Executor;
import com.cards.logger.Logger;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Logger logger = new Logger();
        Scanner scanner = new Scanner(System.in);
        Executor executor = new Executor(logger, scanner);

        executor.init(args);
    }
}
