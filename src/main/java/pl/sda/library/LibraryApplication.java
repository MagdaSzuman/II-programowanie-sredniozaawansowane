package pl.sda.library;

import pl.sda.library.application.ConsoleApplication;

import java.io.IOException;

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
