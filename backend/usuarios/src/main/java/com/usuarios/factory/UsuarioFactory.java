package com.usuarios.factory;

import com.usuarios.model.Usuario;

public abstract class UsuarioFactory {

    public abstract Usuario crearUsuario(Usuario usuario);

}