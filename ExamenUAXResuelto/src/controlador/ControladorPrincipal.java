package controlador;

import vista.Consola;
import vista.Escaner;

public class ControladorPrincipal {

    public static void iniciar() {
        int opcion;
        boolean salir = false;

        while (!salir) {
            Consola.mostrarMenu();
            opcion = Escaner.pedirInt("Selecciona una opcion:");

            switch (opcion) {
                case 1:
                    ControladorNumeros.iniciar();
                    break;
                case 2:
                    ControladorLetras.iniciar();
                    break;
                case 3:
                    ControladorWeb.iniciar();
                    break;
                case 4:
                    ControladorVentas.iniciar();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }
    }
}

