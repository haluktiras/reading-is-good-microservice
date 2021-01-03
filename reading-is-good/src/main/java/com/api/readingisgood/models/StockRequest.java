package com.api.readingisgood.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
public class StockRequest implements Serializable {
    String stockId;
    Double quantity;
    String name;
    String author;
    Date createdAt;
}
