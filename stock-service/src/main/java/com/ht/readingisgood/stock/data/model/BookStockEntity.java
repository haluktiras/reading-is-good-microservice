package com.ht.readingisgood.stock.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@Data
public class BookStockEntity {

    private @Id String stockId;
    private int quantity;
    private String name;
    private String author;
    private Double price;
}
