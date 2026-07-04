package com.matching.controllers;

import com.matching.dto.MatchingDTO;
import com.matching.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/matching")
@CrossOrigin(origins = "*")
public class MatchingController {

    @Autowired
    private MatchingService matchingService;

    // TAREA GITHUB: Endpoint adaptado para simular la llegada del evento "ReporteCreado"
    @PostMapping("/generar")
    public ResponseEntity<String> crearMatch(
            @RequestParam String idReporte,
            @RequestParam String tipoReporte,
            @RequestParam String rutUsuario) {

        matchingService.procesarEventoReporteCreado(
                idReporte,
                tipoReporte,
                rutUsuario
        );

        return ResponseEntity.ok("Evento procesado correctamente");
    }

    // Endpoint para listar las alertas de un usuario específico desde la base de datos
    @GetMapping("/usuario/{rutUsuario}")
    public ResponseEntity<List<MatchingDTO>> listarPorUsuario(
            @PathVariable String rutUsuario) {

        return ResponseEntity.ok(
                matchingService.obtenerMatchesPorUsuario(rutUsuario)
        );
    }
    
}