package com.mascotas.factory;

import com.mascotas.strategy.EstadoMascotaStrategy;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EstadoMascotaFactory {

    private final Map<String, EstadoMascotaStrategy> estrategias;

    // Spring inyecta automáticamente todas las implementaciones de la interfaz
    public EstadoMascotaFactory(List<EstadoMascotaStrategy> listaEstrategias) {
        this.estrategias = listaEstrategias.stream()
                .collect(Collectors.toMap(EstadoMascotaStrategy::getEstado, s -> s));
    }

    public EstadoMascotaStrategy obtener(String estado) {
        EstadoMascotaStrategy strategy = estrategias.get(estado);
        if (strategy == null) {
            throw new IllegalArgumentException("Estado no reconocido: " + estado);
        }
        return strategy;
    }
}