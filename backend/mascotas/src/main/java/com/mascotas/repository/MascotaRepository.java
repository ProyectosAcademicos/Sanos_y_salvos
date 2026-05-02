package com.mascotas.repository;

import com.mascotas.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    // Método extra para buscar todas las mascotas por un mismo dueño (idUsuario)
    List<Mascota> findByIdUsuario(String idUsuario);

    // Método para buscar por estado (ej: Buscar todas las mascotas perdidas)
    List<Mascota> findByEstado(String estado);
}
