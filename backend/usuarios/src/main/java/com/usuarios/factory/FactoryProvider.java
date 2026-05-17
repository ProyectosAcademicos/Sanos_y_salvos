package com.usuarios.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactoryProvider {

    @Autowired
    private ClienteFactory clienteFactory;

    @Autowired
    private AdminFactory adminFactory;

    public UsuarioFactory obtenerFactory(String tipoUsuario) {

        switch (tipoUsuario.toUpperCase()) {

            case "ADMIN":
                return adminFactory;

            case "CLIENTE":
                return clienteFactory;

            default:
                throw new IllegalArgumentException("Tipo de usuario inválido");
        }
    }
}