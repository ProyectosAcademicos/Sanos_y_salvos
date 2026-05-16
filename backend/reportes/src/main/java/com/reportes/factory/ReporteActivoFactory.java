package com.reportes.factory;

import com.reportes.model.Reporte;
import org.springframework.stereotype.Component;

@Component
public class ReporteActivoFactory extends ReporteFactory {

    @Override
    public Reporte crearReporte(Reporte reporte) {
        reporte.setEstado("activo");
        return reporte;
    }
}