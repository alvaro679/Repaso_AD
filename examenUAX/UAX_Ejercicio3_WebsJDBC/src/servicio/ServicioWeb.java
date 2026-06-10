package servicio;

import modelo.Web;
import repositorio.RepositorioWeb;
import java.util.List;

public class ServicioWeb {
    private RepositorioWeb repositorio;

    public ServicioWeb() {
        repositorio = new RepositorioWeb();
    }

    public List<Web> buscarPorPalabras(List<String> palabras) {
        return repositorio.buscarPorPalabras(palabras);
    }
}
