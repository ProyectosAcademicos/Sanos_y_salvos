package com.usuarios.service;

import com.usuarios.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.usuarios.repository.RepositoryUsuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.usuarios.strategy.PasswordStrategy;
import com.usuarios.factory.FactoryProvider;

import java.util.List;
import java.util.ArrayList;

@Service
public class ServiceUsuario {

    @Autowired
    private PasswordStrategy passwordStrategy; // Inyección de dependencia del codificador de contraseñas (hasheo)

    @Autowired
    private RepositoryUsuario repositoryUsuario; // Inyección de dependencia del repositorio

    @Autowired
    private FactoryProvider factoryProvider;

    public List<Usuario> getUsuarios(){ //traemos todos los usuarios
        return repositoryUsuario.findAll();
    }

    public Usuario crearUsuario(Usuario usuario) throws Exception { //creamos un nuevo usuario
        if (repositoryUsuario.findByRut(usuario.getRut()).isPresent()) {
            throw new Exception("El RUT ya está registrado.");
        }
        UsuarioFactory factory = factoryProvider.obtenerFactory(usuario.getTipoUsuario());
        Usuario nuevoUsuario = factory.crearUsuario(usuario);

        String passwordHash = passwordStrategy.encode(nuevoUsuario.getContrasena());
        nuevoUsuario.setContrasena(passwordHash);

        return repositoryUsuario.save(nuevoUsuario);
    }

    public Usuario actualizarUsuario(String rut, Usuario usuarioActualizado){ //actualizamos un usuario existente
        return repositoryUsuario.findByRut(rut).map(usuario -> {
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setEmail(usuarioActualizado.getEmail());
            String passwordHash = passwordStrategy.encode(usuarioActualizado.getContrasena());
            usuario.setContrasena(passwordHash);
            return repositoryUsuario.save(usuario);
        }).orElse(null);
    }

    public boolean eliminarUsuario(String rut) throws Exception{ //eliminamos un usuario por su rut
        if (repositoryUsuario.findByRut(rut).isEmpty()) {
            throw new Exception("El RUT no existe en el sistema.");
        }
        repositoryUsuario.deleteById(rut);
        return true;    

        // return repositoryUsuario.findByRut(rut).map(usuario -> {
        //     repositoryUsuario.delete(usuario);
        //     return true;
        // }).orElse(false);
    }

    public Usuario getUsuarioByRut(String rut) { //obtenemos un usuario por su rut
        return repositoryUsuario.findByRut(rut).orElse(null);
    }
}