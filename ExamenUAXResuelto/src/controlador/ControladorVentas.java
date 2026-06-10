package controlador;

import modelo.ResumenVenta;
import modelo.VentaEntrada;
import servicio.ServicioVentas;

import java.util.ArrayList;
import java.util.List;

public class ControladorVentas {

    public static void iniciar() {
        String[] datos = {
                "Supermercado A;tomate;30;40.10",
                "Supermercado A;lechuga;50;60.19",
                "Supermercado B;tomate;30;40.18"
        };

        ServicioVentas servicio = new ServicioVentas();
        List<VentaEntrada> ventas = new ArrayList<>();
        List<ResumenVenta> resumenes;
        VentaEntrada venta;

        for (String linea : datos) {
            venta = servicio.parsearVenta(linea);

            if (venta != null) {
                ventas.add(venta);
            }
        }

        resumenes = servicio.agrupar(ventas);
        servicio.volcarResumenes(resumenes);
        System.out.println("Resumen de ventas volcado.");
    }
}

