package com.reportes.factory;

import com.reportes.model.Reporte;

public abstract class ReporteFactory {
    public abstract Reporte crearReporte(Reporte reporte);
}