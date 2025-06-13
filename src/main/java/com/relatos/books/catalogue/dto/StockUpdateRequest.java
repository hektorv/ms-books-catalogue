package com.relatos.books.catalogue.dto;

import lombok.Data;

@Data
public class StockUpdateRequest {
    private Long bookId;
    private Integer variation; 
}    


 