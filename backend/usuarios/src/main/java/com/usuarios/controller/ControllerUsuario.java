package com.usuarios.controller;

import com.usuarios.model.Usuario;
import com.usuarios.service.ServiceUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class ControllerUsuario {

    @Autowired
    private ServiceUsuario serviceUsuario;
    
    @GetMapping
    public List<Usuario> getUsuarios(){
        return serviceUsuario.getUsuarios();
    }

    @GetMapping("/{rut}")
    public Usuario getUsuarioById(@PathVariable String rut){
        return serviceUsuario.getUsuarios().stream()
                .filter(usuario -> usuario.getRut().equals(rut))
                .findFirst()
                .orElse(null); // Usuario no encontrado
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        try {
            return serviceUsuario.crearUsuario(usuario);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{rut}")
    public Usuario actualizarUsuario(@PathVariable String rut, @RequestBody Usuario usuarioActualizado){
        return serviceUsuario.actualizarUsuario(rut, usuarioActualizado);
    }

    @DeleteMapping("/{rut}")
    public boolean eliminarUsuario(@PathVariable String rut){
        try{
            return serviceUsuario.eliminarUsuario(rut);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
