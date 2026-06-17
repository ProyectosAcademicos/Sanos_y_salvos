package com.reportes.strategy;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class EstadoStrategyProviderTest {

    @Test
    void debeRetornarActivoStrategy() {

        EstadoStrategyProvider provider = new EstadoStrategyProvider();

        ReflectionTestUtils.setField(provider, "activoStrategy", new ActivoStrategy());
        ReflectionTestUtils.setField(provider, "suspendidoStrategy", new SuspendidoStrategy());

        EstadoReporteStrategy strategy =
                provider.obtenerStrategy("activo");

        assertTrue(strategy instanceof ActivoStrategy);
    }

    @Test
    void debeRetornarSuspendidoStrategy() {

        EstadoStrategyProvider provider = new EstadoStrategyProvider();

        ReflectionTestUtils.setField(provider, "activoStrategy", new ActivoStrategy());
        ReflectionTestUtils.setField(provider, "suspendidoStrategy", new SuspendidoStrategy());

        EstadoReporteStrategy strategy =
                provider.obtenerStrategy("suspendido");

        assertTrue(strategy instanceof SuspendidoStrategy);
    }

    @Test
    void debeLanzarExcepcionEstadoInvalido() {

        EstadoStrategyProvider provider = new EstadoStrategyProvider();

        ReflectionTestUtils.setField(provider, "activoStrategy", new ActivoStrategy());
        ReflectionTestUtils.setField(provider, "suspendidoStrategy", new SuspendidoStrategy());

        assertThrows(IllegalArgumentException.class,
                () -> provider.obtenerStrategy("otro"));
    }
}