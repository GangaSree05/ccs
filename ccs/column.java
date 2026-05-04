import java.util.*;

public class column {

    // Encrypt (Row Major)
    static String encrypt(String text, int key) {
        char[][] rail = new char[key][text.length()];

        // fill with '\n'
        for (int i = 0; i < key; i++)
            Arrays.fill(rail[i], '\n');

        boolean dirDown = false;
        int row = 0, col = 0;

        for (int i = 0; i < text.length(); i++) {

            if (row == 0 || row == key - 1)
                dirDown = !dirDown;

            rail[row][col++] = text.charAt(i);

            row += dirDown ? 1 : -1;
        }

        // read row-wise
        String result = "";
        for (int i = 0; i < key; i++)
            for (int j = 0; j < text.length(); j++)
                if (rail[i][j] != '\n')
                    result += rail[i][j];

        return result;
    }

    // Decrypt
    static String decrypt(String cipher, int key) {
        char[][] rail = new char[key][cipher.length()];

        for (int i = 0; i < key; i++)
            Arrays.fill(rail[i], '\n');

        boolean dirDown = false;
        int row = 0, col = 0;

        // mark positions
        for (int i = 0; i < cipher.length(); i++) {
            if (row == 0)
                dirDown = true;
            else if (row == key - 1)
                dirDown = false;

            rail[row][col++] = '*';

            row += dirDown ? 1 : -1;
        }

        // fill characters row-wise
        int index = 0;
        for (int i = 0; i < key; i++)
            for (int j = 0; j < cipher.length(); j++)
                if (rail[i][j] == '*' && index < cipher.length())
                    rail[i][j] = cipher.charAt(index++);

        // read zigzag
        String result = "";
        row = 0; col = 0;

        for (int i = 0; i < cipher.length(); i++) {
            if (row == 0)
                dirDown = true;
            else if (row == key - 1)
                dirDown = false;

            result += rail[row][col++];

            row += dirDown ? 1 : -1;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Input message: ");
        String text = sc.nextLine();

        System.out.print("Enter key (rows): ");
        int key = sc.nextInt();

        String encrypted = encrypt(text, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Encoded message: " + encrypted);
        System.out.println("Decoded message: " + decrypted);
    }
}