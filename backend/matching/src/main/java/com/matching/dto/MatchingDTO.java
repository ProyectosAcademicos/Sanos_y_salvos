package com.matching.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MatchingDTO {
    private Long id;
    private Long idMascota;
    private Long idUsuario;
    private Long idReporte;
    private Double porcentajeCompatibilidad;
    private LocalDateTime fecha;
}