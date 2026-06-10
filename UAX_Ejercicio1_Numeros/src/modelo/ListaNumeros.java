package modelo;

import java.util.ArrayList;
import java.util.List;

public class ListaNumeros {
    private List<Numero> numeros;

    public ListaNumeros() {
        numeros = new ArrayList<>();
    }

    public void addNumero(Numero numero) {
        numeros.add(numero);
    }

    public List<Numero> getNumeros() {
        return numeros;
    }
}

