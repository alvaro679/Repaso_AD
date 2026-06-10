package modelo;

public class VentaEntrada {
    private String supermercado;
    private String producto;
    private int unidades;
    private double ventas;

    public VentaEntrada(String supermercado, String producto, int unidades, double ventas) {
        this.supermercado = supermercado;
        this.producto = producto;
        this.unidades = unidades;
        this.ventas = ventas;
    }

    public String getSupermercado() {
        return supermercado;
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

