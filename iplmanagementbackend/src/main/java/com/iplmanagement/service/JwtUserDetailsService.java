package com.iplmanagement.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    // Implement the loadUserByUsername method to load user details based on the provided username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load user details from your data source (e.g., database) and return an implementation of UserDetails
        // You can use your User class here and map it to UserDetails
        // Throw UsernameNotFoundException if the user is not found
        throw new UsernameNotFoundException("User not found");
    }
}
