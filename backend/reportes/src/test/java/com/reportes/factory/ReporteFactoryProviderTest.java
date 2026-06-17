package com.reportes.factory;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class ReporteFactoryProviderTest {

    @Test
    void debeRetornarFactoryActivo() {

        ReporteFactoryProvider provider = new ReporteFactoryProvider();

        ReflectionTestUtils.setField(
                provider,
                "activoFactory",
                new ReporteActivoFactory()
        );

        ReporteFactory factory =
                provider.obtenerFactory("activo");

        assertTrue(factory instanceof ReporteActivoFactory);
    }

    @Test
    void debeLanzarExcepcion() {

        ReporteFactoryProvider provider = new ReporteFactoryProvider();

        assertThrows(IllegalArgumentException.class,
                () -> provider.obtenerFactory("otro"));
    }
}