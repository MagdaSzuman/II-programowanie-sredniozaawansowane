package pl.sda.library.domain;

import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.Borrow;
import pl.sda.library.domain.model.BorrowStatus;
import pl.sda.library.domain.port.BooksRepository;
import pl.sda.library.domain.port.BorrowRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BorrowService {

    private BooksService booksService;
    private BorrowRepository borrowRepository;

    public BorrowService(BooksService booksService, BorrowRepository borrowRepository) {
        this.booksService = booksService;
        this.borrowRepository = borrowRepository;
    }

    public Borrow borrow(String bookId, String userName) {
        Optional<Book> book = booksService.findById(bookId);
        Borrow borrow = null;
        if (book.isPresent()) {
            borrow = new Borrow(book.get(),userName);
            borrowRepository.save(borrow);
        }
        return borrow;
    }


    public List<Borrow> findByUserAndStatus(String userName, BorrowStatus status) {
        return borrowRepository.findAll()
                .stream()
                .filter(e->userName.equals(e.getUser()))
                .filter(e->status.equals(e.getBorrowStatus()))
                .collect(Collectors.toList());
    }
}
