package controlador;

import modelo.Recurso;
import servicio.ServicioRecurso;
import vista.Consola;
import vista.Escaner;

import java.util.ArrayList;
import java.util.List;

public class ControladorRecursos {

    public static void iniciar() {
        List<String> palabras = new ArrayList<>();
        ServicioRecurso servicio = new ServicioRecurso();
        List<Recurso> recursos;
        String palabra = Escaner.pedirString("Introduce palabra o Fin:");

        while (!palabra.equalsIgnoreCase("Fin")) {
            palabras.add(palabra);
            palabra = Escaner.pedirString("Introduce palabra o Fin:");
        }

        recursos = servicio.buscarPorPalabras(palabras);
        Consola.mostrarRecursos(recursos);
    }
}

