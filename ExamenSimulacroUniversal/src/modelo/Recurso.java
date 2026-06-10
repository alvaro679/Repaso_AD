package modelo;

public class Recurso {
    private int idRecurso;
    private String url;
    private String titulo;
    private String palabrasClave;

    public Recurso(int idRecurso, String url, String titulo, String palabrasClave) {
        this.idRecurso = idRecurso;
        this.url = url;
        this.titulo = titulo;
        this.palabrasClave = palabrasClave;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    @Override
    public String toString() {
        return idRecurso + " | " + titulo + " | " + url + " | " + palabrasClave;
    }
}

