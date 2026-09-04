package com.amedida.modelo;

/**
 * Clase abstracta: agrupa lo que TODO traje tiene en común
 * (lo que se pide en el formulario de registro). Igual que en
 * la versión web, nunca se instancia directamente — solo a
 * través de TrajeCaballero, TrajeDama o TrajeInfantil.
 */
public abstract class Traje {

    private Long id;
    private String nombre;
    private String ocasion;
    private double precio;
    private String descripcion;
    private String color;
    private String tela;
    private boolean incluyeForro;

    protected Traje(Long id, String nombre, String ocasion, double precio,
                     String descripcion, String color, String tela, boolean incluyeForro) {
        this.id = id;
        this.nombre = nombre;
        this.ocasion = ocasion;
        this.precio = precio;
        this.descripcion = descripcion;
        this.color = color;
        this.tela = tela;
        this.incluyeForro = incluyeForro;
    }

    /** Polimorfismo: cada subclase responde su propia categoría. */
    public abstract String getCategoria();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public String getOcasion() { return ocasion; }
    public double getPrecio() { return precio; }
    public String getDescripcion() { return descripcion; }
    public String getColor() { return color; }
    public String getTela() { return tela; }
    public boolean isIncluyeForro() { return incluyeForro; }
}
