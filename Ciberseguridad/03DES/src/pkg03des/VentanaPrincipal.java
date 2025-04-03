package pkg03des;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class VentanaPrincipal extends JFrame {
    private JTextField txtRutaArchivo;
    private JButton btnSeleccionar, btnCifrar, btnDescifrar;
    private File archivoSeleccionado;

    public VentanaPrincipal() {
        setTitle("Cifrado y Descifrado con DES");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        txtRutaArchivo = new JTextField(30);
        txtRutaArchivo.setEditable(false);
        
        btnSeleccionar = new JButton("Seleccionar Archivo");
        btnCifrar = new JButton("Cifrar");
        btnDescifrar = new JButton("Descifrar");

        add(txtRutaArchivo);
        add(btnSeleccionar);
        add(btnCifrar);
        add(btnDescifrar);

        btnSeleccionar.addActionListener(e -> seleccionarArchivo());
        btnCifrar.addActionListener(e -> cifrarArchivo());
        btnDescifrar.addActionListener(e -> descifrarArchivo());

        setVisible(true);
    }

    private void seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int opcion = fileChooser.showOpenDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            archivoSeleccionado = fileChooser.getSelectedFile();
            txtRutaArchivo.setText(archivoSeleccionado.getAbsolutePath());
        }
    }

    private void cifrarArchivo() {
        if (archivoSeleccionado != null) {
            try {
                CifradoDES cifrado = new CifradoDES();
                cifrado.cifrarArchivo(archivoSeleccionado.getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Archivo cifrado correctamente.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cifrar: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un archivo primero.");
        }
    }

    private void descifrarArchivo() {
        if (archivoSeleccionado != null) {
            try {
                CifradoDES cifrado = new CifradoDES();
                cifrado.descifrarArchivo(archivoSeleccionado.getAbsolutePath() + ".cifrado");
                JOptionPane.showMessageDialog(this, "Archivo descifrado correctamente.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al descifrar: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un archivo primero.");
        }
    }
}
