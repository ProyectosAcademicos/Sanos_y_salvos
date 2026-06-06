package com.reportes.controller;

import com.reportes.model.Reporte;
import com.reportes.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController  {
    //comentario de prueba

    @Autowired
    private ReporteService service;

    @GetMapping
    public List<Reporte> obtenerReportes() {
        return service.buscarReportes();
    }

    @GetMapping("/activos")
    public List<Reporte> obtenerReportesActivos() {
        return service.obtenerReportesActivos();
    }

    @PostMapping
    public Reporte agregarReporte(@RequestBody Reporte nuevoReporte) {
        return service.agregarReporte(nuevoReporte);
    }

    @GetMapping("/{idReporte}")
    public ResponseEntity<Reporte> obtenerReportePorId(@PathVariable String idReporte) {
        Reporte reporte = service.obtenerReportePorId(idReporte);
        if (reporte != null) {
            return ResponseEntity.ok(reporte);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idReporte}")
    public ResponseEntity<Reporte> modificarReporte(@PathVariable String idReporte, @RequestBody Reporte reporteActualizado) {
        Reporte reporte = service.modificarReporte(idReporte, reporteActualizado);
        if (reporte != null) {
            return ResponseEntity.ok(reporte);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idReporte}/suspender")
    public ResponseEntity<Reporte> suspenderReporte(@PathVariable String idReporte) {
        Reporte reporte = service.suspenderReporte(idReporte);
        if (reporte != null) {
            return ResponseEntity.ok(reporte);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}