package pl.sda.library.domain.filtering;

import pl.sda.library.domain.model.Book;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class SimpleAbstractFilterAction implements FilterAction{

    @Override
    public boolean isMyResponsibility(Map<String, Object> parameters) {
        return parameters.containsKey(getKey());
    }

    @Override
    public Stream<Book> action(Stream<Book> stream, Map<String, Object> parameters) {
        return stream.filter(predicate(parameters));
    }

    protected abstract String getKey();

    protected abstract Predicate<Book> predicate(Map<String, Object> parameters);
}
