package com.ht.readingisgood.securitylibrary.controller;

import com.ht.readingisgood.securitylibrary.controller.model.AuthDto;
import com.ht.readingisgood.securitylibrary.service.BookStoreCustomerDetailService;
import com.ht.readingisgood.securitylibrary.service.BookStoreCustomerService;
import com.ht.readingisgood.securitylibrary.service.JwtUtil;
import com.ht.readingisgood.securitylibrary.controller.exception.SecurityServiceException;
import com.ht.readingisgood.securitylibrary.model.AuthRequest;
import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class AuthRestControllerTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private BookStoreCustomerDetailService detailService;

    @Mock
    private BookStoreCustomerService customerService;

    @InjectMocks
    private AuthRestController authRestController;

    private EasyRandom generator = new EasyRandom();
    private AuthDto authDto;
    private AuthRequest authRequest;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        authDto = generator.nextObject(AuthDto.class);
        authRequest = new AuthRequest();
        authRequest.setUsername(authDto.getUsername());
        authRequest.setPassword(authDto.getPassword());
    }

    @Test
    public void testSignUp() {
        Mockito.when(customerService.register(any()))
                .thenReturn(authDto);
        AuthDto response = authRestController.signUp(authRequest).getBody();
        Assert.assertEquals(authDto.getUsername(), response.getUsername());
        Assert.assertEquals(authDto.getPassword(), response.getPassword());
    }

    @Test
    public void testCreateToken() {
        UserDetails userDetails = Mockito.mock(UserDetails.class);
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authenticationManager.authenticate(authentication))
                .thenReturn(authentication);
        Mockito.when(detailService.loadUserByUsername(anyString()))
                .thenReturn(userDetails);
        Mockito.when(jwtUtil.generateToken(userDetails))
                .thenReturn("token");
        String response = authRestController.creteToken(authRequest).getBody();
        Assert.assertEquals("token", response);
    }

    @Test(expected = SecurityServiceException.class)
    public void testCreateTokenException() {
        authRestController.creteToken(authRequest).getBody();
    }
}
