package com.amedida.vista;

import com.amedida.dao.TrajeRepositorio;
import com.amedida.modelo.Traje;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Vista que muestra todos los trajes guardados en la base de datos
 * (operación RETRIEVE del punto 5.c) dentro de una JTable.
 */
public class PanelCatalogo extends JPanel {

    private static final Color VERDE = new Color(31, 61, 43);
    private static final Color CREMA = new Color(250, 247, 242);

    private final TrajeRepositorio repositorio;
    private final DefaultTableModel modeloTabla;
    private final JTable tabla;

    public PanelCatalogo(TrajeRepositorio repositorio) {
        this.repositorio = repositorio;
        setLayout(new BorderLayout());
        setBackground(CREMA);
        setBorder(BorderFactory.createEmptyBorder(24, 30, 24, 30));

        JLabel titulo = new JLabel("Catálogo de trajes registrados");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setForeground(VERDE);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));
        add(titulo, BorderLayout.NORTH);

        String[] columnas = {"ID", "Nombre", "Categoría", "Ocasión", "Precio", "Color", "Tela", "Forro"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tabla de solo lectura
            }
        };
        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(26);
        tabla.getTableHeader().setBackground(VERDE);
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setSelectionBackground(new Color(235, 211, 213));

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JButton botonActualizar = new JButton("Actualizar lista");
        botonActualizar.setBackground(VERDE);
        botonActualizar.setForeground(Color.WHITE);
        botonActualizar.setFocusPainted(false);
        botonActualizar.setBorder(BorderFactory.createEmptyBorder(10, 22, 10, 22));
        botonActualizar.addActionListener(e -> cargarDatos());

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.setBackground(CREMA);
        panelBoton.add(botonActualizar);
        add(panelBoton, BorderLayout.SOUTH);

        cargarDatos();
    }

    /** Consulta la base de datos y refresca las filas de la tabla. */
    public void cargarDatos() {
        try {
            List<Traje> trajes = repositorio.listarTodos();
            modeloTabla.setRowCount(0); // limpia la tabla antes de recargar
            for (Traje t : trajes) {
                modeloTabla.addRow(new Object[]{
                        t.getId(),
                        t.getNombre(),
                        t.getCategoria(),
                        t.getOcasion(),
                        String.format("$ %,.0f", t.getPrecio()),
                        t.getColor(),
                        t.getTela(),
                        t.isIncluyeForro() ? "Sí" : "No"
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al consultar los datos: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
