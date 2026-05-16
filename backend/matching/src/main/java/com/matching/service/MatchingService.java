package com.matching.service;

import com.matching.dto.MatchingDTO;
import com.matching.model.Matching;
import com.matching.repository.MatchingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchingService {

    @Autowired
    private MatchingRepository matchingRepository;

    // --- MÉTODOS DE CONVERSIÓN (Usando Lombok detras de escena) ---
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

    // --- LÓGICA DEL DIAGRAMA DE CLASES ---
    public Double calcularCompatibilidad(Long idMascota, Long idReporte) {
        // Simulación: En el futuro aquí compararemos campos reales (raza, tipo, etc.)
        return 85.5;
    }

    public MatchingDTO generarMatch(Long idMascota, Long idUsuario, Long idReporte) {
        Double compatibilidad = calcularCompatibilidad(idMascota, idReporte);
        
        // Si el porcentaje es alto (>= 70%), guardamos la coincidencia
        if (compatibilidad >= 70.0) {
            Matching matching = new Matching(null, idMascota, idUsuario, idReporte, compatibilidad, LocalDateTime.now());
            Matching guardado = matchingRepository.save(matching);
            notificarCoincidencia(guardado.getIdUsuario(), guardado.getId());
            return convertirADTO(guardado);
        }
        return null;
    }

    public List<MatchingDTO> obtenerMatchesPorUsuario(Long idUsuario) {
        return matchingRepository.findByIdUsuario(idUsuario)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public void notificarCoincidencia(Long idUsuario, Long idMatch) {
        System.out.println("NOTIFICACIÓN SYSTEM: Coincidencia encontrada para Usuario ID: " + idUsuario + " en Match ID: " + idMatch);
    }
}