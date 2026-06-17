package com.notificaciones.factory;

import com.notificaciones.strategy.NotificacionStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotificacionStrategyFactoryTest {

    @Test
    void debeObtenerEstrategiaMatch() {

        NotificacionStrategy match = new NotificacionStrategy() {
            public void enviar(com.notificaciones.model.Notificacion n) {}
            public String getTipo() { return "MATCH"; }
        };

        NotificacionStrategyFactory factory =
                new NotificacionStrategyFactory(List.of(match));

        NotificacionStrategy result = factory.obtener("MATCH");

        assertNotNull(result);
        assertEquals("MATCH", result.getTipo());
    }

    @Test
    void debeObtenerEstrategiaSistema() {

        NotificacionStrategy sistema = new NotificacionStrategy() {
            public void enviar(com.notificaciones.model.Notificacion n) {}
            public String getTipo() { return "SISTEMA"; }
        };

        NotificacionStrategyFactory factory =
                new NotificacionStrategyFactory(List.of(sistema));

        NotificacionStrategy result = factory.obtener("SISTEMA");

        assertEquals("SISTEMA", result.getTipo());
    }

    @Test
    void debeLanzarExcepcionSiTipoNoExiste() {

        NotificacionStrategyFactory factory =
                new NotificacionStrategyFactory(List.of());

        assertThrows(IllegalArgumentException.class, () ->
                factory.obtener("INVALIDO")
        );
    }
}