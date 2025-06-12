package com.relatos.books;

import com.relatos.books.catalogue.model.Book;
import com.relatos.books.catalogue.persistence.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookEntity toEntity(Book book);
    Book toModel(BookEntity entity);
}