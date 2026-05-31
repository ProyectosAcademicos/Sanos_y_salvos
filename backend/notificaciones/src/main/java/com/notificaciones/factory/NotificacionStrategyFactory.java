package com.notificaciones.factory;

import com.notificaciones.strategy.NotificacionStrategy;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class NotificacionStrategyFactory {

    private final Map<String, NotificacionStrategy> estrategias;

    public NotificacionStrategyFactory(List<NotificacionStrategy> lista) {
        this.estrategias = lista.stream()
                .collect(Collectors.toMap(NotificacionStrategy::getTipo, s -> s));
    }

    public NotificacionStrategy obtener(String tipo) {
        NotificacionStrategy strategy = estrategias.get(tipo);
        if (strategy == null) {
            throw new IllegalArgumentException("Tipo de notificación no reconocido: " + tipo);
        }
        return strategy;
    }
}