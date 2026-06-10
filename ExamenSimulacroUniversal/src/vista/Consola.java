package vista;

import modelo.Recurso;
import modelo.ResultadoClasificacion;
import modelo.ResultadoVentas;

import java.util.List;

public class Consola {

    public static void mostrarMenu() {
        System.out.println("\n===== EXAMEN SIMULACRO UNIVERSAL =====");
        System.out.println("1. Ejercicio 1 - Consumos y fichero");
        System.out.println("2. Ejercicio 2 - Letras y ficheros");
        System.out.println("3. Ejercicio 3 - Recursos JDBC");
        System.out.println("4. Ejercicio 4 - Ventas JDBC");
        System.out.println("0. Salir");
    }

    public static void mostrarResultadoConsumos(int introducidos, int guardados, int sumaIntroducidos, int sumaGuardados) {
        System.out.println("Total registros introducidos: " + introducidos);
        System.out.println("Total registros guardados: " + guardados);
        System.out.println("Suma kwh introducidos: " + sumaIntroducidos);
        System.out.println("Suma kwh guardados: " + sumaGuardados);
    }

    public static void mostrarResultadoClasificacion(ResultadoClasificacion resultado) {
        System.out.println("Palabras escritas: " + resultado.getPalabrasEscritas());
        System.out.println("Palabras no escritas: " + resultado.getPalabrasNoEscritas());
        System.out.println("Ficheros creados: " + resultado.getFicherosCreados());
    }

    public static void mostrarRecursos(List<Recurso> recursos) {
        if (recursos.isEmpty()) {
            System.out.println("No se encontraron recursos.");
        } else {
            for (Recurso recurso : recursos) {
                System.out.println(recurso);
            }
        }
    }

    public static void mostrarResultadoVentas(ResultadoVentas resultado) {
        System.out.println("Ventas procesadas: " + resultado.getProcesadas());
        System.out.println("Ventas no procesadas: " + resultado.getNoProcesadas());
        System.out.println("Resumenes insertados: " + resultado.getInsertadas());
        System.out.println("Resumenes actualizados: " + resultado.getActualizadas());
    }
}

