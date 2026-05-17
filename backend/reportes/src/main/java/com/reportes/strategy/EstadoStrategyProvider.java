package com.reportes.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoStrategyProvider {

    @Autowired
    private ActivoStrategy activoStrategy;

    @Autowired
    private SuspendidoStrategy suspendidoStrategy;

    public EstadoReporteStrategy obtenerStrategy(String estado) {

        switch (estado.toLowerCase()) {

            case "activo":
                return activoStrategy;

            case "suspendido":
                return suspendidoStrategy;

            default:
                throw new IllegalArgumentException("Estado inválido");
        }
    }
}