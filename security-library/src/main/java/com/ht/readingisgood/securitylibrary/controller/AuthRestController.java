package com.ht.readingisgood.securitylibrary.controller;

import com.ht.readingisgood.securitylibrary.service.BookStoreCustomerDetailService;
import com.ht.readingisgood.securitylibrary.controller.exception.SecurityServiceException;
import com.ht.readingisgood.securitylibrary.controller.model.AuthDto;
import com.ht.readingisgood.securitylibrary.model.AuthRequest;
import com.ht.readingisgood.securitylibrary.service.BookStoreCustomerService;
import com.ht.readingisgood.securitylibrary.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.ht.readingisgood.securitylibrary.controller.constant.SecurityControllerConstants.*;

@RestController
@RequestMapping(value = SECURITY_CONTROLLER + V1)
public class AuthRestController {

    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private BookStoreCustomerDetailService detailService;
    private BookStoreCustomerService customerService;

    @Autowired
    public AuthRestController(
            JwtUtil jwtUtil, AuthenticationManager authenticationManager,
            BookStoreCustomerDetailService detailService,
            BookStoreCustomerService customerService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.detailService = detailService;
        this.customerService = customerService;
    }

    @PostMapping(value = SIGNUP)
    public ResponseEntity<AuthDto> signUp(@RequestBody AuthRequest authRequest) {
        AuthDto authDto = new AuthDto(
                authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok(customerService.register(authDto));
    }

    @PostMapping(value = LOGIN)
    public ResponseEntity<String> creteToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new SecurityServiceException("Incorrect username or password", ex);
        }
        UserDetails userDetails = Optional.ofNullable(detailService.loadUserByUsername(authRequest.getUsername()))
                .orElseThrow(() -> new SecurityServiceException("Incorrect username or password."));

        return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
    }
}