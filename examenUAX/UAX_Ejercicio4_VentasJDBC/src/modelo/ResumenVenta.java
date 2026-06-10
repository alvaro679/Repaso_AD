package modelo;

public class ResumenVenta {
    private String supermercado;
    private String producto;
    private int totalUnidades;
    private double totalVentas;

    public ResumenVenta(String supermercado, String producto, int totalUnidades, double totalVentas) {
        this.supermercado = supermercado;
        this.producto = producto;
        this.totalUnidades = totalUnidades;
        this.totalVentas = totalVentas;
    }

    public String getSupermercado() {
        return supermercado;
    }

    public String getProducto() {
        return producto;
    }

    public int getTotalUnidades() {
        return totalUnidades;
    }

    public double getTotalVentas() {
        return totalVentas;
    }

    public void sumar(VentaEntrada venta) {
        totalUnidades = totalUnidades + venta.getUnidades();
        totalVentas = totalVentas + venta.getVentas();
    }
}

