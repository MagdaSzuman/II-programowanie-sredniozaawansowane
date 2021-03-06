package pl.sda.library.infrastructure.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.port.BooksRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonBooksRepository implements BooksRepository {

    private File fileWithJson;
    private List<BookDto> books;

    public JsonBooksRepository(File fileWithJson) {
        this.fileWithJson = fileWithJson;
    }

    @Override
    public List<Book> findAll() {
        if (books == null) {
            instantiateBooks();
        }
        List<Book>booksToReturn = new ArrayList<>();
        for (int i=0; i<books.size(); i++){
            BookDto bookDto = books.get(i);
            booksToReturn.add(bookDto.mapToDomain(i));
        }
        return booksToReturn;
//        return books.stream()
//                .map(book -> book.mapToDomain())
//                .collect(Collectors.toList());
    }

    private void instantiateBooks() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.books = objectMapper.readValue(fileWithJson, new TypeReference<List<BookDto>>() {
            });
        } catch (Exception e) {
            System.out.println("ERROR: BŁĄD PRZY CZYTANIU JSONA");
            this.books = Collections.emptyList();
        }

    }
}
