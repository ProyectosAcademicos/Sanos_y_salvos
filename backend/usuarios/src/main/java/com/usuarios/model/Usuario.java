package com.usuarios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import jakarta.persistence.Column;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @Column(nullable = false, unique = true)
    private String rut;
    private String nombre;
    private String email;
    private String contrasena;
    private String telefono;
    private String direccion;
    private LocalDate fechaRegistro;


}
