package com.matching.service;

import com.matching.dto.MatchingDTO;
import com.matching.factory.MatchingStrategyFactory;
import com.matching.model.Matching;
import com.matching.repository.MatchingRepository;
import com.matching.strategy.MatchingFisicoStrategy;
import com.matching.strategy.MatchingStrategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MatchingServiceTest {

    @Mock
    private MatchingRepository matchingRepository;

    @Mock
    private MatchingStrategyFactory strategyFactory;

    @InjectMocks
    private MatchingService matchingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void debeCrearMatchCuandoCompatibilidadEsMayorA70() {

        MatchingStrategy strategy =
                new MatchingFisicoStrategy();

        when(strategyFactory.getEstrategia("fisicoStrategy"))
                .thenReturn(strategy);

        Matching matchGuardado = new Matching();

        matchGuardado.setId(1L);
        matchGuardado.setPorcentajeCompatibilidad(75.0);

        when(matchingRepository.save(any(Matching.class)))
                .thenReturn(matchGuardado);

        matchingService.procesarEventoReporteCreado(
                1L,
                1L,
                1L
        );

        verify(matchingRepository, times(1))
                .save(any(Matching.class));
    }

    @Test
    void debeObtenerMatchesPorUsuario() {

        Matching match = new Matching();

        match.setId(1L);
        match.setIdUsuario(99L);
        match.setIdMascota(1L);

        when(matchingRepository.findByIdUsuario(99L))
                .thenReturn(List.of(match));

        List<MatchingDTO> resultado =
                matchingService.obtenerMatchesPorUsuario(99L);

        assertEquals(1, resultado.size());
        assertEquals(99L,
                resultado.get(0).getIdUsuario());
    }
}