package com.api.readingisgood.rest;

import com.api.readingisgood.error.exceptions.BussinesException;
import com.api.readingisgood.models.AuthenticationRequest;
import com.api.readingisgood.models.AuthenticationResponse;
import com.api.readingisgood.models.CustomerRequest;
import com.api.readingisgood.service.CompositeService;
import com.api.readingisgood.service.CustomerDetailsService;
import com.api.readingisgood.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtTokenUtil;
    private CustomerDetailsService userDetailsService;
    private CompositeService compositeService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtService jwtTokenUtil,
                                    CustomerDetailsService userDetailsService,
                                    CompositeService compositeService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.compositeService = compositeService;
    }

    @GetMapping("/auth/signin")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getCustomerId(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getCustomerId());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody CustomerRequest customerRequest) {

        try {
            compositeService.register(customerRequest);
        } catch (BussinesException e) {
            throw new BussinesException("customer already exist", new Exception());
        }
        return ResponseEntity.ok("Register Succesfully..");

    }
}
