package com.example.bffservice.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UsuarioDTO {

    private String rut;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private String tipoUsuario;
    private LocalDate fechaRegistro;

}