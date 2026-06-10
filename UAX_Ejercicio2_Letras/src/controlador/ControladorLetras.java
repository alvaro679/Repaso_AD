package controlador;

import modelo.ResultadoPalabras;
import servicio.ServicioLetras;
import vista.Escaner;

import java.util.ArrayList;
import java.util.List;

public class ControladorLetras {

    public static void iniciar() {
        List<String> letras = new ArrayList<>();
        String letra;
        String[] palabras = {"Amigo", "Gorro", "Luces", "Pina"};
        ResultadoPalabras resultado;

        for (int i = 0; i < 10; i++) {
            letra = Escaner.pedirString("Introduce una letra:");
            ServicioLetras.addLetraSiNoExiste(letras, letra);
        }

        ServicioLetras.crearFicheros(letras);
        resultado = ServicioLetras.escribirPalabras(letras, palabras);
        System.out.println("Palabras introducidas en ficheros: " + resultado.getEscritas());
        System.out.println("Palabras no introducidas: " + resultado.getNoEscritas());
    }
}
