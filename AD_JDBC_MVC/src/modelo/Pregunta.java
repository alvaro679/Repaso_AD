package modelo;

import java.util.List;

public class Pregunta {
    private int id;
    private String enunciado;
    private List<String> opciones;
    // Esto permite que si mañana quiero poner 5 opciones no haya que cambiar la estructura de la clase
    private String respuestaCorrecta;

    public Pregunta(String enunciado, int id, List<String> opciones, String respuestaCorrecta) {
        this.enunciado = enunciado;
        this.id = id;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }
}