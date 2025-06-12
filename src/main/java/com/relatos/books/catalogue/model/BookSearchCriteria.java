package com.relatos.books.catalogue.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookSearchCriteria {
    private String title;
    private String author;
    private Integer year;
    private String genre;
    private String isbn;
    private Integer rating;
    private Boolean visible;
}
