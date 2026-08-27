package com.amedida.modelo;

/**
 * Representa la decisión de talla que tomó el cliente al comprar:
 * O bien eligió una TallaEstandar, o bien ingresó una Medida personalizada.
 * Nunca ambas a la vez.
 *
 * Encapsular esta regla aquí (en vez de dejar que el controlador
 * decida) es un ejemplo de encapsulamiento: la clase se protege
 * a sí misma de quedar en un estado inválido.
 */
public class SeleccionTalla {

    private TallaEstandar tallaEstandar; // null si es personalizada
    private Medida medidaPersonalizada;  // null si es talla estándar

    public SeleccionTalla() {
    }

    public static SeleccionTalla porTallaEstandar(TallaEstandar talla) {
        SeleccionTalla seleccion = new SeleccionTalla();
        seleccion.tallaEstandar = talla;
        return seleccion;
    }

    public static SeleccionTalla porMedidaPersonalizada(Medida medida) {
        SeleccionTalla seleccion = new SeleccionTalla();
        seleccion.medidaPersonalizada = medida;
        return seleccion;
    }

    public boolean esPersonalizada() {
        return medidaPersonalizada != null;
    }

    public String descripcion() {
        if (esPersonalizada()) {
            return "Medida personalizada (" + medidaPersonalizada + ")";
        }
        return "Talla " + tallaEstandar;
    }

    public TallaEstandar getTallaEstandar() {
        return tallaEstandar;
    }

    public void setTallaEstandar(TallaEstandar tallaEstandar) {
        this.tallaEstandar = tallaEstandar;
    }

    public Medida getMedidaPersonalizada() {
        return medidaPersonalizada;
    }

    public void setMedidaPersonalizada(Medida medidaPersonalizada) {
        this.medidaPersonalizada = medidaPersonalizada;
    }
}
