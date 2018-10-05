package pl.sda.library.domain;

import org.apache.commons.lang3.StringUtils;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.port.BooksRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BooksService {
    private BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findByTitle(String title) {
        return StringUtils.isBlank(title) ?
                Collections.emptyList() :
                booksRepository.findAll()
                        .stream()
                        .filter(e -> e.getTitle().contains(title))
                        .collect(Collectors.toList());
    }
}