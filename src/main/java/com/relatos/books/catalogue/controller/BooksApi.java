package com.relatos.books.catalogue.controller;

import com.relatos.books.catalogue.dto.BookSearchParameters;
import com.relatos.books.catalogue.dto.CreateBookRequest;
import com.relatos.books.catalogue.dto.StockUpdateRequest;
import com.relatos.books.catalogue.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.domain.Pageable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(name = "Books", description = "API para operaciones con libros")
@RequestMapping("/api/books")
public interface BooksApi {

    @Operation(summary = "Buscar libros con filtros opcionales")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado de libros encontrados"),
        @ApiResponse(responseCode = "400", description = "Parámetros de búsqueda inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    ResponseEntity<List<Book>> getBooks(
        @Parameter(description = "Parámetros de búsqueda") @ModelAttribute BookSearchParameters params,
        @Parameter(description = "Parámetros de paginación") Pageable pageable
    );

    @Operation(summary = "Crear un nuevo libro")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Libro creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos para la creación"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    ResponseEntity<Book> createBook(
        @Parameter(description = "Libro a crear") @RequestBody CreateBookRequest book
    );

    @Operation(summary = "Obtener un libro por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Libro encontrado"),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    ResponseEntity<Book> getBookById(
        @Parameter(description = "ID del libro") @PathVariable String id
    );

    @Operation(summary = "Actualizar un libro por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Libro actualizado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    ResponseEntity<Book> updateBook(
        @Parameter(description = "ID del libro") @PathVariable String id,
        @Parameter(description = "Libro actualizado") @RequestBody Book book
    );

    @Operation(summary = "Actualizar parcialmente un libro por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Libro parcialmente actualizado"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PatchMapping("/{id}")
    ResponseEntity<Book> patchBook(
        @Parameter(description = "ID del libro") @PathVariable String id,
        @Parameter(description = "Datos a actualizar parcialmente") @RequestBody String partialUpdate
    );

    @Operation(summary = "Eliminar un libro por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Libro eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteBook(
        @Parameter(description = "ID del libro") @PathVariable String id
    );

    @PostMapping("/stock")
    @ApiResponses({
        @ApiResponse(responseCode = "404", description = "Libro no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "200", description = "Libro parcialmente actualizado")
    })
    public ResponseEntity<Void> updateStockBulk(
        @Parameter(description = "Lista de variaciones en el stock")     
        
        @RequestBody List<StockUpdateRequest> updates
    );

}
