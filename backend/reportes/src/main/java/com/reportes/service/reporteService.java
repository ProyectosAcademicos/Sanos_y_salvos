package com.reportes.service;

import com.reportes.model.reporte;
import com.reportes.repository.reporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class reporteService {

    @Autowired
    private reporteRepository repository;

    //obtener todos los reportes
    public List<reporte> buscarReportes() {
        return repository.findAll();
    }

    //obtener reportes activos

    public List<reporte> obtenerReportesActivos() {
        return repository.findByEstado("activo");
    }

    public reporte agregarReporte(reporte nuevoReporte) {
        return repository.save(nuevoReporte);
    }

    public reporte obtenerReportePorId(String idReporte) {
        return repository.findById(idReporte).orElse(null);
    }

    public reporte modificarReporte(String idReporte, reporte reporteActualizado) {
        if (repository.existsById(idReporte)) {
            reporteActualizado.setIdReporte(idReporte);
            return repository.save(reporteActualizado);
        }
        return null;
    }

    //suspender reporte
    public reporte suspenderReporte(String idReporte) {
        reporte reporteExistente = repository.findById(idReporte).
                orElse(null);
        if (reporteExistente != null) {
            reporteExistente.setEstado("suspendido");
            return repository.save(reporteExistente);
        }
        return null;
    }



}