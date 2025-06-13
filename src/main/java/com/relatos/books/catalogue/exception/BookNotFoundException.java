package com.relatos.books.catalogue.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long bookId) {
        super("Book not found: " + bookId);
    }
}