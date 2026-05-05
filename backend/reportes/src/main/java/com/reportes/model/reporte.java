package com.reportes.model;

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
public class reporte {
    
    @Id
    @Column(nullable = false, unique = true)
    private String idReporte;
    private String rutUsuario;
    private String idMascota;
    private String ubicacionPerdida; 
    private LocalDate fechaPerdida;
    private String descripcion;
    private String estado;
    private LocalDate fechaReporte;
    
    @PrePersist
        protected void onCreate() {
            this.fechaReporte = LocalDate.now(); // Establece la fecha actual al crear una nuevo reporte
        }

}