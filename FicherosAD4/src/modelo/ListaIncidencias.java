package modelo;

import servicio.ServicioFicheros;

import java.util.List;

public class ListaIncidencias {

    private List<Incidencia> incidencias;

    public ListaIncidencias() {
        incidencias = ServicioFicheros.leerIncidencias();
    }

    public void guardarIncidencia(Incidencia i) {
        incidencias.add(i);
        ServicioFicheros.guardarIncidencia(i);
    }

    public List<Incidencia> obtenerIncidencias() {
        return incidencias;
    }
}
