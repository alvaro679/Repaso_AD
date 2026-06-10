package vista;

import modelo.ResultadoPalabras;
import modelo.Web;

import java.util.List;

public class Consola {

    public static void mostrarMenu() {
        System.out.println("\n===== EXAMEN UAX RESUELTO =====");
        System.out.println("1. Ejercicio 1 - Numeros y fichero");
        System.out.println("2. Ejercicio 2 - Letras y ficheros");
        System.out.println("3. Ejercicio 3 - Webs JDBC");
        System.out.println("4. Ejercicio 4 - Ventas JDBC");
        System.out.println("0. Salir");
    }

    public static void mostrarResultadoPalabras(ResultadoPalabras resultado) {
        System.out.println("Palabras introducidas en ficheros: " + resultado.getEscritas());
        System.out.println("Palabras no introducidas: " + resultado.getNoEscritas());
    }

    public static void mostrarWebs(List<Web> webs) {
        if (webs.isEmpty()) {
            System.out.println("No se encontraron webs.");
        } else {
            for (Web web : webs) {
                System.out.println(web);
            }
        }
    }
}

