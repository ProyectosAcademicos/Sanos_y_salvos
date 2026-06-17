package com.matching.config;

import org.junit.jupiter.api.Test;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class SecurityConfigTest {

    @Autowired
        private SecurityConfig config;
    
        @Test
        void debeCrearSecurityFilterChain() throws Exception {
            HttpSecurity http = org.mockito.Mockito.mock(HttpSecurity.class, org.mockito.Answers.RETURNS_SELF);
    
            SecurityFilterChain chain = config.securityFilterChain(http);
    
            assertNotNull(chain);
        }
}