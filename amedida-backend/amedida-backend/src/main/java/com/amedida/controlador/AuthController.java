package com.amedida.controlador;

import com.amedida.modelo.Usuario;
import com.amedida.servicio.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // POST /api/auth/registro   body: { "nombre": "...", "email": "...", "password": "..." }
    @PostMapping("/registro")
    public Usuario registrar(@RequestBody RegistroRequest datos) {
        return usuarioService.registrar(datos.nombre(), datos.email(), datos.password());
    }

    // POST /api/auth/login      body: { "email": "...", "password": "..." }
    @PostMapping("/login")
    public Usuario login(@RequestBody LoginRequest datos) {
        return usuarioService.login(datos.email(), datos.password());
    }

    // "record" es una forma corta de Java para clases que solo cargan datos
    // (equivalente a lo que en el JSON del front-end llega como objeto plano).
    public record RegistroRequest(String nombre, String email, String password) {}
    public record LoginRequest(String email, String password) {}
}
