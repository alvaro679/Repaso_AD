package servicio;

import modelo.ResumenVenta;
import modelo.ResultadoVentas;
import modelo.Venta;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioVentas {

    public Venta parsearVenta(String linea) {
        String[] partes;
        Venta venta = null;

        try {
            partes = linea.split(";");

            if (partes.length == 4) {
                venta = new Venta(
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

    public List<ResumenVenta> agruparVentas(List<Venta> ventas) {
        List<ResumenVenta> resumenes = new ArrayList<>();
        ResumenVenta resumen;

        for (Venta venta : ventas) {
            resumen = buscarResumen(resumenes, venta.getEstablecimiento(), venta.getProducto());

            if (resumen == null) {
                resumenes.add(new ResumenVenta(
                        venta.getEstablecimiento(),
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

    public ResultadoVentas volcarResumenes(List<ResumenVenta> resumenes) {
        ResultadoVentas resultado = new ResultadoVentas();
        Connection conn = null;
        int idEstablecimiento;
        int idProducto;

        try {
            conn = DBConnection.getConnection();

            for (ResumenVenta resumen : resumenes) {
                idEstablecimiento = buscarIdEstablecimiento(conn, resumen.getEstablecimiento());
                idProducto = buscarIdProducto(conn, resumen.getProducto());

                if (idEstablecimiento == 0 || idProducto == 0) {
                    resultado.addNoProcesadas(resumen.getCantidadVentas());
                } else if (existeResumen(conn, idEstablecimiento, idProducto)) {
                    actualizarResumen(conn, idEstablecimiento, idProducto, resumen);
                    resultado.addActualizada();
                    resultado.addProcesadas(resumen.getCantidadVentas());
                } else {
                    insertarResumen(conn, idEstablecimiento, idProducto, resumen);
                    resultado.addInsertada();
                    resultado.addProcesadas(resumen.getCantidadVentas());
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

        return resultado;
    }

    private ResumenVenta buscarResumen(List<ResumenVenta> resumenes, String establecimiento, String producto) {
        ResumenVenta encontrado = null;

        for (ResumenVenta resumen : resumenes) {
            if (resumen.getEstablecimiento().equalsIgnoreCase(establecimiento)
                    && resumen.getProducto().equalsIgnoreCase(producto)) {
                encontrado = resumen;
            }
        }

        return encontrado;
    }

    private int buscarIdEstablecimiento(Connection conn, String nombre) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        String sql = "SELECT id_establecimiento FROM establecimientos WHERE nombre_establecimiento = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_establecimiento");
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
        String sql = "SELECT id_producto FROM productos WHERE nombre_producto = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_producto");
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

    private boolean existeResumen(Connection conn, int idEstablecimiento, int idProducto) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean existe = false;
        String sql = "SELECT id_establecimiento FROM resumen_ventas WHERE id_establecimiento = ? AND id_producto = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idEstablecimiento);
            ps.setInt(2, idProducto);
            rs = ps.executeQuery();

            if (rs.next()) {
                existe = true;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return existe;
    }

    private void insertarResumen(Connection conn, int idEstablecimiento, int idProducto, ResumenVenta resumen) throws SQLException {
        PreparedStatement ps = null;
        String sql = "INSERT INTO resumen_ventas (id_establecimiento, id_producto, total_unidades, total_ventas) VALUES (?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idEstablecimiento);
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

    private void actualizarResumen(Connection conn, int idEstablecimiento, int idProducto, ResumenVenta resumen) throws SQLException {
        PreparedStatement ps = null;
        String sql = "UPDATE resumen_ventas SET total_unidades = ?, total_ventas = ? WHERE id_establecimiento = ? AND id_producto = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, resumen.getTotalUnidades());
            ps.setDouble(2, resumen.getTotalVentas());
            ps.setInt(3, idEstablecimiento);
            ps.setInt(4, idProducto);
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
}
