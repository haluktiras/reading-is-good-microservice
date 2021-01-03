package com.api.readingisgood.service;

import com.api.readingisgood.models.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerDetailsService implements UserDetailsService {

    private CompositeService compositeService;

    @Autowired
    public CustomerDetailsService(CompositeService compositeService) {
        this.compositeService = compositeService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        CustomerResponse customerResponse = compositeService.findCustomer(s);
        return new User(customerResponse.getCustomerId(), customerResponse.getPassword(), new ArrayList<>());
    }
}