package servicio;

import modelo.ResultadoPalabras;
import repositorio.Fichero;

import java.util.List;

public class ServicioLetras {

    public static void addLetraSiNoExiste(List<String> letras, String letra) {
        String normalizada = letra.toUpperCase();

        if (!letras.contains(normalizada)) {
            letras.add(normalizada);
        }
    }

    public static void crearFicheros(List<String> letras) {
        Fichero fichero;

        for (String letra : letras) {
            fichero = new Fichero("datos/" + letra + ".txt");
            fichero.crearSiNoExiste();
        }
    }

    public static ResultadoPalabras escribirPalabras(List<String> letras, String[] palabras) {
        int escritas = 0;
        int noEscritas = 0;
        String inicial;
        Fichero fichero;

        for (String palabra : palabras) {
            inicial = palabra.substring(0, 1).toUpperCase();

            if (letras.contains(inicial)) {
                fichero = new Fichero("datos/" + inicial + ".txt");
                fichero.escribirLinea(palabra);
                escritas = escritas + 1;
            } else {
                noEscritas = noEscritas + 1;
            }
        }

        return new ResultadoPalabras(escritas, noEscritas);
    }
}

