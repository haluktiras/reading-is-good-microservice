package com.ht.readingisgood.securitylibrary.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.ArgumentMatchers.anyString;

public class BookStoreCustomerDetailServiceTest {

    @Mock
    private BookStoreCustomerService customerService;

    @InjectMocks
    private BookStoreCustomerDetailService detailService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoadUserByUsername() {
        Mockito.when(customerService.getUserByUsername(anyString()))
                .thenReturn(Mockito.mock(User.class));
        UserDetails userDetails = detailService.loadUserByUsername("username");
        Assert.assertNotNull(userDetails);
    }
}