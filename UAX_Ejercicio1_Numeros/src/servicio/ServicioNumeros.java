package servicio;

import modelo.ListaNumeros;
import modelo.Numero;
import repositorio.Fichero;

public class ServicioNumeros {
    private static final String RUTA = "datos/numeros.txt";

    public static boolean estaEnRango(Numero numero) {
        return numero.getValor() >= 100 && numero.getValor() <= 1000;
    }

    public static int sumar(ListaNumeros lista) {
        int suma = 0;

        for (Numero numero : lista.getNumeros()) {
            suma = suma + numero.getValor();
        }

        return suma;
    }

    public static String numerosALinea(ListaNumeros lista) {
        String linea = "";
        int i = 0;

        for (Numero numero : lista.getNumeros()) {
            if (i == 0) {
                linea = linea + numero.getValor();
            } else {
                linea = linea + ";" + numero.getValor();
            }

            i = i + 1;
        }

        return linea;
    }

    public static void guardarResultado(ListaNumeros validos, ListaNumeros introducidos) {
        Fichero fichero = new Fichero(RUTA);
        String contenido = numerosALinea(validos) + "\n" +
                sumar(validos) + "/" + sumar(introducidos);

        fichero.escribirContenido(contenido);
    }
}

