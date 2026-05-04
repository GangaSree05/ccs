import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

public class rsa {

    public static void main(String[] args) throws Exception {

        // 1. Generate RSA Key Pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // key size
        KeyPair pair = keyGen.generateKeyPair();

        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        // Input message
        String message = "HELLORSA";

        // 2. Encryption (using Public Key)
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] encryptedBytes = encryptCipher.doFinal(message.getBytes());
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);

        // 3. Decryption (using Private Key)
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedText));
        String decryptedText = new String(decryptedBytes);

        // Output
        System.out.println("Input message: " + message);
        System.out.println("Encrypted message: " + encryptedText);
        System.out.println("Decrypted message: " + decryptedText);
    }
} 
