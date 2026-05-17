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

    // Endpoint para probar el flujo de match del diagrama
    @PostMapping("/generar")
    public ResponseEntity<MatchingDTO> crearMatch(
            @RequestParam Long idMascota, 
            @RequestParam Long idUsuario, 
            @RequestParam Long idReporte) {
        MatchingDTO nuevoMatch = matchingService.generarMatch(idMascota, idUsuario, idReporte);
        if (nuevoMatch != null) {
            return ResponseEntity.ok(nuevoMatch);
        }
        return ResponseEntity.badRequest().build(); 
    }

    // Endpoint para listar las alertas de un usuario específico
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<MatchingDTO>> listarPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(matchingService.obtenerMatchesPorUsuario(idUsuario));
    }
}