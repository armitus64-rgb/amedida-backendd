package com.amedida.modelo;

/**
 * Representa una cuenta de cliente.
 * Guarda su última Medida personalizada para que, en su próxima
 * compra, el front-end pueda pre-llenar los sliders.
 */
public class Usuario {

    private Long id;
    private String nombre;
    private String email;
    private String passwordHash; // nunca se guarda la contraseña en texto plano
    private Medida medidaGuardada;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String email, String passwordHash) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public void guardarMedida(Medida medida) {
        this.medidaGuardada = medida;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Medida getMedidaGuardada() {
        return medidaGuardada;
    }
}
