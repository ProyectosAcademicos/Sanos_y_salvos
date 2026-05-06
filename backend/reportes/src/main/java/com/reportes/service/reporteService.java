package com.reportes.service;

import com.reportes.model.Reporte;
import com.reportes.repository.RepositoryReportes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private RepositoryReportes repository;

    //obtener todos los reportes
    public List<Reporte> buscarReportes() {
        return repository.findAll();
    }

    //obtener reportes activos
    public List<Reporte> obtenerReportesActivos() {
        return repository.findByEstado("activo");
    }

    public Reporte agregarReporte(Reporte nuevoReporte) {
        return repository.save(nuevoReporte);
    }

    public Reporte obtenerReportePorId(String idReporte) {
        return repository.findById(idReporte).orElse(null);
    }

    public Reporte modificarReporte(String idReporte, Reporte reporteActualizado) {
    Reporte existente = repository.findById(idReporte).orElse(null);

    if (existente != null) {
        existente.setRutUsuario(reporteActualizado.getRutUsuario());
        existente.setIdMascota(reporteActualizado.getIdMascota());
        existente.setUbicacionPerdida(reporteActualizado.getUbicacionPerdida());
        existente.setFechaPerdida(reporteActualizado.getFechaPerdida());
        existente.setDescripcion(reporteActualizado.getDescripcion());
        existente.setEstado(reporteActualizado.getEstado());

        return repository.save(existente);
    }

    return null;
    }

    //suspender reporte
    public Reporte suspenderReporte(String idReporte) {
        Reporte reporteExistente = repository.findById(idReporte).
                orElse(null);
        if (reporteExistente != null) {
            reporteExistente.setEstado("suspendido");
            return repository.save(reporteExistente);
        }
        return null;
    }



}