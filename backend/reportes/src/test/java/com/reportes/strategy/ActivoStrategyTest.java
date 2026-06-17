package com.reportes.strategy;

import com.reportes.model.Reporte;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivoStrategyTest {

    @Test
    void debeCambiarEstadoAActivo() {

        ActivoStrategy strategy = new ActivoStrategy();

        Reporte r = new Reporte();

        strategy.cambiarEstado(r);

        assertEquals("activo", r.getEstado());
    }
}