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
        System.out.println("2. Dodaj fraze");
        System.out.println("0. Koniec");
        System.out.println("======================");
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

    public String addPhraseMessage() {
        System.out.println("==================");
        System.out.println("Podaj frazę");
        System.out.println("==================");
        return scanner.nextLine();
    }

    public void displayPhraseContainsForbiddenWords() {
        System.out.println("==================");
        System.out.println("Podana fraza zawiera zobronione słowa");
        System.out.println("==================");
        waitForAction();
    }

    public void displayPhraseAlreadyExists() {
        System.out.println("==================");
        System.out.println("Podana fraza już istnieje");
        System.out.println("==================");
        waitForAction();
    }

    public void displayPhraseAddedSucessfully(String phrase) {
        System.out.println("==================");
        System.out.println("Podana fraza " + phrase + " została dodana pomyślnie");
        System.out.println("==================");
        waitForAction();
    }
}
