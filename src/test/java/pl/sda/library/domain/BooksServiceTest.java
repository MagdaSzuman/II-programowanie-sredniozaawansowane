package pl.sda.library.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.sda.library.domain.exceptions.InvalidPagesValueException;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.port.BooksRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BooksServiceTest {

    private BooksRepository booksRepository;
    private BooksService booksService;

    @Before
    public void before() {
        this.booksRepository = Mockito.mock(BooksRepository.class);
        Mockito.when(booksRepository.findAll()).thenReturn(
                Arrays.asList(
                        Book.builder().id("7").title("Dziady III").author("Adam Mickiewicz").year(1823)
                                .language("Polish").pages(250).build(),
                        Book.builder().id("13").title("Dziady IV").author("Adam Mickiewicz").year(1823)
                                .language("Polish").pages(190).build(),
                        Book.builder().id("34").title("W pustyni i w puszczy").author("Sienkiewicz").year(1911)
                                .language("German").pages(150).build()));
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
    public void findByAuthor5ShouldReturnEmptyListForNonExistingAuthor() {
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
    public void findByYear3ShouldReturnEmptyListForNonExistingYear() {
//       given
        Integer year = -100;
        //when
        List<Book> books = booksService.findByYear(year);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByLanguage1ShouldReturnEmptyListForNullLanguage() {
        //given
        String language = null;
        //when
        List<Book> books = booksService.findByLanguage(language);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByLanguage2ShouldReturnEmptyListForEmptyLanguage() {
        //given
        String language = "";
        //when
        List<Book> books = booksService.findByLanguage(language);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByLanguage3ShouldReturnItemsForExistingLanguage() {
        //given
        String language = "German";
        //when
        List<Book> books = booksService.findByLanguage(language);
        //then
        Assert.assertEquals(1, books.size());
        books.forEach(book -> Assert.assertTrue(book.getLanguage().contains(language)));
    }

    @Test
    public void findByLanguage4ShouldReturnEmptyListForNonExistingLanguage() {
        //given
        String language = "non-existing";
        //when
        List<Book> books = booksService.findByLanguage(language);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByPagesRange1ShouldReturnBooksForValidPagesRange() throws InvalidPagesValueException {
        //given
        Integer from = 100;
        Integer to = 200;
        //when
        List<Book> books = booksService.findByPagesRange(from, to);
        //then
        Assert.assertEquals(2, books.size());
        books.forEach(e -> Assert.assertTrue(e.getPages() >= from && e.getPages() <= to));
    }

    @Test
    public void findByPagesRange2ShouldReturnBooksWhenFromIsEqualToTo() throws InvalidPagesValueException {
        //given
        Integer from = 150;
        Integer to = from;
        //when
        List<Book> books = booksService.findByPagesRange(from, to);
        //then
        Assert.assertEquals(1, books.size());
        Assert.assertEquals(from, books.get(0).getPages());
    }

    @Test(expected = InvalidPagesValueException.class)
    public void findByPagesRange3ShouldThrowExceptionWhenFromIsNegative() throws InvalidPagesValueException {
        //given
        Integer from = -100;
        Integer to = 100;
        //when
        booksService.findByPagesRange(from, to);
    }

    @Test(expected = InvalidPagesValueException.class)
    public void findByPagesRange4ShouldThrowExceptionWhenToIsNegative() throws InvalidPagesValueException {
        //given
        Integer from = 100;
        Integer to = -100;
        //when
        booksService.findByPagesRange(from, to);
    }

    @Test(expected = InvalidPagesValueException.class)
    public void findByPagesRange4ShouldThrowExceptionWhenFromAndToAreBothNegative() throws InvalidPagesValueException {
        //given
        Integer from = -100;
        Integer to = -100;
        //when
        booksService.findByPagesRange(from, to);
    }

    @Test(expected = InvalidPagesValueException.class)
    public void findByPagesRange5ShouldThrowExceptionWhenFromIsBiggerThanTo() throws InvalidPagesValueException {
        ///given
        Integer from = 150;
        Integer to = 100;
        //when
        booksService.findByPagesRange(from, to);
    }

    @Test
    public void getAutorsShouldReturnAutors() {
        //when
        Map<String, Long> authors = booksService.getAuthors();
        //then
        Assert.assertEquals(authors.get("Adam Mickiewicz"), new Long(2));
        Assert.assertEquals(authors.get("Sienkiewicz"), new Long(1));
    }

    @Test
    public void findByInShouldReturnBookForExistingId() {
        //given
        String id = "7";
        String expectedTitle = "Dziady III";
        //when
        Optional<Book> book = booksService.findById(id);
        //then
        Assert.assertTrue(book.isPresent());
        Assert.assertEquals(id, book.get().getId());
        Assert.assertEquals(expectedTitle, book.get().getTitle());
    }

    @Test
    public void findByInShouldNotReturnBookForNonExistingId() {
        //given
        String id = "non-existing-id";
        //when
        Optional<Book> book = booksService.findById(id);
        //then
        Assert.assertFalse(book.isPresent());
    }
}