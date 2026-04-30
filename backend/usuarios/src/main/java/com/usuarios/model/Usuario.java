package com.usuarios.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {

    private String id;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private LocalDate fechaRegistro;


}
