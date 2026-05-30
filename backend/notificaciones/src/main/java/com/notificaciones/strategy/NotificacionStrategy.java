package com.notificaciones.strategy;
import com.notificaciones.model.Notificacion;

public interface NotificacionStrategy {
    void enviar(Notificacion notificacion);
    String getTipo();
}
