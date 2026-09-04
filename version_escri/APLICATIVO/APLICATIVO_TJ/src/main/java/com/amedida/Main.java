package com.amedida;

import com.amedida.dao.RepositorioEnMemoria;
import com.amedida.dao.TrajeRepositorio;
import com.amedida.vista.VentanaPrincipal;

import javax.swing.*;

/**
 * Punto de entrada de la aplicación.
 *
 * UNICO LUGAR que hay que tocar cuando la base de datos este lista:
 * cambiar la linea de abajo por la implementacion real, por ejemplo:
 *
 *     TrajeRepositorio repositorio = new RepositorioSQLite();
 *
 * Ningun otro archivo (ni PanelRegistro, ni PanelCatalogo, ni
 * VentanaPrincipal) necesita modificarse, porque todos hablan
 * con la interfaz TrajeRepositorio, no con esta clase en concreto.
 */
public class Main {
    public static void main(String[] args) {

        TrajeRepositorio repositorio = new RepositorioEnMemoria();

        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal(repositorio);
            ventana.setVisible(true);
        });
    }
}
