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
                        Book.builder().title("Dziady III").author("Adam Mickiewicz").year(1823).build(),
                        Book.builder().title("Dziady IV").author("Adam Mickiewicz").year(1823).build(),
                        Book.builder().title("W pustyni i w puszczy").author("Sienkiewicz").year(1911).build()));
        this.booksService = new BooksService(booksRepository);
    }

    @Test
    public void findByTitle1ShouldReturnEmptyListForNullTitle() {
        //given
        String title = null;
        //when
        List<Book> books = booksService.findByTitle(title);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByTitle2ShouldReturnEmptyListForEmptyTitle() {
        //given
        String title = "";
        //when
        List<Book> books = booksService.findByTitle(title);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByTitle3ShouldReturnItemsForExistingTitle() {
        //given
        String title = "Dziady";
        //when
        List<Book> books = booksService.findByTitle(title);
        //then
        Assert.assertEquals(2, books.size());
        books.forEach(book -> Assert.assertTrue(book.getTitle().contains(title)));
    }

    @Test
    public void findByTitle4ShouldReturnEmptyListForNonExistingTitle() {
        //given
        String title = "non-existing";
        //when
        List<Book> books = booksService.findByTitle(title);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByAuthor1ShouldReturnEmptyListForNullAuthor() {
        //given
        String author = null;
        //when
        List<Book> books = booksService.findByAuthor(author);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByAuthor2ShouldReturnEmptyListForEmptyAuthor() {
        //given
        String author = "";
        //when
        List<Book> books = booksService.findByAuthor(author);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByAuthor3ShouldReturnItemsForExistingAuthor() {
        //given
        String author = "Adam Mickiewicz";
        //when
        List<Book> books = booksService.findByAuthor(author);
        //then
        Assert.assertEquals(2, books.size());
        books.forEach(book -> Assert.assertTrue(book.getAuthor().equals(author)));
    }

    @Test
    public void findByAuthor4ShouldReturnItemsForExistingAuthorLastName() {
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
    public void findByAuthor5ShouldReturnEmptyListForNonExistingAuthor(){
//        /given
        String author = "non-existing";
        //when
        List<Book> books = booksService.findByAuthor(author);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByYear1ShouldReturnEmptyListForNullYear() {
        //given
        Integer year = null;
        //when
        List<Book> books = booksService.findByYear(year);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByYear2ShouldReturnItemsForExistingYear() {
        //given
        Integer year = 1823;
        //when
        List<Book> books = booksService.findByYear(year);
        //then
        Assert.assertEquals(2, books.size());
        books.forEach(book -> Assert.assertEquals(book.getYear(), year));
    }

    @Test
    public void findByYear3ShouldReturnEmptyListForNonExistingYear(){
//       given
        Integer year = -100;
        //when
        List<Book> books = booksService.findByYear(year);
        //then
        Assert.assertEquals(0, books.size());
    }

}