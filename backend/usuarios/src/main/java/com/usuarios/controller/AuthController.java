package com.usuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.usuarios.model.Usuario;
import com.usuarios.service.ServiceUsuario;
import com.usuarios.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ServiceUsuario serviceUsuario;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody Usuario request) {

        Usuario usuario = serviceUsuario.getUsuarioByRut(request.getRut());

        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // comparar password hasheado
        if (!passwordEncoder.matches(request.getContrasena(), usuario.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // generar token
        return jwtUtil.generateToken(usuario.getRut());
    }

    // REGISTRO 
    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario usuario) {

        // encriptar password antes de guardar
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));

        try {
            return serviceUsuario.crearUsuario(usuario);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}