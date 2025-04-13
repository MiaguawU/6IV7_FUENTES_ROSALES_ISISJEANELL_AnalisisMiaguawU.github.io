package pkg04aes;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Ejemplo de Cifrado AES");

        String mensaje = "había una vez un patito que decía miau miau";

        // Usamos los métodos actualizados
        String mensajeCifrado = CifradorAES.encrypt(mensaje);
        System.out.println("El mensaje cifrado es: " + mensajeCifrado);

        String mensajeDescifrado = CifradorAES.decrypt(mensajeCifrado);
        System.out.println("El mensaje descifrado es: " + mensajeDescifrado);
    }
}
