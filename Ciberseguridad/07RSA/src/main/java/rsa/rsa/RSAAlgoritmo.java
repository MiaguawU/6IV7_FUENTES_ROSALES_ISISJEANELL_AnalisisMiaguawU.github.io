/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rsa.rsa;

/**
 *
 * @author miagu
 */
import java.math.BigInteger;
import java.util.Random;

/**
 * Clase que implementa el algoritmo RSA para cifrado y descifrado de números.
 * @author miagu
 */
public class RSAAlgoritmo {
    // Variables principales del algoritmo RSA
    private int tamPrimo; // Tamaño en bits de los números primos p y q
    private BigInteger n, p, q; // p*q (módulo)
    private BigInteger fi; // (p-1)*(q-1) (función phi de Euler)
    private BigInteger e, d; // Claves pública (e) y privada (d)

    /**
     * Constructor de la clase RSAAlgoritmo.
     * Inicializa el tamaño en bits para la generación de primos.
     * @param tamPrimo El tamaño en bits deseado para los números primos.
     */
    public RSAAlgoritmo(int tamPrimo) {
        this.tamPrimo = tamPrimo;
    }

    /**
     * Genera dos números primos grandes, p y q, utilizando el tamaño especificado.
     * Asegura que p y q sean diferentes.
     */
    public void generarPrimos() {
        // Genera p: Un número primo probable de 'tamPrimo' bits con 10 iteraciones de certeza.
        p = new BigInteger(tamPrimo, 10, new Random());
        do {
            // Genera q: Otro número primo probable.
            q = new BigInteger(tamPrimo, 10, new Random());
        } while (q.compareTo(p) == 0); // Asegura que q sea diferente de p
    }

    /**
     * Genera las claves pública y privada del algoritmo RSA.
     * Calcula n = p * q, fi = (p-1)*(q-1), e (coprimo de fi) y d (inverso modular de e mod fi).
     */
    public void generarClaves() {
        // Calcula n (módulo) = p * q
        n = p.multiply(q);

        // Calcula fi (función phi de Euler) = (p-1)*(q-1)
        fi = p.subtract(BigInteger.valueOf(1));
        fi = fi.multiply(q.subtract(BigInteger.valueOf(1)));

        // Calcula e (exponente de cifrado, clave pública)
        // e debe ser un número coprimo de fi (gcd(e, fi) = 1)
        // y 1 < e < fi
        do {
            e = new BigInteger(2 * tamPrimo, new Random()); // Se elige un 'e' de tamaño hasta 2*tamPrimo bits.
        } while (
            (e.compareTo(fi) != -1) || // e debe ser menor que fi (e < fi)
            (e.gcd(fi).compareTo(BigInteger.valueOf(1)) != 0) // e y fi deben ser coprimos (gcd = 1)
        );

        // Calcula d (exponente de descifrado, clave privada)
        // d es el inverso modular de e módulo fi (d * e mod fi = 1)
        d = e.modInverse(fi);
    }

    /**
     * Cifra un número (mensaje) utilizando la clave pública (e, n).
     * @param mensaje El número (BigInteger) a cifrar.
     * @return El número cifrado (BigInteger).
     */
    public BigInteger cifrar(BigInteger mensaje) {
        // Fórmula de cifrado: C = M^e mod n
        return mensaje.modPow(e, n);
    }

    /**
     * Descifra un número cifrado utilizando la clave privada (d, n).
     * @param cifrado El número cifrado (BigInteger) a descifrar.
     * @return El número descifrado (BigInteger).
     */
    public BigInteger descifrar(BigInteger cifrado) {
        // Fórmula de descifrado: M = C^d mod n
        return cifrado.modPow(d, n);
    }

    // Métodos Getter para obtener los valores de las claves y parámetros

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getFi() {
        return fi;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }
}
