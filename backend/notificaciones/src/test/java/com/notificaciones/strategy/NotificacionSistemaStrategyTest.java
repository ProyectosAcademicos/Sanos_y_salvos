package com.notificaciones.strategy;

import com.notificaciones.model.Notificacion;
import org.junit.jupiter.api.Test;

class NotificacionSistemaStrategyTest {

    @Test
    void debeEjecutarEnviar() {

        NotificacionSistemaStrategy strategy = new NotificacionSistemaStrategy();

        Notificacion n = new Notificacion();
        n.setIdUsuario("1");
        n.setMensaje("sistema");

        strategy.enviar(n);
    }

    @Test
    void debeRetornarTipo() {
        NotificacionSistemaStrategy strategy = new NotificacionSistemaStrategy();
        assert strategy.getTipo().equals("SISTEMA");
    }
}