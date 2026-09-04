package com.amedida.vista;

import com.amedida.dao.TrajeRepositorio;
import com.amedida.modelo.Traje;
import com.amedida.modelo.TrajeFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Vista con el FORMULARIO para registrar un traje nuevo (punto 5.a del
 * requisito). Al presionar "Guardar", inserta el registro en la base
 * de datos (punto 5.c) y avisa al panel de catálogo que debe refrescarse.
 *
 * Componentes GUI usados aquí: JTextField, JComboBox, JCheckBox,
 * JTextArea, JButton, JLabel — ya son más de 6.
 */
public class PanelRegistro extends JPanel {

    // Paleta de la marca "A Medida"
    private static final Color VERDE = new Color(31, 61, 43);
    private static final Color ROSA = new Color(201, 123, 132);
    private static final Color CREMA = new Color(250, 247, 242);
    private static final Color LINEA = new Color(222, 209, 193);

    private final JTextField campoNombre = new JTextField();
    private final JComboBox<String> comboCategoria = new JComboBox<>(new String[]{"Caballero", "Dama", "Infantil"});
    private final JComboBox<String> comboOcasion = new JComboBox<>(new String[]{"Ejecutivo", "Ceremonia", "Casual"});
    private final JTextField campoPrecio = new JTextField();
    private final JTextField campoColor = new JTextField();
    private final JComboBox<String> comboTela = new JComboBox<>(new String[]{"Lana peinada", "Lino", "Crepé", "Terciopelo", "Oxford"});
    private final JCheckBox checkForro = new JCheckBox("Incluye forro interior");
    private final JTextArea campoDescripcion = new JTextArea(4, 20);

    private final TrajeRepositorio repositorio;
    private final Runnable alGuardarExitoso; // callback para refrescar la tabla del otro panel

    public PanelRegistro(TrajeRepositorio repositorio, Runnable alGuardarExitoso) {
        this.repositorio = repositorio;
        this.alGuardarExitoso = alGuardarExitoso;
        construirInterfaz();
    }

    private void construirInterfaz() {
        setLayout(new BorderLayout());
        setBackground(CREMA);
        setBorder(BorderFactory.createEmptyBorder(24, 30, 24, 30));

        JLabel titulo = new JLabel("Registrar nuevo traje");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setForeground(VERDE);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(CREMA);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;

        int fila = 0;
        agregarCampo(formulario, c, fila++, "Nombre del traje:", campoNombre);
        agregarCampo(formulario, c, fila++, "Categoría:", comboCategoria);
        agregarCampo(formulario, c, fila++, "Ocasión:", comboOcasion);
        agregarCampo(formulario, c, fila++, "Precio (COP):", campoPrecio);
        agregarCampo(formulario, c, fila++, "Color principal:", campoColor);
        agregarCampo(formulario, c, fila++, "Tela:", comboTela);

        c.gridx = 0; c.gridy = fila; c.gridwidth = 1;
        formulario.add(new JLabel("Descripción:"), c);
        c.gridx = 1; c.gridwidth = 2;
        campoDescripcion.setLineWrap(true);
        campoDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDescripcion = new JScrollPane(campoDescripcion);
        formulario.add(scrollDescripcion, c);
        fila++;

        c.gridx = 1; c.gridy = fila; c.gridwidth = 2;
        checkForro.setBackground(CREMA);
        formulario.add(checkForro, c);
        fila++;

        add(formulario, BorderLayout.CENTER);

        JButton botonGuardar = new JButton("Guardar traje");
        estilizarBoton(botonGuardar);
        botonGuardar.addActionListener(e -> guardarTraje());

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.setBackground(CREMA);
        panelBoton.add(botonGuardar);
        add(panelBoton, BorderLayout.SOUTH);
    }

    private void agregarCampo(JPanel formulario, GridBagConstraints c, int fila, String etiqueta, JComponent campo) {
        c.gridy = fila; c.gridx = 0; c.gridwidth = 1;
        JLabel label = new JLabel(etiqueta);
        formulario.add(label, c);
        c.gridx = 1; c.gridwidth = 2;
        formulario.add(campo, c);
    }

    private void estilizarBoton(JButton boton) {
        boton.setBackground(VERDE);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("SansSerif", Font.BOLD, 13));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 22, 10, 22));
    }

    /** Valida el formulario, arma el objeto Traje (vía la fábrica) y lo inserta en la BD. */
    private void guardarTraje() {
        String nombre = campoNombre.getText().trim();
        String precioTexto = campoPrecio.getText().trim();

        if (nombre.isEmpty() || precioTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre y precio son obligatorios.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioTexto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número (ej: 480000).", "Precio inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String categoria = (String) comboCategoria.getSelectedItem();
        Traje nuevoTraje = TrajeFactory.crear(
                categoria, null, nombre,
                (String) comboOcasion.getSelectedItem(),
                precio,
                campoDescripcion.getText().trim(),
                campoColor.getText().trim(),
                (String) comboTela.getSelectedItem(),
                checkForro.isSelected()
        );

        try {
            repositorio.insertar(nuevoTraje);
            JOptionPane.showMessageDialog(this, "Traje guardado correctamente.", "Listo", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
            alGuardarExitoso.run(); // refresca la tabla del catálogo
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        campoNombre.setText("");
        campoPrecio.setText("");
        campoColor.setText("");
        campoDescripcion.setText("");
        checkForro.setSelected(false);
        comboCategoria.setSelectedIndex(0);
        comboOcasion.setSelectedIndex(0);
        comboTela.setSelectedIndex(0);
    }
}
