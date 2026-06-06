package com.reportes.service;

import com.reportes.factory.ReporteFactory;
import com.reportes.factory.ReporteFactoryProvider;
import com.reportes.model.Reporte;
import com.reportes.repository.RepositoryReportes;
import com.reportes.strategy.EstadoReporteStrategy;
import com.reportes.strategy.EstadoStrategyProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReporteServiceTest {

    @Mock
    private RepositoryReportes repository;

    @Mock
    private ReporteFactoryProvider factoryProvider;

    @Mock
    private EstadoStrategyProvider strategyProvider;

    @Mock
    private ReporteFactory reporteFactory;

    @Mock
    private EstadoReporteStrategy estadoStrategy;

    @InjectMocks
    private ReporteService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void debeAgregarReporte() {

        Reporte reporte = new Reporte();

        when(factoryProvider.obtenerFactory("activo"))
                .thenReturn(reporteFactory);

        when(reporteFactory.crearReporte(reporte))
                .thenReturn(reporte);

        when(repository.save(reporte))
                .thenReturn(reporte);

        Reporte resultado =
                service.agregarReporte(reporte);

        assertNotNull(resultado);
    }

    @Test
    void debeObtenerReportePorId() {

        Reporte reporte = new Reporte();
        reporte.setIdReporte("123");

        when(repository.findById("123"))
                .thenReturn(Optional.of(reporte));

        Reporte resultado =
                service.obtenerReportePorId("123");

        assertNotNull(resultado);
        assertEquals("123",
                resultado.getIdReporte());
    }

    @Test
    void debeSuspenderReporte() {

        Reporte reporte = new Reporte();
        reporte.setIdReporte("123");

        when(repository.findById("123"))
                .thenReturn(Optional.of(reporte));

        when(strategyProvider.obtenerStrategy("suspendido"))
                .thenReturn(estadoStrategy);

        when(repository.save(any(Reporte.class)))
                .thenReturn(reporte);

        Reporte resultado =
                service.suspenderReporte("123");

        assertNotNull(resultado);

        verify(estadoStrategy)
                .cambiarEstado(reporte);
    }
}