package controlador;

import modelo.ResumenVenta;
import modelo.ResultadoVentas;
import modelo.Venta;
import servicio.ServicioVentas;

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
        System.out.println("Ventas procesadas: " + resultado.getProcesadas());
        System.out.println("Ventas no procesadas: " + resultado.getNoProcesadas());
        System.out.println("Resumenes insertados: " + resultado.getInsertadas());
        System.out.println("Resumenes actualizados: " + resultado.getActualizadas());
    }
}
