package pl.sda.library.application;

import pl.sda.library.domain.model.Book;

import java.util.List;
import java.util.Map;
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
        System.out.println("2. Autorzy");
        System.out.println("0. Koniec");
        System.out.println("--------------------------");
        return getNumberFromUser();
    }

    public Integer showBooksMenu() {
        System.out.println("==========================");
        System.out.println("1. Znajdź po nazwie");
        System.out.println("2. Znajdź po autorze");
        System.out.println("3. Znajdź po dacie wydania");
        System.out.println("4. Znajdź po języku wydania");
        System.out.println("5. Znajdź po zakresie stron");
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
        if (books.size() > 0) {
//            books.forEach(this::displayShortenedBook);
            //tożsame z poniższym, ale tylko dla prostych lambdach, przy wielu argumentach to nie zadziała
            books.forEach(book -> displayShortenedBook(book));
        } else {
            System.out.println("Brak książek do wyświetlania");
        }
        waitForAction();
    }

    private void displayShortenedBook(Book book) {
        System.out.println("==========================");
        System.out.println(book.getId() + ". " + book.getTitle() + " (" + book.getYear() + ") - " + book.getAuthor());
    }

    private void waitForAction() {
        System.out.println("--------------------------");
        System.out.println("Wciśnij ENTER aby kontynuować");
        System.out.println("--------------------------");
        scanner.nextLine();
    }

    public String getBookAuthor() {
        System.out.println("==========================");
        System.out.println("Podaj autora książki");
        System.out.println("--------------------------");
        return scanner.nextLine();
    }

    public Integer getBookYear() {
        System.out.println("==========================");
        System.out.println("Podaj rok wydania książki");
        System.out.println("--------------------------");
        return getNumberFromUser();
    }

    public String getBookLanguage() {
        System.out.println("==========================");
        System.out.println("Podaj język książki");
        System.out.println("--------------------------");
        return scanner.nextLine();
    }

    public Integer getBookFromPages() {
        System.out.println("==========================");
        System.out.println("Podaj dolny zakres stron");
        System.out.println("--------------------------");
        return getNumberFromUser();
    }

    public Integer getBookToPages() {
        System.out.println("==========================");
        System.out.println("Podaj górny zakres stron");
        System.out.println("--------------------------");
        return getNumberFromUser();
    }

    public void displayError(String message) {
        System.out.println("==========================");
        System.out.println("ERROR " + message);
        System.out.println("==========================");
        waitForAction();
    }

    public void displayAuthors(Map<String, Long> autors) {
        autors.entrySet()
                .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue()));
        System.out.println();
    }

    public String getBookId() {
        System.out.println("==========================");
        System.out.println("Podaj identyfikator ksiazki");
        System.out.println("--------------------------");
        return scanner.nextLine();
    }

    public String getUserName() {
        System.out.println("==========================");
        System.out.println("Podaj nazwę użytkownika");
        System.out.println("--------------------------");
        return scanner.nextLine();
    }

    public void displayBorrowSuccess(String bookTitle) {
        System.out.println("==========================");
        System.out.println("Udało się wypożyczyć książkę " + bookTitle);
        System.out.println("--------------------------");
    }

    public void displayBorrowFailure() {
        System.out.println("==========================");
        System.out.println("Nie udało się wypożyczyć książki");
        System.out.println("--------------------------");
    }
}
