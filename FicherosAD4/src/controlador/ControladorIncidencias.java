package controlador;

import modelo.Incidencia;
import modelo.ListaIncidencias;
import vista.Consola;
import vista.Escaner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ControladorIncidencias {

    public static void iniciar() {
        String usuario;
        ListaIncidencias datos;
        int opcion;
        boolean salir;

        usuario = Escaner.pedirString("Introduce tu nombre de usuario:");
        datos = new ListaIncidencias();
        salir = false;
        String usuarioBuscado;

        while (!salir) {
            Consola.menuPrincipal(usuario);
            opcion = Escaner.pedirInt("Selecciona una opción:");

            switch (opcion) {
                case 1:
                    Consola.menuExcepciones(usuario, datos);
                    break;
                case 2:
                    usuarioBuscado = Escaner.pedirString("Introduce el usuario a buscar:");
                    buscarPorUsuario(usuarioBuscado, datos);
                    break;
                case 3:
                    buscarPorFechas(datos);
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    Consola.mostrarError("Opción no válida, intenta de nuevo.");
                    break;
            }
        }
    }

    public static void registrarExcepcion(String usuario, ListaIncidencias datos, int tipoError) {
        try {
            // Según el número que elija el usuario, lanzamos un tipo de excepción
            switch (tipoError) {
                case 1:
                    throw new IOException("Simulación de IOException");
                case 2:
                    throw new FileNotFoundException("Simulación de FileNotFoundException");
                case 3:
                    throw new Exception("Simulación de excepción genérica");
                default:
                    Consola.mostrarAdvertencia("Opción no válida.");
            }

        } catch (Exception e) {
            // Cuando se lanza la excepción, llegamos aquí

            // Creamos una incidencia con los datos de la excepción
            Incidencia nueva = new Incidencia(
                    usuario,                            // quién causó la excepción
                    e.getClass().getSimpleName(),        // nombre del tipo de excepción
                    LocalDate.now(),                     // fecha actual
                    LocalTime.now().toString()           // hora actual
            );

            // La guardamos en la lista y en el fichero
            datos.guardarIncidencia(nueva);

            // Avisamos por consola
            Consola.mostrarExito("Incidencia registrada correctamente: " + nueva);
        }
    }


    public static void buscarPorUsuario(String usuario, ListaIncidencias datos) {
        boolean encontrada = false;

        for (Incidencia i : datos.obtenerIncidencias()) {
            if (i.getUsuario().equalsIgnoreCase(usuario)) {
                System.out.println(i);
                encontrada = true;
            }
        }

        if (!encontrada) {
            Consola.mostrarAdvertencia("No se encontraron incidencias para ese usuario.");
        }
    }

    public static void buscarPorFechas(ListaIncidencias datos) {
        String fechaInicioStr;
        String fechaFinStr;
        LocalDate inicio;
        LocalDate fin;
        boolean encontrada = false;

        Consola.mostrarInfo("Buscar por fechas");
        fechaInicioStr = Escaner.pedirString("Introduce la fecha de inicio (YYYY-MM-DD):");
        fechaFinStr = Escaner.pedirString("Introduce la fecha de fin (YYYY-MM-DD):");
        inicio = LocalDate.parse(fechaInicioStr);
        fin = LocalDate.parse(fechaFinStr);

        for (Incidencia i : datos.obtenerIncidencias()) {
            if (!i.getFecha().isBefore(inicio) && !i.getFecha().isAfter(fin)) {
                System.out.println(i);
                encontrada = true;
            }
        }

        if (!encontrada) {
            Consola.mostrarAdvertencia("No se encontraron incidencias en ese rango de fechas.");
        }
    }
}
