package pl.sda.library.domain;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.IntegerAssert;
import pl.sda.library.domain.filtering.BooksFilteringChain;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.port.BooksRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BooksService {
    private BooksRepository booksRepository;
    private BooksFilteringChain chain;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
        this.chain = new BooksFilteringChain();
    }

    public List<Book> findByTitle(String title) {
        if (StringUtils.isBlank(title)) {
            return Collections.emptyList();
        }
        Map<String,Object>parameters = new HashMap<>();
        parameters.put("TITLE", title);
        return filterBooks(parameters);
//        return StringUtils.isBlank(title) ?
//                Collections.emptyList() :
//                booksRepository.findAll()
//                        .stream()
//                        .filter(e -> StringUtils.containsIgnoreCase(e.getTitle(), title))
//                        .collect(Collectors.toList());
    }

    public List<Book> findByAuthor(String author) {
        if (StringUtils.isBlank(author)) {
            return Collections.emptyList();
        }
        Map<String,Object>parameters = new HashMap<>();
        parameters.put("AUTHOR", author);
        return filterBooks(parameters);
//        return StringUtils.isBlank(author) ?
//                Collections.emptyList() :
//                booksRepository.findAll()
//                        .stream()
//                        .filter(e -> StringUtils.containsIgnoreCase(e.getAuthor(), author))
//                        .collect(Collectors.toList());
    }

    private List<Book> filterBooks(Map<String, Object> filterParameters) {
        return chain.filter(booksRepository.findAll(), filterParameters).collect(Collectors.toList());
    }

    public List<Book> findByYear(Integer year) {
        if (year == null) {
            return Collections.emptyList();
        }
        Map<String,Object>parameters = new HashMap<>();
        parameters.put("YEAR", year);
        return filterBooks(parameters);
    }
}
