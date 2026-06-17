package com.usuarios.factory;

import com.usuarios.model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminFactoryTest {

    @Test
    void debeCrearUsuarioAdmin() {

        AdminFactory factory = new AdminFactory();

        Usuario usuario = new Usuario();

        Usuario resultado = factory.crearUsuario(usuario);

        assertEquals("ADMIN", resultado.getTipoUsuario());
    }
}