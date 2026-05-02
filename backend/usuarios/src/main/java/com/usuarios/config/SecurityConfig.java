package com.usuarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpMethod;

import com.usuarios.security.JwtAuthFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth

            // AUTH (login público)
            .requestMatchers("/auth/**").permitAll() // permitir todo lo que empiece con /auth sin autenticación

            // registro (para permitir creación sin token)
            .requestMatchers(HttpMethod.GET, "/usuarios/**").permitAll() // permitir GET sin autenticación
            .requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll() // permitir POST sin autenticación
            .requestMatchers(HttpMethod.PUT, "/usuarios/**").permitAll() // permitir PUT sin autenticación
            .requestMatchers(HttpMethod.DELETE, "/usuarios/**").permitAll() // permitir DELETE sin autenticación

            // todo lo demás protegido
            //.requestMatchers("/usuarios/**").authenticated()

            .anyRequest().authenticated()
        );
        //.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
    }

    // 🔐 para hashear contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}