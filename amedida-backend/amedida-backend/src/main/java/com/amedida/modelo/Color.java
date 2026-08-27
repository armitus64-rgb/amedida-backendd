package com.amedida.modelo;

/**
 * Representa una variante de color disponible para un traje.
 * Es un objeto "simple" (sin comportamiento complejo), pensado
 * para encapsular datos que siempre viajan juntos: el código
 * hexadecimal y el nombre visible.
 */
public class Color {

    private String hex;
    private String nombre;

    public Color() {
        // Constructor vacío requerido por Spring/Jackson al convertir JSON -> objeto
    }

    public Color(String hex, String nombre) {
        this.hex = hex;
        this.nombre = nombre;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
