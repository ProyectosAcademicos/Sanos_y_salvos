package com.notificaciones.service;

import com.notificaciones.dto.NotificacionDTO;
import com.notificaciones.factory.NotificacionStrategyFactory;
import com.notificaciones.model.Notificacion;
import com.notificaciones.repository.NotificacionRepository;
import com.notificaciones.strategy.NotificacionStrategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NotificacionServiceTest {

    @Mock
    private NotificacionRepository repository;

    @Mock
    private NotificacionStrategyFactory factory;

    @Mock
    private NotificacionStrategy strategy;

    @InjectMocks
    private NotificacionService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void debeProcesarEventoMatch() {

        when(factory.obtener("MATCH"))
                .thenReturn(strategy);

        Notificacion guardada = new Notificacion();
        guardada.setId(1L);
        guardada.setIdUsuario("11111111-1");

        when(repository.save(any(Notificacion.class)))
                .thenReturn(guardada);

        NotificacionDTO resultado =
                service.procesarEventoMatchEncontrado(
                        "11111111-1",
                        "1",
                        "Match encontrado"
                );

        assertNotNull(resultado);
        assertEquals(
                "11111111-1",
                resultado.getIdUsuario()
        );
    }

    @Test
    void debeMarcarComoLeida() {

        Notificacion n = new Notificacion();
        n.setId(1L);
        n.setLeido(false);

        when(repository.findById(1L))
                .thenReturn(Optional.of(n));

        when(repository.save(any(Notificacion.class)))
                .thenReturn(n);

        NotificacionDTO resultado =
                service.marcarComoLeida(1L);

        assertNotNull(resultado);
    }

    @Test
    void debeEliminarNotificacion() {

        service.eliminar(1L);

        verify(repository)
                .deleteById(1L);
    }
}