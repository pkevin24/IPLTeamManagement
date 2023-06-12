package com.iplmanagement.controller;
import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iplmanagement.model.AuthenticationRequest;
import com.iplmanagement.model.AuthenticationResponse;
import com.iplmanagement.model.User;
import com.iplmanagement.service.JwtUserDetailsService;
import com.iplmanagement.util.JwtTokenUtil;

@RestController
public class JwtAuthenticationController {
	
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    public JwtAuthenticationController(AuthenticationManager authenticationManager,
                                       JwtTokenUtil jwtTokenUtil,
                                       JwtUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }
    @PostMapping("/jwt/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            UserDetails userDetails = userDetailsService.authenticate(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()
            );
            
            // Generate the JWT token
            String token = jwtTokenUtil.generateToken(userDetails);
            
            // Return the token in the response
            return ResponseEntity.ok(new AuthenticationResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
    
    @PostMapping("/jwt/register")
    public ResponseEntity<String> register(@RequestBody User user) {
    	userDetailsService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
