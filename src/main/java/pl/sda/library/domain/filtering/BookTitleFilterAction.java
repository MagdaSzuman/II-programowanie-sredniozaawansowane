package pl.sda.library.domain.filtering;

import org.apache.commons.lang3.StringUtils;
import pl.sda.library.domain.model.Book;

import java.util.Map;
import java.util.stream.Stream;

public class BookTitleFilterAction implements FilterAction{

    @Override
    public boolean isMyResponsibility(Map<String, String> parameters) {
        return parameters.containsKey("TITLE");
    }

    @Override
    public Stream<Book> action(Stream<Book> stream, Map<String, String> parameters) {
        return stream.filter(e-> StringUtils.containsIgnoreCase(e.getTitle(), parameters.get("TITLE")));
    }
}
