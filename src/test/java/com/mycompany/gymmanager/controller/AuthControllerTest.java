package com.mycompany.gymmanager.controller;

import com.mycompany.gymmanager.entity.AuthUser;
import com.mycompany.gymmanager.repository.AuthUserRepository;
import com.mycompany.gymmanager.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthenticationManager authManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private AuthUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private AuthController controller;

    @BeforeEach
    void setUp() {
        controller = new AuthController(authManager, jwtTokenProvider, userRepository, passwordEncoder);
    }

    @Test
    void login_success_returnsToken() {
        Map<String, String> req = Map.of("username", "user", "password", "pass");

        Authentication auth = mock(Authentication.class);
        GrantedAuthority ga = (GrantedAuthority) () -> "ROLE_USER";
        Collection<? extends GrantedAuthority> authorities = Collections.singleton(ga);

        when(authManager.authenticate(any())).thenReturn(auth);
        when(auth.getAuthorities()).thenReturn((Collection) authorities);
        when(auth.getName()).thenReturn("user");
        when(jwtTokenProvider.generateToken("user", "ROLE_USER")).thenReturn("token123");

        ResponseEntity<?> resp = controller.login(req);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertNotNull(resp.getBody());
        assertInstanceOf(Map.class, resp.getBody());
        @SuppressWarnings("unchecked")
        Map<String, String> body = (Map<String, String>) resp.getBody();
        assertEquals("token123", body.get("token"));

        verify(authManager).authenticate(any());
        verify(jwtTokenProvider).generateToken("user", "ROLE_USER");
    }

    @Test
    void login_failure_returnsUnauthorized() {
        Map<String, String> req = Map.of("username", "user", "password", "wrong");

        when(authManager.authenticate(any())).thenThrow(new BadCredentialsException("bad"));

        ResponseEntity<?> resp = controller.login(req);

        assertEquals(HttpStatus.UNAUTHORIZED, resp.getStatusCode());
        assertNotNull(resp.getBody());
        @SuppressWarnings("unchecked")
        Map<String, String> body = (Map<String, String>) resp.getBody();
        assertEquals("Invalid credentials", body.get("error"));

        verify(authManager).authenticate(any());
    }

    @Test
    void register_success_savesUserAndReturnsMessage() {
        AuthUser user = new AuthUser();
        user.setUsername("newuser");
        user.setPassword("plain");

        when(userRepository.findByUsername("newuser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("plain")).thenReturn("encoded");
        when(userRepository.save(any(AuthUser.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ResponseEntity<?> resp = controller.register(user);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertNotNull(resp.getBody());
        @SuppressWarnings("unchecked")
        Map<String, String> body = (Map<String, String>) resp.getBody();
        assertEquals("User registered successfully", body.get("message"));

        // password should have been encoded on the same user instance
        assertEquals("encoded", user.getPassword());
        verify(userRepository).findByUsername("newuser");
        verify(passwordEncoder).encode("plain");
        verify(userRepository).save(user);
    }

    @Test
    void register_conflict_whenUsernameExists() {
        AuthUser existing = new AuthUser();
        existing.setUsername("exists");
        existing.setPassword("x");

        AuthUser incoming = new AuthUser();
        incoming.setUsername("exists");
        incoming.setPassword("p");

        when(userRepository.findByUsername("exists")).thenReturn(Optional.of(existing));

        ResponseEntity<?> resp = controller.register(incoming);

        assertEquals(HttpStatus.CONFLICT, resp.getStatusCode());
        assertNotNull(resp.getBody());
        @SuppressWarnings("unchecked")
        Map<String, String> body = (Map<String, String>) resp.getBody();
        assertEquals("Username already exists", body.get("error"));

        verify(userRepository).findByUsername("exists");
        verifyNoInteractions(passwordEncoder);
        verify(userRepository, never()).save(any());
    }
}
