package com.mascotas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mascotas.dto.MascotaDTO;
import com.mascotas.service.MascotaService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = MascotaController.class,
        excludeAutoConfiguration = {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
        }
)
@AutoConfigureMockMvc(addFilters = false)
public class MascotaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MascotaService mascotaService;

    @Test
    void debeRetornarListaMascotas() throws Exception {

        MascotaDTO mascota = new MascotaDTO();
        mascota.setId(1L);
        mascota.setNombre("Firulais");

        when(mascotaService.listarTodas())
                .thenReturn(List.of(mascota));

        mockMvc.perform(get("/api/mascotas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre")
                        .value("Firulais"));
    }

    @Test
    void debeCrearMascota() throws Exception {

        MascotaDTO mascota = new MascotaDTO();
        mascota.setId(1L);
        mascota.setNombre("Firulais");

        when(mascotaService.guardar(any(MascotaDTO.class), any(String.class)))
                .thenReturn(mascota);

        mockMvc.perform(post("/api/mascotas")
                        .header("Authorization", "Bearer token-falso")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(mascota)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre")
                        .value("Firulais"));
    }

    @Test
    void debeBuscarMascotaPorId() throws Exception {

        MascotaDTO mascota = new MascotaDTO();
        mascota.setId(1L);
        mascota.setNombre("Firulais");

        when(mascotaService.obtenerPorId(1L))
                .thenReturn(Optional.of(mascota));

        mockMvc.perform(get("/api/mascotas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre")
                        .value("Firulais"));
    }
}