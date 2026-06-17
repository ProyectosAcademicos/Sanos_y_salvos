package com.reportes.strategy;

import com.reportes.model.Reporte;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class SuspendidoStrategyTest {

    @Test
    void debeCambiarEstadoASuspendido() {

        SuspendidoStrategy strategy = new SuspendidoStrategy();

        Reporte r = new Reporte();

        strategy.cambiarEstado(r);

        assertEquals("suspendido", r.getEstado());
    }
}