package com.mascotas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;
    private String tipo; // Perro, Gato, etc.
    private String raza;
    private Integer edad;
    private String tamaño;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    //Relacion con Microservicio Usuario: Guardamos el ID como String
    //para que coincida con el RUT o ID del usuario en el microservicio de usuarios
    @Column(name = "id_usuario", nullable = false)
    private String idUsuario;

    private String estado; // Encontrado y Perdido

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_publicacion")
    private Date fechaPublicacion;

    //Este método se ejecuta automáticamente antes de insertar un nuevo registro en la base de datos
    @PrePersist
    protected void onCreate() {
        this.fechaPublicacion = new Date(); // Establece la fecha actual al crear una nueva mascota
    }
}
