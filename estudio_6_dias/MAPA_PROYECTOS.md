# Mapa de los dos proyectos

## Patron comun

```text
Main
  -> Controlador
      -> Vista/Escaner
      -> Modelo y listas
      -> Servicio
          -> Repositorio de fichero o util de JDBC
```

Traduccion mental:

- `Main`: arranca.
- `Controlador`: decide el orden de acciones.
- `Modelo`: representa datos.
- `Lista...`: transporta colecciones de objetos.
- `Servicio`: transforma datos y aplica logica.
- `Repositorio/Fichero`: lee o escribe fisicamente.
- `DBConnection`: abre conexion a base de datos.
- `Vista/Escaner`: entrada y salida por consola.

## FicherosAD4

### Clases

```text
Main
ControladorIncidencias
Incidencia
ListaIncidencias
ServicioFicheros
Fichero
Consola
Escaner
```

### Flujo principal

```text
Main.main
  -> ControladorIncidencias.iniciar
      -> pedir usuario
      -> crear ListaIncidencias
      -> mostrar menu
      -> leer opcion
      -> ejecutar accion
```

### Flujo al guardar

```text
usuario elige excepcion
  -> controlador lanza/captura excepcion
  -> crea Incidencia
  -> ListaIncidencias.guardarIncidencia
  -> ServicioFicheros.guardarIncidencia
  -> Fichero.escribirLinea
```

### Flujo al leer

```text
ListaIncidencias()
  -> ServicioFicheros.leerIncidencias
  -> Fichero.leerFichero
  -> por cada linea: split(";")
  -> new Incidencia(...)
  -> add a List<Incidencia>
```

### Lo que debes poder explicar

- Por que `Incidencia` no lee ni escribe ficheros.
- Por que `ListaIncidencias` guarda una `List<Incidencia>`.
- Por que `ServicioFicheros` convierte entre objetos y texto.
- Por que `Fichero` solo sabe leer/escribir lineas.
- Por que el controlador coordina y no deberia tener todo mezclado.

## AD_JDBC_MVC

### Clases

```text
Main
ControladorPreguntas
Pregunta
ListaPreguntas
ServicioFichero
ServicioPregunta
Fichero
DBConnection
Consola
Escaner
```

### Flujo principal

```text
Main.main
  -> ControladorPreguntas.iniciar
      -> cargar preguntas desde fichero
      -> borrar datos previos
      -> guardar preguntas en BD
      -> pedir cantidad
      -> obtener preguntas aleatorias
      -> mostrar preguntas
      -> comprobar respuestas
      -> mostrar resultados
      -> limpiar BD
```

### Flujo fichero a objetos

```text
Fichero.leerFichero
  -> List<String> lineas
  -> ServicioFichero agrupa cada bloque
  -> crea opciones
  -> calcula respuesta correcta
  -> new Pregunta(...)
  -> ListaPreguntas.addPregunta
```

### Flujo objetos a base de datos

```text
ListaPreguntas
  -> for Pregunta p
  -> INSERT en preguntas
  -> recuperar id generado
  -> INSERT en respuestas_correctas usando id_pregunta
```

### Flujo base de datos a objetos

```text
SELECT con JOIN
  -> ResultSet
  -> crear List<String> opciones
  -> new Pregunta(...)
  -> add a List<Pregunta>
```

### Regla importante sobre tablas

No tienes que escribir `CREATE TABLE` en el codigo Java.

El enunciado te da la estructura para saber:

- que tabla consultar;
- que columnas usar;
- que ids relacionan tablas;
- que campos insertar, actualizar o mostrar.

