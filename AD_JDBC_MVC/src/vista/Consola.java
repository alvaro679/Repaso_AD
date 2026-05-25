package vista;

import modelo.Pregunta;
import java.util.List;

public class Consola {

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public int pedirNumeroPreguntas() {
        System.out.print("¿Cuántas preguntas quieres responder?: ");
        return Escaner.leerInt();
    }

    public String mostrarPreguntaYPedirRespuesta(Pregunta p, int numero) {
        System.out.println("\n--- Pregunta " + numero + " ---");
        System.out.println(p.getEnunciado());
        System.out.println(p.getOpciones().get(0));
        System.out.println(p.getOpciones().get(1));
        System.out.println(p.getOpciones().get(2));
        System.out.println(p.getOpciones().get(3));
        System.out.print("Tu respuesta: ");
        return Escaner.leerString();
    }

    public void mostrarResultados(int aciertos, int total, List<String> resumen) {
        System.out.println("\n========== RESULTADOS ==========");
        System.out.println("Has acertado " + aciertos + " de " + total);
        for (String linea : resumen) {
            System.out.println(linea);
        }
        System.out.println("================================");
    }
}