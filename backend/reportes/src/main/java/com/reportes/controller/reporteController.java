package com.reportes.controller;

import com.reportes.model.reporte;
import com.reportes.service.reporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class reporteController {

    @Autowired
    private reporteService service;

    @GetMapping
    public List<reporte> obtenerReportes() {
        return service.buscarReportes();
    }

    @GetMapping("/activos")
    public List<reporte> obtenerReportesActivos() {
        return service.obtenerReportesActivos();
    }

    @PostMapping
    public reporte agregarReporte(@RequestBody reporte nuevoReporte) {
        return service.agregarReporte(nuevoReporte);
    }

    @GetMapping("/{idReporte}")
    public ResponseEntity<reporte> obtenerReportePorId(@PathVariable String idReporte) {
        reporte reporte = service.obtenerReportePorId(idReporte);
        if (reporte != null) {
            return ResponseEntity.ok(reporte);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idReporte}")
    public ResponseEntity<reporte> modificarReporte(@PathVariable String idReporte, @RequestBody reporte reporteActualizado) {
        reporte reporte = service.modificarReporte(idReporte, reporteActualizado);
        if (reporte != null) {
            return ResponseEntity.ok(reporte);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idReporte}/suspender")
    public ResponseEntity<reporte> suspenderReporte(@PathVariable String idReporte) {
        reporte reporte = service.suspenderReporte(idReporte);
        if (reporte != null) {
            return ResponseEntity.ok(reporte);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}