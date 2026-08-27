package com.amedida.servicio;

import com.amedida.modelo.Medida;
import com.amedida.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Lógica de registro/login. En esta versión inicial NO se usa
 * encriptación real de contraseñas (eso se agrega después con
 * Spring Security + BCrypt) — el objetivo aquí es que entiendas
 * el flujo completo antes de sumar seguridad real.
 */
@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    public Usuario registrar(String nombre, String email, String password) {
        boolean yaExiste = usuarios.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
        if (yaExiste) {
            throw new IllegalArgumentException("Ya existe una cuenta con ese correo");
        }
        Usuario nuevo = new Usuario(contadorId.getAndIncrement(), nombre, email, password);
        usuarios.add(nuevo);
        return nuevo;
    }

    public Usuario login(String email, String password) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email) && u.getPasswordHash().equals(password))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Correo o contraseña incorrectos"));
    }

    public void guardarMedida(Long usuarioId, Medida medida) {
        Optional<Usuario> usuario = usuarios.stream().filter(u -> u.getId().equals(usuarioId)).findFirst();
        usuario.ifPresent(u -> u.guardarMedida(medida));
    }
}
