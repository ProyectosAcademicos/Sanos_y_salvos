package com.usuarios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;


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
    private String tipoUsuario;
    private LocalDate fechaRegistro;
    
    @PrePersist
        protected void onCreate() {
            this.fechaRegistro = LocalDate.now(); // Establece la fecha actual al crear una nuevo usuario
    }

}