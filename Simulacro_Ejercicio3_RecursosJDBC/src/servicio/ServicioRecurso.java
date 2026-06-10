package servicio;

import modelo.Recurso;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioRecurso {

    public List<Recurso> buscarPorPalabras(List<String> palabras) {
        List<Recurso> recursos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id_recurso, url, titulo, palabras_clave FROM recursos " +
                "WHERE titulo LIKE ? OR palabras_clave LIKE ?";
        String patron;
        Recurso recurso;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);

            for (String palabra : palabras) {
                patron = "%" + palabra + "%";
                ps.setString(1, patron);
                ps.setString(2, patron);
                rs = ps.executeQuery();

                while (rs.next()) {
                    recurso = new Recurso(
                            rs.getInt("id_recurso"),
                            rs.getString("url"),
                            rs.getString("titulo"),
                            rs.getString("palabras_clave")
                    );

                    if (!existeRecurso(recursos, recurso.getIdRecurso())) {
                        recursos.add(recurso);
                    }
                }

                rs.close();
                rs = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return recursos;
    }

    private boolean existeRecurso(List<Recurso> recursos, int id) {
        boolean existe = false;

        for (Recurso recurso : recursos) {
            if (recurso.getIdRecurso() == id) {
                existe = true;
            }
        }

        return existe;
    }
}

