package com.ht.readingisgood.customer.data.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
@Data
@Getter
@Setter
public class CustomerEntity {

    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
}