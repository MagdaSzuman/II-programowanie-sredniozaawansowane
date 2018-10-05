package pl.sda.library;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sda.library.application.ConsoleApplication;
import pl.sda.library.infrastructure.json.BookDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LibraryApplication {
    public static void main(String[] args) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<BookDto> books = objectMapper.readValue(
//                new File("C:\\Users\\M\\Desktop\\IntelliJ - projekty\\programowanie-sredniozaawansowane\\src\\main\\resources\\books.json"),
//                new TypeReference<List<BookDto>>(){}); // trzeba użyć TypeReference jeśli mapujemy na listę
//        System.out.println();

        new ConsoleApplication().start();
    }
}
