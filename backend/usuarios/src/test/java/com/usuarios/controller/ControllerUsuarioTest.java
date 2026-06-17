package com.usuarios.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usuarios.model.Usuario;
import com.usuarios.service.ServiceUsuario;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import org.springframework.test.web.servlet.MockMvc;

import com.usuarios.config.SecurityConfig;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.usuarios.security.JwtAuthFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(
    controllers = ControllerUsuario.class,
    excludeAutoConfiguration = {
            org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
        }
    )
@AutoConfigureMockMvc(addFilters = false)

// @Import(SecurityConfig.class)
public class ControllerUsuarioTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ServiceUsuario serviceUsuario;


    @MockBean
    private JwtAuthFilter jwtAuthFilter;

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

    @Test
    void debeRetornarUsuarioPorRut() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setRut("11111111-1");
        usuario.setNombre("Jonathan");
        
        when(serviceUsuario.getUsuarioByRut("11111111-1"))
        .thenReturn(usuario);
        
        mockMvc.perform(get("/usuarios/11111111-1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.rut")
                .value("11111111-1"));
}
 
        @Test
        void debeActualizarUsuario() throws Exception {
        
            Usuario usuario = new Usuario();
            usuario.setRut("11111111-1");
            usuario.setNombre("Pedro");
        
            when(serviceUsuario.actualizarUsuario(
                    eq("11111111-1"),
                    any(Usuario.class)))
                    .thenReturn(usuario);
        
            mockMvc.perform(put("/usuarios/11111111-1")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(usuario)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nombre")
                            .value("Pedro"));
        }

        @Test
        void debeEliminarUsuario() throws Exception {

        when(serviceUsuario.eliminarUsuario("11111111-1"))
                .thenReturn(true);

        mockMvc.perform(delete("/usuarios/11111111-1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        }

        @Test
        void debeLanzarErrorAlCrearUsuario() throws Exception   {

        Usuario usuario = new Usuario();
        usuario.setRut("11111111-1");

        when(serviceUsuario.crearUsuario(any(Usuario.class)))
                .thenThrow(new Exception("Error al crear"));

        assertThrows(Exception.class, () ->
                mockMvc.perform(post("/usuarios")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(usuario)))
        );
        }

        @Test
        void debeLanzarErrorAlActualizarUsuario() {

        Usuario usuario = new Usuario();

        when(serviceUsuario.actualizarUsuario(
                eq("11111111-1"),
                any(Usuario.class)))
                .thenThrow(new RuntimeException("Error al actualizar"));

        assertThrows(Exception.class, () ->
                mockMvc.perform(put("/usuarios/11111111-1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(usuario)))
        );
        }

        @Test
        void debeLanzarErrorAlEliminarUsuario() throws Exception {

        when(serviceUsuario.eliminarUsuario("11111111-1"))
                .thenThrow(new Exception("Error al eliminar"));

        assertThrows(Exception.class, () ->
                mockMvc.perform(delete("/usuarios/11111111-1"))
        );
        }

        @Test
        void debeRetornarNullCuandoUsuarioNoExiste() throws Exception {

        when(serviceUsuario.getUsuarioByRut("99999999-9"))
                .thenReturn(null);

        mockMvc.perform(get("/usuarios/99999999-9"))
                .andExpect(status().isOk());
        }

        @Test
        void debeCrearUsuarioSinNombre() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setRut("11111111-1");

        when(serviceUsuario.crearUsuario(any()))
                .thenReturn(usuario);

        mockMvc.perform(post("/usuarios")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk());
        }

        @Test
        void debeActualizarUsuarioRetornandoNull()
                throws Exception {

        when(serviceUsuario.actualizarUsuario(
                eq("11111111-1"),
                any(Usuario.class)))
                .thenReturn(null);

        mockMvc.perform(put("/usuarios/11111111-1")
                .contentType("application/json")
                .content("{}"))
                .andExpect(status().isOk());
        }

        @Test
        void debeEliminarUsuarioRetornandoFalse()
                throws Exception {

        when(serviceUsuario.eliminarUsuario("11111111-1"))
                .thenReturn(false);

        mockMvc.perform(delete("/usuarios/11111111-1"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        }

}
