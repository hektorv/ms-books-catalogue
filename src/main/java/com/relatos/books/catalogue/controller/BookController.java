package com.relatos.books.catalogue.controller;

import com.relatos.books.catalogue.dto.BookSearchParameters;
import com.relatos.books.catalogue.dto.CreateBookRequest;
import com.relatos.books.catalogue.dto.StockUpdateRequest;
import com.relatos.books.catalogue.model.Book;
import com.relatos.books.catalogue.model.BookSearchCriteria;
import com.relatos.books.catalogue.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController implements BooksApi {

    private final BookService bookService;
    private static final BooksMapper mapper = Mappers.getMapper(BooksMapper.class);

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(
          @Valid @ModelAttribute BookSearchParameters params,
         Pageable pageable
    ){
        List<Book> books = bookService.searchBooks(
            mapper.fromParams(params),
            pageable);
        return ResponseEntity.ok(books);
    }


    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody CreateBookRequest book) {
          
        return ResponseEntity.status(201).body(bookService.createBook(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book book) {
        Optional<Book> response = bookService.updateBook(id, book);
        return response.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> patchBook(@PathVariable String id, @RequestBody String partialUpdate) {
        Optional<Book> response = bookService.patchBook(id, partialUpdate);
        return response.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    
    @Override
    @PostMapping("/stock")
    public ResponseEntity<Void> updateStockBulk(List<StockUpdateRequest> updates) {
        bookService.updateStocks(updates);
        return ResponseEntity.noContent().build();
    }

    
    @Mapper
    public interface BooksMapper{
        BookSearchCriteria fromParams(BookSearchParameters params);
       
    }




}
