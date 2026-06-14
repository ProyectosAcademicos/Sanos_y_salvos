package com.example.bffservice.dto;

import lombok.Data;
import java.util.Date;

@Data
public class NotificacionDTO {
    private Long id;
    private String idUsuario;
    private String idMatch;
    private String tipo;
    private String mensaje;
    private Boolean leido;
    private Date fechaEnvio;
}