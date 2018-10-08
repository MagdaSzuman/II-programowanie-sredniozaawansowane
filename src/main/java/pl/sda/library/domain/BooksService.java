package pl.sda.library.domain;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.IntegerAssert;
import pl.sda.library.domain.exceptions.InvalidPagesValueException;
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
        return findBy(title, "TITLE");

//        if (StringUtils.isBlank(title)) {
//            return Collections.emptyList();
//        }
//        Map<String,Object>parameters = new HashMap<>();
//        parameters.put("TITLE", title);
//        return filterBooks(parameters);

//        return StringUtils.isBlank(title) ?
//                Collections.emptyList() :
//                booksRepository.findAll()
//                        .stream()
//                        .filter(e -> StringUtils.containsIgnoreCase(e.getTitle(), title))
//                        .collect(Collectors.toList());
    }

    public List<Book> findByAuthor(String author) {
        return findBy(author, "AUTHOR");

//        if (StringUtils.isBlank(author)) {
//            return Collections.emptyList();
//        }
//        Map<String,Object>parameters = new HashMap<>();
//        parameters.put("AUTHOR", author);
//        return filterBooks(parameters);

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
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("YEAR", year);
        return filterBooks(parameters);
    }

    public List<Book> findByLanguage(String language) {

        return findBy(language, "LANGUAGE");

//        if (StringUtils.isBlank(language)) {
//            return Collections.emptyList();
//        }
//        Map<String,Object>parameters = new HashMap<>();
//        parameters.put("LANGUAGE", language);
//        return filterBooks(parameters);
    }


    private List<Book> findBy(String value, String key) {
        if (StringUtils.isBlank(value)) {
            return Collections.emptyList();
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(key, value);
        return filterBooks(parameters);
    }

    public List<Book> findByPagesRange(Integer from, Integer to) throws InvalidPagesValueException {
        validatePagesRangeArgument(from, to);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("PAGES_FROM", from);
        parameters.put("PAGES_TO", to);
        return filterBooks(parameters);
    }

    private void validatePagesRangeArgument(Integer from, Integer to) throws InvalidPagesValueException {
        if (from < 0 || to < 0) {
            throw new InvalidPagesValueException("Arguments can't be negative");
        }
        if (from>to){
            throw new InvalidPagesValueException("From can't bo greater than to");
        }
    }
}
