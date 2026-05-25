package controlador;

import modelo.Pregunta;
import servicio.ServicioFichero;
import servicio.ServicioPregunta;
import vista.Consola;
import java.util.ArrayList;
import java.util.List;

public class ControladorPreguntas {
    // ZONA DECLARATIVA (Atributos)
    private ServicioFichero sFichero;
    private ServicioPregunta sPregunta;
    private Consola consola;

    public ControladorPreguntas() {
        this.sFichero = new ServicioFichero("data/preguntas.txt");
        this.sPregunta = new ServicioPregunta();
        this.consola = new Consola();
    }

    public void iniciar() {

        List<Pregunta> listaFichero;
        List<Pregunta> listaJuego;
        List<String> resumen;
        int cantidad;
        int aciertos;
        int i;
        String respuestaUser;
        boolean correcta;

        aciertos = 0;
        resumen = new ArrayList<>();

        // 1. Cargar y Guardar en BD
        consola.mostrarMensaje("Cargando fichero y guardando en BD...");
        listaFichero = sFichero.obtenerPreguntasDeFichero();

        sPregunta.borrarTodo(); // Limpieza inicial
        sPregunta.guardarListaEnBD(listaFichero);

        // 2. Pedir cantidad y jugar
        cantidad = consola.pedirNumeroPreguntas();
        listaJuego = sPregunta.obtenerPreguntasAleatorias(cantidad);

        if (listaJuego.size() < cantidad) {
            consola.mostrarMensaje("Solo hay " + listaJuego.size() + " preguntas disponibles.");
        }

        i = 1;
        for (Pregunta p : listaJuego) {
            respuestaUser = consola.mostrarPreguntaYPedirRespuesta(p, i);

            // Comprobamos si la respuesta correcta contiene lo que escribió el usuario
            correcta = p.getRespuestaCorrecta().toLowerCase().contains(respuestaUser.toLowerCase());

            if (correcta) {
                aciertos = aciertos + 1;
                resumen.add("P" + i + ": Correcta (" + p.getRespuestaCorrecta() + ")");
            } else {
                resumen.add("P" + i + ": Incorrecta. Era: " + p.getRespuestaCorrecta());
            }
            i = i + 1;
        }

        // 3. Resultados y Limpieza
        consola.mostrarResultados(aciertos, listaJuego.size(), resumen);

        consola.mostrarMensaje("Limpiando base de datos...");
        sPregunta.borrarTodo();
    }
}