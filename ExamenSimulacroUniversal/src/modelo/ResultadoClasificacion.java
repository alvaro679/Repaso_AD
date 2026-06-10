package modelo;

public class ResultadoClasificacion {
    private int palabrasEscritas;
    private int palabrasNoEscritas;
    private int ficherosCreados;

    public ResultadoClasificacion(int palabrasEscritas, int palabrasNoEscritas, int ficherosCreados) {
        this.palabrasEscritas = palabrasEscritas;
        this.palabrasNoEscritas = palabrasNoEscritas;
        this.ficherosCreados = ficherosCreados;
    }

    public int getPalabrasEscritas() {
        return palabrasEscritas;
    }

    public int getPalabrasNoEscritas() {
        return palabrasNoEscritas;
    }

    public int getFicherosCreados() {
        return ficherosCreados;
    }
}

