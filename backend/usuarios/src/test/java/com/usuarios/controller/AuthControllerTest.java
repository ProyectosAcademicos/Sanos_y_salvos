package com.usuarios.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usuarios.model.Usuario;
import com.usuarios.security.JwtAuthFilter;
import com.usuarios.security.JwtUtil;
import com.usuarios.service.ServiceUsuario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = AuthController.class,
        excludeAutoConfiguration = {
                SecurityAutoConfiguration.class
        }
)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ServiceUsuario serviceUsuario;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private JwtAuthFilter jwtAuthFilter;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void debeIniciarSesion() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setRut("11111111-1");
        usuario.setContrasena("hashGuardado");

        when(serviceUsuario.getUsuarioByRut("11111111-1"))
                .thenReturn(usuario);

        when(passwordEncoder.matches(
                "1234",
                "hashGuardado"))
                .thenReturn(true);

        when(jwtUtil.generateToken("11111111-1"))
                .thenReturn("token123");

        Usuario request = new Usuario();
        request.setRut("11111111-1");
        request.setContrasena("1234");

        mockMvc.perform(post("/auth/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token")
                        .value("token123"));
    }

    @Test
    void debeRegistrarUsuario() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setRut("11111111-1");

        when(serviceUsuario.crearUsuario(any()))
                .thenReturn(usuario);

        mockMvc.perform(post("/auth/register")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rut")
                        .value("11111111-1"));
    }

    @Test
    void debeFallarLoginPorPasswordIncorrecta() {

        Usuario usuario = new Usuario();
        usuario.setRut("11111111-1");
        usuario.setContrasena("hashGuardado");

        when(serviceUsuario.getUsuarioByRut("11111111-1"))
                .thenReturn(usuario);

        when(passwordEncoder.matches(
                "9999",
                "hashGuardado"))
                .thenReturn(false);

        Usuario request = new Usuario();
        request.setRut("11111111-1");
        request.setContrasena("9999");

        assertThrows(Exception.class, () ->
                mockMvc.perform(post("/auth/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request))
                )
        );
    }
}