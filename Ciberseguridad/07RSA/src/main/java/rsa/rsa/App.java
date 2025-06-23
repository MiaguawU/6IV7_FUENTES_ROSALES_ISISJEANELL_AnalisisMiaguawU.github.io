/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package rsa.rsa;
/**
 * @author miagu
 */

import javax.swing.SwingUtilities;
public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CifradoGUI();
            }
        });
    }


    /*

    opcion 1
    realizar este programa del calculo de RSA con una ventana
    a manita, sin el uso del editor el calculo sera con maximo 3 digitos

    se debe de mostrar todas las operaciones
    p, q, n, fi, e, d

    debe de aparecer un campo de texto donde se ingrese el numero que se require
    cifrar boton para cifrar

    otra ventana donde aparezca para decifrar y se daba colocar
    el mensaje cifrado, d, y boton para descifrar

    En un video son dos aplicaciones


    */
}
