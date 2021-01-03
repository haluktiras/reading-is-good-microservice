package com.api.stock.models;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class StockRequest implements Serializable {
    String stockId;
    Double quantity;
    String name;
    String author;
    Date createdAt;
}
