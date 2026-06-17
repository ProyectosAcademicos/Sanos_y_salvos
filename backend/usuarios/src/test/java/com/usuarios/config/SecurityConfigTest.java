package com.usuarios.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class SecurityConfigTest {

    private final SecurityConfig securityConfig = new SecurityConfig();

    @Test
    void debeCrearPasswordEncoder() {

        PasswordEncoder encoder =
                securityConfig.passwordEncoder();

        assertNotNull(encoder);

        String hash = encoder.encode("1234");

        assertNotEquals("1234", hash);
        assertTrue(encoder.matches("1234", hash));
    }
}