package pl.sda.library.infrastructure.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.sda.library.domain.model.Book;

public class BookDto {

    private String author;
    private String country;
    private String language;
    private String link;
    private Integer pages;
    private String title;
    private Integer year;

    @JsonCreator
    public BookDto(@JsonProperty("author") String author,
                   @JsonProperty("country") String country,
                   @JsonProperty("language") String language,
                   @JsonProperty("link") String link,
                   @JsonProperty("pages") Integer pages,
                   @JsonProperty("title") String title,
                   @JsonProperty("year") Integer year) {
        this.author = author;
        this.country = country;
        this.language = language;
        this.link = link;
        this.pages = pages;
        this.title = title;
        this.year = year;
    }

    public Book mapToDomain(Integer id) {
        return Book.builder()
                .id(id.toString())
                .author(author)
                .country(country)
                .language(language)
                .link(link)
                .pages(pages)
                .title(title)
                .year(year)
                .build();
    }
}

