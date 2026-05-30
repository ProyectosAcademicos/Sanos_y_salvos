package com.notificaciones.strategy;

import com.notificaciones.model.Notificacion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificacionSistemaStrategy implements NotificacionStrategy {

    @Override
    public void enviar(Notificacion notificacion) {
        log.info("[SISTEMA] Mensaje del sistema para usuario '{}': {}",
                notificacion.getIdUsuario(),
                notificacion.getMensaje());
    }

    @Override
    public String getTipo() {
        return "SISTEMA";
    }
}