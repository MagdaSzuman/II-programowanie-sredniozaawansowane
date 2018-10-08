package pl.sda.library.domain.filtering;

import org.apache.commons.lang3.StringUtils;
import pl.sda.library.domain.model.Book;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BookYearFilterAction extends SimpleAbstractFilterAction{
    @Override
    protected String getKey() {
        return "YEAR";
    }

    @Override
    protected Predicate<Book> predicate(Map<String, Object> parameters) {
        return e-> e.getYear().equals((Integer) parameters.get("YEAR"));
    }

//    @Override
//    public boolean isMyResponsibility(Map<String, Object> parameters) {
//        return parameters.containsKey("YEAR");
//    }
//
//    @Override
//    public Stream<Book> action(Stream<Book> stream, Map<String, Object> parameters) {
//        return stream.filter(e-> e.getYear().equals((Integer) parameters.get("YEAR")));
//    }
}
