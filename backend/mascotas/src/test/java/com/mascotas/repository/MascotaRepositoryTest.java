package com.mascotas.repository;

import com.mascotas.model.Mascota;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class MascotaRepositoryTest {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Test
    void debeGuardarMascota() {

        Mascota mascota = new Mascota();
        mascota.setNombre("Firulais");
        mascota.setTipo("Perro");
        mascota.setIdUsuario("11111111-1");

        Mascota guardada = mascotaRepository.save(mascota);

        assertNotNull(guardada);
        assertNotNull(guardada.getId());
    }

    @Test
    void debeBuscarPorIdUsuario() {

        Mascota mascota = new Mascota();
        mascota.setNombre("Firulais");
        mascota.setIdUsuario("11111111-1");

        mascotaRepository.save(mascota);

        List<Mascota> resultado =
                mascotaRepository.findByIdUsuario("11111111-1");

        assertEquals(1, resultado.size());
    }

    @Test
    void debeBuscarPorEstado() {

        Mascota mascota = new Mascota();
        mascota.setNombre("Firulais");
        mascota.setEstado("Perdido");
        mascota.setIdUsuario("11111111-1");

        mascotaRepository.save(mascota);

        List<Mascota> resultado =
                mascotaRepository.findByEstado("Perdido");

        assertEquals(1, resultado.size());
    }
}