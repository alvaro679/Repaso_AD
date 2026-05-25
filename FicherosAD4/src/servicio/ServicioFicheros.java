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
        Incidencia inc;

        for (String linea : lineas) {
            partes = linea.split(";");
            if (partes.length == 4) {
                inc = new Incidencia(
                        partes[0],
                        partes[1],
                        LocalDate.parse(partes[2]),
                        partes[3]
                );
                lista.add(inc);
            }
        }

        return lista;
    }
}
