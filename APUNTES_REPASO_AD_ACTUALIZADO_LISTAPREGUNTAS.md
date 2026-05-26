# Apuntes limpios del repositorio `Repaso_AD` actualizado

Este documento reúne la explicación de los dos proyectos del repositorio `Repaso_AD`, quitando las partes de sugerencias, problemas, veredictos y cierres de fase. Está actualizado con la implementación real de `ListaPreguntas.java` en el flujo del proyecto `AD_JDBC_MVC`.

---

## Índice

1. [Visión general del repositorio](#1-visión-general-del-repositorio)
2. [Proyecto `FicherosAD4`](#2-proyecto-ficherosad4)
3. [Proyecto `AD_JDBC_MVC`](#3-proyecto-ad_jdbc_mvc)
4. [Comparación entre los dos proyectos](#4-comparación-entre-los-dos-proyectos)
5. [Explicación oral preparada](#5-explicación-oral-preparada)

---

# 1. Visión general del repositorio

El repositorio `Repaso_AD` contiene dos proyectos Java de consola:

```text
Repaso_AD/
├── AD_JDBC_MVC/
└── FicherosAD4/
```

Los dos proyectos están relacionados con la asignatura de Acceso a Datos.

| Proyecto | Resumen |
|---|---|
| `FicherosAD4` | Aplicación de consola que registra incidencias provocadas por excepciones simuladas y las guarda en un fichero de texto. |
| `AD_JDBC_MVC` | Juego de preguntas tipo test que lee preguntas desde un fichero, las guarda en MySQL mediante JDBC y recupera preguntas aleatorias para jugar. |

## 1.1. Tecnologías usadas

| Proyecto | Lenguaje | Entrada/salida | Persistencia | Base de datos |
|---|---|---|---|---|
| `FicherosAD4` | Java | Consola | Fichero de texto | No |
| `AD_JDBC_MVC` | Java | Consola | Fichero + MySQL | Sí, mediante JDBC |

## 1.2. Paquetes principales

Los dos proyectos usan una estructura parecida:

```text
src/
├── Main.java
├── controlador/
├── modelo/
├── servicio/
├── repositorio/
└── vista/
```

En `AD_JDBC_MVC` también aparece:

```text
util/
```

donde se encuentra la clase `DBConnection`.

| Paquete | Función |
|---|---|
| `controlador` | Coordina el flujo principal del programa. |
| `modelo` | Representa los datos principales. |
| `servicio` | Contiene lógica intermedia. |
| `repositorio` | Accede físicamente a los ficheros. |
| `vista` | Muestra información y lee datos del usuario. |
| `util` | Contiene utilidades generales, como la conexión a base de datos. |

---

# 2. Proyecto `FicherosAD4`

## 2.1. Qué hace

`FicherosAD4` es una aplicación Java de consola que registra incidencias provocadas por excepciones simuladas.

El programa permite:

1. Introducir un nombre de usuario.
2. Mostrar un menú principal.
3. Registrar una excepción simulada.
4. Crear una incidencia con usuario, tipo de excepción, fecha y hora.
5. Guardar la incidencia en una lista en memoria.
6. Guardar la incidencia en un fichero de texto.
7. Buscar incidencias por usuario.
8. Buscar incidencias por rango de fechas.

## 2.2. Estructura

```text
FicherosAD4/
└── src/
    ├── Main.java
    ├── controlador/
    │   └── ControladorIncidencias.java
    ├── modelo/
    │   ├── Incidencia.java
    │   └── ListaIncidencias.java
    ├── repositorio/
    │   └── Fichero.java
    ├── servicio/
    │   └── ServicioFicheros.java
    └── vista/
        ├── Consola.java
        └── Escaner.java
```

## 2.3. Flujo general

```text
Main
↓
ControladorIncidencias.iniciar()
↓
Escaner pide el usuario
↓
ListaIncidencias carga incidencias desde fichero
↓
Consola muestra el menú
↓
Escaner lee la opción
↓
ControladorIncidencias ejecuta la acción elegida
```

Cuando se registra una excepción:

```text
Usuario elige tipo de excepción
↓
ControladorIncidencias lanza una excepción simulada
↓
El catch captura la excepción
↓
Se crea una Incidencia
↓
ListaIncidencias guarda la incidencia en memoria
↓
ServicioFicheros convierte la incidencia en texto
↓
Fichero escribe la línea en datos/incidencias.txt
```

## 2.4. Clases

| Clase | Función |
|---|---|
| `Main` | Arranca el programa. |
| `ControladorIncidencias` | Controla el menú y las acciones principales. |
| `Incidencia` | Representa una incidencia con usuario, excepción, fecha y hora. |
| `ListaIncidencias` | Guarda una lista de incidencias en memoria. |
| `ServicioFicheros` | Convierte incidencias en líneas de texto y líneas de texto en incidencias. |
| `Fichero` | Lee y escribe físicamente en el fichero. |
| `Consola` | Muestra menús y mensajes por pantalla. |
| `Escaner` | Lee datos introducidos por teclado. |

---

## 2.5. `Main.java`

### Para qué sirve

`Main.java` es el punto de entrada del programa.

```java
import controlador.ControladorIncidencias;

public class Main {
    public static void main(String[] args){

        ControladorIncidencias.iniciar();

    }
}
```

### Elementos importantes

| Elemento | Explicación |
|---|---|
| `import controlador.ControladorIncidencias;` | Permite usar la clase `ControladorIncidencias`. |
| `public class Main` | Declara la clase principal. |
| `public static void main(String[] args)` | Método principal que Java ejecuta al arrancar. |
| `ControladorIncidencias.iniciar();` | Llama al controlador para iniciar el programa. |

---

## 2.6. `ControladorIncidencias.java`

### Para qué sirve

`ControladorIncidencias` coordina el funcionamiento del proyecto.

Se encarga de:

- pedir el usuario,
- crear la lista de incidencias,
- mostrar el menú principal,
- leer la opción del usuario,
- registrar excepciones,
- buscar incidencias por usuario,
- buscar incidencias por fechas,
- controlar cuándo termina el programa.

### Relaciones

| Clase | Relación |
|---|---|
| `Incidencia` | Crea objetos `Incidencia` al registrar una excepción. |
| `ListaIncidencias` | Guarda y consulta incidencias. |
| `Consola` | Muestra menús y mensajes. |
| `Escaner` | Lee datos del usuario. |
| `LocalDate` | Maneja fechas. |
| `LocalTime` | Obtiene la hora actual. |
| `IOException` | Simula una excepción de entrada/salida. |
| `FileNotFoundException` | Simula una excepción de fichero no encontrado. |

### Variables de `iniciar()`

| Variable | Tipo | Función |
|---|---|---|
| `usuario` | `String` | Guarda el nombre del usuario actual. |
| `datos` | `ListaIncidencias` | Guarda la lista de incidencias cargadas. |
| `opcion` | `int` | Guarda la opción elegida en el menú. |
| `salir` | `boolean` | Controla si el programa sigue ejecutándose. |
| `usuarioBuscado` | `String` | Guarda el usuario introducido en una búsqueda. |

### Método `iniciar()`

Primero pide el usuario:

```java
usuario = Escaner.pedirString("Introduce tu nombre de usuario:");
```

Después crea la lista de incidencias:

```java
datos = new ListaIncidencias();
```

Luego inicializa el control del bucle:

```java
salir = false;
```

Mientras `salir` sea `false`, se muestra el menú y se procesa la opción elegida:

```java
while (!salir) {
    Consola.menuPrincipal(usuario);
    opcion = Escaner.pedirInt("Selecciona una opción:");

    switch (opcion) {
        case 1:
            Consola.menuExcepciones(usuario, datos);
            break;
        case 2:
            usuarioBuscado = Escaner.pedirString("Introduce el usuario a buscar:");
            buscarPorUsuario(usuarioBuscado, datos);
            break;
        case 3:
            buscarPorFechas(datos);
            break;
        case 0:
            salir = true;
            break;
        default:
            Consola.mostrarError("Opción no válida, intenta de nuevo.");
            break;
    }
}
```

### Método `registrarExcepcion()`

Recibe:

| Parámetro | Tipo | Función |
|---|---|---|
| `usuario` | `String` | Usuario que provoca la incidencia. |
| `datos` | `ListaIncidencias` | Lista donde se guarda la incidencia. |
| `tipoError` | `int` | Número que indica qué excepción se simula. |

Según la opción elegida, lanza una excepción:

```java
switch (tipoError) {
    case 1:
        throw new IOException("Simulación de IOException");
    case 2:
        throw new FileNotFoundException("Simulación de FileNotFoundException");
    case 3:
        throw new Exception("Simulación de excepción genérica");
    default:
        Consola.mostrarAdvertencia("Opción no válida.");
}
```

El `catch` captura la excepción y crea una incidencia:

```java
Incidencia nueva = new Incidencia(
        usuario,
        e.getClass().getSimpleName(),
        LocalDate.now(),
        LocalTime.now().toString()
);
```

| Valor | Explicación |
|---|---|
| `usuario` | Usuario actual. |
| `e.getClass().getSimpleName()` | Nombre de la excepción capturada. |
| `LocalDate.now()` | Fecha actual. |
| `LocalTime.now().toString()` | Hora actual convertida a texto. |

Después se guarda la incidencia:

```java
datos.guardarIncidencia(nueva);
```

### Método `buscarPorUsuario()`

Recorre todas las incidencias y muestra las que coinciden con el usuario indicado.

```java
for (Incidencia i : datos.obtenerIncidencias()) {
    if (i.getUsuario().equalsIgnoreCase(usuario)) {
        System.out.println(i);
        encontrada = true;
    }
}
```

`equalsIgnoreCase()` compara ignorando mayúsculas y minúsculas.

### Método `buscarPorFechas()`

Pide dos fechas:

```java
fechaInicioStr = Escaner.pedirString("Introduce la fecha de inicio (YYYY-MM-DD):");
fechaFinStr = Escaner.pedirString("Introduce la fecha de fin (YYYY-MM-DD):");
```

Las convierte a `LocalDate`:

```java
inicio = LocalDate.parse(fechaInicioStr);
fin = LocalDate.parse(fechaFinStr);
```

Después recorre las incidencias y comprueba si cada fecha está dentro del rango:

```java
if (!i.getFecha().isBefore(inicio) && !i.getFecha().isAfter(fin)) {
    System.out.println(i);
    encontrada = true;
}
```

La condición significa que la fecha no está antes del inicio y no está después del fin.

---

## 2.7. `Incidencia.java`

### Para qué sirve

`Incidencia` es el modelo principal de `FicherosAD4`.

Representa una incidencia registrada.

### Atributos

```java
private String usuario;
private String excepcion;
private LocalDate fecha;
private String hora;
```

| Atributo | Tipo | Función |
|---|---|---|
| `usuario` | `String` | Guarda el usuario relacionado con la incidencia. |
| `excepcion` | `String` | Guarda el tipo de excepción. |
| `fecha` | `LocalDate` | Guarda la fecha de la incidencia. |
| `hora` | `String` | Guarda la hora de la incidencia. |

### Constructor

```java
public Incidencia(String usuario, String excepcion, LocalDate fecha, String hora) {
    this.usuario = usuario;
    this.excepcion = excepcion;
    this.fecha = fecha;
    this.hora = hora;
}
```

`this.usuario` se refiere al atributo de la clase, mientras que `usuario` es el parámetro recibido.

### Getters

```java
public String getUsuario() {
    return usuario;
}

public String getExcepcion() {
    return excepcion;
}

public LocalDate getFecha() {
    return fecha;
}

public String getHora() {
    return hora;
}
```

Permiten consultar los datos privados de la incidencia.

### Método `toString()`

```java
@Override
public String toString() {
    return "Usuario: " + usuario +
            " | Excepción: " + excepcion +
            " | Fecha: " + fecha +
            " | Detalle: " + hora;
}
```

Convierte una incidencia en texto legible.

---

## 2.8. `ListaIncidencias.java`

### Para qué sirve

`ListaIncidencias` guarda una colección de incidencias en memoria.

También carga incidencias desde fichero al crearse y guarda nuevas incidencias en el fichero.

### Atributo

```java
private List<Incidencia> incidencias;
```

| Atributo | Tipo | Función |
|---|---|---|
| `incidencias` | `List<Incidencia>` | Guarda todas las incidencias del programa. |

### Constructor

```java
public ListaIncidencias() {
    incidencias = ServicioFicheros.leerIncidencias();
}
```

Al crear una `ListaIncidencias`, se cargan las incidencias previamente guardadas.

### Método `guardarIncidencia()`

```java
public void guardarIncidencia(Incidencia i) {
    incidencias.add(i);
    ServicioFicheros.guardarIncidencia(i);
}
```

Hace dos cosas:

1. Añade la incidencia a la lista en memoria.
2. La guarda en el fichero.

### Método `obtenerIncidencias()`

```java
public List<Incidencia> obtenerIncidencias() {
    return incidencias;
}
```

Devuelve la lista completa de incidencias.

---

## 2.9. `ServicioFicheros.java`

### Para qué sirve

`ServicioFicheros` convierte objetos `Incidencia` en líneas de texto y líneas de texto en objetos `Incidencia`.

El formato usado es:

```text
usuario;excepcion;fecha;hora
```

### Constante principal

```java
private static final String RUTA = "datos/incidencias.txt";
```

| Elemento | Explicación |
|---|---|
| `private` | Solo se puede usar dentro de esta clase. |
| `static` | Pertenece a la clase, no a un objeto concreto. |
| `final` | No se puede modificar. |
| `RUTA` | Guarda la ruta del fichero de incidencias. |

### Método `guardarIncidencia()`

```java
public static void guardarIncidencia(Incidencia incidencia) {
    Fichero fichero = new Fichero(RUTA);

    String linea = incidencia.getUsuario() + ";" +
            incidencia.getExcepcion() + ";" +
            incidencia.getFecha() + ";" +
            incidencia.getHora();

    fichero.escribirLinea(linea);
}
```

Pasos:

1. Crea un objeto `Fichero` con la ruta.
2. Construye una línea de texto separada por `;`.
3. Escribe esa línea en el fichero.

Ejemplo:

```text
alvaro;IOException;2026-05-25;18:30:10
```

### Método `leerIncidencias()`

```java
public static List<Incidencia> leerIncidencias() {
    Fichero fichero = new Fichero(RUTA);
    List<String> lineas = fichero.leerFichero();
    List<Incidencia> lista = new ArrayList<>();
    String[] partes;
    Incidencia inc;

    for (String linea : lineas) {
        partes = linea.split(";");
        if (partes.length == 4) {
            inc = new Incidencia(
                    partes[0],
                    partes[1],
                    LocalDate.parse(partes[2]),
                    partes[3]
            );
            lista.add(inc);
        }
    }

    return lista;
}
```

Pasos:

1. Lee todas las líneas del fichero.
2. Recorre cada línea.
3. Divide cada línea con `split(";")`.
4. Comprueba que haya 4 partes.
5. Crea una `Incidencia`.
6. Añade la incidencia a una lista.
7. Devuelve la lista.

---

## 2.10. `Fichero.java` de `FicherosAD4`

### Para qué sirve

`Fichero` trabaja directamente con el sistema de ficheros.

Permite:

- escribir una línea,
- leer todas las líneas de un fichero.

### Atributo

```java
private String ruta;
```

Guarda la ruta del fichero.

### Constructor

```java
public Fichero(String ruta) {
    this.ruta = ruta;
}
```

Crea un objeto `Fichero` asociado a una ruta concreta.

### Método `escribirLinea()`

```java
public void escribirLinea(String dato) {
    FileWriter fw = null;
    BufferedWriter bw = null;

    try {
        fw = new FileWriter(ruta, true);
        bw = new BufferedWriter(fw);
        bw.write(dato);
        bw.newLine();
    } catch (IOException e) {
        System.err.println("Error al escribir en el fichero: " + e.getMessage());
    } finally {
        try {
            if (bw != null) {
                bw.close();
            }
            if (fw != null){
                fw.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar el fichero.");
        }
    }
}
```

Elementos importantes:

| Elemento | Explicación |
|---|---|
| `FileWriter(ruta, true)` | Abre el fichero en modo añadir. |
| `BufferedWriter` | Permite escribir de forma cómoda. |
| `bw.write(dato)` | Escribe el texto recibido. |
| `bw.newLine()` | Añade un salto de línea. |
| `finally` | Cierra los recursos. |

### Método `leerFichero()`

```java
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
        System.err.println("Error al leer el fichero: " + e.getMessage());
    } finally {
        try {
            if (br != null) {
                br.close();
            }
            if (fr != null) {
                fr.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar el fichero.");
        }
    }

    return lineas;
}
```

Pasos:

1. Crea un objeto `File`.
2. Comprueba si el fichero existe.
3. Abre `FileReader`.
4. Envuelve con `BufferedReader`.
5. Lee línea por línea.
6. Añade cada línea a una lista.
7. Cierra recursos.
8. Devuelve la lista.

---

## 2.11. `Escaner.java` de `FicherosAD4`

### Para qué sirve

`Escaner` lee datos introducidos por teclado usando `Scanner`.

### Atributo

```java
private static final Scanner sc = new Scanner(System.in);
```

| Elemento | Explicación |
|---|---|
| `private` | Solo se usa dentro de `Escaner`. |
| `static` | Pertenece a la clase. |
| `final` | No se puede reasignar. |
| `Scanner` | Clase de Java para leer entrada. |
| `System.in` | Entrada estándar del teclado. |

### Método `pedirInt()`

```java
public static int pedirInt(String mensaje) {
    int numero;
    System.out.println(mensaje + ": ");
    numero = sc.nextInt();
    sc.nextLine();
    return numero;
}
```

Muestra un mensaje, lee un entero y limpia el salto de línea pendiente.

### Método `pedirString()`

```java
public static String pedirString(String mensaje) {
    String texto;
    System.out.println(mensaje);
    texto = sc.nextLine();
    return texto;
}
```

Muestra un mensaje y lee una línea completa.

### Método `mostrarString()`

```java
public static void mostrarString(String mensaje) {
    System.out.println(mensaje);
}
```

Muestra un texto por consola.

---

# 3. Proyecto `AD_JDBC_MVC`

## 3.1. Qué hace

`AD_JDBC_MVC` es una aplicación Java de consola que funciona como un juego de preguntas tipo test.

El programa:

1. Lee preguntas desde `data/preguntas.txt`.
2. Convierte las líneas del fichero en objetos `Pregunta`.
3. Borra los datos previos de la base de datos.
4. Guarda las preguntas en MySQL mediante JDBC.
5. Pide al usuario cuántas preguntas quiere responder.
6. Obtiene preguntas aleatorias desde MySQL.
7. Muestra las preguntas por consola.
8. Lee las respuestas del usuario.
9. Comprueba los aciertos.
10. Muestra los resultados.
11. Limpia la base de datos.

## 3.2. Estructura

```text
AD_JDBC_MVC/
├── data/
│   └── preguntas.txt
└── src/
    ├── Main.java
    ├── controlador/
    │   └── ControladorPreguntas.java
    ├── modelo/
    │   ├── Pregunta.java
    │   └── ListaPreguntas.java
    ├── repositorio/
    │   └── Fichero.java
    ├── servicio/
    │   ├── ServicioFichero.java
    │   └── ServicioPregunta.java
    ├── util/
    │   └── DBConnection.java
    └── vista/
        ├── Consola.java
        └── Escaner.java
```

## 3.3. Flujo general

```text
Main
↓
ControladorPreguntas
↓
ServicioFichero
↓
Fichero lee data/preguntas.txt
↓
ServicioFichero convierte líneas en una ListaPreguntas
↓
ServicioPregunta guarda las preguntas de ListaPreguntas en MySQL
↓
Consola pide cantidad de preguntas
↓
ServicioPregunta obtiene preguntas aleatorias
↓
Consola muestra preguntas y pide respuestas
↓
ControladorPreguntas comprueba aciertos
↓
Consola muestra resultados
```

## 3.4. Clases

| Clase | Función |
|---|---|
| `Main` | Arranca el programa. |
| `ControladorPreguntas` | Coordina todo el juego. |
| `Pregunta` | Representa una pregunta con id, enunciado, opciones y respuesta correcta. |
| `ListaPreguntas` | Representa una colección de preguntas y se usa para transportar las preguntas cargadas desde el fichero antes de guardarlas en la base de datos. |
| `ServicioFichero` | Convierte el fichero de preguntas en una `ListaPreguntas` formada por objetos `Pregunta`. |
| `Fichero` | Lee físicamente las líneas del fichero. |
| `ServicioPregunta` | Guarda las preguntas de `ListaPreguntas`, consulta preguntas aleatorias y borra datos en MySQL. |
| `DBConnection` | Centraliza la conexión con MySQL. |
| `Consola` | Muestra preguntas, pide respuestas y muestra resultados. |
| `Escaner` | Lee datos desde teclado. |

---

## 3.5. `Main.java`

### Para qué sirve

`Main.java` es el punto de entrada del proyecto.

```java
import controlador.ControladorPreguntas;

public class Main {
    public static void main(String[] args) {

        ControladorPreguntas c = new ControladorPreguntas();

        c.iniciar();
    }
}
```

| Elemento | Explicación |
|---|---|
| `import controlador.ControladorPreguntas;` | Permite usar la clase `ControladorPreguntas`. |
| `ControladorPreguntas c = new ControladorPreguntas();` | Crea el controlador del programa. |
| `c.iniciar();` | Inicia el flujo del juego. |

---

## 3.6. `ControladorPreguntas.java`

### Para qué sirve

`ControladorPreguntas` coordina todo el juego.

Se encarga de:

- crear los servicios,
- cargar preguntas desde fichero,
- guardar preguntas en la base de datos,
- pedir cantidad de preguntas,
- obtener preguntas aleatorias,
- mostrar preguntas,
- corregir respuestas,
- contar aciertos,
- mostrar resultados.

### Atributos

```java
private ServicioFichero sFichero;
private ServicioPregunta sPregunta;
private Consola consola;
```

| Atributo | Tipo | Función |
|---|---|---|
| `sFichero` | `ServicioFichero` | Obtiene preguntas desde el fichero. |
| `sPregunta` | `ServicioPregunta` | Trabaja con la base de datos. |
| `consola` | `Consola` | Interactúa con el usuario. |

### Constructor

```java
public ControladorPreguntas() {
    this.sFichero = new ServicioFichero("data/preguntas.txt");
    this.sPregunta = new ServicioPregunta();
    this.consola = new Consola();
}
```

Inicializa los objetos necesarios para el programa.

### Variables de `iniciar()`

| Variable | Tipo | Función |
|---|---|---|
| `listaFichero` | `ListaPreguntas` | Colección de preguntas leídas desde el fichero. |
| `listaJuego` | `List<Pregunta>` | Preguntas aleatorias usadas en la partida. |
| `resumen` | `List<String>` | Resumen de respuestas correctas e incorrectas. |
| `cantidad` | `int` | Cantidad de preguntas solicitadas por el usuario. |
| `aciertos` | `int` | Número de respuestas correctas. |
| `i` | `int` | Contador de pregunta actual. |
| `respuestaUser` | `String` | Respuesta introducida por el usuario. |
| `correcta` | `boolean` | Indica si la respuesta fue correcta. |

### Cargar fichero y guardar en base de datos

```java
consola.mostrarMensaje("Cargando fichero y guardando en BD...");
listaFichero = sFichero.obtenerPreguntasDeFichero();

sPregunta.borrarTodo();
sPregunta.guardarListaEnBD(listaFichero);
```

Pasos:

1. Muestra un mensaje.
2. Obtiene una `ListaPreguntas` con las preguntas leídas desde el fichero.
3. Borra datos previos de la base de datos.
4. Guarda en MySQL las preguntas contenidas dentro de `ListaPreguntas`.

### Pedir cantidad y jugar

```java
cantidad = consola.pedirNumeroPreguntas();
listaJuego = sPregunta.obtenerPreguntasAleatorias(cantidad);
```

El usuario indica cuántas preguntas quiere responder y se recuperan preguntas aleatorias desde la base de datos.

### Recorrer preguntas

```java
i = 1;
for (Pregunta p : listaJuego) {
    respuestaUser = consola.mostrarPreguntaYPedirRespuesta(p, i);

    correcta = p.getRespuestaCorrecta().toLowerCase().contains(respuestaUser.toLowerCase());

    if (correcta) {
        aciertos = aciertos + 1;
        resumen.add("P" + i + ": Correcta (" + p.getRespuestaCorrecta() + ")");
    } else {
        resumen.add("P" + i + ": Incorrecta. Era: " + p.getRespuestaCorrecta());
    }
    i = i + 1;
}
```

Pasos:

1. Muestra una pregunta.
2. Lee la respuesta del usuario.
3. Compara la respuesta con la correcta.
4. Suma aciertos si corresponde.
5. Añade una línea al resumen.
6. Pasa a la siguiente pregunta.

### Mostrar resultados

```java
consola.mostrarResultados(aciertos, listaJuego.size(), resumen);
```

Muestra los aciertos y el resumen de la partida.

---

## 3.7. `Pregunta.java`

### Para qué sirve

`Pregunta` es el modelo principal del juego.

Representa una pregunta con:

- identificador,
- enunciado,
- opciones,
- respuesta correcta.

### Atributos

```java
private int id;
private String enunciado;
private List<String> opciones;
private String respuestaCorrecta;
```

| Atributo | Tipo | Función |
|---|---|---|
| `id` | `int` | Identificador de la pregunta. |
| `enunciado` | `String` | Texto de la pregunta. |
| `opciones` | `List<String>` | Lista de opciones posibles. |
| `respuestaCorrecta` | `String` | Respuesta correcta. |

### Constructor

```java
public Pregunta(String enunciado, int id, List<String> opciones, String respuestaCorrecta) {
    this.enunciado = enunciado;
    this.id = id;
    this.opciones = opciones;
    this.respuestaCorrecta = respuestaCorrecta;
}
```

Crea una pregunta completa.

### Métodos

| Método | Función |
|---|---|
| `getEnunciado()` | Devuelve el enunciado. |
| `setEnunciado()` | Cambia el enunciado. |
| `getId()` | Devuelve el ID. |
| `setId()` | Cambia el ID. |
| `getOpciones()` | Devuelve la lista de opciones. |
| `setOpciones()` | Cambia la lista de opciones. |
| `getRespuestaCorrecta()` | Devuelve la respuesta correcta. |
| `setRespuestaCorrecta()` | Cambia la respuesta correcta. |

---

## 3.8. `ListaPreguntas.java`

### Para qué sirve

`ListaPreguntas` representa una colección de objetos `Pregunta`.

Ahora sí participa en el flujo real del proyecto:

```text
ServicioFichero
↓
crea una ListaPreguntas
↓
ControladorPreguntas
↓
ServicioPregunta.guardarListaEnBD(listaFichero)
```

Es decir, el fichero ya no devuelve directamente un `List<Pregunta>`, sino una `ListaPreguntas`.

### Imports

```java
import java.util.ArrayList;
import java.util.List;
```

| Import | Función |
|---|---|
| `ArrayList` | Permite crear una lista vacía dentro del constructor vacío. |
| `List` | Permite declarar el atributo como una lista de preguntas. |

### Atributo

```java
private List<Pregunta> listaPreguntas;
```

| Atributo | Tipo | Función |
|---|---|---|
| `listaPreguntas` | `List<Pregunta>` | Contiene todas las preguntas cargadas desde el fichero. |

### Constructor vacío

```java
public ListaPreguntas() {
    this.listaPreguntas = new ArrayList<>();
}
```

Este constructor permite crear una `ListaPreguntas` vacía.

Se usa en `ServicioFichero` para crear la colección e ir añadiendo preguntas una a una con `addPregunta()`.

### Constructor con lista

```java
public ListaPreguntas(List<Pregunta> listaPreguntas) {
    this.listaPreguntas = listaPreguntas;
}
```

Este constructor permite crear una `ListaPreguntas` a partir de una lista ya existente.

### Métodos

```java
public List<Pregunta> getListaPreguntas() {
    return listaPreguntas;
}

public void setListaPreguntas(List<Pregunta> listaPreguntas) {
    this.listaPreguntas = listaPreguntas;
}

public void addPregunta(Pregunta p) {
    listaPreguntas.add(p);
}
```

| Método | Función |
|---|---|
| `getListaPreguntas()` | Devuelve la lista interna de preguntas. |
| `setListaPreguntas()` | Cambia la lista interna por otra lista. |
| `addPregunta(Pregunta p)` | Añade una pregunta a la colección. |

### Uso dentro del proyecto

En `ServicioFichero`, se crea una colección vacía:

```java
ListaPreguntas listaFinal = new ListaPreguntas();
```

Después se añaden preguntas:

```java
listaFinal.addPregunta(new Pregunta(enunciado, 0, opciones, correcta));
```

En `ServicioPregunta`, se recorre su lista interna para guardar cada pregunta en MySQL:

```java
for (Pregunta p : lista.getListaPreguntas()) {
    ...
}
```

---

## 3.9. `ServicioFichero.java`

### Para qué sirve

`ServicioFichero` convierte el fichero `data/preguntas.txt` en una `ListaPreguntas`.

Su responsabilidad es interpretar las líneas del fichero y crear objetos `Pregunta`, pero ahora esos objetos se guardan dentro de una clase contenedora llamada `ListaPreguntas`.

### Imports principales

```java
import modelo.ListaPreguntas;
import modelo.Pregunta;
import repositorio.Fichero;

import java.util.ArrayList;
import java.util.List;
```

| Import | Función |
|---|---|
| `ListaPreguntas` | Permite devolver una colección propia de preguntas. |
| `Pregunta` | Permite crear objetos pregunta a partir del fichero. |
| `Fichero` | Permite leer físicamente el archivo. |
| `ArrayList` | Permite crear la lista de opciones. |
| `List` | Permite trabajar con listas de líneas y opciones. |

### Atributo

```java
private Fichero fichero;
```

Guarda el objeto encargado de leer físicamente el fichero.

### Constructor

```java
public ServicioFichero(String ruta) {
    this.fichero = new Fichero(ruta);
}
```

Crea un servicio asociado a una ruta de fichero.

### Método `obtenerPreguntasDeFichero()`

Este método devuelve una `ListaPreguntas`:

```java
public ListaPreguntas obtenerPreguntasDeFichero()
```

El método:

1. Lee las líneas del fichero.
2. Crea una `ListaPreguntas` vacía.
3. Recorre el contenido de 7 en 7 líneas.
4. Extrae enunciado, opciones y respuesta.
5. Crea objetos `Pregunta`.
6. Añade cada `Pregunta` a `ListaPreguntas`.
7. Devuelve la `ListaPreguntas`.

Formato esperado:

```text
enunciado
opción A
opción B
opción C
opción D
ANSWER: letra
línea vacía
```

Código principal actualizado:

```java
lineas = fichero.leerFichero();

for (int i = 0; i < lineas.size(); i = i + 7) {
    if ((i + 5) < lineas.size()) {
        enunciado = lineas.get(i);
        opA = lineas.get(i + 1);
        opB = lineas.get(i + 2);
        opC = lineas.get(i + 3);
        opD = lineas.get(i + 4);
        lineaResp = lineas.get(i + 5);

        opciones = new ArrayList<>();
        opciones.add(opA);
        opciones.add(opB);
        opciones.add(opC);
        opciones.add(opD);

        char letra = lineaResp.charAt(lineaResp.length() - 1);
        correcta = "";

        switch (letra) {
            case 'A':
                correcta = opA;
                break;
            case 'B':
                correcta = opB;
                break;
            case 'C':
                correcta = opC;
                break;
            case 'D':
                correcta = opD;
                break;
            default:
                System.err.println("Error: Letra de respuesta no reconocida: " + letra);
                break;
        }

        listaFinal.addPregunta(new Pregunta(enunciado, 0, opciones, correcta));
    }
}
```

### Explicación del cambio principal

Antes se usaba una lista normal:

```java
List<Pregunta> listaFinal = new ArrayList<>();
```

Ahora se usa la clase propia del modelo:

```java
ListaPreguntas listaFinal = new ListaPreguntas();
```

Y antes se añadía así:

```java
listaFinal.add(new Pregunta(enunciado, 0, opciones, correcta));
```

Ahora se añade así:

```java
listaFinal.addPregunta(new Pregunta(enunciado, 0, opciones, correcta));
```

### Explicación del `switch`

Si la línea es:

```text
ANSWER: C
```

se obtiene la letra `C`.

Después el `switch` asigna:

```text
correcta = opC
```

---

## 3.10. `Fichero.java` de `AD_JDBC_MVC`

### Para qué sirve

`Fichero` lee físicamente las líneas de un fichero.

En este proyecto se usa para leer:

```text
data/preguntas.txt
```

### Atributo

```java
private String ruta;
```

Guarda la ruta del fichero.

### Constructor

```java
public Fichero(String ruta) {
    this.ruta = ruta;
}
```

Crea un objeto `Fichero` asociado a una ruta.

### Método `leerFichero()`

```java
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
        System.err.println("Error al leer el fichero: " + e.getMessage());
    } finally {

        try {
            if (br != null){
                br.close();
            }
            if (fr != null){
                fr.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar el fichero.");
        }
    }

    return lineas;
}
```

Pasos:

1. Crea un objeto `File`.
2. Comprueba si existe.
3. Abre `FileReader`.
4. Envuelve con `BufferedReader`.
5. Lee línea por línea.
6. Guarda cada línea en una lista.
7. Cierra recursos.
8. Devuelve la lista.

---

## 3.11. `ServicioPregunta.java`

### Para qué sirve

`ServicioPregunta` trabaja con la base de datos MySQL mediante JDBC.

Permite:

- guardar preguntas en la base de datos,
- obtener preguntas aleatorias,
- borrar los datos de las tablas.

### Constructor

```java
public ServicioPregunta() {}
```

Permite crear objetos `ServicioPregunta`.

### Método `guardarListaEnBD()`

Recibe una `ListaPreguntas` y guarda en MySQL las preguntas que contiene.

Variables principales:

```java
Connection conn = null;
PreparedStatement psPregunta = null;
PreparedStatement psRespuesta = null;
ResultSet rs = null;
```

| Variable | Tipo | Función |
|---|---|---|
| `conn` | `Connection` | Conexión con MySQL. |
| `psPregunta` | `PreparedStatement` | Inserta una pregunta. |
| `psRespuesta` | `PreparedStatement` | Inserta la respuesta correcta. |
| `rs` | `ResultSet` | Recibe el ID generado por MySQL. |

Consultas SQL:

```java
String sqlPregunta = "INSERT INTO preguntas (enunciado, opcion1, opcion2, opcion3, opcion4) VALUES (?, ?, ?, ?, ?)";
String sqlRespuesta = "INSERT INTO respuestas_correctas (id_pregunta, respuesta) VALUES (?, ?)";
```

#### Consulta para insertar pregunta

```sql
INSERT INTO preguntas (enunciado, opcion1, opcion2, opcion3, opcion4)
VALUES (?, ?, ?, ?, ?)
```

#### Consulta para insertar respuesta

```sql
INSERT INTO respuestas_correctas (id_pregunta, respuesta)
VALUES (?, ?)
```

### `PreparedStatement`

`PreparedStatement` prepara una consulta con parámetros.

Ejemplo:

```java
psPregunta.setString(1, p.getEnunciado());
```

Sustituye el primer `?` por el enunciado de la pregunta.

### Recorrer la `ListaPreguntas`

Para guardar las preguntas, el método recorre la lista interna de `ListaPreguntas`:

```java
for (Pregunta p : lista.getListaPreguntas()) {
    ...
}
```

### Guardar una pregunta

```java
psPregunta = conn.prepareStatement(sqlPregunta, Statement.RETURN_GENERATED_KEYS);
psPregunta.setString(1, p.getEnunciado());
psPregunta.setString(2, p.getOpciones().get(0));
psPregunta.setString(3, p.getOpciones().get(1));
psPregunta.setString(4, p.getOpciones().get(2));
psPregunta.setString(5, p.getOpciones().get(3));

psPregunta.executeUpdate();
```

`executeUpdate()` se usa para consultas que modifican datos.

### Recuperar el ID generado

```java
rs = psPregunta.getGeneratedKeys();
idGenerado = 0;
if (rs.next()) {
    idGenerado = rs.getInt(1);
}
```

MySQL genera automáticamente el ID de la pregunta.

Ese ID se recupera para guardar la respuesta correcta asociada.

### Guardar la respuesta correcta

```java
psRespuesta = conn.prepareStatement(sqlRespuesta);
psRespuesta.setInt(1, idGenerado);
psRespuesta.setString(2, p.getRespuestaCorrecta());
psRespuesta.executeUpdate();
```

| Campo | Valor |
|---|---|
| `id_pregunta` | ID generado por MySQL. |
| `respuesta` | Respuesta correcta de la pregunta. |

### Método `obtenerPreguntasAleatorias()`

Recibe una cantidad y devuelve una lista de preguntas aleatorias.

Consulta SQL:

```sql
SELECT p.id, p.enunciado, p.opcion1, p.opcion2, p.opcion3, p.opcion4, r.respuesta
FROM preguntas p JOIN respuestas_correctas r ON p.id = r.id_pregunta
ORDER BY RAND() LIMIT ?
```

| Parte | Explicación |
|---|---|
| `SELECT` | Indica qué columnas se recuperan. |
| `FROM preguntas p` | Lee desde la tabla `preguntas` con alias `p`. |
| `JOIN respuestas_correctas r` | Une preguntas con respuestas correctas. |
| `ON p.id = r.id_pregunta` | Relaciona ambas tablas. |
| `ORDER BY RAND()` | Ordena aleatoriamente. |
| `LIMIT ?` | Limita la cantidad de preguntas devueltas. |

Ejecución:

```java
conn = DBConnection.getConnection();
ps = conn.prepareStatement(sql);
ps.setInt(1, cantidad);
rs = ps.executeQuery();
```

`executeQuery()` se usa para consultas `SELECT`.

Después se recorre el `ResultSet`:

```java
while (rs.next()) {
    List<String> opcionesAux = new ArrayList<>();

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
```

### `ResultSet`

`ResultSet` representa los resultados de una consulta `SELECT`.

Funciona como un cursor:

```text
rs.next() avanza a la siguiente fila.
Devuelve true si hay fila.
Devuelve false si no quedan más filas.
```

### Método `borrarTodo()`

Borra los datos de las tablas:

```java
stmt.executeUpdate("DELETE FROM respuestas_correctas");
stmt.executeUpdate("DELETE FROM preguntas");
```

Después reinicia los contadores:

```java
stmt.executeUpdate("ALTER TABLE preguntas AUTO_INCREMENT = 1");
stmt.executeUpdate("ALTER TABLE respuestas_correctas AUTO_INCREMENT = 1");
```

`AUTO_INCREMENT` es el contador que MySQL usa para generar IDs automáticos.

---

## 3.12. `DBConnection.java`

### Para qué sirve

`DBConnection` centraliza la conexión con MySQL.

### Constantes

```java
private static final String URL = "jdbc:mysql://localhost:3306/jdbc_preguntas?useSSL=false&serverTimezone=UTC";
private static final String USER = "root";
private static final String PASSWORD = "";
```

| Constante | Función |
|---|---|
| `URL` | Dirección de conexión a la base de datos. |
| `USER` | Usuario de MySQL. |
| `PASSWORD` | Contraseña de MySQL. |

### Significado de la URL

```text
jdbc:mysql://localhost:3306/jdbc_preguntas?useSSL=false&serverTimezone=UTC
```

| Parte | Significado |
|---|---|
| `jdbc:mysql` | Conexión JDBC con MySQL. |
| `localhost` | La base de datos está en el propio ordenador. |
| `3306` | Puerto habitual de MySQL. |
| `jdbc_preguntas` | Nombre de la base de datos. |
| `useSSL=false` | Desactiva SSL. |
| `serverTimezone=UTC` | Define la zona horaria. |

### Atributo de conexión

```java
private static Connection connection = null;
```

Guarda una conexión reutilizable.

### Constructor privado

```java
private DBConnection(){}
```

Impide crear objetos de esta clase.

### Método `getConnection()`

```java
public static Connection getConnection() throws SQLException {

    if (connection == null || connection.isClosed()){
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }
    return connection;
}
```

Pasos:

1. Comprueba si no hay conexión o si está cerrada.
2. Si hace falta, crea una nueva conexión.
3. Devuelve la conexión.

### `DriverManager`

`DriverManager` abre conexiones JDBC.

```java
DriverManager.getConnection(URL, USER, PASSWORD)
```

abre la conexión usando la URL, usuario y contraseña.

---

## 3.13. `Escaner.java` de `AD_JDBC_MVC`

### Para qué sirve

`Escaner` centraliza la lectura por teclado.

### Atributo

```java
private static Scanner sc = new Scanner(System.in);
```

Crea un `Scanner` para leer desde teclado.

### Método `leerString()`

```java
public static String leerString() {
    return sc.nextLine();
}
```

Lee una línea completa.

### Método `leerInt()`

```java
public static int leerInt() {
    int numero;
    while (!sc.hasNextInt()) {
        System.out.println("Introduce un número válido:");
        sc.next();
    }
    numero = sc.nextInt();
    sc.nextLine();
    return numero;
}
```

Pasos:

1. Comprueba si lo escrito es un entero.
2. Si no lo es, muestra un aviso.
3. Descarta la entrada incorrecta.
4. Lee el entero válido.
5. Limpia el salto de línea.
6. Devuelve el número.

---

# 4. Comparación entre los dos proyectos

## 4.1. Tabla comparativa

| Aspecto | `FicherosAD4` | `AD_JDBC_MVC` |
|---|---|---|
| Objetivo | Registrar incidencias de excepciones. | Juego de preguntas tipo test. |
| Entrada principal | Usuario y menú. | Fichero de preguntas y respuestas del usuario. |
| Almacenamiento | Fichero de texto. | Fichero de origen + MySQL. |
| Modelo principal | `Incidencia` | `Pregunta` |
| Controlador | `ControladorIncidencias` | `ControladorPreguntas` |
| Vista | `Consola`, `Escaner` | `Consola`, `Escaner` |
| Servicio | `ServicioFicheros` | `ServicioFichero`, `ServicioPregunta` |
| Repositorio | `Fichero` | `Fichero` |
| Base de datos | No | Sí |
| JDBC | No | Sí |
| SQL | No | Sí |
| Ficheros | Lectura y escritura | Lectura |
| Tipo de datos guardados | Incidencias | Preguntas y respuestas |

## 4.2. Diferencia principal

```text
FicherosAD4 usa un fichero como almacenamiento principal.
AD_JDBC_MVC usa un fichero como origen de datos y MySQL como base de datos durante el juego.
```

## 4.3. Diferencia de flujo

### `FicherosAD4`

```text
Excepción simulada
↓
Incidencia
↓
Lista en memoria
↓
Fichero de texto
```

### `AD_JDBC_MVC`

```text
Fichero de preguntas
↓
Objetos Pregunta
↓
Base de datos MySQL
↓
Preguntas aleatorias
↓
Juego por consola
```

## 4.4. Diferencia entre modelos

| Proyecto | Modelo | Datos que guarda |
|---|---|---|
| `FicherosAD4` | `Incidencia` | Usuario, excepción, fecha y hora. |
| `AD_JDBC_MVC` | `Pregunta` | ID, enunciado, opciones y respuesta correcta. |

## 4.5. Diferencia entre servicios

| Proyecto | Servicio | Función |
|---|---|---|
| `FicherosAD4` | `ServicioFicheros` | Convierte incidencias a texto y texto a incidencias. |
| `AD_JDBC_MVC` | `ServicioFichero` | Convierte el fichero de preguntas en una `ListaPreguntas` formada por objetos `Pregunta`. |
| `AD_JDBC_MVC` | `ServicioPregunta` | Ejecuta operaciones SQL sobre MySQL usando las preguntas contenidas en `ListaPreguntas`. |

## 4.6. Diferencia entre clases `Fichero`

| Proyecto | Clase `Fichero` |
|---|---|
| `FicherosAD4` | Lee y escribe líneas. |
| `AD_JDBC_MVC` | Lee líneas. |

## 4.7. Diferencia entre vistas

| Proyecto | Vista |
|---|---|
| `FicherosAD4` | Muestra menús de incidencias y excepciones. |
| `AD_JDBC_MVC` | Muestra preguntas, opciones y resultados. |

## 4.8. Diferencia en acceso a datos

| Proyecto | Acceso a datos |
|---|---|
| `FicherosAD4` | Usa `FileReader`, `BufferedReader`, `FileWriter` y `BufferedWriter`. |
| `AD_JDBC_MVC` | Usa ficheros y JDBC con `Connection`, `PreparedStatement`, `Statement` y `ResultSet`. |

---

# 5. Explicación oral preparada

## 5.1. Explicación del repositorio

```text
El repositorio contiene dos proyectos Java de consola relacionados con Acceso a Datos.

El primero, FicherosAD4, trabaja con incidencias y ficheros. El usuario puede simular excepciones, el programa las captura, crea una incidencia con usuario, tipo de excepción, fecha y hora, y la guarda en un fichero de texto.

El segundo, AD_JDBC_MVC, es un juego de preguntas tipo test. Lee preguntas desde un fichero, las transforma en objetos Pregunta, las guarda en MySQL mediante JDBC, recupera preguntas aleatorias y permite al usuario responderlas por consola.
```

## 5.2. Explicación de `FicherosAD4`

```text
FicherosAD4 empieza en Main, que llama al ControladorIncidencias.

El controlador pide el nombre del usuario, crea una ListaIncidencias y muestra un menú. Si el usuario decide registrar una excepción, el programa lanza una excepción simulada, la captura en un catch y crea un objeto Incidencia.

La incidencia guarda usuario, excepción, fecha y hora. Después se añade a una lista en memoria y se guarda en datos/incidencias.txt.

ServicioFicheros convierte la incidencia en una línea de texto separada por punto y coma, y Fichero se encarga de escribir físicamente esa línea.
```

## 5.3. Explicación de `AD_JDBC_MVC`

```text
AD_JDBC_MVC empieza en Main, que crea un ControladorPreguntas y llama a iniciar.

El controlador crea un ServicioFichero, un ServicioPregunta y una Consola. Primero lee las preguntas desde data/preguntas.txt. ServicioFichero interpreta el fichero de 7 en 7 líneas, crea objetos Pregunta y los guarda dentro de una ListaPreguntas.

Después ServicioPregunta recibe esa ListaPreguntas y guarda sus preguntas en MySQL. Para ello usa PreparedStatement, inserta la pregunta en la tabla preguntas, recupera el ID generado y guarda la respuesta correcta en respuestas_correctas.

Luego el usuario indica cuántas preguntas quiere responder. ServicioPregunta obtiene preguntas aleatorias con una consulta SELECT que usa JOIN, ORDER BY RAND() y LIMIT. Consola muestra cada pregunta y Escaner lee la respuesta. Al final se muestran los aciertos y el resumen.
```

## 5.4. Explicación de la diferencia entre ambos

```text
La diferencia principal entre los dos proyectos está en la persistencia.

FicherosAD4 usa un fichero de texto como almacenamiento principal. Guarda cada incidencia como una línea con el formato usuario;excepcion;fecha;hora.

AD_JDBC_MVC usa un fichero como origen de preguntas, pero después guarda esas preguntas en una base de datos MySQL mediante JDBC. Por eso incluye clases como ServicioPregunta y DBConnection.
```

## 5.5. Explicación de MVC

```text
Los dos proyectos están organizados con una estructura parecida a MVC.

El modelo representa los datos, como Incidencia o Pregunta.

La vista se encarga de mostrar información y leer datos por consola, mediante Consola y Escaner.

El controlador coordina el flujo principal del programa.

Los servicios contienen lógica intermedia, como convertir objetos a texto, leer preguntas desde un fichero o ejecutar consultas SQL.

El repositorio Fichero se encarga del acceso físico a los archivos.
```

## 5.6. Explicación de ficheros

```text
En FicherosAD4, el fichero se usa para guardar incidencias. ServicioFicheros convierte una Incidencia en una línea de texto y Fichero la escribe usando FileWriter y BufferedWriter.

En AD_JDBC_MVC, el fichero se usa como origen de datos. Fichero lee todas las líneas con FileReader y BufferedReader, y ServicioFichero interpreta esas líneas como preguntas, guardándolas dentro de una ListaPreguntas.
```

## 5.7. Explicación de JDBC

```text
En AD_JDBC_MVC, JDBC se usa para conectar Java con MySQL.

DBConnection centraliza los datos de conexión y devuelve una Connection.

ServicioPregunta usa esa conexión para ejecutar operaciones SQL. Usa PreparedStatement para insertar preguntas y respuestas, ResultSet para recuperar datos y Statement para borrar tablas.

La consulta más importante es la que une preguntas con respuestas_correctas mediante JOIN y devuelve preguntas aleatorias usando ORDER BY RAND() LIMIT ?.
```

---

# 6. Esquemas finales

## 6.1. Esquema de `FicherosAD4`

```text
FicherosAD4
│
├── Main
│   └── Arranca el controlador
│
├── ControladorIncidencias
│   └── Controla menú, registro y búsquedas
│
├── Incidencia
│   └── Modelo: usuario, excepción, fecha, hora
│
├── ListaIncidencias
│   └── Lista en memoria
│
├── ServicioFicheros
│   └── Convierte Incidencia ↔ texto
│
├── Fichero
│   └── Lee y escribe líneas
│
├── Consola
│   └── Muestra menús y mensajes
│
└── Escaner
    └── Lee datos por teclado
```

## 6.2. Esquema de `AD_JDBC_MVC`

```text
AD_JDBC_MVC
│
├── Main
│   └── Arranca el controlador
│
├── ControladorPreguntas
│   └── Coordina carga, juego y resultados
│
├── Pregunta
│   └── Modelo: id, enunciado, opciones, respuesta correcta
│
├── ListaPreguntas
│   └── Colección usada para transportar las preguntas del fichero a la BD
│
├── ServicioFichero
│   └── Convierte data/preguntas.txt en objetos Pregunta
│
├── Fichero
│   └── Lee líneas del fichero
│
├── ServicioPregunta
│   └── Ejecuta operaciones SQL en MySQL
│
├── DBConnection
│   └── Abre la conexión con MySQL
│
├── Consola
│   └── Muestra preguntas y resultados
│
└── Escaner
    └── Lee datos por teclado
```
