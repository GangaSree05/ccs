import java.util.*;

public class HillCipher {

    static int[][] key = { {3, 3}, {2, 5} }; // fixed key (easy for exam)

    // Padding (make even length)
    public static String padText(String text) {
        text = text.toUpperCase().replaceAll(" ", "");
        if (text.length() % 2 != 0) {
            text += "X";
        }
        return text;
    }

    // Encryption
    public static String encrypt(String text) {
        String result = "";

        for (int i = 0; i < text.length(); i += 2) {
            int a = text.charAt(i) - 'A';
            int b = text.charAt(i + 1) - 'A';

            int c1 = (key[0][0]*a + key[0][1]*b) % 26;
            int c2 = (key[1][0]*a + key[1][1]*b) % 26;

            result += (char)(c1 + 'A');
            result += (char)(c2 + 'A');
        }

        return result;
    }

    public static String decrypt(String text) {
        int[][] invKey = { {15, 17}, {20, 9} }; // inverse of key

        String result = "";

        for (int i = 0; i < text.length(); i += 2) {
            int a = text.charAt(i) - 'A';
            int b = text.charAt(i + 1) - 'A';

            int p1 = (invKey[0][0]*a + invKey[0][1]*b) % 26;
            int p2 = (invKey[1][0]*a + invKey[1][1]*b) % 26;

            result += (char)(p1 + 'A');
            result += (char)(p2 + 'A');
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Input message: ");
        String input = sc.nextLine();

        String padded = padText(input);
        String encoded = encrypt(padded);
        String decoded = decrypt(encoded);

        System.out.println("Padded message: " + padded);
        System.out.println("Encoded message: " + encoded);
        System.out.println("Decoded message: " + decoded);

        sc.close();
    }
}