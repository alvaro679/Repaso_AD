package controlador;

import modelo.ResultadoClasificacion;
import servicio.ServicioLetras;
import vista.Escaner;

import java.util.ArrayList;
import java.util.List;

public class ControladorLetras {

    public static void iniciar() {
        List<String> letras = new ArrayList<>();
        String letra;
        int creados;
        ResultadoClasificacion resultado;
        String[] palabras = {"Arbol", "Avion", "Barco", "Casa", "Camino", "Dado", "Elefante"};

        for (int i = 0; i < 8; i++) {
            letra = Escaner.pedirString("Introduce una letra:");
            ServicioLetras.addLetraSiNoExiste(letras, letra);
        }

        creados = ServicioLetras.crearFicheros(letras);
        resultado = ServicioLetras.escribirPalabras(letras, palabras, creados);
        System.out.println("Palabras escritas: " + resultado.getPalabrasEscritas());
        System.out.println("Palabras no escritas: " + resultado.getPalabrasNoEscritas());
        System.out.println("Ficheros creados: " + resultado.getFicherosCreados());
    }
}
