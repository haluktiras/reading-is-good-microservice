package com.ht.readingisgood.securitylibrary.data.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "credentials")
@Data
@Getter
@Setter
@AllArgsConstructor
public class CredentialEntity {

    @Id
    private String username;
    private String password;
}