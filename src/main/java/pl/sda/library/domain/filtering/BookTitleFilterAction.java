package pl.sda.library.domain.filtering;

import org.apache.commons.lang3.StringUtils;
import pl.sda.library.domain.model.Book;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BookTitleFilterAction extends SimpleAbstractFilterAction{

//    @Override
//    public boolean isMyResponsibility(Map<String, Object> parameters) {
//        return parameters.containsKey("TITLE");
//    }
//
//    @Override
//    public Stream<Book> action(Stream<Book> stream, Map<String, Object> parameters) {
//        return stream.filter(e-> StringUtils.containsIgnoreCase(e.getTitle(), (String)parameters.get("TITLE")));
//    }

    @Override
    protected String getKey() {
        return "TITLE";
    }

    @Override
    protected Predicate<Book> predicate(Map<String, Object> parameters) {
        return e-> StringUtils.containsIgnoreCase(e.getTitle(), (String)parameters.get("TITLE"));
    }
}
