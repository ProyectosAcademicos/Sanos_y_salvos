package com.mascotas.controller;

import com.mascotas.service.MascotaService;
import com.mascotas.dto.MascotaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "*")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping
    public List<MascotaDTO> listar() {
        return mascotaService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<MascotaDTO> crear(@RequestBody MascotaDTO dto) {
        return ResponseEntity.ok(mascotaService.guardar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaDTO> buscar(@PathVariable Long id) {
        return mascotaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaDTO> actualizar(@PathVariable Long id, @RequestBody MascotaDTO dto) {
        return ResponseEntity.ok(mascotaService.actualizar(id, dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<MascotaDTO> actualizarEstado(@PathVariable Long id, @RequestBody String estado) {
        return ResponseEntity.ok(mascotaService.actualizarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mascotaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
