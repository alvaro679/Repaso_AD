package modelo;

import java.util.ArrayList;
import java.util.List;

public class ListaPreguntas {

    private List<Pregunta> listaPreguntas;

    public ListaPreguntas() {
        this.listaPreguntas = new ArrayList<>();
    }

    public ListaPreguntas(List<Pregunta> listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }

    public List<Pregunta> getListaPreguntas() {
        return listaPreguntas;
    }

    public void setListaPreguntas(List<Pregunta> listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }

    public void addPregunta(Pregunta p) {
        listaPreguntas.add(p);
    }
}