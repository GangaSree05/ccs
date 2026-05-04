import java.util.*;

public class Playfaircipher {

    static char[][] matrix = new char[5][5];

    public static void generateMatrix(String key) {
        boolean[] used = new boolean[26];
        key = key.toLowerCase().replace("j", "i");

        int row = 0, col = 0;
        for (char c : key.toCharArray()) {
            if (!used[c - 'a']) {
                matrix[row][col] = c;
                used[c - 'a'] = true;
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }

        for (char c = 'a'; c <= 'z'; c++) {
            if (c == 'j') continue;
            if (!used[c - 'a']) {
                matrix[row][col] = c;
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    public static int[] findPos(char c) {
        if (c == 'j') c = 'i';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static String prepareText(String text) {
        text = text.toLowerCase().replace("j", "i").replaceAll(" ", "");
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char a = text.charAt(i);
            char b = (i + 1 < text.length()) ? text.charAt(i + 1) : 'x';

            if (a == b) {
                result += a;
                result += 'x';
            } else {
                result += a;
                result += b;
                i++;
            }
        }

        if (result.length() % 2 != 0) {
            result += 'x';
        }

        return result;
    }

    public static String encrypt(String text) {
        String result = "";

        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] p1 = findPos(a);
            int[] p2 = findPos(b);

            if (p1[0] == p2[0]) {
                result += matrix[p1[0]][(p1[1] + 1) % 5];
                result += matrix[p2[0]][(p2[1] + 1) % 5];
            }
            else if (p1[1] == p2[1]) {
                result += matrix[(p1[0] + 1) % 5][p1[1]];
                result += matrix[(p2[0] + 1) % 5][p2[1]];
            }
            else {
                result += matrix[p1[0]][p2[1]];
                result += matrix[p2[0]][p1[1]];
            }
        }

        return result;
    }

    // Decrypt
    public static String decrypt(String text) {
        String result = "";

        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);
            int[] p1 = findPos(a);
            int[] p2 = findPos(b);
            if (p1[0] == p2[0]) {
                result += matrix[p1[0]][(p1[1] + 4) % 5];
                result += matrix[p2[0]][(p2[1] + 4) % 5];
            }
            else if (p1[1] == p2[1]) {
                result += matrix[(p1[0] + 4) % 5][p1[1]];
                result += matrix[(p2[0] + 4) % 5][p2[1]];
            }
            else {
                result += matrix[p1[0]][p2[1]];
                result += matrix[p2[0]][p1[1]];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter key: ");
        String key = sc.nextLine();

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        generateMatrix(key);

        String prepared = prepareText(text);
        String encrypted = encrypt(prepared);
        String decrypted = decrypt(encrypted);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}