// package com.reportes.config;

// import org.junit.jupiter.api.Test;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.mock;
// import org.mockito.Answers;

// class ReporteConfigTest {

//     @Test
//     void debeCrearSecurityFilterChain() throws Exception {

//         ReporteConfig config = new ReporteConfig();

//         HttpSecurity http = mock(HttpSecurity.class, Answers.RETURNS_SELF);

//         SecurityFilterChain chain = config.filterChain(http);

//         assertNotNull(chain);
//     }
// }