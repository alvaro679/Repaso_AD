# Plantillas de codigo para escribir a mano

Usa estas plantillas como estructura mental. Cambia nombres de clases, atributos y tablas segun el enunciado.

## Modelo

```java
package modelo;

public class Entidad {
    private String campo1;
    private int campo2;

    public Entidad(String campo1, int campo2) {
        this.campo1 = campo1;
        this.campo2 = campo2;
    }

    public String getCampo1() {
        return campo1;
    }

    public int getCampo2() {
        return campo2;
    }

    @Override
    public String toString() {
        return campo1 + " - " + campo2;
    }
}
```

## Lista de objetos

```java
package modelo;

import java.util.ArrayList;
import java.util.List;

public class ListaEntidades {
    private List<Entidad> lista;

    public ListaEntidades() {
        lista = new ArrayList<>();
    }

    public void addEntidad(Entidad entidad) {
        lista.add(entidad);
    }

    public List<Entidad> getLista() {
        return lista;
    }
}
```

## Escaner

```java
package vista;

import java.util.Scanner;

public class Escaner {
    private static final Scanner sc = new Scanner(System.in);

    public static String pedirString(String mensaje) {
        System.out.println(mensaje);
        return sc.nextLine();
    }

    public static int pedirInt(String mensaje) {
        int numero;
        System.out.println(mensaje);
        while (!sc.hasNextInt()) {
            System.out.println("Introduce un numero valido:");
            sc.next();
        }
        numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }
}
```

## Repositorio de fichero

```java
package repositorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fichero {
    private String ruta;

    public Fichero(String ruta) {
        this.ruta = ruta;
    }

    public void escribirLinea(String dato) {
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(ruta, true);
            bw = new BufferedWriter(fw);
            bw.write(dato);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar fichero.");
            }
        }
    }

    public List<String> leerFichero() {
        FileReader fr = null;
        BufferedReader br = null;
        List<String> lineas = new ArrayList<>();
        String linea;
        File archivo;

        try {
            archivo = new File(ruta);
            if (archivo.exists()) {
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);
                linea = br.readLine();

                while (linea != null) {
                    lineas.add(linea);
                    linea = br.readLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar fichero.");
            }
        }

        return lineas;
    }
}
```

## Servicio de fichero: objeto a linea

```java
public static void guardarEntidad(Entidad entidad) {
    Fichero fichero = new Fichero(RUTA);

    String linea = entidad.getCampo1() + ";" +
            entidad.getCampo2();

    fichero.escribirLinea(linea);
}
```

## Servicio de fichero: linea a objeto

```java
public static List<Entidad> leerEntidades() {
    Fichero fichero = new Fichero(RUTA);
    List<String> lineas = fichero.leerFichero();
    List<Entidad> lista = new ArrayList<>();
    String[] partes;

    for (String linea : lineas) {
        partes = linea.split(";");
        if (partes.length == 2) {
            lista.add(new Entidad(
                    partes[0],
                    Integer.parseInt(partes[1])
            ));
        }
    }

    return lista;
}
```

## Controlador con menu

```java
public static void iniciar() {
    int opcion;
    boolean salir = false;

    while (!salir) {
        Consola.mostrarMenu();
        opcion = Escaner.pedirInt("Elige una opcion:");

        switch (opcion) {
            case 1:
                accionUno();
                break;
            case 2:
                accionDos();
                break;
            case 0:
                salir = true;
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }
}
```

## DBConnection

```java
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/nombre_bd?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection = null;

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
```

## JDBC INSERT

```java
public void insertar(Entidad entidad) {
    Connection conn = null;
    PreparedStatement ps = null;
    String sql = "INSERT INTO tabla (campo1, campo2) VALUES (?, ?)";

    try {
        conn = DBConnection.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, entidad.getCampo1());
        ps.setInt(2, entidad.getCampo2());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
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
}
```

## JDBC SELECT

```java
public List<Entidad> buscarTodos() {
    List<Entidad> lista = new ArrayList<>();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "SELECT campo1, campo2 FROM tabla";

    try {
        conn = DBConnection.getConnection();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(new Entidad(
                    rs.getString("campo1"),
                    rs.getInt("campo2")
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

    return lista;
}
```

## JDBC LIKE con palabras

```java
public List<Web> buscarPorPalabra(String palabra) {
    List<Web> lista = new ArrayList<>();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "SELECT id, url, palabrasClave FROM webs WHERE palabrasClave LIKE ?";

    try {
        conn = DBConnection.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + palabra + "%");
        rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(new Web(
                    rs.getInt("id"),
                    rs.getString("url"),
                    rs.getString("palabrasClave")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // cerrar rs, ps y conn como en la plantilla SELECT
    }

    return lista;
}
```

## JDBC con id generado

```java
String sql = "INSERT INTO tabla_padre (nombre) VALUES (?)";
ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
ps.setString(1, nombre);
ps.executeUpdate();

rs = ps.getGeneratedKeys();
if (rs.next()) {
    idGenerado = rs.getInt(1);
}
```

## JDBC UPDATE

```java
String sql = "UPDATE ventas SET total_unidades = ?, total_ventas = ? WHERE id_supermercado = ? AND id_producto = ?";
ps = conn.prepareStatement(sql);
ps.setInt(1, totalUnidades);
ps.setDouble(2, totalVentas);
ps.setInt(3, idSupermercado);
ps.setInt(4, idProducto);
ps.executeUpdate();
```

## Lo que no debes escribir como codigo Java

No metas `CREATE TABLE` en el programa salvo que el enunciado lo pida expresamente. En el examen anterior, la estructura de tablas era informacion para consultar columnas y relaciones.

