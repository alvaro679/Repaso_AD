package modelo;

public class Web {
    private int id;
    private String url;
    private String palabrasClave;

    public Web(int id, String url, String palabrasClave) {
        this.id = id;
        this.url = url;
        this.palabrasClave = palabrasClave;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " | " + url + " | " + palabrasClave;
    }
}

