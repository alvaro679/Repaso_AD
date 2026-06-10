package repositorio;

import modelo.Web;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioWeb {

    public List<Web> buscarPorPalabras(List<String> palabras) {
        List<Web> webs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id, url, palabrasClave FROM webs WHERE palabrasClave LIKE ?";
        String patron;
        Web web;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);

            for (String palabra : palabras) {
                patron = "%" + palabra + "%";
                ps.setString(1, patron);
                rs = ps.executeQuery();

                while (rs.next()) {
                    web = new Web(
                            rs.getInt("id"),
                            rs.getString("url"),
                            rs.getString("palabrasClave")
                    );

                    if (!existeWeb(webs, web.getId())) {
                        webs.add(web);
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

        return webs;
    }

    private boolean existeWeb(List<Web> webs, int id) {
        boolean existe = false;

        for (Web web : webs) {
            if (web.getId() == id) {
                existe = true;
            }
        }

        return existe;
    }
}
