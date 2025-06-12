package com.relatos.books.catalogue.dto;

import lombok.Data;


@Data
public class BookSearchParameters {
    private String title;
    private String author;
    private Integer year;
    private String genre;
    private String isbn;
    private Integer rating;
    private Boolean visible;
}
