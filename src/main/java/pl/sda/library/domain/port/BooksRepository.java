package pl.sda.library.domain.port;

import pl.sda.library.domain.model.Book;

import java.io.IOException;
import java.util.List;

public interface BooksRepository {
    List<Book> findAll();
}
