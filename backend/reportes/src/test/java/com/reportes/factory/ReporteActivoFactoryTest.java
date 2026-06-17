package com.reportes.factory;

import com.reportes.model.Reporte;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReporteActivoFactoryTest {

    @Test
    void debeCrearReporteActivo() {

        ReporteActivoFactory factory = new ReporteActivoFactory();

        Reporte r = new Reporte();

        Reporte result = factory.crearReporte(r);

        assertEquals("activo", result.getEstado());
    }
}