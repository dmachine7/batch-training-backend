package com.bankapp.app.controller;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.app.entity.AuthRequest;
import com.bankapp.app.entity.AuthResponse;
import com.bankapp.app.model.Account;
import com.bankapp.app.repository.AccountRepository;
import com.bankapp.app.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JwtUtil helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        this.doAuthenticate(request.getUsername(), request.getPassword());
        //System.out.println("Load user" + request.getUsername());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);
        
        Optional<Account> account = accountRepository.findByEmail(request.getUsername());
        
        if (account.isPresent()) {
            Account accountDetails = account.get();
            AuthResponse response = AuthResponse.builder()
                    .jwtToken(token)
                    .username(accountDetails.getEmail())
                    .accNo(accountDetails.getAcc_no())
                    .balance(accountDetails.getBalance())
                    .accStatus(accountDetails.getAccount_status())
                    .isAdmin(accountDetails.getIsAdmin()).build();
                   
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return null;
        
    }

    private void doAuthenticate(String username, String password) {
      
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password ");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> exceptionHandler() {
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
    }
    
}
