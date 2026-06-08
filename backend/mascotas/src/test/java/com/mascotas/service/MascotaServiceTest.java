package com.mascotas.service;

import com.mascotas.dto.MascotaDTO;
import com.mascotas.factory.EstadoMascotaFactory;
import com.mascotas.model.Mascota;
import com.mascotas.repository.MascotaRepository;
import com.mascotas.strategy.EstadoMascotaStrategy;
import com.mascotas.strategy.EstadoPerdidoStrategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import com.mascotas.config.JwtUtil;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MascotaServiceTest {

    @Mock
    private MascotaRepository mascotaRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private EstadoMascotaFactory estadoFactory;

    @InjectMocks
    private MascotaService mascotaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void debeGuardarMascota() {
    
        Mascota mascota = new Mascota();
        mascota.setId(1L);
        mascota.setNombre("Firulais");
        mascota.setIdUsuario("11111111-1");
    
        MascotaDTO dto = new MascotaDTO();
        dto.setNombre("Firulais");
    
        // Mock del JWT
        when(jwtUtil.extractUsername(anyString()))
                .thenReturn("11111111-1");
    
        // Mock del repositorio
        when(mascotaRepository.save(any(Mascota.class)))
                .thenReturn(mascota);
    
        MascotaDTO resultado =
                mascotaService.guardar(
                        dto,
                        "Bearer token-falso"
                );
    
        assertNotNull(resultado);
        assertEquals("Firulais", resultado.getNombre());
    }

    @Test
    void debeObtenerMascotaPorId() {

        Mascota mascota = new Mascota();
        mascota.setId(1L);
        mascota.setNombre("Firulais");
        mascota.setIdUsuario("11111111-1");

        when(mascotaRepository.findById(1L))
                .thenReturn(Optional.of(mascota));

        Optional<MascotaDTO> resultado =
                mascotaService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Firulais",
                resultado.get().getNombre());
    }

    @Test
    void debeEliminarMascota() {

        mascotaService.eliminar(1L);

        verify(mascotaRepository)
                .deleteById(1L);
    }

    @Test
    void debeActualizarEstadoAPerdido() {

        Mascota mascota = new Mascota();
        mascota.setId(1L);
        mascota.setNombre("Firulais");

        EstadoMascotaStrategy strategy =
                new EstadoPerdidoStrategy();

        when(mascotaRepository.findById(1L))
                .thenReturn(Optional.of(mascota));

        when(estadoFactory.obtener("Perdido"))
                .thenReturn(strategy);

        when(mascotaRepository.save(any(Mascota.class)))
                .thenReturn(mascota);

        MascotaDTO resultado =
                mascotaService.actualizarEstado(
                        1L,
                        "Perdido"
                );

        assertEquals(
                "Perdido",
                resultado.getEstado()
        );
    }
}