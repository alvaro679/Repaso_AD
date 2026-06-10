package controlador;

import modelo.Web;
import servicio.ServicioWeb;
import vista.Escaner;

import java.util.ArrayList;
import java.util.List;

public class ControladorWeb {

    public static void iniciar() {
        List<String> palabras = new ArrayList<>();
        ServicioWeb servicio = new ServicioWeb();
        List<Web> webs;
        String palabra = Escaner.pedirString("Introduce palabra o Fin:");

        while (!palabra.equalsIgnoreCase("Fin")) {
            palabras.add(palabra);
            palabra = Escaner.pedirString("Introduce palabra o Fin:");
        }

        webs = servicio.buscarPorPalabras(palabras);

        if (webs.isEmpty()) {
            System.out.println("No se encontraron webs.");
        } else {
            for (Web web : webs) {
                System.out.println(web);
            }
        }
    }
}
