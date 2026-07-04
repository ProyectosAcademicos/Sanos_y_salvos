package com.notificaciones.controller;

import com.notificaciones.dto.NotificacionDTO;
import com.notificaciones.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
//@CrossOrigin(origins = "*")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    // Simula recibir el evento MatchEncontrado
    @PostMapping("/evento/match")
    public ResponseEntity<NotificacionDTO> recibirEventoMatch(
            @RequestParam String idUsuario,
            @RequestParam String idMatch,
            @RequestParam String mensaje) {
        return ResponseEntity.ok(
            notificacionService.procesarEventoMatchEncontrado(idUsuario, idMatch, mensaje)
        );
    }

    // Obtener notificaciones de un usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<NotificacionDTO>> obtenerPorUsuario(
            @PathVariable String idUsuario) {
        return ResponseEntity.ok(notificacionService.obtenerPorUsuario(idUsuario));
    }

    // Marcar como leída
    @PatchMapping("/{id}/leida")
    public ResponseEntity<NotificacionDTO> marcarLeida(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.marcarComoLeida(id));
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        notificacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}