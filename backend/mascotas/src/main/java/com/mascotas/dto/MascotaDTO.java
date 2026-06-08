package com.mascotas.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class MascotaDTO implements Serializable {
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
