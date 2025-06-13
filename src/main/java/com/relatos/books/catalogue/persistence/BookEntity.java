package com.relatos.books.catalogue.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String title;
    private String author;
    private Double price;
    private String description;
    @Column(name = "cover_image")
    private String coverImage;
    private String category;
    @Column(name = "publish_year")
    private Integer publishYear;
    private String genre;
    private Integer pages;
    private String isbn;
    private Integer rating;
    private Boolean visible;
    private Integer stock;
}