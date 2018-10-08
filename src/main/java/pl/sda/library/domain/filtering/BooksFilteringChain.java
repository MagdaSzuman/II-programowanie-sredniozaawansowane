package pl.sda.library.domain.filtering;

import pl.sda.library.domain.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class BooksFilteringChain {

    private List<FilterAction> chain;

    public BooksFilteringChain() {
        this.chain = new ArrayList<>();
        this.chain.add(new BookAuthorFilterAction());
        this.chain.add(new BookTitleFilterAction());
        this.chain.add(new BookYearFilterAction());
        this.chain.add(new BookLanguageFilterAction());
        this.chain.add(new BookPagesRangeFilterAction());
    }

    public Stream<Book> filter(List<Book> books, Map<String, Object> parameters) {
        Stream<Book> stream = books.stream();
        for (FilterAction action : chain) {
            if (action.isMyResponsibility(parameters)) {
                stream = action.action(stream, parameters);
            }
        }
        return stream;
    }
}
