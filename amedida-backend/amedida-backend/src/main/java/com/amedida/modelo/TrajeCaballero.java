package com.amedida.modelo;

import java.util.List;

/**
 * Traje de caballero. Hereda todo de Traje y solo agrega
 * lo que le es propio: el tipo de corte (ej: "entallado", "clásico").
 */
public class TrajeCaballero extends Traje {

    private String corte;

    public TrajeCaballero(Long id, String nombre, String ocasion, double precioBase,
                           String descripcion, List<String> caracteristicas,
                           List<Color> colores, List<Tela> telas, String corte) {
        super(id, nombre, ocasion, precioBase, descripcion, caracteristicas, colores, telas);
        this.corte = corte;
    }

    @Override
    public String getCategoria() {
        return "caballero";
    }

    public String getCorte() {
        return corte;
    }
}
