package pl.sda.library.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.port.BooksRepository;

import java.util.Arrays;
import java.util.List;

public class BooksServiceTest {

    private BooksRepository booksRepository;
    private BooksService booksService;

    @Before
    public void before(){
        this.booksRepository = Mockito.mock(BooksRepository.class);
        Mockito.when(booksRepository.findAll()).thenReturn(
                Arrays.asList(
                        Book.builder().title("Dziady III").author("Adam Mickiewicz").build(),
                        Book.builder().title("Dziady IV").author("Adam Mickiewicz").build(),
                        Book.builder().title("W pustyni i w puszczy").author("Sienkiewicz").build()));
        this.booksService = new BooksService(booksRepository);
    }

    @Test
    public void findByTitleShouldReturnEmptyListForNullTitle() {
        //given
        String title = null;
        //when
        List<Book> books = booksService.findByTitle(title);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByTitleShouldReturnEmptyListForEmptyTitle() {
        //given
        String title = "";
        //when
        List<Book> books = booksService.findByTitle(title);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByTitleShouldReturnItemsForExistingTitle() {
        //given
        String title = "Dziady";
        //when
        List<Book> books = booksService.findByTitle(title);
        //then
        Assert.assertEquals(2, books.size());
        books.forEach(book -> Assert.assertTrue(book.getTitle().contains(title)));
    }

    @Test
    public void findByTitleShouldReturnEmptyListForNonExistingTitle() {
        //given
        String title = "non-existing";
        //when
        List<Book> books = booksService.findByTitle(title);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByAuthorShouldReturnEmptyListForNullAuthor() {
        //given
        String author = null;
        //when
        List<Book> books = booksService.findByAuthor(author);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByAuthorShouldReturnEmptyListForEmptyAuthor() {
        //given
        String author = "";
        //when
        List<Book> books = booksService.findByAuthor(author);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByAuthorShouldReturnItemsForExistingAuthor() {
        //given
        String author = "Adam Mickiewicz";
        //when
        List<Book> books = booksService.findByAuthor(author);
        //then
        Assert.assertEquals(2, books.size());
        books.forEach(book -> Assert.assertTrue(book.getAuthor().equals(author)));
    }

    @Test
    public void findByAuthorShouldReturnItemsForExistingAuthorLastName() {
        //given
        String authorLastName = "Mickiewicz";
        String expectedAuthorName = "Adam Mickiewicz";
        //when
        List<Book> books = booksService.findByAuthor(authorLastName);
        //then
        Assert.assertEquals(2, books.size());
        books.forEach(book -> Assert.assertTrue(book.getAuthor().equals(expectedAuthorName)));
    }

    @Test
    public void findByAuthorShouldReturnEmptyListForNonExistingAuthor(){
//        /given
        String author = "non-existing";
        //when
        List<Book> books = booksService.findByAuthor(author);
        //then
        Assert.assertEquals(0, books.size());
    }



}