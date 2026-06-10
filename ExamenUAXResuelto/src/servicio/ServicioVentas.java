package servicio;

import modelo.ResumenVenta;
import modelo.VentaEntrada;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioVentas {

    public VentaEntrada parsearVenta(String linea) {
        String[] partes;
        VentaEntrada venta = null;

        try {
            partes = linea.split(";");

            if (partes.length == 4) {
                venta = new VentaEntrada(
                        partes[0],
                        partes[1],
                        Integer.parseInt(partes[2]),
                        Double.parseDouble(partes[3])
                );
            }
        } catch (NumberFormatException e) {
            System.err.println("Venta no valida: " + linea);
        }

        return venta;
    }

    public List<ResumenVenta> agrupar(List<VentaEntrada> ventas) {
        List<ResumenVenta> resumenes = new ArrayList<>();
        ResumenVenta resumen;

        for (VentaEntrada venta : ventas) {
            resumen = buscarResumen(resumenes, venta.getSupermercado(), venta.getProducto());

            if (resumen == null) {
                resumenes.add(new ResumenVenta(
                        venta.getSupermercado(),
                        venta.getProducto(),
                        venta.getUnidades(),
                        venta.getVentas()
                ));
            } else {
                resumen.sumar(venta);
            }
        }

        return resumenes;
    }

    public void volcarResumenes(List<ResumenVenta> resumenes) {
        Connection conn = null;
        int idSupermercado;
        int idProducto;

        try {
            conn = DBConnection.getConnection();

            for (ResumenVenta resumen : resumenes) {
                idSupermercado = buscarIdSupermercado(conn, resumen.getSupermercado());
                idProducto = buscarIdProducto(conn, resumen.getProducto());

                if (idSupermercado != 0 && idProducto != 0) {
                    insertarVenta(conn, idSupermercado, idProducto, resumen);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private ResumenVenta buscarResumen(List<ResumenVenta> resumenes, String supermercado, String producto) {
        ResumenVenta encontrado = null;

        for (ResumenVenta resumen : resumenes) {
            if (resumen.getSupermercado().equalsIgnoreCase(supermercado)
                    && resumen.getProducto().equalsIgnoreCase(producto)) {
                encontrado = resumen;
            }
        }

        return encontrado;
    }

    private int buscarIdSupermercado(Connection conn, String nombre) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        String sql = "SELECT Id_supermercado FROM Tabla_supermercados WHERE Nombre_supermercado = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("Id_supermercado");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return id;
    }

    private int buscarIdProducto(Connection conn, String nombre) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        String sql = "SELECT Id_producto FROM Tabla_producto WHERE Nombre_producto = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("Id_producto");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return id;
    }

    private void insertarVenta(Connection conn, int idSupermercado, int idProducto, ResumenVenta resumen) throws SQLException {
        PreparedStatement ps = null;
        String sql = "INSERT INTO Tabla_Ventas (Id_supermercado, Id_producto, Total_unidades, Total_ventas) VALUES (?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idSupermercado);
            ps.setInt(2, idProducto);
            ps.setInt(3, resumen.getTotalUnidades());
            ps.setDouble(4, resumen.getTotalVentas());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
}

