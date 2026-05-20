package com.reportes.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReporteFactoryProvider {

    @Autowired
    private ReporteActivoFactory activoFactory;

    public ReporteFactory obtenerFactory(String estado) {

        switch (estado.toLowerCase()) {

            case "activo":
                return activoFactory;

            default:
                throw new IllegalArgumentException("Estado inválido");
        }
    }
}