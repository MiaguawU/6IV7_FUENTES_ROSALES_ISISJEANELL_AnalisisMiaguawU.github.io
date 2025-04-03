package pkg03des;

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class CifradoDES {
    private static final String CLAVE_ARCHIVO = "clave.des";

    public void cifrarArchivo(String rutaArchivo) throws Exception {
        SecretKey clave = obtenerClave();
        String archivoCifrado = rutaArchivo + ".cifrado";

        SecureRandom sr = new SecureRandom();
        byte[] iv = new byte[8]; 
        sr.nextBytes(iv);
        IvParameterSpec ivParams = new IvParameterSpec(iv);

        Cipher cifrador = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cifrador.init(Cipher.ENCRYPT_MODE, clave, ivParams);

        try (FileInputStream entrada = new FileInputStream(rutaArchivo);
             FileOutputStream salida = new FileOutputStream(archivoCifrado)) {
            
            salida.write(iv);

            byte[] buffer = new byte[1024];
            int bytesLeidos;
            while ((bytesLeidos = entrada.read(buffer)) != -1) {
                byte[] bufferCifrado = cifrador.update(buffer, 0, bytesLeidos);
                if (bufferCifrado != null) {
                    salida.write(bufferCifrado);
                }
            }
            salida.write(cifrador.doFinal());
        }
    }

    public void descifrarArchivo(String rutaArchivo) throws Exception {
        SecretKey clave = obtenerClave();
        String archivoDescifrado = rutaArchivo.replace(".cifrado", ".descifrado");

        try (FileInputStream entrada = new FileInputStream(rutaArchivo);
             FileOutputStream salida = new FileOutputStream(archivoDescifrado)) {
            
            byte[] iv = new byte[8];
            entrada.read(iv);
            IvParameterSpec ivParams = new IvParameterSpec(iv);

            Cipher cifrador = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cifrador.init(Cipher.DECRYPT_MODE, clave, ivParams);

            byte[] buffer = new byte[1024];
            int bytesLeidos;
            while ((bytesLeidos = entrada.read(buffer)) != -1) {
                byte[] bufferDescifrado = cifrador.update(buffer, 0, bytesLeidos);
                if (bufferDescifrado != null) {
                    salida.write(bufferDescifrado);
                }
            }
            salida.write(cifrador.doFinal());
        }
    }

    private SecretKey obtenerClave() throws Exception {
        File archivo = new File(CLAVE_ARCHIVO);
        if (archivo.exists()) {
            return cargarClave(CLAVE_ARCHIVO);
        }

        KeyGenerator generadorDES = KeyGenerator.getInstance("DES");
        generadorDES.init(56);
        SecretKey clave = generadorDES.generateKey();
        guardarClave(clave, CLAVE_ARCHIVO);

        return clave;
    }

    private void guardarClave(SecretKey clave, String archivo) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(archivo)) {
            fos.write(clave.getEncoded());
        }
    }

    private SecretKey cargarClave(String archivo) throws Exception {
        byte[] claveBytes = new byte[8];
        try (FileInputStream fis = new FileInputStream(archivo)) {
            fis.read(claveBytes);
        }
        return new SecretKeySpec(claveBytes, "DES");
    }
}
