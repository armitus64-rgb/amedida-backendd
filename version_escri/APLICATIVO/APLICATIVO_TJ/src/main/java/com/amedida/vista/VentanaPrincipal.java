package com.amedida.vista;

import com.amedida.dao.TrajeRepositorio;

import javax.swing.*;
import java.awt.*;

/**
 * JFrame principal de la aplicación. Organiza los dos paneles
 * (Registro y Catálogo) en pestañas (JTabbedPane).
 *
 * Recibe un TrajeRepositorio ya armado desde Main — no le importa
 * si por dentro guarda en memoria o en una base de datos real.
 */
public class VentanaPrincipal extends JFrame {

    private static final Color VERDE = new Color(31, 61, 43);
    private static final Color CREMA = new Color(250, 247, 242);

    private final TrajeRepositorio repositorio;

    public VentanaPrincipal(TrajeRepositorio repositorio) {
        super("A Medida — Gestión de Trajes");
        this.repositorio = repositorio;
        configurarVentana();
        construirInterfaz();
    }

    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 560);
        setLocationRelativeTo(null); // centra la ventana en la pantalla
        getContentPane().setBackground(CREMA);
    }

    private void construirInterfaz() {
        // El panel de catálogo se crea primero para poder pasarle
        // su método cargarDatos() como "callback" al panel de registro:
        // así, cuando se guarda un traje nuevo, la tabla se refresca sola.
        PanelCatalogo panelCatalogo = new PanelCatalogo(repositorio);
        PanelRegistro panelRegistro = new PanelRegistro(repositorio, panelCatalogo::cargarDatos);

        JTabbedPane pestañas = new JTabbedPane();
        pestañas.setFont(new Font("SansSerif", Font.PLAIN, 14));
        pestañas.addTab("  Registrar traje  ", panelRegistro);
        pestañas.addTab("  Catálogo  ", panelCatalogo);

        // Refresca la tabla también cada vez que el usuario entra a esa pestaña
        pestañas.addChangeListener(e -> {
            if (pestañas.getSelectedComponent() == panelCatalogo) {
                panelCatalogo.cargarDatos();
            }
        });

        add(pestañas);
    }
}
