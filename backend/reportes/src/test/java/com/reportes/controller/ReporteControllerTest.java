package com.reportes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reportes.model.Reporte;
import com.reportes.service.ReporteService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ReporteController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ReporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReporteService service;

    @Test
    void debeListarReportes() throws Exception {

        Reporte reporte = new Reporte();
        reporte.setIdReporte("123");
        reporte.setEstado("activo");

        when(service.buscarReportes())
                .thenReturn(List.of(reporte));

        mockMvc.perform(get("/reportes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado")
                        .value("activo"));
    }
}
