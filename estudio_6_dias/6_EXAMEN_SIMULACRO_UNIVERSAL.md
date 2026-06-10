# Examen simulacro universal - Acceso a Datos

Duracion recomendada: 3 horas y 30 minutos.

Puntuacion total: 10 puntos.

Este simulacro esta pensado para obligarte a aplicar lo importante de cualquier examen parecido:

- POO.
- Objetos.
- Listas.
- Metodos con parametros.
- Scanner.
- Ficheros.
- Conversion objeto-texto y texto-objeto.
- Control de excepciones.
- JDBC con tablas ya dadas.
- `PreparedStatement`.
- `ResultSet`.
- `INSERT`, `SELECT`, `UPDATE`, `JOIN`, `LIKE`.

## Normas generales

En todos los ejercicios debes usar:

- Clases de modelo.
- Alguna estructura de lista.
- Metodos con parametros.
- Separacion minima por responsabilidades.
- Control correcto de excepciones.
- Codigo limpio y tabulado.

No hagas todo en `main`.

No escribas codigo innecesario.

En JDBC, la estructura de la base de datos se considera ya existente. Tu tarea es usar las tablas y columnas indicadas.

---

# Ejercicio 1 - Ficheros: lectura, filtro y resumen

Valor: 2,5 puntos.

El usuario introduce por teclado registros de consumo electrico hasta escribir `Fin`.

Cada registro se introduce con este formato:

```text
zona;kwh
```

Ejemplos:

```text
norte;120
sur;80
centro;300
Fin
```

Solo se deben guardar en el fichero `consumos_validos.txt` los registros cuyo consumo este entre 100 y 500 kwh, ambos incluidos.

El formato del fichero debe ser:

```text
zona;kwh
zona;kwh
zona;kwh
```

Despues debes mostrar por consola:

```text
Total registros introducidos: X
Total registros guardados: Y
Suma kwh introducidos: Z
Suma kwh guardados: W
```

## Obligatorio

- Crear un modelo para representar un consumo.
- Guardar consumos en una lista.
- Crear un metodo que reciba una linea y devuelva un objeto consumo.
- Crear un metodo que reciba un consumo y devuelva una linea de texto.
- Controlar errores si el usuario introduce mal el numero.
- Usar fichero para escribir los consumos validos.

## Lo que se evalua

- Saber leer por teclado hasta una palabra de fin.
- Saber filtrar datos.
- Saber escribir en fichero.
- Saber usar objetos y listas.
- Saber convertir objeto a linea de texto.

---

# Ejercicio 2 - Ficheros: creacion de ficheros y clasificacion

Valor: 2,5 puntos.

El usuario introduce 8 letras por teclado.

Debes crear un fichero por cada letra distinta introducida.

Ejemplo:

```text
A
B
A
C
```

Debe crear:

```text
A.txt
B.txt
C.txt
```

No se debe intentar crear dos veces el mismo fichero.

Despues el programa recibe este array:

```java
String[] palabras = {"Arbol", "Avion", "Barco", "Casa", "Camino", "Dado", "Elefante"};
```

Cada palabra debe escribirse dentro del fichero correspondiente a su inicial.

Si no existe fichero para la inicial de una palabra, esa palabra no se escribe.

Finalmente muestra:

```text
Palabras escritas: X
Palabras no escritas: Y
Ficheros creados: Z
```

## Obligatorio

- Guardar las letras distintas en una lista.
- Crear metodos separados para:
  - leer letras;
  - eliminar duplicados;
  - crear ficheros;
  - escribir palabras;
  - mostrar resumen.
- Controlar excepciones de fichero.
- No hacer todo en `main`.

## Lo que se evalua

- Saber evitar duplicados.
- Saber crear y usar varios ficheros.
- Saber clasificar datos.
- Saber contar resultados.
- Saber organizar el codigo en metodos.

---

# Ejercicio 3 - JDBC: busqueda por palabras

Valor: 2,5 puntos.

La base de datos ya contiene esta tabla:

```text
Tabla: recursos

id_recurso
url
titulo
palabras_clave
```

Ejemplo de datos:

```text
1 | https://uax.com       | Universidad UAX       | universidad, ciclos, educacion, alumno
2 | https://noticias.com  | Noticias deportivas   | actualidad, deporte, ocio
3 | https://java.com      | Tutorial Java         | programacion, java, base de datos
```

El usuario introduce varias palabras por teclado hasta escribir `Fin`.

Debes mostrar todos los recursos que contengan al menos una de esas palabras en `titulo` o en `palabras_clave`.

La busqueda debe usar `LIKE ?`.

No deben mostrarse recursos duplicados aunque coincidan con varias palabras.

## Obligatorio

- Crear modelo `Recurso`.
- Guardar las palabras buscadas en una lista.
- Crear un servicio JDBC.
- Usar `PreparedStatement`.
- Usar `ResultSet`.
- Devolver una lista de objetos `Recurso`.
- Mostrar los resultados desde controlador o consola, no directamente desde el `ResultSet`.
- Cerrar recursos JDBC.

## Lo que se evalua

- Saber hacer `SELECT`.
- Saber usar `LIKE ?`.
- Saber recorrer `ResultSet`.
- Saber construir objetos desde una consulta.
- Saber evitar duplicados.

---

# Ejercicio 4 - JDBC: resumen y actualizacion de ventas

Valor: 2,5 puntos.

La base de datos ya contiene estas tablas:

```text
Tabla: establecimientos

id_establecimiento
nombre_establecimiento
```

```text
Tabla: productos

id_producto
nombre_producto
```

```text
Tabla: resumen_ventas

id_establecimiento
id_producto
total_unidades
total_ventas
```

El programa recibe esta lista de ventas:

```text
Supermercado A;tomate;30;40.10
Supermercado A;lechuga;50;60.19
Supermercado B;tomate;30;40.18
Supermercado A;tomate;10;12.50
Supermercado C;pan;20;22.00
```

Debes volcar un resumen agrupado por establecimiento y producto en `resumen_ventas`.

Ejemplo:

```text
Supermercado A + tomate
unidades = 40
ventas = 52.60
```

Para insertar o actualizar en `resumen_ventas`, primero debes buscar:

```text
id_establecimiento por nombre_establecimiento
id_producto por nombre_producto
```

Si no existe el establecimiento o el producto, esa venta no se vuelca y se cuenta como no procesada.

Finalmente muestra:

```text
Ventas procesadas: X
Ventas no procesadas: Y
Resumenes insertados: Z
Resumenes actualizados: W
```

## Obligatorio

- Crear modelo para una venta recibida.
- Crear modelo o estructura para el resumen agrupado.
- Guardar ventas en una lista.
- Agrupar por establecimiento y producto.
- Buscar ids con JDBC.
- Usar `SELECT`.
- Usar `INSERT` o `UPDATE` segun corresponda.
- Usar `PreparedStatement`.
- Controlar excepciones.
- Cerrar recursos.

## Lo que se evalua

- Saber usar ids para relacionar tablas.
- Saber agrupar datos.
- Saber insertar o actualizar.
- Saber separar modelo, servicio y controlador.
- Saber controlar ventas que no se pueden procesar.

---

# Entrega minima esperada

Aunque no puedas terminar todo perfecto, intenta entregar una estructura parecida a esta:

```text
src/
  Main.java
  controlador/
  modelo/
  servicio/
  repositorio/
  vista/
  util/
```

En ejercicios de solo ficheros, `util/` puede no hacer falta.

En ejercicios JDBC, `repositorio/` puede no hacer falta si centralizas JDBC en servicio.

Lo importante es que no sea un `main` gigante.

---

# Rubrica de autocorreccion

Puntua cada ejercicio sobre 2,5.

## POO y listas

- 0,5 puntos: hay modelos adecuados.
- 0,5 puntos: se usan listas para transportar datos.

## Metodos

- 0,5 puntos: hay metodos con parametros y responsabilidades claras.

## Persistencia

- 0,5 puntos: fichero o JDBC se usa correctamente segun el ejercicio.

## Limpieza y excepciones

- 0,5 puntos: excepciones controladas, codigo limpio y sin mezclar todo.

Si haces todo en `main`, aunque parezca funcionar, te estas engañando: en este tipo de examen eso te puede bajar mucho la nota.

---

# Checklist final antes de corregir

Antes de mirar el codigo del repo, preguntate:

- He creado objetos?
- He usado listas?
- He separado lectura, logica y escritura?
- He usado metodos con parametros?
- He controlado excepciones?
- En ficheros, convierto objeto a texto y texto a objeto?
- En JDBC, uso `PreparedStatement`?
- En JDBC, cierro `ResultSet`, `PreparedStatement` y `Connection`?
- En JDBC, construyo objetos desde `ResultSet`?
- Mi solucion se parece a la arquitectura del repo?

