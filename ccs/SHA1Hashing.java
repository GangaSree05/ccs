import java.security.MessageDigest;
import java.util.Scanner;

public class SHA1Hashing {

    public static String generateSHA1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            byte[] messageDigest = md.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        String hash = generateSHA1(text);

        System.out.println("Input message: " + text);
        System.out.println("SHA-1 Hash: " + hash);

        sc.close();
    }
}