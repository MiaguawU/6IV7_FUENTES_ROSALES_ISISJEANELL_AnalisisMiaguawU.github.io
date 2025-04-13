package pkg04aes;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CifradorAES {

    // Llave de 16 bytes (AES-128)
    public static final byte[] keyvalue = new byte[]{
        'q', 'w', 'e', 'r', 't', 'y', 'u', 'i',
        'q', 'w', 'e', 'r', 't', 'y', 'u', 'i'
    };

    private static final String instancia = "AES";

    public static String encrypt(String data) throws Exception {
        Key subllave = generateKey();
        Cipher cifrado = Cipher.getInstance(instancia);
        cifrado.init(Cipher.ENCRYPT_MODE, subllave);
        byte[] encValores = cifrado.doFinal(data.getBytes());

        // Codificación con Base64
        return Base64.getEncoder().encodeToString(encValores);
    }

    public static String decrypt(String valoresEncriptados) throws Exception {
        Key subllave = generateKey();
        Cipher cifrado = Cipher.getInstance(instancia);
        cifrado.init(Cipher.DECRYPT_MODE, subllave);

        // Decodificación con Base64
        byte[] decodificados = Base64.getDecoder().decode(valoresEncriptados);
        byte[] decValores = cifrado.doFinal(decodificados);

        return new String(decValores);
    }

    private static Key generateKey() {
        return new SecretKeySpec(keyvalue, instancia);
    }
}
