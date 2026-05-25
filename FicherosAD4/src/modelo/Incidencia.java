package modelo;

import java.time.LocalDate;

public class Incidencia {

    // Zona declarativa
    private String usuario;
    private String excepcion;
    private LocalDate fecha;
    private String hora;

    // Constructor
    public Incidencia(String usuario, String excepcion, LocalDate fecha, String hora) {
        this.usuario = usuario;
        this.excepcion = excepcion;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Métodos de acceso
    public String getUsuario() {
        return usuario;
    }
    public String getExcepcion() {
        return excepcion;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public String getHora() {
        return hora;
    }

    // Representación
    @Override
    public String toString() {
        return "Usuario: " + usuario +
                " | Excepción: " + excepcion +
                " | Fecha: " + fecha +
                " | Detalle: " + hora;
    }
}
