# Guia diaria exacta de 6 dias

Esta es la guia que tienes que seguir. No la leas entera cada dia. Abre el dia que toca y ejecuta los pasos.

Nota: las casillas de este Markdown pueden no marcarse al hacer click segun el editor. Si quieres checkboxes interactivos, abre `checklist_interactiva.html` en un navegador.

Regla base:

```text
mirar 10 min -> cerrar -> escribir sin mirar -> corregir -> repetir
```

Si solo lees, no cuenta como estudio serio para este examen.

## Como usar cada bloque

En cada bloque haz siempre lo mismo:

1. Abre el archivo indicado.
2. Miralo pocos minutos.
3. Cierra el archivo.
4. Escribe a mano lo que se pide.
5. Vuelve a abrir el archivo real.
6. Marca fallos.
7. Repite solo la parte fallada.

No tienes que escribir perfecto a la primera. Tienes que conseguir no quedarte en blanco.

---

## Dia 1 - Modelos y listas

Objetivo: saber crear clases de datos y clases que guardan listas.

Archivos que puedes abrir:

```text
FicherosAD4/src/modelo/Incidencia.java
FicherosAD4/src/modelo/ListaIncidencias.java
AD_JDBC_MVC/src/modelo/Pregunta.java
AD_JDBC_MVC/src/modelo/ListaPreguntas.java
```

### Bloque 1 - Mapa rapido

Tiempo: 20 minutos.

- [ ] Abre `estudio_6_dias/2_MAPA_PROYECTOS.md`.
- [ ] Mira solo `Patron comun`, `FicherosAD4` y `AD_JDBC_MVC`.
- [ ] Cierra el archivo.

En una hoja, dibuja:

```text
Main
  -> Controlador
      -> Modelo
      -> Lista
      -> Servicio
      -> Repositorio/Fichero o DBConnection
      -> Vista/Escaner
```

Despues escribe de memoria:

```text
Main arranca.
Controlador organiza.
Modelo guarda datos.
Lista guarda muchos objetos.
Servicio gestiona logica.
Fichero lee y escribe.
DBConnection conecta con MySQL.
Vista/Escaner muestra y pide datos.
```

### Bloque 2 - Incidencia

Tiempo: 35 minutos.

- [ ] Mira `FicherosAD4/src/modelo/Incidencia.java` durante 5 minutos.
- [ ] Cierra el archivo.
- [ ] Escribe la clase a mano.
- [ ] Comprueba si pusiste `package`, atributos privados, constructor, getters y `toString`.
- [ ] Reescribe solo lo que hayas fallado.

### Bloque 3 - ListaIncidencias

Tiempo: 30 minutos.

- [ ] Mira `FicherosAD4/src/modelo/ListaIncidencias.java` durante 5 minutos.
- [ ] Cierra el archivo.
- [ ] Escribe la clase a mano.
- [ ] Comprueba si pusiste `List<Incidencia>`, constructor, `guardarIncidencia` y `obtenerIncidencias`.
- [ ] Explica en voz alta: `ListaIncidencias` guarda muchas incidencias, no es una incidencia.

### Bloque 4 - Pregunta y ListaPreguntas

Tiempo: 45 minutos.

- [ ] Abre `AD_JDBC_MVC/src/modelo/Pregunta.java`.
- [ ] Mira sus atributos: `id`, `enunciado`, `opciones`, `respuestaCorrecta`.
- [ ] Cierra el archivo y escribe `Pregunta` a mano.
- [ ] Abre `AD_JDBC_MVC/src/modelo/ListaPreguntas.java`.
- [ ] Mira `List<Pregunta> listaPreguntas`, constructores, `getListaPreguntas` y `addPregunta`.
- [ ] Cierra el archivo y escribe `ListaPreguntas` a mano.
- [ ] Comprueba si sabes escribir `List<String> opciones`.
- [ ] Comprueba si sabes escribir `addPregunta(Pregunta p)`.

### Bloque 5 - Comprobacion

Tiempo: 20 minutos.

Sin mirar, responde:

```text
Que es un modelo?
Que es una lista?
Que hace un constructor?
Que hacen los getters?
Por que usamos List<Incidencia>?
Por que usamos List<Pregunta>?
```

Puedes pasar al dia 2 si puedes escribir una clase modelo y una clase lista sin quedarte en blanco.

---

## Dia 2 - Ficheros

Objetivo: leer, escribir y convertir texto en objetos.

Archivos que puedes abrir:

```text
FicherosAD4/src/repositorio/Fichero.java
FicherosAD4/src/servicio/ServicioFicheros.java
```

### Bloque 1 - Leer fichero

Tiempo: 45 minutos.

- [ ] Abre `FicherosAD4/src/repositorio/Fichero.java`.
- [ ] Mira solo el metodo `leerFichero` durante 10 minutos.
- [ ] Cierra el archivo.
- [ ] Escribe el metodo a mano.
- [ ] Comprueba si pusiste `File`, `FileReader`, `BufferedReader`, `List<String>`, `while (linea != null)`, `catch` y `finally`.
- [ ] Repite el metodo hasta poder escribir su estructura sin mirar.

### Bloque 2 - Escribir fichero

Tiempo: 35 minutos.

- [ ] Abre `FicherosAD4/src/repositorio/Fichero.java`.
- [ ] Mira solo el metodo `escribirLinea` durante 8 minutos.
- [ ] Cierra el archivo.
- [ ] Escribe el metodo a mano.
- [ ] Comprueba si pusiste `FileWriter(ruta, true)`, `BufferedWriter`, `write`, `newLine`, `catch` y `finally`.

### Bloque 3 - Convertir objeto a texto

Tiempo: 30 minutos.

- [ ] Abre `FicherosAD4/src/servicio/ServicioFicheros.java`.
- [ ] Mira solo el metodo `guardarIncidencia(Incidencia incidencia)`.
- [ ] Fijate en la parte donde crea `String linea = incidencia.getUsuario() + ";" + ...`.
- [ ] Fijate en la llamada final `fichero.escribirLinea(linea)`.
- [ ] Cierra el archivo.
- [ ] Escribe el patron:

```text
objeto -> campos separados con ; -> fichero.escribirLinea(linea)
```

### Bloque 4 - Convertir texto a objeto

Tiempo: 35 minutos.

- [ ] Abre `FicherosAD4/src/servicio/ServicioFicheros.java`.
- [ ] Mira solo el metodo `leerIncidencias()`.
- [ ] Fijate en `List<String> lineas = fichero.leerFichero()`.
- [ ] Fijate en el `for (String linea : lineas)`.
- [ ] Fijate en `partes = linea.split(";")`.
- [ ] Fijate en `new Incidencia(...)` y `lista.add(incidencia)`.
- [ ] Cierra el archivo.
- [ ] Escribe el patron:

```text
leer lineas
for cada linea
split(";")
if partes.length correcto
new Objeto(...)
lista.add(objeto)
```

### Bloque 5 - Ejercicio del dia

Tiempo: 60 minutos.

Resuelve a mano:

```text
leer numeros hasta Fin
guardar solo los de 100 a 1000
calcular suma total
calcular suma filtrada
escribir el resultado en fichero
```

Puedes pasar al dia 3 si sabes escribir lectura, escritura y `try/catch/finally` sin mirar.

---

## Dia 3 - Controlador y flujo

Objetivo: montar un programa completo sin meter todo en `main`.

Archivos que puedes abrir:

```text
FicherosAD4/src/controlador/ControladorIncidencias.java
FicherosAD4/src/vista/Escaner.java
FicherosAD4/src/vista/Consola.java
```

### Bloque 1 - Flujo del controlador

Tiempo: 30 minutos.

- [ ] Abre `FicherosAD4/src/controlador/ControladorIncidencias.java`.
- [ ] Mira solo el metodo `iniciar()`.
- [ ] Localiza las variables `usuario`, `datos`, `opcion`, `salir` y `usuarioBuscado`.
- [ ] Localiza el `while (!salir)` y el `switch (opcion)`.
- [ ] Cierra el archivo.

Dibuja y memoriza:

```text
pedir usuario
crear lista
salir = false
while no salir
mostrar menu
leer opcion
switch
llamar metodo correspondiente
```

### Bloque 2 - Rehacer iniciar()

Tiempo: 45 minutos.

- [ ] Abre `FicherosAD4/src/controlador/ControladorIncidencias.java`.
- [ ] Mira `ControladorIncidencias.iniciar` durante 10 minutos.
- [ ] Fijate en que el controlador llama a `Consola.menuPrincipal(usuario)` y a `Escaner.pedirInt(...)`.
- [ ] Fijate en que cada `case` llama a otro metodo o cambia `salir`.
- [ ] Cierra el archivo.
- [ ] Escribe un metodo `iniciar` parecido.
- [ ] Comprueba si pusiste variables, bucle, menu, `switch`, `case`, `default` y salida.

### Bloque 3 - Metodos con parametros

Tiempo: 45 minutos.

- [ ] Abre `FicherosAD4/src/controlador/ControladorIncidencias.java`.
- [ ] Mira solo estos metodos: `registrarExcepcion`, `buscarPorUsuario` y `buscarPorFechas`.
- [ ] En cada metodo, subraya mentalmente que parametros recibe y que lista recorre o modifica.
- [ ] Cierra el archivo.

Escribe de memoria metodos parecidos a:

```text
registrarExcepcion(String usuario, ListaIncidencias datos, int tipoError)
buscarPorUsuario(String usuario, ListaIncidencias datos)
buscarPorFechas(ListaIncidencias datos)
```

No es suficiente saber el nombre. Debes saber que recibe cada metodo y por que.

### Bloque 4 - Vista y Escaner

Tiempo: 30 minutos.

- [ ] Abre `FicherosAD4/src/vista/Escaner.java`.
- [ ] Mira `pedirString` y `pedirInt`.
- [ ] Cierra el archivo y escribe ambos metodos.
- [ ] Abre `FicherosAD4/src/vista/Consola.java`.
- [ ] Mira `menuPrincipal` y `menuExcepciones`.
- [ ] Cierra el archivo y escribe un menu simple en `Consola`.

### Bloque 5 - Ejercicio del dia

Tiempo: 60 minutos.

Rehaz el ejercicio de letras y ficheros, pero con metodos separados:

```text
leerLetras()
crearFicheros(...)
escribirPalabras(...)
mostrarResumen(...)
```

Si te bloqueas, consulta solo:

```text
FicherosAD4/src/repositorio/Fichero.java
FicherosAD4/src/servicio/ServicioFicheros.java
FicherosAD4/src/vista/Escaner.java
```

Puedes pasar al dia 4 si tus ejercicios ya no dependen de un `main` gigante.

---

## Dia 4 - JDBC basico

Objetivo: escribir JDBC usando las tablas y columnas que te da el enunciado.

Archivos que puedes abrir:

```text
AD_JDBC_MVC/src/util/DBConnection.java
AD_JDBC_MVC/src/servicio/ServicioPregunta.java
```

### Bloque 1 - DBConnection

Tiempo: 30 minutos.

- [ ] Mira `AD_JDBC_MVC/src/util/DBConnection.java` durante 8 minutos.
- [ ] Cierra el archivo.
- [ ] Escribe la clase a mano.
- [ ] Comprueba si pusiste `URL`, `USER`, `PASSWORD`, constructor privado y `getConnection`.

### Bloque 2 - INSERT con PreparedStatement

Tiempo: 45 minutos.

- [ ] Abre `AD_JDBC_MVC/src/servicio/ServicioPregunta.java`.
- [ ] Mira dentro de `guardarListaEnBD`.
- [ ] Localiza `String sqlPregunta = "INSERT INTO preguntas ..."` .
- [ ] Localiza `conn = DBConnection.getConnection()`.
- [ ] Localiza `psPregunta = conn.prepareStatement(sqlPregunta, Statement.RETURN_GENERATED_KEYS)`.
- [ ] Localiza los `psPregunta.setString(...)`.
- [ ] Localiza `psPregunta.executeUpdate()`.
- [ ] Cierra el archivo.

Escribe una plantilla de `INSERT` con:

```text
Connection conn = null
PreparedStatement ps = null
String sql = "INSERT INTO ..."
conn = DBConnection.getConnection()
ps = conn.prepareStatement(sql)
ps.setString(...)
ps.executeUpdate()
finally cerrar ps y conn
```

### Bloque 3 - SELECT con ResultSet

Tiempo: 50 minutos.

- [ ] Abre `AD_JDBC_MVC/src/servicio/ServicioPregunta.java`.
- [ ] Mira dentro de `obtenerPreguntasAleatorias`.
- [ ] Localiza el `SELECT ... FROM preguntas p JOIN respuestas_correctas r ...`.
- [ ] Localiza `ps.setInt(1, cantidad)`.
- [ ] Localiza `rs = ps.executeQuery()`.
- [ ] Localiza `while (rs.next())`.
- [ ] Localiza donde crea `opcionesAux` y `new Pregunta(...)`.
- [ ] Cierra el archivo.

Escribe una plantilla de `SELECT` con:

```text
List<Objeto> lista = new ArrayList<>()
Connection conn = null
PreparedStatement ps = null
ResultSet rs = null
ps = conn.prepareStatement(sql)
rs = ps.executeQuery()
while (rs.next())
new Objeto(...)
cerrar rs, ps y conn
```

### Bloque 4 - ServicioPregunta

Tiempo: 50 minutos.

- [ ] Vuelve a abrir `AD_JDBC_MVC/src/servicio/ServicioPregunta.java`.
- [ ] En `guardarListaEnBD`, fijate en el flujo: recorrer lista, insertar pregunta, recuperar id, insertar respuesta.
- [ ] En `obtenerPreguntasAleatorias`, fijate en el flujo: consultar BD, recorrer `ResultSet`, crear objetos, devolver lista.
- [ ] Escribe a mano la idea de cada metodo en 5-7 lineas.
- [ ] Practica `Statement.RETURN_GENERATED_KEYS` mirando las lineas donde se usa `getGeneratedKeys`.

### Bloque 5 - Comprobacion

Tiempo: 20 minutos.

Sin mirar, escribe:

```text
conn = DBConnection.getConnection();
ps = conn.prepareStatement(sql);
ps.setString(1, valor);
ps.executeUpdate();
rs = ps.executeQuery();
while (rs.next()) { ... }
```

Puedes pasar al dia 5 si puedes escribir un `SELECT` completo sin mirar.

---

## Dia 5 - Ejercicios tipo examen

Objetivo: adaptar los patrones a enunciados nuevos.

Hoy no empiezas mirando codigo. Primero intentas resolver.

### Bloque 1 - Numeros y fichero

Tiempo: 45 minutos.

Antes de empezar, no mires codigo. Si tras 10 minutos te quedas bloqueado, consulta:

```text
FicherosAD4/src/repositorio/Fichero.java
FicherosAD4/src/servicio/ServicioFicheros.java
```

Resuelve:

```text
leer hasta Fin
filtrar 100 a 1000
guardar en fichero
sumar total
sumar filtrados
```

### Bloque 2 - Letras y ficheros

Tiempo: 60 minutos.

Antes de empezar, no mires codigo. Si te bloqueas, consulta:

```text
FicherosAD4/src/repositorio/Fichero.java
FicherosAD4/src/vista/Escaner.java
FicherosAD4/src/controlador/ControladorIncidencias.java
```

Resuelve:

```text
leer 10 letras
crear un fichero por letra distinta
recibir array de palabras
escribir cada palabra segun inicial
contar escritas y no escritas
```

### Bloque 3 - Webs con LIKE

Tiempo: 60 minutos.

Antes de empezar, no mires codigo. Si te bloqueas, consulta:

```text
AD_JDBC_MVC/src/util/DBConnection.java
AD_JDBC_MVC/src/servicio/ServicioPregunta.java
estudio_6_dias/3_PLANTILLAS_EXAMEN_A_MANO.md
```

En `ServicioPregunta`, usa `obtenerPreguntasAleatorias` como patron de `SELECT + ResultSet`. En plantillas, busca `JDBC LIKE con palabras`.

Resuelve:

```text
leer palabras
buscar webs donde palabrasClave contenga alguna palabra
usar LIKE ?
devolver objetos Web en una lista
mostrar resultados
```

### Bloque 4 - Ventas agrupadas

Tiempo: 75 minutos.

Antes de empezar, no mires codigo. Si te bloqueas, consulta:

```text
AD_JDBC_MVC/src/servicio/ServicioPregunta.java
estudio_6_dias/3_PLANTILLAS_EXAMEN_A_MANO.md
```

Usa `guardarListaEnBD` como patron de `INSERT` y `obtenerPreguntasAleatorias` como patron de `SELECT`.

Resuelve:

```text
recibir ventas
agrupar por supermercado + producto
buscar id de supermercado
buscar id de producto
insertar o actualizar total_unidades y total_ventas
```

### Bloque 5 - Correccion

Tiempo: 40 minutos.

Corrige mirando solo:

```text
Fichero.java
ServicioFicheros.java
ServicioPregunta.java
DBConnection.java
```

Puedes pasar al dia 6 si ya sabes empezar cualquier ejercicio sin quedarte bloqueado.

---

## Dia 6 - Simulacro final

Objetivo: comprobar si puedes producir codigo bajo presion.

### Bloque 1 - Proyecto de ficheros

Tiempo: 90 minutos.

Primero escribe sin mirar. Despues compara con:

```text
FicherosAD4/src/Main.java
FicherosAD4/src/modelo/Incidencia.java
FicherosAD4/src/modelo/ListaIncidencias.java
FicherosAD4/src/repositorio/Fichero.java
FicherosAD4/src/servicio/ServicioFicheros.java
FicherosAD4/src/controlador/ControladorIncidencias.java
FicherosAD4/src/vista/Escaner.java
```

Escribe a mano un proyecto completo con:

```text
Main
modelo
lista
fichero
servicio
controlador
vista/escaner
```

Debe leer datos, guardar en fichero, volver a leer y mostrar un resumen o busqueda.

### Bloque 2 - Descanso

Tiempo: 15 minutos.

No mires codigo.

### Bloque 3 - Proyecto JDBC

Tiempo: 120 minutos.

Primero escribe sin mirar. Despues compara con:

```text
AD_JDBC_MVC/src/Main.java
AD_JDBC_MVC/src/modelo/Pregunta.java
AD_JDBC_MVC/src/modelo/ListaPreguntas.java
AD_JDBC_MVC/src/util/DBConnection.java
AD_JDBC_MVC/src/servicio/ServicioPregunta.java
AD_JDBC_MVC/src/controlador/ControladorPreguntas.java
AD_JDBC_MVC/src/vista/Escaner.java
```

Escribe a mano un proyecto completo con:

```text
Main
modelo
lista si hace falta
DBConnection
servicio JDBC
controlador
vista/escaner
```

Debe leer datos, consultar una tabla, construir objetos desde `ResultSet` y mostrar resultados.

### Bloque 4 - Correccion final

Tiempo: 45 minutos.

Corrige con esta pregunta:

```text
Esto se parece a la arquitectura del repo o es codigo improvisado?
```

Revisa:

```text
imports
atributos privados
constructores
listas
metodos con parametros
try/catch/finally
PreparedStatement
ResultSet
cierre de recursos
separacion por clases
```

Estas listo si puedes escribir una solucion estructurada aunque tenga errores menores de sintaxis.
