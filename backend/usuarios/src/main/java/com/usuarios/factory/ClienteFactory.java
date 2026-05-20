package com.usuarios.factory;

import com.usuarios.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ClienteFactory extends UsuarioFactory {

    @Override
    public Usuario crearUsuario(Usuario usuario) {

        usuario.setTipoUsuario("CLIENTE");

        return usuario;
    }
}