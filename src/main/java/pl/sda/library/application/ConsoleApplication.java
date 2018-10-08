package pl.sda.library.application;

import pl.sda.library.domain.BooksService;
import pl.sda.library.domain.exceptions.InvalidPagesValueException;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.port.BooksRepository;
import pl.sda.library.infrastructure.json.JsonBooksRepository;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {
    private ConsoleViews consoleViews;
    private BooksService booksService;

    public ConsoleApplication() {
        BooksRepository booksRepository = new JsonBooksRepository(new File("C:\\Users\\M\\Desktop\\IntelliJ - projekty\\programowanie-sredniozaawansowane\\src\\main\\resources\\books.json"));
        this.consoleViews = new ConsoleViews(new Scanner(System.in));
        this.booksService = new BooksService(booksRepository);
    }

    public void start() {
        boolean flag = true;
        while (flag) {
            Integer option = consoleViews.menu();
            switch (option) {
                case 1:
                    showBooks();
                    break;
                case 0:
                    flag = false;
                default:
                    System.out.println("Wybrano błędną opcję");
            }
        }
    }

    private void showBooks() {
        Integer option = consoleViews.showBooksMenu();
        switch (option) {
            case 1:
                String title = consoleViews.getBookName();
//                long before = System.currentTimeMillis();
                List<Book> books = booksService.findByTitle(title);
//                long after = System.currentTimeMillis();
//                System.out.println(after - before);
                consoleViews.displayBooks(books);
                break;
            case 2:
                String author = consoleViews.getBookAuthor();
                List<Book> booksByAuthor = booksService.findByAuthor(author);
                consoleViews.displayBooks(booksByAuthor);
                break;
            case 3:
                Integer year = consoleViews.getBookYear();
                List<Book> booksByYear = booksService.findByYear(year);
                consoleViews.displayBooks(booksByYear);
                break;
            case 4:
                String language = consoleViews.getBookLanguage();
                List<Book> booksByLanguage = booksService.findByLanguage(language);
                consoleViews.displayBooks(booksByLanguage);
                break;
            case 5:
                findByPagesRange();
                break;
            default:
                System.out.println("Błąd");
        }
    }

    private void findByPagesRange() {
        try{
            Integer from = consoleViews.getBookFromPages();
            Integer to = consoleViews.getBookToPages();
            List<Book> booksByPages = booksService.findByPagesRange(from, to);
            consoleViews.displayBooks(booksByPages);
        }catch (InvalidPagesValueException e){
            consoleViews.displayError("Niepoprawne dane");
        }

    }
}
