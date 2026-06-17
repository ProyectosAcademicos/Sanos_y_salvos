package com.reportes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reportes.model.Reporte;
import com.reportes.service.ReporteService;
import static org.springframework.http.MediaType.APPLICATION_JSON;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReporteController.class)
@AutoConfigureMockMvc(addFilters = false)
class ReporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReporteService service;

    @Test
    void debeListarReportes() throws Exception {

        when(service.buscarReportes())
                .thenReturn(List.of(new Reporte()));

        mockMvc.perform(get("/reportes"))
                .andExpect(status().isOk());
    }

    @Test
    void debeObtenerReportePorId() throws Exception {

        Reporte r = new Reporte();
        r.setIdReporte("1");

        when(service.obtenerReportePorId("1")).thenReturn(r);

        mockMvc.perform(get("/reportes/1"))
                .andExpect(status().isOk());
    }

    @Test
    void debeRetornar404SiNoExiste() throws Exception {

        when(service.obtenerReportePorId("999")).thenReturn(null);

        mockMvc.perform(get("/reportes/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void debeCrearReporte() throws Exception {

        Reporte r = new Reporte();
        r.setIdReporte("1");

        when(service.agregarReporte(org.mockito.ArgumentMatchers.any()))
                .thenReturn(r);

        mockMvc.perform(post("/reportes")
                .contentType(APPLICATION_JSON)
                .content("{}"))
            .andExpect(status().isOk());
    }

    @Test
    void debeModificarReporte() throws Exception {

        Reporte r = new Reporte();
        r.setIdReporte("1");

        when(service.modificarReporte(org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.any()))
                .thenReturn(r);

        mockMvc.perform(post("/reportes")
                .contentType(APPLICATION_JSON)
                .content("{}"))
        .andExpect(status().isOk());
    }

    @Test
    void debeSuspenderReporte() throws Exception {

        Reporte r = new Reporte();

        when(service.suspenderReporte("1")).thenReturn(r);

        mockMvc.perform(put("/reportes/1/suspender"))
                .andExpect(status().isOk());
    }
}