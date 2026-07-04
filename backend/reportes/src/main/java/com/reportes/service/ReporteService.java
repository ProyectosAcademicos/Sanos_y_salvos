package com.reportes.service;

import com.reportes.model.Reporte;
import com.reportes.repository.RepositoryReportes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reportes.strategy.EstadoReporteStrategy;
import com.reportes.strategy.EstadoStrategyProvider;
import com.reportes.factory.ReporteFactory;
import com.reportes.factory.ReporteFactoryProvider;
import com.reportes.factory.ReporteFactory;
import com.reportes.strategy.EstadoReporteStrategy;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private ReporteFactoryProvider factoryProvider;

    @Autowired
    private EstadoStrategyProvider strategyProvider;

    @Autowired
    private RepositoryReportes repository;

    @Autowired
    private RestTemplate restTemplate;

    private final String MATCHING_URL =
        "http://matching-service:8080/api/matching/generar";

    //obtener todos los reportes
    public List<Reporte> buscarReportes() {
        return repository.findAll();
    }

    //obtener reportes activos
    public List<Reporte> obtenerReportesActivos() {
        return repository.findByEstado("activo");
    }

    public Reporte agregarReporte(Reporte nuevoReporte) {

        // 1. Crear el reporte usando tu factory
        ReporteFactory factory = factoryProvider.obtenerFactory("activo");
        Reporte reporteCreado = factory.crearReporte(nuevoReporte);

        // 2. Guardar en base de datos
        Reporte reporteGuardado = repository.save(reporteCreado);

        // 3. Preparar llamada a Matching Service
        try {
            String url = MATCHING_URL
                    + "?idReporte=" + reporteGuardado.getIdReporte()
                    + "&tipoReporte=" + reporteGuardado.getTipoReporte()
                    + "&rutUsuario=" + reporteGuardado.getRutUsuario();

            System.out.println("📡 Enviando evento a Matching: " + url);

            restTemplate.postForObject(
                    url,
                    null,
                    String.class
            );

        } catch (Exception e) {
            System.out.println("Error llamando a Matching: " + e.getMessage());
        }

        // 4. Retornar reporte
        return reporteGuardado;
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
        existente.setTipoReporte(reporteActualizado.getTipoReporte());

        return repository.save(existente);
    }

    return null;
    }

    //suspender reporte
    public Reporte suspenderReporte(String idReporte) {
        Reporte reporteExistente =repository.findById(idReporte).orElse(null);
        if (reporteExistente != null) {
            EstadoReporteStrategy strategy = strategyProvider.obtenerStrategy("suspendido");
            strategy.cambiarEstado(reporteExistente);
            return repository.save(reporteExistente);
    }
        return null;
    }

}