package servicio;

import modelo.ListaPreguntas;
import modelo.Pregunta;
import util.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicioPregunta {

    // Constructor vacío para poder instanciarlo
    public ServicioPregunta() {
    }

    public void guardarListaEnBD(ListaPreguntas lista) {
        Connection conn = null;
        PreparedStatement psPregunta = null;
        PreparedStatement psRespuesta = null;
        ResultSet rs = null;

        // Consultas preparadas.
        String sqlPregunta = "INSERT INTO preguntas (enunciado, opcion1, opcion2, opcion3, opcion4) VALUES (?, ?, ?, ?, ?)";
        String sqlRespuesta = "INSERT INTO respuestas_correctas (id_pregunta, respuesta) VALUES (?, ?)";
        int idGenerado;

        try {
//            if (connection == null || connection.isClosed()){
//                connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            }
            conn = DBConnection.getConnection();

            for (Pregunta p : lista.getListaPreguntas()) {

                psPregunta = conn.prepareStatement(sqlPregunta, Statement.RETURN_GENERATED_KEYS);
                psPregunta.setString(1, p.getEnunciado());
                psPregunta.setString(2, p.getOpciones().get(0));
                psPregunta.setString(3, p.getOpciones().get(1));
                psPregunta.setString(4, p.getOpciones().get(2));
                psPregunta.setString(5, p.getOpciones().get(3));

                psPregunta.executeUpdate();

                // Recuperamos el ID autogenerado.
                rs = psPregunta.getGeneratedKeys();
                idGenerado = 0;

                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                }

                rs.close();
                psPregunta.close();

                // Guardamos la respuesta vinculada al ID recuperado.
                psRespuesta = conn.prepareStatement(sqlRespuesta);
                psRespuesta.setInt(1, idGenerado);
                psRespuesta.setString(2, p.getRespuestaCorrecta());
                psRespuesta.executeUpdate();
                psRespuesta.close();
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

    public List<Pregunta> obtenerPreguntasAleatorias(int cantidad) {
        List<Pregunta> listaDevolver = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> opcionesAux;

        // Usamos ORDER BY RAND().
        // Delegamos en la base de datos la tarea de desordenar las preguntas para que la partida sea impredecible.
        String sql = "SELECT p.id, p.enunciado, p.opcion1, p.opcion2, p.opcion3, p.opcion4, r.respuesta " +
                "FROM preguntas p JOIN respuestas_correctas r ON p.id = r.id_pregunta " +
                "ORDER BY RAND() LIMIT ?";

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cantidad);
            rs = ps.executeQuery();

            while (rs.next()) {
                opcionesAux = new ArrayList<>();

                opcionesAux.add(rs.getString("opcion1"));
                opcionesAux.add(rs.getString("opcion2"));
                opcionesAux.add(rs.getString("opcion3"));
                opcionesAux.add(rs.getString("opcion4"));

                listaDevolver.add(new Pregunta(
                        rs.getString("enunciado"),
                        rs.getInt("id"),
                        opcionesAux,
                        rs.getString("respuesta")
                ));
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

        return listaDevolver;
    }

    public void borrarTodo() {
        Connection conn = null;
        PreparedStatement psBorrarRespuestas = null;
        PreparedStatement psBorrarPreguntas = null;
        PreparedStatement psReiniciarPreguntas = null;
        PreparedStatement psReiniciarRespuestas = null;

        String sqlBorrarRespuestas = "DELETE FROM respuestas_correctas";
        String sqlBorrarPreguntas = "DELETE FROM preguntas";
        String sqlReiniciarPreguntas = "ALTER TABLE preguntas AUTO_INCREMENT = 1";
        String sqlReiniciarRespuestas = "ALTER TABLE respuestas_correctas AUTO_INCREMENT = 1";

        try {
            conn = DBConnection.getConnection();

            psBorrarRespuestas = conn.prepareStatement(sqlBorrarRespuestas);
            psBorrarRespuestas.executeUpdate();

            psBorrarPreguntas = conn.prepareStatement(sqlBorrarPreguntas);
            psBorrarPreguntas.executeUpdate();

            psReiniciarPreguntas = conn.prepareStatement(sqlReiniciarPreguntas);
            psReiniciarPreguntas.executeUpdate();

            psReiniciarRespuestas = conn.prepareStatement(sqlReiniciarRespuestas);
            psReiniciarRespuestas.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psReiniciarRespuestas != null) {
                    psReiniciarRespuestas.close();
                }
                if (psReiniciarPreguntas != null) {
                    psReiniciarPreguntas.close();
                }
                if (psBorrarPreguntas != null) {
                    psBorrarPreguntas.close();
                }
                if (psBorrarRespuestas != null) {
                    psBorrarRespuestas.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}