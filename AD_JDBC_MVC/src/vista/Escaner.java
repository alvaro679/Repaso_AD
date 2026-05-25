package vista;

import java.util.Scanner;

public class Escaner {

    private static Scanner sc = new Scanner(System.in);


    public static String leerString() {
        return sc.nextLine();
    }

    public static int leerInt() {
        int numero;
        while (!sc.hasNextInt()) {
            System.out.println("Introduce un número válido:");
            sc.next();
        }
        numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }
}