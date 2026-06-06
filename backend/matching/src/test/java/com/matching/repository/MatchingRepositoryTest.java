package com.matching.repository;

import com.matching.model.Matching;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class MatchingRepositoryTest {

    @Autowired
    private MatchingRepository matchingRepository;

    @Test
    void debeGuardarMatch() {

        Matching match = new Matching();

        match.setIdMascota(1L);
        match.setIdUsuario(1L);
        match.setIdReporte(1L);
        match.setPorcentajeCompatibilidad(75.0);
        match.setFecha(LocalDateTime.now());

        Matching guardado = matchingRepository.save(match);

        assertNotNull(guardado);
        assertNotNull(guardado.getId());
    }

    @Test
    void debeBuscarPorMascota() {

        Matching match = new Matching();

        match.setIdMascota(1L);
        match.setIdUsuario(1L);
        match.setIdReporte(1L);
        match.setPorcentajeCompatibilidad(75.0);
        match.setFecha(LocalDateTime.now());

        matchingRepository.save(match);

        List<Matching> resultado =
                matchingRepository.findByIdMascota(1L);

        assertEquals(1, resultado.size());
    }

    @Test
    void debeBuscarPorUsuario() {

        Matching match = new Matching();

        match.setIdMascota(1L);
        match.setIdUsuario(99L);
        match.setIdReporte(1L);
        match.setPorcentajeCompatibilidad(75.0);
        match.setFecha(LocalDateTime.now());

        matchingRepository.save(match);

        List<Matching> resultado =
                matchingRepository.findByIdUsuario(99L);

        assertEquals(1, resultado.size());
    }
}