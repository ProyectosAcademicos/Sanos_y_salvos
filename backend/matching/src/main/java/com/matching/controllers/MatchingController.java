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
            @RequestParam Long idReporte,
            @RequestParam Long idMascota, 
            @RequestParam Long idUsuario) {
        
        // Ejecutamos la  lógica basada en Eventos + Strategy + Factory
        matchingService.procesarEventoReporteCreado(idReporte, idMascota, idUsuario);
        
        // Retornamos una respuesta simple indicando que el evento fue recibido por el sistema
        return ResponseEntity.ok("Evento 'ReporteCreado' recibido y procesado en la cola del sistema.");
    }

    // Endpoint para listar las alertas de un usuario específico desde la base de datos
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<MatchingDTO>> listarPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(matchingService.obtenerMatchesPorUsuario(idUsuario));
    }
    
}