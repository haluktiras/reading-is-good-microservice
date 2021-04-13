package com.ht.readingisgood.securitylibrary.service;

import com.ht.readingisgood.securitylibrary.controller.model.AuthDto;
import com.ht.readingisgood.securitylibrary.data.model.CredentialEntity;
import com.ht.readingisgood.securitylibrary.data.repository.CredentialRepository;
import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class BookStoreCustomerServiceTest {

    @Mock
    private CredentialRepository credentialRepository;

    @InjectMocks
    private BookStoreCustomerService customerService;

    private EasyRandom generator = new EasyRandom();
    private CredentialEntity credentialEntity;
    private AuthDto authDto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        credentialEntity = generator.nextObject(CredentialEntity.class);
        authDto = generator.nextObject(AuthDto.class);
    }

    @Test
    public void testGetByUsername() {
        Mockito.when(credentialRepository.findById(anyString()))
                .thenReturn(Optional.of(credentialEntity));
        User user = customerService.getUserByUsername("username");
        Assert.assertEquals(credentialEntity.getUsername(), user.getUsername());
        Assert.assertEquals(credentialEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testRegister() {
        Mockito.when(credentialRepository.save(any()))
                .thenReturn(credentialEntity);
        AuthDto response = customerService.register(authDto);
        Assert.assertEquals(authDto.getUsername(), response.getUsername());
        Assert.assertEquals(authDto.getPassword(), response.getPassword());
    }
}