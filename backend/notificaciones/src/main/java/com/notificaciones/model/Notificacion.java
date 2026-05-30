package com.notificaciones.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "notificaciones")
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idUsuario;
    private String idMatch;
    private String tipo;
    private String mensaje;
    private Boolean leido;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;
}
