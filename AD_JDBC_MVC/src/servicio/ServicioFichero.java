package servicio;

import modelo.Pregunta;
import repositorio.Fichero;
import java.util.ArrayList;
import java.util.List;

public class ServicioFichero {
    private Fichero fichero;

    public ServicioFichero(String ruta) {
        this.fichero = new Fichero(ruta);
    }

    public List<Pregunta> obtenerPreguntasDeFichero() {
        List<String> lineas;
        List<Pregunta> listaFinal = new ArrayList<>();
        List<String> opciones;
        String enunciado;
        String opA;
        String opB;
        String opC;
        String opD;
        String lineaResp;
        String correcta;

        // Delegamos la lectura física al repositorio.
        lineas = fichero.leerFichero();

        // Iteramos de 7 en 7.
        // El archivo de texto tiene un formato:
        // 1 Enunciado + 4 Opciones + 1 Respuesta + 1 Línea vacía = 7 líneas por bloque.
        for (int i = 0; i < lineas.size(); i = i + 7) {
            if ((i + 5) < lineas.size()) {
                enunciado = lineas.get(i);
                opA = lineas.get(i + 1);
                opB = lineas.get(i + 2);
                opC = lineas.get(i + 3);
                opD = lineas.get(i + 4);
                lineaResp = lineas.get(i + 5); // Ejemplo: ANSWER: B

                opciones = new ArrayList<>();
                opciones.add(opA);
                opciones.add(opB);
                opciones.add(opC);
                opciones.add(opD);

                char letra = lineaResp.charAt(lineaResp.length() - 1);
                correcta = "";

                switch (letra) {
                    case 'A':
                        correcta = opA;
                        break;
                    case 'B':
                        correcta = opB;
                        break;
                    case 'C':
                        correcta = opC;
                        break;
                    case 'D':
                        correcta = opD;
                        break;
                    default:
                        // El default captura errores si el fichero viene mal .
                        System.err.println("Error: Letra de respuesta no reconocida: " + letra);
                        break;
                }

                // Creamos el objeto con ID 0 temporalmente (la BD asignará el real).
                listaFinal.add(new Pregunta(enunciado, 0, opciones, correcta));
            }
        }
        return listaFinal;
    }
}