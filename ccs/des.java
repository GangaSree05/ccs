import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class des {

    public static void main(String[] args) throws Exception {
 
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        SecretKey secretKey = keyGen.generateKey();
 
        String message = "HELLODES";
 
        Cipher encryptCipher = Cipher.getInstance("DES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = encryptCipher.doFinal(message.getBytes());
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
 
        Cipher decryptCipher = Cipher.getInstance("DES");
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedText));
        String decryptedText = new String(decryptedBytes);
 
        System.out.println("Input message: " + message);
        System.out.println("Encrypted message: " + encryptedText);
        System.out.println("Decrypted message: " + decryptedText);
    }
}