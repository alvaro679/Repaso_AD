# Solucion orientativa del examen simulacro

Esta solucion esta basada en los patrones de:

- `FicherosAD4`: `Fichero`, `ServicioFicheros`, `ControladorIncidencias`, `Escaner`, `Consola`.
- `AD_JDBC_MVC`: `DBConnection`, `ServicioPregunta`, `PreparedStatement`, `ResultSet`.

No es la unica solucion posible. La idea es que estudies la forma:

```text
modelo -> lista -> servicio -> controlador -> vista/repositorio
```

No estudies esto como texto literal. Estudia la estructura y reescribela a mano.

---

# Codigo comun de ficheros

Puedes usar una clase `Fichero` igual o muy parecida a la del proyecto `FicherosAD4`.

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

---

# Ejercicio 1 - Solucion de consumos

## Modelo `Consumo`

```java
package modelo;

public class Consumo {
    private String zona;
    private int kwh;

    public Consumo(String zona, int kwh) {
        this.zona = zona;
        this.kwh = kwh;
    }

    public String getZona() {
        return zona;
    }

    public int getKwh() {
        return kwh;
    }
}
```

## Lista `ListaConsumos`

```java
package modelo;

import java.util.ArrayList;
import java.util.List;

public class ListaConsumos {
    private List<Consumo> consumos;

    public ListaConsumos() {
        consumos = new ArrayList<>();
    }

    public void addConsumo(Consumo consumo) {
        consumos.add(consumo);
    }

    public List<Consumo> getConsumos() {
        return consumos;
    }
}
```

## Servicio `ServicioConsumos`

```java
package servicio;

import modelo.Consumo;
import modelo.ListaConsumos;
import repositorio.Fichero;

public class ServicioConsumos {
    private static final String RUTA = "datos/consumos_validos.txt";

    public static Consumo parsearLinea(String linea) {
        String[] partes;
        String zona;
        int kwh;
        Consumo consumo = null;

        try {
            partes = linea.split(";");

            if (partes.length == 2) {
                zona = partes[0];
                kwh = Integer.parseInt(partes[1]);
                consumo = new Consumo(zona, kwh);
            }
        } catch (NumberFormatException e) {
            System.err.println("Consumo no valido: " + linea);
        }

        return consumo;
    }

    public static String consumoALinea(Consumo consumo) {
        return consumo.getZona() + ";" + consumo.getKwh();
    }

    public static boolean esValido(Consumo consumo) {
        return consumo.getKwh() >= 100 && consumo.getKwh() <= 500;
    }

    public static void guardarConsumosValidos(ListaConsumos lista) {
        Fichero fichero = new Fichero(RUTA);
        String linea;

        for (Consumo consumo : lista.getConsumos()) {
            linea = consumoALinea(consumo);
            fichero.escribirLinea(linea);
        }
    }

    public static int sumarKwh(ListaConsumos lista) {
        int suma = 0;

        for (Consumo consumo : lista.getConsumos()) {
            suma = suma + consumo.getKwh();
        }

        return suma;
    }
}
```

## Controlador `ControladorConsumos`

```java
package controlador;

import modelo.Consumo;
import modelo.ListaConsumos;
import servicio.ServicioConsumos;
import vista.Escaner;

public class ControladorConsumos {

    public static void iniciar() {
        ListaConsumos introducidos = new ListaConsumos();
        ListaConsumos guardados = new ListaConsumos();
        String linea;
        Consumo consumo;

        linea = Escaner.pedirString("Introduce zona;kwh o Fin:");

        while (!linea.equalsIgnoreCase("Fin")) {
            consumo = ServicioConsumos.parsearLinea(linea);

            if (consumo != null) {
                introducidos.addConsumo(consumo);

                if (ServicioConsumos.esValido(consumo)) {
                    guardados.addConsumo(consumo);
                }
            }

            linea = Escaner.pedirString("Introduce zona;kwh o Fin:");
        }

        ServicioConsumos.guardarConsumosValidos(guardados);

        System.out.println("Total registros introducidos: " + introducidos.getConsumos().size());
        System.out.println("Total registros guardados: " + guardados.getConsumos().size());
        System.out.println("Suma kwh introducidos: " + ServicioConsumos.sumarKwh(introducidos));
        System.out.println("Suma kwh guardados: " + ServicioConsumos.sumarKwh(guardados));
    }
}
```

---

# Ejercicio 2 - Solucion de letras y ficheros

## Modelo `ResultadoClasificacion`

```java
package modelo;

public class ResultadoClasificacion {
    private int palabrasEscritas;
    private int palabrasNoEscritas;
    private int ficherosCreados;

    public ResultadoClasificacion(int palabrasEscritas, int palabrasNoEscritas, int ficherosCreados) {
        this.palabrasEscritas = palabrasEscritas;
        this.palabrasNoEscritas = palabrasNoEscritas;
        this.ficherosCreados = ficherosCreados;
    }

    public int getPalabrasEscritas() {
        return palabrasEscritas;
    }

    public int getPalabrasNoEscritas() {
        return palabrasNoEscritas;
    }

    public int getFicherosCreados() {
        return ficherosCreados;
    }
}
```

## Servicio `ServicioLetras`

```java
package servicio;

import modelo.ResultadoClasificacion;
import repositorio.Fichero;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServicioLetras {

    public static void addLetraSiNoExiste(List<String> letras, String letra) {
        String normalizada = letra.toUpperCase();

        if (!letras.contains(normalizada)) {
            letras.add(normalizada);
        }
    }

    public static int crearFicheros(List<String> letras) {
        File archivo;
        int creados = 0;

        for (String letra : letras) {
            try {
                archivo = new File("datos/" + letra + ".txt");

                if (!archivo.exists()) {
                    if (archivo.createNewFile()) {
                        creados = creados + 1;
                    }
                }
            } catch (IOException e) {
                System.err.println("Error al crear fichero: " + e.getMessage());
            }
        }

        return creados;
    }

    public static ResultadoClasificacion escribirPalabras(List<String> letras, String[] palabras, int ficherosCreados) {
        int escritas = 0;
        int noEscritas = 0;
        String inicial;
        Fichero fichero;

        for (String palabra : palabras) {
            inicial = palabra.substring(0, 1).toUpperCase();

            if (letras.contains(inicial)) {
                fichero = new Fichero("datos/" + inicial + ".txt");
                fichero.escribirLinea(palabra);
                escritas = escritas + 1;
            } else {
                noEscritas = noEscritas + 1;
            }
        }

        return new ResultadoClasificacion(escritas, noEscritas, ficherosCreados);
    }
}
```

## Controlador `ControladorLetras`

```java
package controlador;

import modelo.ResultadoClasificacion;
import servicio.ServicioLetras;
import vista.Escaner;

import java.util.ArrayList;
import java.util.List;

public class ControladorLetras {

    public static void iniciar() {
        List<String> letras = new ArrayList<>();
        String letra;
        int ficherosCreados;
        ResultadoClasificacion resultado;

        for (int i = 0; i < 8; i++) {
            letra = Escaner.pedirString("Introduce una letra:");
            ServicioLetras.addLetraSiNoExiste(letras, letra);
        }

        ficherosCreados = ServicioLetras.crearFicheros(letras);

        String[] palabras = {"Arbol", "Avion", "Barco", "Casa", "Camino", "Dado", "Elefante"};
        resultado = ServicioLetras.escribirPalabras(letras, palabras, ficherosCreados);

        System.out.println("Palabras escritas: " + resultado.getPalabrasEscritas());
        System.out.println("Palabras no escritas: " + resultado.getPalabrasNoEscritas());
        System.out.println("Ficheros creados: " + resultado.getFicherosCreados());
    }
}
```

---

# Codigo comun JDBC

Basado en `AD_JDBC_MVC/src/util/DBConnection.java`.

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

---

# Ejercicio 3 - Solucion JDBC recursos

## Modelo `Recurso`

```java
package modelo;

public class Recurso {
    private int idRecurso;
    private String url;
    private String titulo;
    private String palabrasClave;

    public Recurso(int idRecurso, String url, String titulo, String palabrasClave) {
        this.idRecurso = idRecurso;
        this.url = url;
        this.titulo = titulo;
        this.palabrasClave = palabrasClave;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public String getUrl() {
        return url;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getPalabrasClave() {
        return palabrasClave;
    }

    @Override
    public String toString() {
        return idRecurso + " | " + titulo + " | " + url + " | " + palabrasClave;
    }
}
```

## Servicio `ServicioRecurso`

```java
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
        String sql = "SELECT id_recurso, url, titulo, palabras_clave " +
                "FROM recursos " +
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
```

## Controlador `ControladorRecursos`

```java
package controlador;

import modelo.Recurso;
import servicio.ServicioRecurso;
import vista.Escaner;

import java.util.ArrayList;
import java.util.List;

public class ControladorRecursos {

    public static void iniciar() {
        List<String> palabras = new ArrayList<>();
        ServicioRecurso servicio = new ServicioRecurso();
        List<Recurso> recursos;
        String palabra;

        palabra = Escaner.pedirString("Introduce palabra o Fin:");

        while (!palabra.equalsIgnoreCase("Fin")) {
            palabras.add(palabra);
            palabra = Escaner.pedirString("Introduce palabra o Fin:");
        }

        recursos = servicio.buscarPorPalabras(palabras);

        for (Recurso recurso : recursos) {
            System.out.println(recurso);
        }
    }
}
```

---

# Ejercicio 4 - Solucion JDBC ventas

## Modelo `Venta`

```java
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
```

## Modelo `ResumenVenta`

```java
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
        this.totalUnidades = this.totalUnidades + venta.getUnidades();
        this.totalVentas = this.totalVentas + venta.getVentas();
        this.cantidadVentas = this.cantidadVentas + 1;
    }
}
```

## Modelo `ResultadoVentas`

```java
package modelo;

public class ResultadoVentas {
    private int procesadas;
    private int noProcesadas;
    private int insertadas;
    private int actualizadas;

    public void addProcesadas(int cantidad) {
        procesadas = procesadas + cantidad;
    }

    public void addNoProcesadas(int cantidad) {
        noProcesadas = noProcesadas + cantidad;
    }

    public void addInsertada() {
        insertadas++;
    }

    public void addActualizada() {
        actualizadas++;
    }

    public int getProcesadas() {
        return procesadas;
    }

    public int getNoProcesadas() {
        return noProcesadas;
    }

    public int getInsertadas() {
        return insertadas;
    }

    public int getActualizadas() {
        return actualizadas;
    }
}
```

## Servicio `ServicioVentas`

```java
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

    public ResultadoVentas volcarResumenes(List<ResumenVenta> resumenes) {
        ResultadoVentas resultado = new ResultadoVentas();
        Connection conn = null;
        int idEstablecimiento;
        int idProducto;
        boolean existe;

        try {
            conn = DBConnection.getConnection();

            for (ResumenVenta resumen : resumenes) {
                idEstablecimiento = buscarIdEstablecimiento(conn, resumen.getEstablecimiento());
                idProducto = buscarIdProducto(conn, resumen.getProducto());

                if (idEstablecimiento == 0 || idProducto == 0) {
                    resultado.addNoProcesadas(resumen.getCantidadVentas());
                } else {
                    existe = existeResumen(conn, idEstablecimiento, idProducto);

                    if (existe) {
                        actualizarResumen(conn, idEstablecimiento, idProducto, resumen);
                        resultado.addActualizada();
                    } else {
                        insertarResumen(conn, idEstablecimiento, idProducto, resumen);
                        resultado.addInsertada();
                    }

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
        String sql = "SELECT id_establecimiento FROM resumen_ventas " +
                "WHERE id_establecimiento = ? AND id_producto = ?";

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
        String sql = "INSERT INTO resumen_ventas (id_establecimiento, id_producto, total_unidades, total_ventas) " +
                "VALUES (?, ?, ?, ?)";

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
        String sql = "UPDATE resumen_ventas SET total_unidades = ?, total_ventas = ? " +
                "WHERE id_establecimiento = ? AND id_producto = ?";

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
```

## Controlador `ControladorVentas`

```java
package controlador;

import modelo.ResumenVenta;
import modelo.ResultadoVentas;
import modelo.Venta;
import servicio.ServicioVentas;

import java.util.ArrayList;
import java.util.List;

public class ControladorVentas {

    public static void iniciar() {
        String[] datos = {
                "Supermercado A;tomate;30;40.10",
                "Supermercado A;lechuga;50;60.19",
                "Supermercado B;tomate;30;40.18",
                "Supermercado A;tomate;10;12.50",
                "Supermercado C;pan;20;22.00"
        };

        ServicioVentas servicio = new ServicioVentas();
        List<Venta> ventas = new ArrayList<>();
        List<ResumenVenta> resumenes;
        ResultadoVentas resultado;
        Venta venta;

        for (String linea : datos) {
            venta = servicio.parsearVenta(linea);

            if (venta != null) {
                ventas.add(venta);
            }
        }

        resumenes = servicio.agruparVentas(ventas);
        resultado = servicio.volcarResumenes(resumenes);

        System.out.println("Ventas procesadas: " + resultado.getProcesadas());
        System.out.println("Ventas no procesadas: " + resultado.getNoProcesadas());
        System.out.println("Resumenes insertados: " + resultado.getInsertadas());
        System.out.println("Resumenes actualizados: " + resultado.getActualizadas());
    }
}
```

---

# Escaner comun

Puedes usar el patron de `FicherosAD4/src/vista/Escaner.java`.

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

---

# Main de ejemplo

En un examen real no ejecutarias los cuatro a la vez necesariamente. Esto es solo para recordar el patron.

```java
import controlador.ControladorConsumos;

public class Main {
    public static void main(String[] args) {
        ControladorConsumos.iniciar();
    }
}
```

Si quieres probar otro ejercicio, cambias el controlador:

```java
ControladorLetras.iniciar();
ControladorRecursos.iniciar();
ControladorVentas.iniciar();
```

---

# Como estudiar esta solucion

No la leas de corrido. Haz esto:

1. Lee solo un ejercicio.
2. Cierra el archivo.
3. Escribe la estructura de paquetes.
4. Escribe los modelos.
5. Escribe los servicios.
6. Escribe el controlador.
7. Vuelve aqui y corrige.

La parte mas importante no es que recuerdes `Consumo` o `Recurso`. Es que sepas adaptar:

```text
objeto -> linea de texto
linea de texto -> objeto
SELECT -> ResultSet -> objeto
objeto/lista -> INSERT/UPDATE
```
