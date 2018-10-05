package pl.sda.library.application;

import pl.sda.library.domain.model.Book;

import java.util.List;
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

    public void displayBooks(List<Book> books) {
        if (books.size()>0) {
//            books.forEach(this::displayShortenedBook);
            //tożsame z poniższym, ale tylko dla prostych lambdach, przy wielu argumentach to nie zadziała
             books.forEach(book -> displayShortenedBook(book));
        }else {
            System.out.println("Brak książek do wyświetlania");
        }
        waitForAction();
    }

    private void displayShortenedBook(Book book) {
        System.out.println("==========================");
        System.out.println(book.getTitle() + " (" + book.getYear() + ") - " + book.getAuthor());
    }

    private void waitForAction() {
        System.out.println("--------------------------");
        System.out.println("Wciśnij ENTER aby kontynuować");
        System.out.println("--------------------------");
        scanner.nextLine();
    }
}
