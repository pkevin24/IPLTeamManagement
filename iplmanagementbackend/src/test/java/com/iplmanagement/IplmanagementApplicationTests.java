package com.iplmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.naming.AuthenticationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.iplmanagement.model.User;
import com.iplmanagement.repo.UserRepository;
import com.iplmanagement.service.JwtUserDetailsService;

@ExtendWith(MockitoExtension.class)
public class IplmanagementApplicationTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private JwtUserDetailsService userDetailsService;

    @BeforeEach
    public void setup() {
        // Mock the UserRepository and PasswordEncoder
        when(userRepository.findByUsername("existingUser")).thenReturn(createMockUser());
        when(userRepository.findByUsername("nonExistingUser")).thenReturn(null);
        when(passwordEncoder.matches("validPassword", "encodedPassword")).thenReturn(true);
        when(passwordEncoder.matches("invalidPassword", "encodedPassword")).thenReturn(false);
    }

    @Test
    public void testLoadUserByUsernameWithExistingUser() {
        // Arrange
        String username = "existingUser";

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
    }

    @Test
    public void testLoadUserByUsernameWithNonExistingUser() {
        // Arrange
        String username = "nonExistingUser";

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername(username);
        });
    }

    @Test
    public void testRegisterUser() {
        // Arrange
        User user = createMockUser();

        // Act
        userDetailsService.registerUser(user);

        // Assert
        verify(passwordEncoder).encode(user.getPassword());
        verify(userRepository).save(user);
    }

    @Test
    public void testAuthenticateWithValidCredentials() throws AuthenticationException {
        // Arrange
        String username = "existingUser";
        String password = "validPassword";

        // Act
        UserDetails userDetails = userDetailsService.authenticate(username, password);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
    }

    @Test
    public void testAuthenticateWithInvalidCredentials() {
        // Arrange
        String username = "existingUser";
        String password = "invalidPassword";

        // Act & Assert
        assertThrows(BadCredentialsException.class, () -> {
            userDetailsService.authenticate(username, password);
        });
    }

    private User createMockUser() {
        User user = new User();
        user.setUsername("existingUser");
        user.setPassword("encodedPassword");
        // Set other properties as needed
        return user;
    }
}

