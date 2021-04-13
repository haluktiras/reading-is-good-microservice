package com.ht.readingisgood.securitylibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class BookStoreCustomerDetailService implements UserDetailsService {

    private BookStoreCustomerService bookStoreCustomerService;

    @Autowired
    public BookStoreCustomerDetailService(
            BookStoreCustomerService bookStoreCustomerService) {
        this.bookStoreCustomerService = bookStoreCustomerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return bookStoreCustomerService.getUserByUsername(username);
    }
}