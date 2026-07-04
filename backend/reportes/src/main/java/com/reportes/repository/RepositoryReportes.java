package com.reportes.repository;

import com.reportes.model.Reporte;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

@Repository
public interface RepositoryReportes
        extends JpaRepository<Reporte,String>{

    Optional<Reporte> findById(String idReporte);

    List<Reporte> findByEstado(String estado);

    List<Reporte> findByTipoReporteAndEstado(
            String tipoReporte,
            String estado
    );

    List<Reporte> findByTipoReporte(String tipoReporte);

}
