package com.matching.service;

import com.matching.dto.MatchingDTO;
import com.matching.factory.MatchingStrategyFactory;
import com.matching.model.Matching;
import com.matching.repository.MatchingRepository;
import com.matching.strategy.MatchingStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchingService {

    @Autowired
    private MatchingRepository matchingRepository;

    @Autowired
    private MatchingStrategyFactory strategyFactory;

    // --- MÉTODOS DE CONVERSIÓN (Mantener tu DTO limpio con Lombok) ---
    private MatchingDTO convertirADTO(Matching matching) {
        MatchingDTO dto = new MatchingDTO();
        dto.setId(matching.getId());
        dto.setIdMascota(matching.getIdMascota());
        dto.setIdUsuario(matching.getIdUsuario());
        dto.setIdReporte(matching.getIdReporte());
        dto.setPorcentajeCompatibilidad(matching.getPorcentajeCompatibilidad());
        dto.setFecha(matching.getFecha());
        return dto;
    }

    // --- LÓGICA ORIENTADA A EVENTOS (Tablero de GitHub) ---

    // TAREA GITHUB 1: Recibir evento "ReporteCreado"
    public void procesarEventoReporteCreado(Long idReporte, Long idMascota, Long idUsuario) {
        System.out.println("--> EVENTO RECIBIDO: ReporteCreado para el reporte ID: " + idReporte);

        // TAREA GITHUB 2: Lógica de coincidencia usando PATRÓN FACTORY + STRATEGY
        MatchingStrategy estrategia = strategyFactory.getEstrategia("fisicoStrategy");
        
        // El patrón Strategy calcula de forma dinámica (en el futuro pasarás objetos reales)
        Double porcentaje = estrategia.calcular(null, null); 

        // TAREA GITHUB 3: Generar resultado de coincidencia si supera el umbral
        if (porcentaje >= 70.0) {
            Matching match = new Matching(null, idMascota, idUsuario, idReporte, porcentaje, LocalDateTime.now());
            Matching guardado = matchingRepository.save(match);
            
            // TAREA GITHUB 4: Enviar evento "MatchEncontrado"
            emitirEventoMatchEncontrado(guardado.getId());
        }
    }

    // TAREA GITHUB 4 (Detalle): Simulación del emisor de eventos
    private void emitirEventoMatchEncontrado(Long idMatch) {
        System.out.println("--> EVENTO EMITIDO: MatchEncontrado con ID: " + idMatch);
        // Aquí conectarán RabbitMQ / Kafka o el sistema que defina tu grupo para alertar a Notificaciones
    }

    // --- MÉTODOS DE CONSULTA (Para cuando el frontend pida ver los matches) ---
    public List<MatchingDTO> obtenerMatchesPorUsuario(Long idUsuario) {
        return matchingRepository.findByIdUsuario(idUsuario)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }
}