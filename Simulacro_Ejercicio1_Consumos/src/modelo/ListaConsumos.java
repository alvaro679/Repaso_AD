package modelo;

import java.util.ArrayList;
import java.util.List;

public class ListaConsumos {
    private List<Consumo> consumos;

    public ListaConsumos() {
        consumos = new ArrayList<>();
    }

    public void addConsumo(Consumo consumo) {
        consumos.add(consumo);
    }

    public List<Consumo> getConsumos() {
        return consumos;
    }
}

