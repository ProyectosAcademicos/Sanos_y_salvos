package com.usuarios.factory;

import com.usuarios.model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteFactoryTest {

    @Test
    void debeCrearUsuarioCliente() {

        ClienteFactory factory = new ClienteFactory();

        Usuario usuario = new Usuario();

        Usuario resultado = factory.crearUsuario(usuario);

        assertEquals("CLIENTE", resultado.getTipoUsuario());
    }
}