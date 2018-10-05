package pl.sda.library.application;

import java.util.Scanner;

public class ConsoleViews {
    private Scanner scanner;

    public ConsoleViews(Scanner scanner) {
        this.scanner = scanner;
    }
    private Integer getNumberFromUser() {
        Integer option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public Integer menu() {
        System.out.println("========Biblioteka========");
        System.out.println("1. Książki");
        System.out.println("2. ....");
        System.out.println("0. Koniec");
        System.out.println("--------------------------");
        return getNumberFromUser();
    }

    public Integer showBooksMenu() {
        System.out.println("==========================");
        System.out.println("1. Znajdź po nazwie");
        System.out.println("2. Znajdź po autorze");
        System.out.println("0. Wyjdź");
        System.out.println("--------------------------");
        return getNumberFromUser();
    }

    public String getBookName() {
        System.out.println("==========================");
        System.out.println("Podaj nazwę książki");
        System.out.println("--------------------------");
        return scanner.nextLine();
    }
}
