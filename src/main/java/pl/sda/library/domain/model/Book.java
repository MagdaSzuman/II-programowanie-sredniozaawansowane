package pl.sda.library.domain.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Book {
    private String id;
    private String author;
    private String country;
    private String language;
    private String link;
    private Integer pages;
    private String title;
    private Integer year;
}
