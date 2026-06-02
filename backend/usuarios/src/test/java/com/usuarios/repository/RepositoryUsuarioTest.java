package com.usuarios.repository;

import com.usuarios.model.Usuario;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")

public class RepositoryUsuarioTest {

    @Autowired
    private RepositoryUsuario repositoryUsuario;

    //prueba para guardar un usuario

    @Test
    void debeGuardarUsuario() {

        Usuario usuario = new Usuario();
        usuario.setRut("11111111-1");
        usuario.setNombre("Jonathan");

        Usuario guardado = repositoryUsuario.save(usuario);

        assertNotNull(guardado);
        assertEquals("11111111-1", guardado.getRut());
    }

    //prueba para buscar un usuario por su rut

    @Test
    void debeBuscarUsuarioPorRut() {

        Usuario usuario = new Usuario();
        usuario.setRut("11111111-1");
        usuario.setNombre("Jonathan");

        repositoryUsuario.save(usuario);

        Optional<Usuario> resultado =
                repositoryUsuario.findByRut("11111111-1");

        assertTrue(resultado.isPresent());
        assertEquals("Jonathan",
                resultado.get().getNombre());
    }

    //prueba para no encontrar un usuario por su rut

    @Test
    void noDebeEncontrarUsuarioPorRutInexistente() {

        Optional<Usuario> resultado =
                repositoryUsuario.findByRut("99999999-9");

        assertFalse(resultado.isPresent());
    }

    //prueba para listar todos los usuarios

    @Test
    void debeListarUsuarios() {

        Usuario usuario1 = new Usuario();
        usuario1.setRut("11111111-1");
        usuario1.setNombre("Jonathan");

        Usuario usuario2 = new Usuario();
        usuario2.setRut("22222222-2");
        usuario2.setNombre("Pedro");

        repositoryUsuario.save(usuario1);
        repositoryUsuario.save(usuario2);

        List<Usuario> usuarios =
                repositoryUsuario.findAll();

        assertEquals(2, usuarios.size());
    }

}
