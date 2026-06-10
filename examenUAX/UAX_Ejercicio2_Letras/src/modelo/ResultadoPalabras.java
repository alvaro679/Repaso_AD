package modelo;

public class ResultadoPalabras {
    private int escritas;
    private int noEscritas;

    public ResultadoPalabras(int escritas, int noEscritas) {
        this.escritas = escritas;
        this.noEscritas = noEscritas;
    }

    public int getEscritas() {
        return escritas;
    }

    public int getNoEscritas() {
        return noEscritas;
    }
}

