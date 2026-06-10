package servicio;

import modelo.Consumo;
import modelo.ListaConsumos;
import repositorio.Fichero;

public class ServicioConsumos {
    private static final String RUTA = "datos/consumos_validos.txt";

    public static Consumo parsearLinea(String linea) {
        String[] partes;
        Consumo consumo = null;

        try {
            partes = linea.split(";");

            if (partes.length == 2) {
                consumo = new Consumo(partes[0], Integer.parseInt(partes[1]));
            }
        } catch (NumberFormatException e) {
            System.err.println("Consumo no valido: " + linea);
        }

        return consumo;
    }

    public static boolean esValido(Consumo consumo) {
        return consumo.getKwh() >= 100 && consumo.getKwh() <= 500;
    }

    public static String consumoALinea(Consumo consumo) {
        return consumo.getZona() + ";" + consumo.getKwh();
    }

    public static void guardarConsumos(ListaConsumos consumos) {
        Fichero fichero = new Fichero(RUTA);

        for (Consumo consumo : consumos.getConsumos()) {
            fichero.escribirLinea(consumoALinea(consumo));
        }
    }

    public static int sumar(ListaConsumos consumos) {
        int suma = 0;

        for (Consumo consumo : consumos.getConsumos()) {
            suma = suma + consumo.getKwh();
        }

        return suma;
    }
}

