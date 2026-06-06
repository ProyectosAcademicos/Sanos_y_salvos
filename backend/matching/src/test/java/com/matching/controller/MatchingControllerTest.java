package com.matching.controller;

import com.matching.controllers.MatchingController;
import com.matching.dto.MatchingDTO;
import com.matching.service.MatchingService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = MatchingController.class,
        excludeAutoConfiguration = {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
        }
)
@AutoConfigureMockMvc(addFilters = false)
public class MatchingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatchingService matchingService;

    @Test
    void debeProcesarEventoReporteCreado() throws Exception {

        doNothing().when(matchingService)
                .procesarEventoReporteCreado(
                        1L,
                        1L,
                        1L
                );

        mockMvc.perform(
                post("/api/matching/generar")
                        .param("idReporte", "1")
                        .param("idMascota", "1")
                        .param("idUsuario", "1")
        )
        .andExpect(status().isOk());
    }

    @Test
    void debeListarMatchesPorUsuario() throws Exception {

        MatchingDTO dto = new MatchingDTO();

        dto.setId(1L);
        dto.setIdUsuario(99L);

        when(matchingService.obtenerMatchesPorUsuario(99L))
                .thenReturn(List.of(dto));

        mockMvc.perform(
                get("/api/matching/usuario/99")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].idUsuario")
                .value(99));
    }
}