package com.usuarios.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class FactoryProviderTest {

    private FactoryProvider factoryProvider;

    @BeforeEach
    void setUp() throws Exception {

        factoryProvider = new FactoryProvider();

        Field clienteField =
                FactoryProvider.class.getDeclaredField("clienteFactory");

        clienteField.setAccessible(true);
        clienteField.set(factoryProvider, new ClienteFactory());

        Field adminField =
                FactoryProvider.class.getDeclaredField("adminFactory");

        adminField.setAccessible(true);
        adminField.set(factoryProvider, new AdminFactory());
    }

    @Test
    void debeRetornarAdminFactory() {

        UsuarioFactory factory =
                factoryProvider.obtenerFactory("ADMIN");

        assertTrue(factory instanceof AdminFactory);
    }

    @Test
    void debeRetornarClienteFactory() {

        UsuarioFactory factory =
                factoryProvider.obtenerFactory("CLIENTE");

        assertTrue(factory instanceof ClienteFactory);
    }

}