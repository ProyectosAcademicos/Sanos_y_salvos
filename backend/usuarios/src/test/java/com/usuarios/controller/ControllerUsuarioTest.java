package com.usuarios.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usuarios.model.Usuario;
import com.usuarios.service.ServiceUsuario;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import org.springframework.test.web.servlet.MockMvc;

import com.usuarios.config.SecurityConfig;

import java.util.List;

import static org.mockito.Mockito.when;

import com.usuarios.security.JwtAuthFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControllerUsuario.class)
@AutoConfigureMockMvc(addFilters = false)
// @Import(SecurityConfig.class)
public class ControllerUsuarioTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ServiceUsuario serviceUsuario;

    // @MockBean
    // private JwtAuthFilter jwtAuthFilter;

    //prueba para obtener la lista de usuarios
    @Test
    void debeRetornarListaUsuarios() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setRut("11111111-1");
        usuario.setNombre("Jonathan");

        when(serviceUsuario.getUsuarios())
                .thenReturn(List.of(usuario));

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rut")
                        .value("11111111-1"));
    }

    //prueba para crear un usuario exitosamente

    @Test
    void debeCrearUsuario() throws Exception {

        Usuario usuario = new Usuario();

        usuario.setRut("11111111-1");
        usuario.setNombre("Jonathan");

        when(serviceUsuario.crearUsuario(org.mockito.ArgumentMatchers.any()))
                .thenReturn(usuario);

        mockMvc.perform(post("/usuarios")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rut")
                        .value("11111111-1"));
    }



}
