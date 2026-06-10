# Examen UAX FP resuelto

Este documento contiene los enunciados del examen que pasaste y una solucion orientativa basada en los patrones de tus proyectos:

- `FicherosAD4` para ficheros, listas, servicios y controladores.
- `AD_JDBC_MVC` para `DBConnection`, `PreparedStatement`, `ResultSet` y servicios JDBC.

Importante: en la parte JDBC, la estructura de tablas se usa como informacion del enunciado. No se escribe codigo para crear tablas.

## Enunciados de cada ejercicio

1. Ejercicio 1: introducir numeros hasta `Fin`, guardar solo los que estan entre 100 y 1000 en `numeros.txt` separados por `;` y calcular la suma de los validos y la suma total de los introducidos.
2. Ejercicio 2: introducir 10 letras, crear un fichero por cada letra distinta, escribir palabras en el fichero de su inicial y mostrar cuantas se han escrito y cuantas no.
3. Ejercicio 3: introducir palabras por teclado y mostrar los registros de la tabla `webs` que contengan alguna de esas palabras en `palabrasClave`.
4. Ejercicio 4: leer ventas, agruparlas por supermercado y producto, buscar los ids en las tablas ya creadas e insertar el resumen en la tabla de ventas.

---

# Penalizaciones del examen

Para cada ejercicio de codigo:

- Hay que usar POO, objetos y listas.
- Hay que usar metodos con parametros.
- Hay que controlar excepciones.
- Hay que tabular y escribir codigo limpio.
- No conviene meter todo en `main`.
- No conviene escribir codigo que el enunciado no pide.

---

# Codigo comun: Escaner

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

# Codigo comun: Fichero

Para el examen puedes usar la clase `Fichero` del proyecto `FicherosAD4`. Para el primer ejercicio conviene anadir un metodo que escriba contenido completo, porque el enunciado pide numeros separados por `;`.

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

    public void escribirContenido(String contenido) {
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(ruta, false);
            bw = new BufferedWriter(fw);
            bw.write(contenido);
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

# Ejercicio 1 - Enunciado

El usuario introduce por scanner numeros hasta escribir `Fin`.

Todos los numeros entre 100 y 1000 se escriben en `numeros.txt`.

El formato de los numeros validos es:

```text
100;854;211;...
```

Tambien se debe escribir la suma total de los numeros escritos y la suma total de todos los numeros introducidos.

Ejemplo:

```text
12,120,200,8,Fin
```

Resultado de sumas:

```text
320/340
```

Interpretacion usada en esta solucion:

```text
120;200
320/340
```

La primera linea contiene los numeros validos separados por `;`. La segunda linea contiene `sumaValidos/sumaIntroducidos`.

---

# Ejercicio 1 - Solucion

## Modelo `Numero`

```java
package modelo;

public class Numero {
    private int valor;

    public Numero(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
```

## Lista `ListaNumeros`

```java
package modelo;

import java.util.ArrayList;
import java.util.List;

public class ListaNumeros {
    private List<Numero> numeros;

    public ListaNumeros() {
        numeros = new ArrayList<>();
    }

    public void addNumero(Numero numero) {
        numeros.add(numero);
    }

    public List<Numero> getNumeros() {
        return numeros;
    }
}
```

## Servicio `ServicioNumeros`

```java
package servicio;

import modelo.ListaNumeros;
import modelo.Numero;
import repositorio.Fichero;

public class ServicioNumeros {
    private static final String RUTA = "datos/numeros.txt";

    public static boolean estaEnRango(Numero numero) {
        return numero.getValor() >= 100 && numero.getValor() <= 1000;
    }

    public static int sumar(ListaNumeros lista) {
        int suma = 0;

        for (Numero numero : lista.getNumeros()) {
            suma = suma + numero.getValor();
        }

        return suma;
    }

    public static String numerosALinea(ListaNumeros lista) {
        String linea = "";
        int i = 0;

        for (Numero numero : lista.getNumeros()) {
            if (i == 0) {
                linea = linea + numero.getValor();
            } else {
                linea = linea + ";" + numero.getValor();
            }

            i = i + 1;
        }

        return linea;
    }

    public static void guardarResultado(ListaNumeros validos, ListaNumeros introducidos) {
        Fichero fichero = new Fichero(RUTA);
        int sumaValidos = sumar(validos);
        int sumaIntroducidos = sumar(introducidos);

        String contenido = numerosALinea(validos) + "\n" +
                sumaValidos + "/" + sumaIntroducidos;

        fichero.escribirContenido(contenido);
    }
}
```

## Controlador `ControladorNumeros`

```java
package controlador;

import modelo.ListaNumeros;
import modelo.Numero;
import servicio.ServicioNumeros;
import vista.Escaner;

public class ControladorNumeros {

    public static void iniciar() {
        ListaNumeros introducidos = new ListaNumeros();
        ListaNumeros validos = new ListaNumeros();
        String texto;
        int valor;
        Numero numero;

        texto = Escaner.pedirString("Introduce un numero o Fin:");

        while (!texto.equalsIgnoreCase("Fin")) {
            try {
                valor = Integer.parseInt(texto);
                numero = new Numero(valor);
                introducidos.addNumero(numero);

                if (ServicioNumeros.estaEnRango(numero)) {
                    validos.addNumero(numero);
                }
            } catch (NumberFormatException e) {
                System.err.println("Valor no numerico: " + texto);
            }

            texto = Escaner.pedirString("Introduce un numero o Fin:");
        }

        ServicioNumeros.guardarResultado(validos, introducidos);
    }
}
```

---

# Ejercicio 2 - Enunciado

El usuario introduce por scanner 10 letras.

Se genera un fichero por cada letra diferente:

```text
A.txt
B.txt
C.txt
```

No se debe intentar crear de nuevo un fichero ya existente.

Despues se proporciona este array:

```java
String[] palabras = {"Amigo", "Gorro", "Luces", "Pina"};
```

Cada palabra se escribe dentro del fichero correspondiente a la letra por la que empieza.

Finalmente se muestra:

```text
numero total de palabras introducidas en ficheros
numero total de palabras que no se han podido introducir
```

---

# Ejercicio 2 - Solucion

## Modelo `ResultadoPalabras`

```java
package modelo;

public class ResultadoPalabras {
    private int escritas;
    private int noEscritas;

    public ResultadoPalabras(int escritas, int noEscritas) {
        this.escritas = escritas;
        this.noEscritas = noEscritas;
    }

    public int getEscritas() {
        return escritas;
    }

    public int getNoEscritas() {
        return noEscritas;
    }
}
```

## Servicio `ServicioLetras`

```java
package servicio;

import modelo.ResultadoPalabras;
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

    public static void crearFicheros(List<String> letras) {
        File archivo;

        for (String letra : letras) {
            try {
                archivo = new File("datos/" + letra + ".txt");

                if (!archivo.exists()) {
                    archivo.createNewFile();
                }
            } catch (IOException e) {
                System.err.println("Error al crear fichero: " + e.getMessage());
            }
        }
    }

    public static ResultadoPalabras escribirPalabras(List<String> letras, String[] palabras) {
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

        return new ResultadoPalabras(escritas, noEscritas);
    }
}
```

## Controlador `ControladorLetras`

```java
package controlador;

import modelo.ResultadoPalabras;
import servicio.ServicioLetras;
import vista.Escaner;

import java.util.ArrayList;
import java.util.List;

public class ControladorLetras {

    public static void iniciar() {
        List<String> letras = new ArrayList<>();
        String letra;
        String[] palabras = {"Amigo", "Gorro", "Luces", "Pina"};
        ResultadoPalabras resultado;

        for (int i = 0; i < 10; i++) {
            letra = Escaner.pedirString("Introduce una letra:");
            ServicioLetras.addLetraSiNoExiste(letras, letra);
        }

        ServicioLetras.crearFicheros(letras);
        resultado = ServicioLetras.escribirPalabras(letras, palabras);

        System.out.println("Palabras introducidas en ficheros: " + resultado.getEscritas());
        System.out.println("Palabras no introducidas: " + resultado.getNoEscritas());
    }
}
```

---

# Codigo comun JDBC

Usa el patron del proyecto `AD_JDBC_MVC`.

```java
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/aaejemplop1?useSSL=false&serverTimezone=UTC";
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

# Ejercicio 3 - Enunciado

El usuario introduce por scanner unas palabras.

Se deben mostrar todos los registros que contengan al menos una de esas palabras en la tabla `webs`.

Estructura relevante:

```text
webs
id
url
palabrasClave
```

Ejemplos:

```text
1 | https://www.uax.com/       | universidad, educacion, ciclos, alumno
2 | https://www.20minutos.es/  | actualidad, deportes, periodico, ocio, educacion
```

---

# Ejercicio 3 - Solucion

## Modelo `Web`

```java
package modelo;

public class Web {
    private int id;
    private String url;
    private String palabrasClave;

    public Web(int id, String url, String palabrasClave) {
        this.id = id;
        this.url = url;
        this.palabrasClave = palabrasClave;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getPalabrasClave() {
        return palabrasClave;
    }

    @Override
    public String toString() {
        return id + " | " + url + " | " + palabrasClave;
    }
}
```

## Servicio `ServicioWeb`

```java
package servicio;

import modelo.Web;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioWeb {

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
```

## Controlador `ControladorWeb`

```java
package controlador;

import modelo.Web;
import servicio.ServicioWeb;
import vista.Escaner;

import java.util.ArrayList;
import java.util.List;

public class ControladorWeb {

    public static void iniciar() {
        List<String> palabras = new ArrayList<>();
        ServicioWeb servicio = new ServicioWeb();
        List<Web> webs;
        String palabra;

        palabra = Escaner.pedirString("Introduce palabra o Fin:");

        while (!palabra.equalsIgnoreCase("Fin")) {
            palabras.add(palabra);
            palabra = Escaner.pedirString("Introduce palabra o Fin:");
        }

        webs = servicio.buscarPorPalabras(palabras);

        for (Web web : webs) {
            System.out.println(web);
        }
    }
}
```

---

# Ejercicio 4 - Enunciado

Se debe volcar a una base de datos un resumen de una tabla de ventas.

Datos de ejemplo:

```text
Supermercado A | tomate  | 30 | 40.10
Supermercado A | lechuga | 50 | 60.19
Supermercado B | tomate  | 30 | 40.18
```

Estructura relevante de base de datos:

```text
Tabla_supermercados
Id_supermercado
Nombre_supermercado
```

```text
Tabla_producto
Id_producto
Nombre_producto
```

```text
Tabla_Ventas
Id_supermercado
Id_producto
Total_unidades
Total_ventas
```

---

# Ejercicio 4 - Solucion

## Modelo `VentaEntrada`

```java
package modelo;

public class VentaEntrada {
    private String establecimiento;
    private String producto;
    private int unidades;
    private double ventas;

    public VentaEntrada(String establecimiento, String producto, int unidades, double ventas) {
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

    public ResumenVenta(String establecimiento, String producto, int totalUnidades, double totalVentas) {
        this.establecimiento = establecimiento;
        this.producto = producto;
        this.totalUnidades = totalUnidades;
        this.totalVentas = totalVentas;
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

    public void sumar(VentaEntrada venta) {
        totalUnidades = totalUnidades + venta.getUnidades();
        totalVentas = totalVentas + venta.getVentas();
    }
}
```

## Servicio `ServicioVentas`

```java
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

    public void volcarResumenes(List<ResumenVenta> resumenes) {
        Connection conn = null;
        int idSupermercado;
        int idProducto;

        try {
            conn = DBConnection.getConnection();

            for (ResumenVenta resumen : resumenes) {
                idSupermercado = buscarIdSupermercado(conn, resumen.getEstablecimiento());
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
        String sql = "INSERT INTO Tabla_Ventas (Id_supermercado, Id_producto, Total_unidades, Total_ventas) " +
                "VALUES (?, ?, ?, ?)";

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
```

## Controlador `ControladorVentas`

```java
package controlador;

import modelo.ResumenVenta;
import modelo.VentaEntrada;
import servicio.ServicioVentas;

import java.util.ArrayList;
import java.util.List;

public class ControladorVentas {

    public static void iniciar() {
        String[] datos = {
                "Supermercado A;tomate;30;40.10",
                "Supermercado A;lechuga;50;60.19",
                "Supermercado B;tomate;30;40.18"
        };

        ServicioVentas servicio = new ServicioVentas();
        List<VentaEntrada> ventas = new ArrayList<>();
        List<ResumenVenta> resumenes;
        VentaEntrada venta;

        for (String linea : datos) {
            venta = servicio.parsearVenta(linea);

            if (venta != null) {
                ventas.add(venta);
            }
        }

        resumenes = servicio.agrupar(ventas);
        servicio.volcarResumenes(resumenes);
    }
}
```

---

# Main de ejemplo

En el examen normalmente haras un ejercicio cada vez. El `Main` solo arranca el controlador que toque.

```java
import controlador.ControladorNumeros;

public class Main {
    public static void main(String[] args) {
        ControladorNumeros.iniciar();
    }
}
```

Para otro ejercicio:

```java
ControladorLetras.iniciar();
ControladorWeb.iniciar();
ControladorVentas.iniciar();
```

---

# Que debes memorizar de esta solucion

No memorices cada nombre. Memoriza estos patrones:

```text
Scanner hasta Fin
objeto -> lista
objeto -> linea con ;
linea con ; -> objeto
fichero.escribirLinea(...)
PreparedStatement con ?
ResultSet -> objeto
buscar id por nombre
insertar resumen con ids
```
