package com.relatos.books.catalogue.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import com.relatos.books.catalogue.dto.CreateBookRequest;
import com.relatos.books.catalogue.dto.StockUpdateRequest;
import com.relatos.books.catalogue.model.Book;
import com.relatos.books.catalogue.model.BookSearchCriteria;

public interface BookService {

    Book createBook(CreateBookRequest book);

    Optional<Book> getBookById(String id);

    Optional<Book> updateBook(String id, Book book);

    Optional<Book> patchBook(String id, String partialUpdate);

    void deleteBook(String id);

    List<Book> searchBooks(BookSearchCriteria params, Pageable pageable);

    void updateStocks(List<StockUpdateRequest> updates);



}
