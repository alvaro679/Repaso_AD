package servicio;

import modelo.Pregunta;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioPregunta {

    // Constructor vacio para poder instanciarlo
    public ServicioPregunta() {}

    public void guardarListaEnBD(List<Pregunta> lista) {
        Connection conn = null;
        PreparedStatement psPregunta = null;
        PreparedStatement psRespuesta = null;
        ResultSet rs = null;

        // Consultas preparadas.
        String sqlPregunta = "INSERT INTO preguntas (enunciado, opcion1, opcion2, opcion3, opcion4) VALUES (?, ?, ?, ?, ?)";
        String sqlRespuesta = "INSERT INTO respuestas_correctas (id_pregunta, respuesta) VALUES (?, ?)";
        int idGenerado;

        try {
            conn = DBConnection.getConnection();

            for (Pregunta p : lista) {
                // POR QUÉ (Integridad Referencial): Usamos RETURN_GENERATED_KEYS.
                // Necesitamos saber qué ID le ha puesto MySQL a la pregunta para poder usar ese mismo número
                // al guardar su respuesta correcta en la otra tabla.
                // setString sustituye los ? en las sentencias SQL
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
                // Sin recuperar ese ID autogenerado, la respuesta correcta quedaría "huerfana" o no se podría guardar, porque no sabría a qué pregunta del juego pertenece. Es el puente que une las dos tablas.
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
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Pregunta> obtenerPreguntasAleatorias(int cantidad) {
        List<Pregunta> listaDevolver = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Usamos ORDER BY RAND().
        // Delegamos en la base de datos la tarea de desordenar las preguntas para que la partida sea impredecible.
        String sql = "SELECT p.id, p.enunciado, p.opcion1, p.opcion2, p.opcion3, p.opcion4, r.respuesta " +
                "FROM preguntas p JOIN respuestas_correctas r ON p.id = r.id_pregunta " +
                "ORDER BY RAND() LIMIT ?"; // LIMIT ? asegura traer solo la cantidad pedida.

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cantidad);
            rs = ps.executeQuery();
            List<String> opcionesAux = new ArrayList<>();

            // Bucle que recorre fila a fila los resultados devueltos por la base de datos.
            // El ResultSet funciona como un puntero. 'rs.next()' mueve el puntero a la siguiente fila
            // y devuelve true si hay datos, o false si se han acabado.
            while (rs.next()) {

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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaDevolver;
    }

    public void borrarTodo() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM respuestas_correctas");
            stmt.executeUpdate("DELETE FROM preguntas");

            // Reiniciamos el contador AUTO_INCREMENT a 1.
            // Si no lo hiciéramos, los IDs crecerían infinitamente (100, 101...) en cada ejecución.
            // Esto mantiene la BD ordenada como si fuera nueva.
            stmt.executeUpdate("ALTER TABLE preguntas AUTO_INCREMENT = 1");
            stmt.executeUpdate("ALTER TABLE respuestas_correctas AUTO_INCREMENT = 1");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}