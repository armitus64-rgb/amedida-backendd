package com.amedida.modelo;

import java.util.List;

/**
 * Traje de dama. Hereda todo de Traje y agrega lo propio:
 * el tipo de silueta (ej: "entallada", "oversize").
 */
public class TrajeDama extends Traje {

    private String silueta;

    public TrajeDama(Long id, String nombre, String ocasion, double precioBase,
                      String descripcion, List<String> caracteristicas,
                      List<Color> colores, List<Tela> telas, String silueta) {
        super(id, nombre, ocasion, precioBase, descripcion, caracteristicas, colores, telas);
        this.silueta = silueta;
    }

    @Override
    public String getCategoria() {
        return "dama";
    }

    public String getSilueta() {
        return silueta;
    }
}
