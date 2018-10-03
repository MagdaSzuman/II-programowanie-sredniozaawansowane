package pl.sda.hangman;

import pl.sda.hangman.application.ConsoleApplication;

import java.io.FileNotFoundException;

public class HangmanApplication {
    public static void main(String[] args) throws FileNotFoundException {
        ConsoleApplication consoleApplication = new ConsoleApplication();
        consoleApplication.start();
    }
}
