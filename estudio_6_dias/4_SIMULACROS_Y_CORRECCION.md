# Simulacros y autocorreccion

Haz estos ejercicios a mano. Cronometra. Despues compara con las plantillas y con los proyectos reales.

## Penalizaciones que debes tener presentes

- Sin POO, objetos o listas: penalizacion fuerte.
- Sin metodos con parametros: penalizacion fuerte.
- Excepciones mal controladas: penalizacion fuerte.
- Codigo mal tabulado o malas practicas: puede invalidar el ejercicio.
- Codigo innecesario: resta.
- Flujo torpe o estructuras no optimas: resta.

Traduccion directa: una solucion que "funciona en tu cabeza" pero esta toda en `main` no es una buena solucion para este examen.

## Simulacro 1 - Ficheros: numeros

Tiempo: 45-60 minutos.

Enunciado:

El usuario introduce valores por teclado hasta escribir `Fin`. Debes detectar los numeros enteros. Los numeros entre 100 y 1000 se escriben en `numeros.txt` separados por `;` y sin saltos de linea entre ellos. Al final, escribe o muestra la suma de los numeros guardados y la suma de todos los numeros introducidos.

Obligatorio:

- Clase modelo para representar el resumen o los datos necesarios.
- Lista para guardar numeros o entradas validas.
- Metodos con parametros.
- Control de errores al parsear numeros.
- Repositorio/servicio de fichero.

Autocorreccion:

- [ ] No usaste todo en `main`.
- [ ] Controlaste `Fin`.
- [ ] Sumaste todos los numeros introducidos.
- [ ] Sumaste solo los guardados.
- [ ] Filtraste correctamente 100-1000.
- [ ] Escribiste fichero con formato pedido.

## Simulacro 2 - Ficheros: letras

Tiempo: 60-75 minutos.

Enunciado:

El usuario introduce 10 letras. Por cada letra diferente, crea un fichero `LETRA.txt` si no existe. Despues recibe este array:

```java
String[] palabras = {"Amigo", "Gorro", "Luces", "Pina"};
```

Cada palabra debe escribirse en el fichero que coincida con su inicial. Muestra cuantas palabras se han escrito y cuantas no.

Obligatorio:

- Lista de letras distintas.
- Metodo para crear ficheros sin duplicar.
- Metodo para escribir palabra en fichero correspondiente.
- Contadores de escritas/no escritas.

Autocorreccion:

- [ ] Evitaste crear dos veces el mismo fichero.
- [ ] Comparaste iniciales correctamente.
- [ ] Separaste lectura, logica y escritura.
- [ ] Controlaste IOException.

## Simulacro 3 - JDBC: busqueda de webs

Tiempo: 75-90 minutos.

Contexto:

La tabla `webs` ya existe y tiene:

```text
id
url
palabrasClave
```

Enunciado:

El usuario introduce varias palabras. Debes mostrar todos los registros que contengan al menos una de esas palabras en `palabrasClave`.

Obligatorio:

- Modelo `Web`.
- Lista de palabras buscadas.
- Servicio JDBC con `PreparedStatement`.
- Consulta con `LIKE ?`.
- Devolver lista de objetos, no solo imprimir desde el `ResultSet`.

Autocorreccion:

- [ ] Usaste las tablas y columnas indicadas por el enunciado.
- [ ] Usaste `LIKE ?` con `%palabra%`.
- [ ] Cerraste `ResultSet`, `PreparedStatement` y `Connection`.
- [ ] Evitaste duplicados si una web coincide con varias palabras.
- [ ] Mostraste objetos desde consola/controlador.

## Simulacro 4 - JDBC: resumen de ventas

Tiempo: 90-120 minutos.

Contexto:

El enunciado indica estas tablas:

```text
Tabla_supermercados: Id_supermercado, Nombre_supermercado
Tabla_producto: Id_producto, Nombre_producto
Tabla_Ventas: Id_supermercado, Id_producto, Total_unidades, Total_ventas
```

Enunciado:

Recibes una lista de ventas con establecimiento, producto, unidades y ventas. Debes volcar el resumen agrupado por supermercado y producto en la tabla de ventas.

Obligatorio:

- Modelo para venta de entrada.
- Modelo o estructura para resumen.
- Lista de ventas.
- Buscar id de supermercado por nombre.
- Buscar id de producto por nombre.
- Insertar o actualizar totales en `Tabla_Ventas`.

Autocorreccion:

- [ ] Usaste los nombres de tablas y columnas del enunciado.
- [ ] Usaste ids para relacionar ventas.
- [ ] Agrupaste por supermercado + producto.
- [ ] Sumaste unidades.
- [ ] Sumaste ventas.
- [ ] Usaste `PreparedStatement`.

## Simulacro final del dia 6

### Bloque A: ficheros

Tiempo: 90 minutos.

Haz un proyecto completo con:

- `Main`
- `controlador`
- `modelo`
- `servicio`
- `repositorio`
- `vista`

Debe leer datos, guardarlos en fichero, volverlos a leer y mostrar una busqueda o resumen.

### Bloque B: JDBC

Tiempo: 120 minutos.

Haz un proyecto completo con:

- `Main`
- `controlador`
- `modelo`
- `servicio`
- `util.DBConnection`
- `vista`

Debe leer datos, consultar una tabla ya existente, construir objetos desde `ResultSet` y mostrar resultados.

## Metodo de correccion

Usa esta escala brutal:

- 0: no sabria escribirlo sin mirar.
- 1: lo escribo, pero mezclo capas o tengo muchos huecos.
- 2: estructura correcta, errores de sintaxis corregibles.
- 3: solucion clara, separada por clases, con excepciones y listas.

Debes llegar a 2 o 3 en:

- Modelo.
- Lista.
- Fichero.
- Controlador.
- DBConnection.
- INSERT JDBC.
- SELECT JDBC.
- LIKE JDBC.
- Agrupacion de datos.
