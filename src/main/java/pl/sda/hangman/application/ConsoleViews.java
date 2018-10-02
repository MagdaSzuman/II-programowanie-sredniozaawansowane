package pl.sda.hangman.application;

import pl.sda.hangman.domain.model.Game;

import java.util.Scanner;

public class ConsoleViews {
    private Scanner scanner;

    public ConsoleViews(Scanner scanner) {
        this.scanner = scanner;
    }

    public Integer mainMenu(){
        System.out.println("=======Wisielec=======");
        System.out.println("1. Start");
        System.out.println("2. Wyniki");
        System.out.println("0. Koniec");
        return getIntValue();
    }

    private Integer getIntValue(){
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public char displayGame(Game game) {
        char[] phraseStatus = game.getPhraseStatus();
        int leftAttepmts = game.getLeftAttempts();
        String phraseStatusAsSting = new String(phraseStatus);
        System.out.println("==================");
        System.out.println(phraseStatusAsSting+ " (" + leftAttepmts + ")");
        return scanner.nextLine().charAt(0);
    }

    public void displayWrongLetterAdded() {
        System.out.println("Podano niepoprawną literę");
    }

    public void displayGameWon() {
        System.out.println("==================");
        System.out.println("Wygrałaś/wygrałeś");
        System.out.println("==================");
        waitForAction();
    }

    public void displayGameLost() {
        System.out.println("==================");
        System.out.println("Przegrałaś/przegrałeś");
        System.out.println("==================");
        waitForAction();
    }

    private void waitForAction() {
        System.out.println("==================");
        System.out.println("Wciśnij ENTER, aby kontynuować");
        System.out.println("==================");
        scanner.nextLine();
    }

}
