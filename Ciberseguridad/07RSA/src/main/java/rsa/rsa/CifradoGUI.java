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
 * Interfaz gráfica para la generación de claves RSA y el cifrado de números.
 * Muestra los parámetros p, q, n, fi, e, d.
 */
public class CifradoGUI extends JFrame {

    private RSAAlgoritmo rsa; // Instancia del algoritmo RSA
    private final int TAM_PRIMO = 16; // Tamaño en bits para los números primos (ajustado para que n sea grande para 3 dígitos)

    // Componentes de la GUI
    private JLabel labelP, labelQ, labelN, labelFi, labelE, labelD;
    private JTextField txtP, txtQ, txtN, txtFi, txtE, txtD;
    private JTextField txtMensajeCifrar;
    private JButton btnGenerarClaves, btnCifrar, btnAbrirDescifrar;
    private JTextArea txtCifradoResult;

    /**
     * Constructor de CifradoGUI.
     * Inicializa los componentes de la interfaz y configura el RSA.
     */
    public CifradoGUI() {
        super("Cifrado RSA"); // Título de la ventana
        rsa = new RSAAlgoritmo(TAM_PRIMO); // Inicializa el algoritmo RSA con el tamaño de primos
        initComponents(); // Inicializa los componentes de la GUI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar esta ventana
        setSize(500, 600); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true); // Hace visible la ventana
    }

    /**
     * Inicializa y organiza los componentes de la interfaz de usuario.
     */
    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout()); // Usamos GridBagLayout para un control flexible del diseño
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interior

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Los componentes se expanden horizontalmente

        // Componentes para mostrar las claves
        // Fila 0: Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa dos columnas
        JLabel titleLabel = new JLabel("Parámetros RSA Generados", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, gbc);

        // Fila 1: p
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        labelP = new JLabel("p:");
        panel.add(labelP, gbc);
        gbc.gridx = 1;
        txtP = new JTextField(20);
        txtP.setEditable(false);
        panel.add(txtP, gbc);

        // Fila 2: q
        gbc.gridx = 0;
        gbc.gridy = 2;
        labelQ = new JLabel("q:");
        panel.add(labelQ, gbc);
        gbc.gridx = 1;
        txtQ = new JTextField(20);
        txtQ.setEditable(false);
        panel.add(txtQ, gbc);

        // Fila 3: n
        gbc.gridx = 0;
        gbc.gridy = 3;
        labelN = new JLabel("n:");
        panel.add(labelN, gbc);
        gbc.gridx = 1;
        txtN = new JTextField(20);
        txtN.setEditable(false);
        panel.add(txtN, gbc);

        // Fila 4: fi
        gbc.gridx = 0;
        gbc.gridy = 4;
        labelFi = new JLabel("φ (fi):");
        panel.add(labelFi, gbc);
        gbc.gridx = 1;
        txtFi = new JTextField(20);
        txtFi.setEditable(false);
        panel.add(txtFi, gbc);

        // Fila 5: e
        gbc.gridx = 0;
        gbc.gridy = 5;
        labelE = new JLabel("e:");
        panel.add(labelE, gbc);
        gbc.gridx = 1;
        txtE = new JTextField(20);
        txtE.setEditable(false);
        panel.add(txtE, gbc);

        // Fila 6: d
        gbc.gridx = 0;
        gbc.gridy = 6;
        labelD = new JLabel("d:");
        panel.add(labelD, gbc);
        gbc.gridx = 1;
        txtD = new JTextField(20);
        txtD.setEditable(false);
        panel.add(txtD, gbc);

        // Botón Generar Claves
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        btnGenerarClaves = new JButton("Generar Claves RSA");
        panel.add(btnGenerarClaves, gbc);

        // Separador visual
        gbc.gridy = 8;
        panel.add(new JSeparator(), gbc);

        // Componentes para cifrar mensaje
        // Fila 9: Título
        gbc.gridy = 9;
        JLabel cifrarTitleLabel = new JLabel("Cifrar Número", SwingConstants.CENTER);
        cifrarTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(cifrarTitleLabel, gbc);

        // Fila 10: Campo para mensaje
        gbc.gridy = 10;
        panel.add(new JLabel("Número a cifrar (Máx. 3 dígitos):"), gbc);
        gbc.gridy = 11;
        txtMensajeCifrar = new JTextField(10);
        panel.add(txtMensajeCifrar, gbc);

        // Fila 12: Botón Cifrar
        gbc.gridy = 12;
        btnCifrar = new JButton("Cifrar Número");
        panel.add(btnCifrar, gbc);

        // Fila 13: Resultado del cifrado
        gbc.gridy = 13;
        panel.add(new JLabel("Número Cifrado:"), gbc);
        gbc.gridy = 14;
        txtCifradoResult = new JTextArea(3, 20); // Usamos JTextArea para el resultado cifrado
        txtCifradoResult.setEditable(false);
        txtCifradoResult.setLineWrap(true);
        txtCifradoResult.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtCifradoResult);
        panel.add(scrollPane, gbc);

        // Fila 15: Botón Abrir Descifrar
        gbc.gridy = 15;
        btnAbrirDescifrar = new JButton("Abrir Ventana de Descifrado");
        panel.add(btnAbrirDescifrar, gbc);

        add(panel); // Añade el panel a la ventana

        // Configuración de los Listeners de los botones
        btnGenerarClaves.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarClaves();
            }
        });

        btnCifrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cifrarMensaje();
            }
        });

        btnAbrirDescifrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaDescifrado();
            }
        });
    }

    /**
     * Genera las claves RSA y actualiza los campos de texto con los valores.
     */
    private void generarClaves() {
        rsa.generarPrimos(); // Genera p y q
        rsa.generarClaves(); // Genera n, fi, e, d

        // Muestra los parámetros generados en los campos de texto
        txtP.setText(rsa.getP().toString());
        txtQ.setText(rsa.getQ().toString());
        txtN.setText(rsa.getN().toString());
        txtFi.setText(rsa.getFi().toString());
        txtE.setText(rsa.getE().toString());
        txtD.setText(rsa.getD().toString());
        
        txtCifradoResult.setText(""); // Limpia el campo de resultado cifrado
        txtMensajeCifrar.setText(""); // Limpia el campo de mensaje a cifrar
        JOptionPane.showMessageDialog(this, "Claves RSA generadas exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Cifra el número ingresado por el usuario y muestra el resultado.
     * Valida que el número sea de hasta 3 dígitos.
     */
    private void cifrarMensaje() {
        String mensajeStr = txtMensajeCifrar.getText();
        if (mensajeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número a cifrar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Validar que sea un número y de hasta 3 dígitos
            int num = Integer.parseInt(mensajeStr);
            if (num < 0 || num > 999) { // Asumimos números positivos de hasta 3 dígitos (0-999)
                JOptionPane.showMessageDialog(this, "El número debe ser un valor positivo de hasta 3 dígitos (0-999).", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (rsa.getN() == null || rsa.getE() == null) {
                JOptionPane.showMessageDialog(this, "Por favor, genere las claves RSA primero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            BigInteger mensajeBigInt = BigInteger.valueOf(num);
            // Validar que el mensaje sea menor que n para evitar errores en el cifrado RSA
            if (mensajeBigInt.compareTo(rsa.getN()) >= 0) {
                 JOptionPane.showMessageDialog(this, "El número a cifrar es demasiado grande para las claves generadas. Intente generar nuevas claves o un número menor.", "Error de Cifrado", JOptionPane.ERROR_MESSAGE);
                 return;
            }

            BigInteger cifrado = rsa.cifrar(mensajeBigInt);
            txtCifradoResult.setText(cifrado.toString()); // Muestra el BigInteger cifrado
            JOptionPane.showMessageDialog(this, "Número cifrado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida. Por favor, ingrese solo números.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Abre una nueva ventana de DescifradoGUI, pasando los valores necesarios.
     */
    private void abrirVentanaDescifrado() {
        if (rsa.getD() == null || rsa.getN() == null) {
            JOptionPane.showMessageDialog(this, "Por favor, genere las claves RSA y cifre un número primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (txtCifradoResult.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, cifre un número primero para pasarlo a la ventana de descifrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            BigInteger cifradoBigInt = new BigInteger(txtCifradoResult.getText());
            // Se crea una nueva instancia de RSAAlgoritmo para la ventana de descifrado,
            // pero solo se le pasan 'd' y 'n' ya que son los únicos necesarios para descifrar.
            // Los otros parámetros (p, q, fi, e) no son estrictamente necesarios para el descifrado.
            // Sin embargo, para mantener una consistencia, se podría pasar la instancia 'rsa' completa,
            // o solo los valores específicos. Aquí pasaré los valores explícitamente.
            new DescifradoGUI(cifradoBigInt, rsa.getD(), rsa.getN());
        } catch (NumberFormatException ex) {
             JOptionPane.showMessageDialog(this, "El mensaje cifrado no es un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

