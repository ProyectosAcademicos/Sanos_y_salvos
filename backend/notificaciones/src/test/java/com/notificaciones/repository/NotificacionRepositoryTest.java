package com.notificaciones.repository;

import com.notificaciones.model.Notificacion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class NotificacionRepositoryTest {

    @Autowired
    private NotificacionRepository repository;

    @Test
    void debeGuardarNotificacion() {

        Notificacion n = new Notificacion();
        n.setIdUsuario("11111111-1");
        n.setIdMatch("1");
        n.setTipo("MATCH");
        n.setMensaje("Match encontrado");
        n.setLeido(false);

        Notificacion guardada = repository.save(n);

        assertNotNull(guardada.getId());
    }

    @Test
    void debeBuscarPorUsuario() {

        Notificacion n = new Notificacion();
        n.setIdUsuario("11111111-1");
        n.setIdMatch("1");
        n.setTipo("MATCH");
        n.setMensaje("Match encontrado");
        n.setLeido(false);

        repository.save(n);

        List<Notificacion> resultado =
                repository.findByIdUsuario("11111111-1");

        assertEquals(1, resultado.size());
    }
}