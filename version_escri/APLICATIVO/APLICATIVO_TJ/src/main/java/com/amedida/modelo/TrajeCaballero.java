package com.amedida.modelo;

public class TrajeCaballero extends Traje {

    public TrajeCaballero(Long id, String nombre, String ocasion, double precio,
                           String descripcion, String color, String tela, boolean incluyeForro) {
        super(id, nombre, ocasion, precio, descripcion, color, tela, incluyeForro);
    }

    @Override
    public String getCategoria() {
        return "Caballero";
    }
}
