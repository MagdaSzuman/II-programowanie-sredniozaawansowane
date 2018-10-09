package pl.sda.library.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.Borrow;
import pl.sda.library.domain.model.BorrowStatus;
import pl.sda.library.domain.port.BorrowRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BorrowServiceTest {

    private BorrowService borrowService;
    private BooksService booksService;
    private BorrowRepository borrowRepository;
    private Borrow borrow;

    @Before
    public void init() {
        this.booksService = Mockito.mock(BooksService.class);
        this.borrowRepository = Mockito.mock(BorrowRepository.class);
        this.borrowService = new BorrowService(booksService, borrowRepository);
        this.borrow = Mockito.mock(Borrow.class);
    }

    @Test
    public void borrowShouldBorrow() {
        //given
        Mockito.when(booksService.findById(Mockito.anyString())).thenReturn(Optional.of(Book.builder().id("book-id")
                .title("Dziady III").author("Adam Mickiewicz").year(1823).language("Polish").pages(250).build()));
        String id = "book-id";
        String userName = "test-user";
        //when
        Borrow borrow = borrowService.borrow(id, userName);
        //then
        Assert.assertTrue(borrow.getBook() != null);
        Assert.assertEquals(userName, borrow.getUser());
        Assert.assertEquals(BorrowStatus.BORROWED, borrow.getBorrowStatus());
        Mockito.verify(borrowRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void findByUserAndStatusShouldReturnBorrowsForExistingUseAndBorrowedStatus() {
        //given
        Mockito.when(borrowRepository.findAll()).thenReturn(
                Arrays.asList(
                        Borrow.builder().user("test-user").borrowStatus(BorrowStatus.BORROWED)
                        .book(Book.builder().id("1").title("Dzieci z Bulerbyn").build()).build(),
                        Borrow.builder().user("test-user").borrowStatus(BorrowStatus.BORROWED)
                                .book(Book.builder().id("2").title("Dziady IV").build()).build(),
                        Borrow.builder().user("admin-user").borrowStatus(BorrowStatus.BORROWED)
                                .book(Book.builder().id("3").title("Folwark zwierzecy").build()).build(),
                        Borrow.builder().user("test-user").borrowStatus(BorrowStatus.RETURNED)
                                .book(Book.builder().id("4").title("W pustyni i w puszczy").build()).build()
                )
        );
        String userName = "test-user";
        BorrowStatus status = BorrowStatus.BORROWED;
        //when
        List<Borrow> borrows = borrowService.findByUserAndStatus(userName, status);
        //then
        Assert.assertEquals(2,borrows.size());
        borrows.forEach(e->Assert.assertTrue(userName.equals(e.getUser())&&
                status.equals(e.getBorrowStatus())));
    }
}