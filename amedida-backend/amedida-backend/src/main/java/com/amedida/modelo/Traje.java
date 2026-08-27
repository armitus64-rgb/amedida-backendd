package com.amedida.modelo;

import java.util.List;

/**
 * Clase abstracta: define TODO lo que un traje tiene en común,
 * sin importar si es de dama o de caballero.
 *
 * Es "abstracta" porque nunca se crea un Traje "genérico" directamente
 * (new Traje(...) no está permitido) — siempre se crea una subclase
 * concreta: TrajeCaballero o TrajeDama. Esto es una decisión de diseño:
 * obliga a que cada traje declare explícitamente su categoría.
 */
public abstract class Traje {

    private Long id;
    private String nombre;
    private String ocasion;      // ejecutivo, ceremonia, casual
    private double precioBase;
    private String descripcion;
    private List<String> caracteristicas;
    private List<Color> colores;
    private List<Tela> telas;

    protected Traje(Long id, String nombre, String ocasion, double precioBase,
                     String descripcion, List<String> caracteristicas,
                     List<Color> colores, List<Tela> telas) {
        this.id = id;
        this.nombre = nombre;
        this.ocasion = ocasion;
        this.precioBase = precioBase;
        this.descripcion = descripcion;
        this.caracteristicas = caracteristicas;
        this.colores = colores;
        this.telas = telas;
    }

    /**
     * Método abstracto: cada subclase está OBLIGADA a implementarlo.
     * Esto es polimorfismo — el front-end pide "categoria" y cada
     * subclase responde algo distinto sin que el resto del código
     * necesite un if/else para decidirlo.
     */
    public abstract String getCategoria();

    /**
     * Calcula el precio final sumando el costo extra de la tela elegida.
     * Vive aquí (y no repetida en cada subclase) porque el cálculo
     * es igual para dama y caballero.
     */
    public double calcularPrecioConTela(Tela telaElegida) {
        return precioBase + telaElegida.getCostoExtra();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getOcasion() {
        return ocasion;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<String> getCaracteristicas() {
        return caracteristicas;
    }

    public List<Color> getColores() {
        return colores;
    }

    public List<Tela> getTelas() {
        return telas;
    }
}
