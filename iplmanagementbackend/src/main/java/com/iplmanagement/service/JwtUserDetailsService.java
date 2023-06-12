package com.iplmanagement.service;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iplmanagement.model.User;
import com.iplmanagement.repo.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    private final UserRepository userRepository;
    
	@Autowired
	private PasswordEncoder passwordEncoder;
    

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.getAuthorities()
        );
    }
    
    public void registerUser(User user) {
        // Encode the password before saving the user
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
        // Save the user in the database
        userRepository.save(user);
    }
    
    public UserDetails authenticate(String username, String password) throws AuthenticationException {
        User user = userRepository.findByUsername(username);
        
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }
        
        // Create and return a UserDetails object
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            user.getAuthorities()
        );
    }
}
