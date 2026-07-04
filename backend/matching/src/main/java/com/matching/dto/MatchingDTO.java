package com.matching.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MatchingDTO {

    private Long id;

    private String rutUsuario;

    private String idReportePerdida;

    private String idReporteEncontrado;

    private LocalDateTime fecha;

}