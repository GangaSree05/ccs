import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellman {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Get inputs from user
        System.out.print("Enter prime number (p): ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter generator (g): ");
        BigInteger g = sc.nextBigInteger();

        System.out.print("Enter private key of User A (a): ");
        BigInteger a = sc.nextBigInteger();

        System.out.print("Enter private key of User B (b): ");
        BigInteger b = sc.nextBigInteger();
 
        BigInteger A = g.modPow(a, p);
        BigInteger B = g.modPow(b, p);
 
        BigInteger keyA = B.modPow(a, p);
        BigInteger keyB = A.modPow(b, p);
 
        System.out.println("\n--- Output ---");
        System.out.println("Public Key of A: " + A);
        System.out.println("Public Key of B: " + B);
        System.out.println("Shared Secret Key (A): " + keyA);
        System.out.println("Shared Secret Key (B): " + keyB);

        sc.close();
    }
}