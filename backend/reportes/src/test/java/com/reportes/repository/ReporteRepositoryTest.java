package com.reportes.repository;

import com.reportes.model.Reporte;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class ReporteRepositoryTest {

    @Autowired
    private RepositoryReportes repository;

    @Test
    void debeGuardarReporte() {

        Reporte reporte = new Reporte();
        reporte.setRutUsuario("11111111-1");
        reporte.setIdMascota("1");
        reporte.setUbicacionPerdida("Santiago");
        reporte.setFechaPerdida(LocalDate.now());
        reporte.setDescripcion("Mascota perdida");

        Reporte guardado = repository.save(reporte);

        assertNotNull(guardado);
        assertNotNull(guardado.getIdReporte());
    }

    @Test
    void debeBuscarReportesActivos() {

        Reporte reporte = new Reporte();
        reporte.setEstado("activo");
        reporte.setRutUsuario("11111111-1");

        repository.save(reporte);

        List<Reporte> resultado =
                repository.findByEstado("activo");

        assertEquals(1, resultado.size());
    }
}