package modelo;

public class ResultadoVentas {
    private int procesadas;
    private int noProcesadas;
    private int insertadas;
    private int actualizadas;

    public void addProcesadas(int cantidad) {
        procesadas = procesadas + cantidad;
    }

    public void addNoProcesadas(int cantidad) {
        noProcesadas = noProcesadas + cantidad;
    }

    public void addInsertada() {
        insertadas++;
    }

    public void addActualizada() {
        actualizadas++;
    }

    public int getProcesadas() {
        return procesadas;
    }

    public int getNoProcesadas() {
        return noProcesadas;
    }

    public int getInsertadas() {
        return insertadas;
    }

    public int getActualizadas() {
        return actualizadas;
    }
}

