package com.usuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.usuarios.model.Usuario;
import com.usuarios.service.ServiceUsuario;
import java.util.Map;
import com.usuarios.security.JwtUtil;

@RestController
@RequestMapping("/auth")
// @CrossOrigin({"http://localhost:5173", "http://localhost:3000"})
public class AuthController {

    @Autowired
    private ServiceUsuario serviceUsuario;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // LOGIN
   @PostMapping("/login")
    public Map<String, String> login(@RequestBody Usuario request) {

        System.out.println("===============");
        System.out.println("LOGIN VERSION JUNIO 2026");
        System.out.println("RUT recibido: " + request.getRut());
        System.out.println("Password recibida: " + request.getContrasena());

        Usuario usuario = serviceUsuario.getUsuarioByRut(request.getRut());

        System.out.println("Password BD: " + usuario.getContrasena());

        System.out.println(
            "Hash generado para 12345: "
            + passwordEncoder.encode("12345")
        );

        System.out.println(
            "Prueba directa: " +
            passwordEncoder.matches(
                "12345",
                "$2a$10$WeTgSGzQ8k.ViCeYWp1dnOC9BR2cEDo6bMaqrdAyZ3V0NG0Ao7j9S"
            )
        );

        boolean coincide =
                passwordEncoder.matches(
                        request.getContrasena(),
                        usuario.getContrasena());

        System.out.println("MATCHES = " + coincide);
        System.out.println("===============");

        if (!coincide) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtil.generateToken(usuario.getRut());

        return Map.of("token", token);
    }
    // REGISTRO 
    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario usuario) {

        try {
            return serviceUsuario.crearUsuario(usuario);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}