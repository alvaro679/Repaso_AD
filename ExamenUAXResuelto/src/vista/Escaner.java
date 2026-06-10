package vista;

import java.util.Scanner;

public class Escaner {
    private static final Scanner sc = new Scanner(System.in);

    public static String pedirString(String mensaje) {
        System.out.println(mensaje);
        return sc.nextLine();
    }

    public static int pedirInt(String mensaje) {
        int numero;

        System.out.println(mensaje);

        while (!sc.hasNextInt()) {
            System.out.println("Introduce un numero valido:");
            sc.next();
        }

        numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }
}

