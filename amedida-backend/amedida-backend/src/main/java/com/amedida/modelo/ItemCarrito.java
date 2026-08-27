package com.amedida.modelo;

/**
 * Un renglón del carrito: qué traje, con qué color/tela/talla,
 * y en qué cantidad. Sabe calcular su propio subtotal — cada
 * objeto es responsable de su propia lógica (encapsulamiento).
 */
public class ItemCarrito {

    private Traje traje;
    private Color colorElegido;
    private Tela telaElegida;
    private SeleccionTalla talla;
    private int cantidad;

    public ItemCarrito(Traje traje, Color colorElegido, Tela telaElegida,
                        SeleccionTalla talla, int cantidad) {
        this.traje = traje;
        this.colorElegido = colorElegido;
        this.telaElegida = telaElegida;
        this.talla = talla;
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return traje.calcularPrecioConTela(telaElegida) * cantidad;
    }

    public Traje getTraje() {
        return traje;
    }

    public Color getColorElegido() {
        return colorElegido;
    }

    public Tela getTelaElegida() {
        return telaElegida;
    }

    public SeleccionTalla getTalla() {
        return talla;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
