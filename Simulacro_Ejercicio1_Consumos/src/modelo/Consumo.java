package modelo;

public class Consumo {
    private String zona;
    private int kwh;

    public Consumo(String zona, int kwh) {
        this.zona = zona;
        this.kwh = kwh;
    }

    public String getZona() {
        return zona;
    }

    public int getKwh() {
        return kwh;
    }
}

