package com.amedida.modelo;

/**
 * Representa una opción de tela para un traje (ej: "Lana peinada").
 * costoExtra se suma al precio base cuando el cliente elige esta tela.
 */
public class Tela {

    private String nombre;
    private double costoExtra;

    public Tela() {
    }

    public Tela(String nombre, double costoExtra) {
        this.nombre = nombre;
        this.costoExtra = costoExtra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCostoExtra() {
        return costoExtra;
    }

    public void setCostoExtra(double costoExtra) {
        this.costoExtra = costoExtra;
    }
}
