package com.usuarios.factory;

import com.usuarios.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class AdminFactory extends UsuarioFactory {

    @Override
    public Usuario crearUsuario(Usuario usuario) {

        usuario.setTipoUsuario("ADMIN");

        return usuario;
    }
}