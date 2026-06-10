package controlador;

import modelo.Consumo;
import modelo.ListaConsumos;
import servicio.ServicioConsumos;
import vista.Consola;
import vista.Escaner;

public class ControladorConsumos {

    public static void iniciar() {
        ListaConsumos introducidos = new ListaConsumos();
        ListaConsumos guardados = new ListaConsumos();
        String linea = Escaner.pedirString("Introduce zona;kwh o Fin:");
        Consumo consumo;

        while (!linea.equalsIgnoreCase("Fin")) {
            consumo = ServicioConsumos.parsearLinea(linea);

            if (consumo != null) {
                introducidos.addConsumo(consumo);

                if (ServicioConsumos.esValido(consumo)) {
                    guardados.addConsumo(consumo);
                }
            }

            linea = Escaner.pedirString("Introduce zona;kwh o Fin:");
        }

        ServicioConsumos.guardarConsumos(guardados);
        Consola.mostrarResultadoConsumos(
                introducidos.getConsumos().size(),
                guardados.getConsumos().size(),
                ServicioConsumos.sumar(introducidos),
                ServicioConsumos.sumar(guardados)
        );
    }
}

