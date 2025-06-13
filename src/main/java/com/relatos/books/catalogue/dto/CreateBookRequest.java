package com.relatos.books.catalogue.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {
 
    @NotBlank
    @Size(max = 150)
    @Schema(description = "Título del libro", example = "Cien años de soledad")
    private String title;

    @NotBlank
    @Size(max = 100)
    @Schema(description = "Autor del libro", example = "Gabriel García Márquez")
    private String author;

    @NotNull
    @Positive
    @Schema(description = "Precio del libro", example = "19.99")
    private Double price;

    @Size(max = 1000)
    @Schema(description = "Descripción del libro")
    private String description;

    @Size(max = 300)
    @Schema(description = "URL de la imagen de portada")
    private String coverImage;

    @Size(max = 50)
    @Schema(description = "Categoría del libro", example = "Ficción")
    private String category;

    @Min(1500)
    @Max(2100)
    @Schema(description = "Año de publicación", example = "1967")
    private Integer publishYear;

    @Size(max = 50)
    @Schema(description = "Género del libro", example = "Realismo mágico")
    private String genre;

    @Min(1)
    @Max(5000)
    @Schema(description = "Número de páginas", example = "417")
    private Integer pages;

    @Pattern(regexp = "^[0-9\\-]{10,20}$", message = "Debe ser un ISBN válido")
    @Schema(description = "ISBN del libro", example = "9780307474728")
    private String isbn;

    @Min(1)
    @Max(5)
    @Schema(description = "Valoración del libro (1 a 5)", example = "5")
    private Integer rating;

    @NotNull
    @Schema(description = "Indica si el libro es visible", example = "true")
    private Boolean visible;
    
    @NotNull
    @Schema(description = "Indica el número de existencias de libros", example = "10")
    private Integer stock;
}
