package pl.sda.library.domain.filtering;

import pl.sda.library.domain.model.Book;

import java.util.Map;
import java.util.stream.Stream;

public interface FilterAction {
    boolean isMyResponsibility(Map<String, String> parameters);

    Stream<Book> action(Stream<Book> stream, Map<String, String> parameters);
}
