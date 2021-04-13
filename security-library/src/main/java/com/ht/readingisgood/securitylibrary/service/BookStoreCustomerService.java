package com.ht.readingisgood.securitylibrary.service;

import com.ht.readingisgood.securitylibrary.controller.exception.SecurityServiceException;
import com.ht.readingisgood.securitylibrary.controller.model.AuthDto;
import com.ht.readingisgood.securitylibrary.data.model.CredentialEntity;
import com.ht.readingisgood.securitylibrary.data.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookStoreCustomerService {

    private CredentialRepository credentialRepository;

    @Autowired
    public BookStoreCustomerService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public User getUserByUsername(String username) {
        CredentialEntity credentialEntity =
                credentialRepository.findById(username)
                        .orElseThrow(() -> new SecurityServiceException("Could not find any user."));
        return new User(credentialEntity.getUsername(),
                credentialEntity.getPassword(),
                new ArrayList<>());
    }

    public AuthDto register(AuthDto authDto) {

        checkExistence(authDto.getUsername());
        credentialRepository.save(new CredentialEntity(
                authDto.getUsername(), authDto.getPassword()));
        return authDto;
    }

    void checkExistence(String username) {
        if (credentialRepository.findById(username).isPresent()) {
            throw new SecurityServiceException("Username already exists.");
        }
    }
}