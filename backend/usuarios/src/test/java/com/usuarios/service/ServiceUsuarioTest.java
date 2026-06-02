package com.usuarios.service;

import com.usuarios.model.Usuario;
import com.usuarios.repository.RepositoryUsuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServiceUsuarioTest {

    @Mock
    private RepositoryUsuario repositoryUsuario;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ServiceUsuario serviceUsuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //Test para crear un usuario exitosamente

    @Test
    void debeCrearUsuario() throws Exception {
    
        Usuario usuario = new Usuario();
        usuario.setRut("12345678-9");
        usuario.setContrasena("1234");
    
        when(repositoryUsuario.findByRut("12345678-9"))
                .thenReturn(Optional.empty());
    
        when(passwordEncoder.encode("1234"))
                .thenReturn("HASH123");
    
        when(repositoryUsuario.save(any(Usuario.class)))
                .thenReturn(usuario);
    
        Usuario resultado = serviceUsuario.crearUsuario(usuario);
    
        assertNotNull(resultado);
    
        verify(repositoryUsuario, times(1))
                .save(any(Usuario.class));
    }

    //Test que es: no debe crear un usuario si el RUT ya existe en el sistema

    @Test
    void noDebeCrearUsuarioSiRutExiste() {

        Usuario usuario = new Usuario();
        usuario.setRut("12345678-9");

        when(repositoryUsuario.findByRut("12345678-9"))
                .thenReturn(Optional.of(usuario));

        Exception exception = assertThrows(
                Exception.class,
                () -> serviceUsuario.crearUsuario(usuario)
        );

        assertEquals(
                "El RUT ya está registrado en el sistema.",
                exception.getMessage()
        );
    }

    //Obtener usuario por RUT

    @Test
    void debeObtenerUsuarioPorRut() {

        Usuario usuario = new Usuario();
        usuario.setRut("12345678-9");

        when(repositoryUsuario.findByRut("12345678-9"))
                .thenReturn(Optional.of(usuario));

        Usuario resultado =
                serviceUsuario.getUsuarioByRut("12345678-9");

        assertNotNull(resultado);
        assertEquals("12345678-9", resultado.getRut());
    }

    //Eliminar usuario por RUT

    @Test
    void debeEliminarUsuario() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setRut("12345678-9");

        when(repositoryUsuario.findByRut("12345678-9"))
                .thenReturn(Optional.of(usuario));

        boolean resultado =
                serviceUsuario.eliminarUsuario("12345678-9");

        assertTrue(resultado);

        verify(repositoryUsuario)
                .deleteById("12345678-9");
    }

}
