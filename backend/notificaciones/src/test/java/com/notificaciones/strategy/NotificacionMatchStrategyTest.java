package com.notificaciones.strategy;

import com.notificaciones.model.Notificacion;
import org.junit.jupiter.api.Test;

class NotificacionMatchStrategyTest {

    @Test
    void debeEjecutarEnviar() {

        NotificacionMatchStrategy strategy = new NotificacionMatchStrategy();

        Notificacion n = new Notificacion();
        n.setIdUsuario("1");
        n.setMensaje("hola match");

        strategy.enviar(n); // solo valida ejecución sin error
    }

    @Test
    void debeRetornarTipo() {
        NotificacionMatchStrategy strategy = new NotificacionMatchStrategy();
        assert strategy.getTipo().equals("MATCH");
    }
}