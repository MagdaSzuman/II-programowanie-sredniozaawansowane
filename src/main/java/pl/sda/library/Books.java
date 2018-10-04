package pl.sda.library;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Books {

    private String author;
    private String country;
    private String language;
    private String link;
    private Integer pages;
    private String title;
    private Integer year;

    @JsonCreator
    public Books(@JsonProperty ("author") String author,
                 @JsonProperty ("country")String country,
                 @JsonProperty ("language") String language,
                 @JsonProperty ("link")String link,
                 @JsonProperty ("pages")Integer pages,
                 @JsonProperty ("title")String title,
                 @JsonProperty ("year")Integer year) {
        this.author = author;
        this.country = country;
        this.language = language;
        this.link = link;
        this.pages = pages;
        this.title = title;
        this.year = year;
    }
}

