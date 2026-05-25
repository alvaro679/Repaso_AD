package vista;

import java.util.Scanner;

public class Escaner {

    private static final Scanner sc = new Scanner(System.in);

    public static int pedirInt(String mensaje) {
        int numero;
        System.out.println(mensaje + ": ");
        numero = sc.nextInt();
        sc.nextLine(); // limpiar buffer
        return numero;
    }

    public static String pedirString(String mensaje) {
        String texto;
        System.out.println(mensaje);
        texto = sc.nextLine();
        return texto;
    }

    public static void mostrarString(String mensaje) {
        System.out.println(mensaje);
    }
}