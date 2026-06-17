package com.usuarios.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.mockito.Mockito.*;

public class JwtAuthFilterTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtAuthFilter jwtAuthFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();
    }

    @Test
    void debeContinuarCuandoNoExisteHeaderAuthorization()
            throws Exception {

        when(request.getHeader("Authorization"))
                .thenReturn(null);

        jwtAuthFilter.doFilterInternal(
                request,
                response,
                filterChain
        );

        verify(filterChain)
                .doFilter(request, response);
    }

    @Test
    void debeAutenticarUsuarioConTokenValido()
            throws Exception {
    
        when(request.getHeader("Authorization"))
                .thenReturn("Bearer token123");
    
        when(jwtUtil.extractUsername("token123"))
                .thenReturn("jonathan");
    
        when(jwtUtil.validateToken(
                "token123",
                "jonathan"))
                .thenReturn(true);
    
        jwtAuthFilter.doFilterInternal(
                request,
                response,
                filterChain
        );
    
        assertNotNull(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        );
    
        verify(filterChain)
                .doFilter(request, response);
    }

    @Test
    void noDebeAutenticarConTokenInvalido()
            throws Exception {
    
        when(request.getHeader("Authorization"))
                .thenReturn("Bearer token123");
    
        when(jwtUtil.extractUsername("token123"))
                .thenReturn("jonathan");
    
        when(jwtUtil.validateToken(
                "token123",
                "jonathan"))
                .thenReturn(false);
    
        jwtAuthFilter.doFilterInternal(
                request,
                response,
                filterChain
        );
    
        assertNull(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        );
    
        verify(filterChain)
                .doFilter(request, response);
    }

    @Test
    void debeContinuarCuandoHeaderNoComienzaConBearer()
            throws Exception {
    
        when(request.getHeader("Authorization"))
                .thenReturn("Basic abc123");
    
        jwtAuthFilter.doFilterInternal(
                request,
                response,
                filterChain
        );
    
        assertNull(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        );
    
        verify(filterChain)
                .doFilter(request, response);
    }

    @Test
    void noDebeAutenticarCuandoUsernameEsNull()
            throws Exception {

        when(request.getHeader("Authorization"))
                .thenReturn("Bearer token123");

        when(jwtUtil.extractUsername("token123"))
                .thenReturn(null);

        jwtAuthFilter.doFilterInternal(
                request,
                response,
                filterChain
        );

        assertNull(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        );

        verify(filterChain)
                .doFilter(request, response);
    }

    @Test
    void noDebeReautenticarSiYaExisteAutenticacion()
            throws Exception {

        SecurityContextHolder.getContext()
                .setAuthentication(
                        mock(org.springframework.security.core.Authentication.class)
                );

        when(request.getHeader("Authorization"))
                .thenReturn("Bearer token123");

        when(jwtUtil.extractUsername("token123"))
                .thenReturn("jonathan");

        jwtAuthFilter.doFilterInternal(
                request,
                response,
                filterChain
        );

        verify(filterChain)
                .doFilter(request, response);
    }
}