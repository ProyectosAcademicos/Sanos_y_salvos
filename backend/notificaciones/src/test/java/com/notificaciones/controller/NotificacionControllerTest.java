package com.notificaciones.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notificaciones.dto.NotificacionDTO;
import com.notificaciones.service.NotificacionService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = NotificacionController.class,
        excludeAutoConfiguration = {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
        }
)
@AutoConfigureMockMvc(addFilters = false)
public class NotificacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NotificacionService service;

    @Test
    void debeListarNotificaciones() throws Exception {

        NotificacionDTO dto = new NotificacionDTO();
        dto.setId(1L);
        dto.setMensaje("Match encontrado");

        when(service.obtenerPorUsuario("11111111-1"))
                .thenReturn(List.of(dto));

        mockMvc.perform(
                get("/api/notificaciones/usuario/11111111-1")
        )
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$[0].mensaje")
                                .value("Match encontrado")
                );
    }

    @Test
    void debeProcesarEventoMatch() throws Exception {

        NotificacionDTO dto = new NotificacionDTO();
        dto.setId(1L);
        dto.setMensaje("Match encontrado");

        when(service.procesarEventoMatchEncontrado(
                "11111111-1",
                "1",
                "Match encontrado"
        )).thenReturn(dto);

        mockMvc.perform(
                post("/api/notificaciones/evento/match")
                        .param("idUsuario", "11111111-1")
                        .param("idMatch", "1")
                        .param("mensaje", "Match encontrado")
        )
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.mensaje")
                                .value("Match encontrado")
                );
    }

    @Test
        void debeMarcarComoLeida() throws Exception {

        NotificacionDTO dto = new NotificacionDTO();
        dto.setId(1L);
        dto.setMensaje("Leída");

        when(service.marcarComoLeida(1L)).thenReturn(dto);

        mockMvc.perform(
                patch("/api/notificaciones/1/leida")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Leída"));
        }

        @Test
        void debeEliminarNotificacion() throws Exception {

        mockMvc.perform(
                delete("/api/notificaciones/1")
        )
                .andExpect(status().isNoContent());
        }
}