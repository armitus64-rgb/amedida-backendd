package com.amedida.modelo;

/**
 * Encapsula las 4 medidas que el cliente ingresa con los sliders
 * en el front-end (pecho/busto, cintura, cadera, largo).
 *
 * Se usa tanto para guardar la medida elegida en un pedido,
 * como para guardar la "medida favorita" de un Usuario.
 */
public class Medida {

    private int pecho;
    private int cintura;
    private int cadera;
    private int largo;

    public Medida() {
    }

    public Medida(int pecho, int cintura, int cadera, int largo) {
        this.pecho = pecho;
        this.cintura = cintura;
        this.cadera = cadera;
        this.largo = largo;
    }

    public int getPecho() {
        return pecho;
    }

    public void setPecho(int pecho) {
        this.pecho = pecho;
    }

    public int getCintura() {
        return cintura;
    }

    public void setCintura(int cintura) {
        this.cintura = cintura;
    }

    public int getCadera() {
        return cadera;
    }

    public void setCadera(int cadera) {
        this.cadera = cadera;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    @Override
    public String toString() {
        return "Pecho:" + pecho + " Cintura:" + cintura + " Cadera:" + cadera + " Largo:" + largo;
    }
}
