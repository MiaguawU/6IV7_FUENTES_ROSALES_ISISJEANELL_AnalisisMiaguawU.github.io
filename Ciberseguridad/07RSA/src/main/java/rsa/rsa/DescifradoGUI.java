/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rsa.rsa;

/**
 *
 * @author miagu
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import javax.swing.*;
import java.awt.*;

/**
 * Interfaz gráfica para descifrar números utilizando el algoritmo RSA.
 * Recibe el mensaje cifrado, la clave privada 'd' y el módulo 'n'.
 */
public class DescifradoGUI extends JFrame {

    private BigInteger mensajeCifradoOriginal; // El mensaje cifrado que se recibe
    private BigInteger claveDOriginal; // La clave privada 'd' que se recibe
    private BigInteger moduloNOriginal; // El módulo 'n' que se recibe

    // Componentes de la GUI
    private JTextField txtMensajeCifrado;
    private JTextField txtClaveD;
    private JTextField txtModuloN; // Para mostrar N
    private JButton btnDescifrar;
    private JTextField txtDescifradoResult;

    /**
     * Constructor de DescifradoGUI.
     * @param mensajeCifrado El mensaje BigInteger cifrado.
     * @param claveD La clave privada 'd' (BigInteger).
     * @param moduloN El módulo 'n' (BigInteger).
     */
    public DescifradoGUI(BigInteger mensajeCifrado, BigInteger claveD, BigInteger moduloN) {
        super("Descifrado RSA"); // Título de la ventana
        this.mensajeCifradoOriginal = mensajeCifrado;
        this.claveDOriginal = claveD;
        this.moduloNOriginal = moduloN;

        initComponents(); // Inicializa los componentes de la GUI
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana
        setSize(400, 400); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true); // Hace visible la ventana
    }

    /**
     * Inicializa y organiza los componentes de la interfaz de usuario.
     */
    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout()); // Usamos GridBagLayout
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel titleLabel = new JLabel("Descifrar Mensaje RSA", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, gbc);

        // Fila 1: Mensaje Cifrado
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Mensaje Cifrado:"), gbc);
        gbc.gridx = 1;
        txtMensajeCifrado = new JTextField(20);
        txtMensajeCifrado.setText(mensajeCifradoOriginal != null ? mensajeCifradoOriginal.toString() : "");
        txtMensajeCifrado.setEditable(false); // No se debe editar el mensaje cifrado recibido
        panel.add(txtMensajeCifrado, gbc);

        // Fila 2: Clave 'd'
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Clave 'd':"), gbc);
        gbc.gridx = 1;
        txtClaveD = new JTextField(20);
        txtClaveD.setText(claveDOriginal != null ? claveDOriginal.toString() : "");
        txtClaveD.setEditable(false); // Se muestra, pero no se edita
        panel.add(txtClaveD, gbc);

        // Fila 3: Módulo 'n'
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Módulo 'n':"), gbc);
        gbc.gridx = 1;
        txtModuloN = new JTextField(20);
        txtModuloN.setText(moduloNOriginal != null ? moduloNOriginal.toString() : "");
        txtModuloN.setEditable(false); // Se muestra, pero no se edita
        panel.add(txtModuloN, gbc);

        // Fila 4: Botón Descifrar
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        btnDescifrar = new JButton("Descifrar Mensaje");
        panel.add(btnDescifrar, gbc);

        // Fila 5: Resultado del descifrado
        gbc.gridy = 5;
        panel.add(new JLabel("Mensaje Descifrado:"), gbc);
        gbc.gridy = 6;
        txtDescifradoResult = new JTextField(20);
        txtDescifradoResult.setEditable(false);
        panel.add(txtDescifradoResult, gbc);

        add(panel);

        // Listener para el botón Descifrar
        btnDescifrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                descifrarMensaje();
            }
        });
    }

    /**
     * Realiza el proceso de descifrado con los valores recibidos.
     */
    private void descifrarMensaje() {
        if (mensajeCifradoOriginal == null || claveDOriginal == null || moduloNOriginal == null) {
            JOptionPane.showMessageDialog(this, "No se han proporcionado los datos completos para descifrar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Se necesita una instancia de RSAAlgoritmo para usar el método descifrar.
        // Como solo necesitamos 'd' y 'n' para descifrar, podemos crear una instancia temporal
        // o pasar estos valores de forma más directa al método de descifrado si estuviera adaptado.
        // Para simplificar, la clase RSAAlgoritmo se encargará de esto internamente
        // al pasarle los datos necesarios.

        // Dado que el método descifrar en RSAAlgoritmo es un método de instancia
        // y usa las variables internas (d y n), debemos "inyectar" esos valores
        // o modificar RSAAlgoritmo para que el método descifrar acepte d y n como parámetros.
        // Para la simplicidad de esta implementación, creamos una instancia
        // y "simulamos" la configuración de d y n.

        // Una solución más limpia sería tener un método descifrar estático o que acepte
        // los parámetros d y n directamente sin depender del estado interno.
        // Por ahora, crearemos una instancia temporal y usaremos los métodos "setters" imaginarios
        // o pasaremos los valores necesarios si el método de descifrado lo permitiera.

        // Simplificando: Podemos crear una instancia de RSAAlgoritmo y usar los valores originales directamente en modPow.
        // No necesitamos que la instancia de RSAAlgoritmo haya generado las claves internamente para descifrar,
        // solo el 'd' y 'n' correctos.

        // BigInteger descifrado = mensajeCifradoOriginal.modPow(claveDOriginal, moduloNOriginal);

        // Para ser consistentes con la clase RSAAlgoritmo y su método 'descifrar':
        RSAAlgoritmo tempRsa = new RSAAlgoritmo(0); // TamPrimo no importa para descifrar si ya tenemos d y n
        // Aquí estaríamos "engañando" a RSAAlgoritmo porque sus 'd' y 'n' internos no se han generado.
        // La forma correcta es que el método descifrar reciba 'd' y 'n' como parámetros
        // o que los 'setters' de d y n existan y se usen.

        // Opcion mas directa (sin usar la instancia completa de RSAAlgoritmo para descifrar):
        BigInteger mensajeDescifrado = mensajeCifradoOriginal.modPow(claveDOriginal, moduloNOriginal);
        txtDescifradoResult.setText(mensajeDescifrado.toString());
        JOptionPane.showMessageDialog(this, "Mensaje descifrado exitosamente: " + mensajeDescifrado.toString(), "Información", JOptionPane.INFORMATION_MESSAGE);

        // Si se insistiera en usar la instancia de RSAAlgoritmo, se necesitarían setters para d y n
        // en la clase RSAAlgoritmo, lo cual no es ideal ya que d y n son el resultado de la generación.
        // La implementación actual de modPow directamente es la más adecuada para esta ventana.
    }
}

