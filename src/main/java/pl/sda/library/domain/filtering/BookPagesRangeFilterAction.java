package pl.sda.library.domain.filtering;

import pl.sda.library.domain.model.Book;

import java.util.Map;
import java.util.stream.Stream;

public class BookPagesRangeFilterAction implements FilterAction {
    @Override
    public boolean isMyResponsibility(Map<String, Object> parameters) {
        return parameters.containsKey("PAGES_FROM") && parameters.containsKey("PAGES_TO");
    }

    @Override
    public Stream<Book> action(Stream<Book> stream, Map<String, Object> parameters) {
        Integer from = (Integer) parameters.get("PAGES_FROM");
        Integer to = (Integer) parameters.get("PAGES_TO");
        return stream.filter(e -> from <= e.getPages() && e.getPages() <= to);
    }
}
