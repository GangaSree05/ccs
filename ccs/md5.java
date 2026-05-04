import java.security.MessageDigest;
import java.util.Scanner;

public class md5 {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        // Create MD5 object
        MessageDigest md = MessageDigest.getInstance("MD5");

        // Generate hash
        byte[] hashBytes = md.digest(text.getBytes());

        // Convert to hex
        StringBuilder hash = new StringBuilder();
        for (byte b : hashBytes) {
            hash.append(String.format("%02x", b));
        }

        // Output
        System.out.println("Input message: " + text);
        System.out.println("MD5 Hash: " + hash);

        sc.close();
    }
}
