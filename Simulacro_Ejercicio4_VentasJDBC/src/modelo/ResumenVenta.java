package modelo;

public class ResumenVenta {
    private String establecimiento;
    private String producto;
    private int totalUnidades;
    private double totalVentas;
    private int cantidadVentas;

    public ResumenVenta(String establecimiento, String producto, int totalUnidades, double totalVentas) {
        this.establecimiento = establecimiento;
        this.producto = producto;
        this.totalUnidades = totalUnidades;
        this.totalVentas = totalVentas;
        this.cantidadVentas = 1;
    }

    public String getEstablecimiento() {
        return establecimiento;
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

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    public void sumar(Venta venta) {
        totalUnidades = totalUnidades + venta.getUnidades();
        totalVentas = totalVentas + venta.getVentas();
        cantidadVentas = cantidadVentas + 1;
    }
}

