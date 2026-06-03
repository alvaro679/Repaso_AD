package servicio;

import modelo.Incidencia;
import repositorio.Fichero;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServicioFicheros {

    private static final String RUTA = "datos/incidencias.txt";

    public static void guardarIncidencia(Incidencia incidencia) {
        Fichero fichero = new Fichero(RUTA);

        String linea = incidencia.getUsuario() + ";" +
                incidencia.getExcepcion() + ";" +
                incidencia.getFecha() + ";" +
                incidencia.getHora();

        fichero.escribirLinea(linea);
    }

    public static List<Incidencia> leerIncidencias() {
        Fichero fichero = new Fichero(RUTA);
        List<String> lineas = fichero.leerFichero();
        List<Incidencia> lista = new ArrayList<>();
        String[] partes;
        Incidencia incidencia;

        for (String linea : lineas) {
            partes = linea.split(";");
            if (partes.length == 4) {
                incidencia = new Incidencia(
                        partes[0],
                        partes[1],
                        LocalDate.parse(partes[2]),
                        partes[3]
                );
                lista.add(incidencia);
            }
        }

        return lista;
    }
}

/*
import java.util.List;

public class EstadisticasProductos {

    public static void generarEstadisticas() {

        Fichero origen = new Fichero("Dato/productos.txt");
        Fichero destino = new Fichero("Dato/estadisticas.txt");

        List<String> lineas = origen.leerLineas();

        int totalProductos = 0;
        double sumaPrecios = 0;
        double precioMaximo = 0;
        String[] parte;
        String producto;
        double precio;
        double media;

        for (String linea : lineas) {

            parte = linea.split(";");

            producto = parte[0];
            precio = Double.parseDouble(parte[1]);

            totalProductos++;
            sumaPrecios += precio;

            if (precio > precioMaximo) {
                precioMaximo = precio;
            }
        }

        media = sumaPrecios / totalProductos;

        destino.escribirLinea("Total productos: " + totalProductos);
        destino.escribirLinea("Precio más alto: " + precioMaximo);
        destino.escribirLinea("Media precios: " + media);
    }
}
*/