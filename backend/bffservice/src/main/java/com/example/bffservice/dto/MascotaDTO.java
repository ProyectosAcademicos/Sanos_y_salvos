package com.example.bffservice.dto;

import lombok.Data;

@Data
public class MascotaDTO {
    private Long id;
    private String nombre;
    private String tipo;
    private String raza;
    private Integer edad;
    private String tamano;
    private String descripcion;
    private String idUsuario;
    private String estado;
}