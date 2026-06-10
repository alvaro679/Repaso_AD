package controlador;

import modelo.ResumenVenta;
import modelo.ResultadoVentas;
import modelo.Venta;
import servicio.ServicioVentas;
import vista.Consola;

import java.util.ArrayList;
import java.util.List;

public class ControladorVentas {

    public static void iniciar() {
        String[] datos = {
                "Supermercado A;tomate;30;40.10",
                "Supermercado A;lechuga;50;60.19",
                "Supermercado B;tomate;30;40.18",
                "Supermercado A;tomate;10;12.50",
                "Supermercado C;pan;20;22.00"
        };

        ServicioVentas servicio = new ServicioVentas();
        List<Venta> ventas = new ArrayList<>();
        List<ResumenVenta> resumenes;
        ResultadoVentas resultado;
        Venta venta;

        for (String linea : datos) {
            venta = servicio.parsearVenta(linea);

            if (venta != null) {
                ventas.add(venta);
            }
        }

        resumenes = servicio.agruparVentas(ventas);
        resultado = servicio.volcarResumenes(resumenes);
        Consola.mostrarResultadoVentas(resultado);
    }
}

