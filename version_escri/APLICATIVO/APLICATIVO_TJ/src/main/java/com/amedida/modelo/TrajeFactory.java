package com.amedida.modelo;

/**
 * Patrón "Factory" (fábrica): centraliza la decisión de qué subclase
 * de Traje crear. El formulario (PanelRegistro) no necesita saber que
 * existen TrajeCaballero/TrajeDama/TrajeInfantil — solo le dice a esta
 * clase "quiero un traje de categoría X" y ella entrega el objeto correcto.
 *
 * Esto es otro ejemplo de POO: la creación de objetos también se encapsula.
 */
public class TrajeFactory {

    public static Traje crear(String categoria, Long id, String nombre, String ocasion,
                               double precio, String descripcion, String color,
                               String tela, boolean incluyeForro) {
        return switch (categoria) {
            case "Caballero" -> new TrajeCaballero(id, nombre, ocasion, precio, descripcion, color, tela, incluyeForro);
            case "Dama" -> new TrajeDama(id, nombre, ocasion, precio, descripcion, color, tela, incluyeForro);
            case "Infantil" -> new TrajeInfantil(id, nombre, ocasion, precio, descripcion, color, tela, incluyeForro);
            default -> throw new IllegalArgumentException("Categoría desconocida: " + categoria);
        };
    }
}
