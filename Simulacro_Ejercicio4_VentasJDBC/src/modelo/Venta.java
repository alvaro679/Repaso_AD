package modelo;

public class Venta {
    private String establecimiento;
    private String producto;
    private int unidades;
    private double ventas;

    public Venta(String establecimiento, String producto, int unidades, double ventas) {
        this.establecimiento = establecimiento;
        this.producto = producto;
        this.unidades = unidades;
        this.ventas = ventas;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public String getProducto() {
        return producto;
    }

    public int getUnidades() {
        return unidades;
    }

    public double getVentas() {
        return ventas;
    }
}

