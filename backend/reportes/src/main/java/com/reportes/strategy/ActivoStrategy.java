package com.reportes.strategy;

import com.reportes.model.Reporte;
import org.springframework.stereotype.Component;

@Component
public class ActivoStrategy implements EstadoReporteStrategy {

    @Override
    public void cambiarEstado(Reporte reporte) {
        reporte.setEstado("activo");
    }
}