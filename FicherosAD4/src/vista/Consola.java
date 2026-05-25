package vista;

import controlador.ControladorIncidencias;
import modelo.ListaIncidencias;

public class Consola {

    public static final String RESET = "\u001B[0m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String MARRON = "\u001B[33m";

    public static void mostrarInfo(String mensaje) { System.out.println(AZUL + mensaje + RESET); }
    public static void mostrarExito(String mensaje) { System.out.println(VERDE + mensaje + RESET); }
    public static void mostrarError(String mensaje) { System.err.println(ROJO + mensaje + RESET); }
    public static void mostrarAdvertencia(String mensaje) { System.out.println(AMARILLO + mensaje + RESET); }

    public static void menuPrincipal(String usuario) {
        System.out.println(VERDE + "\n+----------------------------------------------+" + RESET);
        System.out.println(VERDE + "| " + RESET + MARRON + "     MENÚ PRINCIPAL - Usuario: " + usuario + "       " + RESET + VERDE + "|" + RESET);
        System.out.println(VERDE + "+----------------------------------------------+" + RESET);
        System.out.println(VERDE + "| " + RESET + " 1. Registrar excepción                  " + VERDE + "|" + RESET);
        System.out.println(VERDE + "| " + RESET + " 2. Buscar por usuario                   " + VERDE + "|" + RESET);
        System.out.println(VERDE + "| " + RESET + " 3. Buscar por rango de fechas           " + VERDE + "|" + RESET);
        System.out.println(VERDE + "| " + RESET + " 0. Salir                                " + VERDE + "|" + RESET);
        System.out.println(VERDE + "+----------------------------------------------+" + RESET);
    }

    public static void menuExcepciones(String usuario, ListaIncidencias datos) {
        int opcion;

        System.out.println(MARRON + "\n+----------------------------------+" + RESET);
        System.out.println(MARRON + "| " + RESET + VERDE + "        SUBMENÚ DE EXCEPCIONES       " + MARRON + "|" + RESET);
        System.out.println(MARRON + "+----------------------------------+" + RESET);
        System.out.println(MARRON + "| " + RESET + " 1. Levantar IOException              " + MARRON + "|" + RESET);
        System.out.println(MARRON + "| " + RESET + " 2. Levantar FileNotFoundException    " + MARRON + "|" + RESET);
        System.out.println(MARRON + "| " + RESET + " 3. Levantar Exception genérica       " + MARRON + "|" + RESET);
        System.out.println(MARRON + "+----------------------------------+" + RESET);

        opcion = Escaner.pedirInt("Elige una opción:");
        ControladorIncidencias.registrarExcepcion(usuario, datos, opcion);
    }
}
