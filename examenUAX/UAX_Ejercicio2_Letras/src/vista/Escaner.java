package vista;

import java.util.Scanner;

public class Escaner {
    private static final Scanner sc = new Scanner(System.in);

    public static String pedirString(String mensaje) {
        System.out.println(mensaje);
        return sc.nextLine();
    }
}
