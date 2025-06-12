package com.relatos.books.catalogue.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.relatos.books.catalogue.dto.CreateBookRequest;
import com.relatos.books.catalogue.model.Book;
import com.relatos.books.catalogue.model.BookSearchCriteria;
import com.relatos.books.catalogue.persistence.BookEntity;
import com.relatos.books.catalogue.persistence.BookRepository;
import com.relatos.books.catalogue.persistence.BookSpecification;
import com.relatos.books.catalogue.service.BookService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final ObjectMapper  objectMapper;
    private final BookRepository bookRepository;
    
    public Book createBook(CreateBookRequest book) {
        BookEntity entity = BookEntity.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .description(book.getDescription())
                .coverImage(book.getCoverImage())
                .category(book.getCategory())
                .publishYear(book.getPublishYear())
                .genre(book.getGenre())
                .pages(book.getPages())
                .isbn(book.getIsbn())
                .rating(book.getRating())
                .visible(book.getVisible())
                .build();;
        BookEntity saved = bookRepository.save(entity);
        return toModel(saved);
    }

    @Override
    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(parseId(id))
                .map(this::toModel);
    }

    @Override
    public Optional<Book> updateBook(String id, Book book) {
        Long bookId = parseId(id);
        Optional<BookEntity> found = bookRepository.findById(bookId);
        if(!found.isPresent()){
            return Optional.empty();
        }
        BookEntity updated = this.toEntity(book);
        updated.setId(bookId);
        return Optional.of(this.toModel(bookRepository.save(updated)));
    }

    @Override
    public Optional<Book> patchBook(String id, String partialUpdate) {
        Long bookId = parseId(id);
        Optional<BookEntity> found = bookRepository.findById(bookId);
        if(!found.isPresent()){
            return Optional.empty();
        }        
        Book book = toModel(found.get());
        try {
            JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(partialUpdate));
            JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(book)));
            Book patched = objectMapper.treeToValue(target, Book.class);
            bookRepository.save(toEntity(patched));
            return Optional.of(patched);
        } catch (JsonProcessingException | JsonPatchException e) {
            log.error("Error updating book {}", id, e);
            return Optional.empty();
        }
    }

    @Override
    public void deleteBook(String id) {
        bookRepository.deleteById(parseId(id));
    }

    @Override
    public List<Book> searchBooks(BookSearchCriteria params, Pageable pageable) {
        Specification<BookEntity> spec = BookSpecification.fromCriteria(params);
        return bookRepository.findAll(spec, pageable)
                .map(this::toModel)
                .toList();
    }

    private Long parseId(String id) {
        try {
            return Long.valueOf(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid book ID: " + id);
        }
    }
    
    private BookEntity toNewEntity(Book book) {
        return BookEntity.builder()
                .id(book.getId()) // solo si no es null o si permites actualización
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .description(book.getDescription())
                .coverImage(book.getCoverImage())
                .category(book.getCategory())
                .publishYear(book.getPublishYear())
                .genre(book.getGenre())
                .pages(book.getPages())
                .isbn(book.getIsbn())
                .rating(book.getRating())
                .visible(book.getVisible())
                .build();
    }
    private BookEntity toEntity(Book book) {
        return BookEntity.builder()
                .id(book.getId()) // solo si no es null o si permites actualización
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .description(book.getDescription())
                .coverImage(book.getCoverImage())
                .category(book.getCategory())
                .publishYear(book.getPublishYear())
                .genre(book.getGenre())
                .pages(book.getPages())
                .isbn(book.getIsbn())
                .rating(book.getRating())
                .visible(book.getVisible())
                .build();
    }
    private Book toModel(BookEntity entity) {
        return Book.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .coverImage(entity.getCoverImage())
                .category(entity.getCategory())
                .publishYear(entity.getPublishYear())
                .genre(entity.getGenre())
                .pages(entity.getPages())
                .isbn(entity.getIsbn())
                .rating(entity.getRating())
                .visible(entity.getVisible())
                .build();
    }


}