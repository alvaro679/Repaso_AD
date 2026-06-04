# Tarjetas de memoria

Responde sin mirar. Si dudas mas de 10 segundos, esa tarjeta cuenta como fallada.

## Arquitectura

1. Que hace `Main`?
2. Que hace el controlador?
3. Que diferencia hay entre `modelo` y `servicio`?
4. Por que no deberias meter lectura de fichero dentro del modelo?
5. Que clase debe pedir datos por teclado?
6. Que clase debe convertir objetos a lineas de texto?
7. Que clase debe abrir la conexion JDBC?

## Modelos y listas

1. Como declaras una clase con atributos privados?
2. Para que sirve el constructor?
3. Para que sirven los getters?
4. Como declaras `List<Incidencia>`?
5. Por que una lista de objetos puntua mejor que variables sueltas?
6. Como haces un metodo `add` dentro de una clase lista?

## Ficheros

1. Que clases usas para leer un fichero de texto?
2. Que clases usas para escribir un fichero de texto?
3. Para que sirve `new FileWriter(ruta, true)`?
4. Para que sirve `split(";")`?
5. Donde controlas `IOException`?
6. Por que se cierran `BufferedReader` y `FileReader`?
7. Como evitas fallar si el fichero no existe?

## Controlador

1. Como se estructura un menu con `while` y `switch`?
2. Donde pones la opcion de salida?
3. Por que conviene sacar acciones a metodos con parametros?
4. Como buscas un objeto dentro de una lista?
5. Como muestras que no se encontro nada?

## JDBC

1. Que tres objetos JDBC aparecen casi siempre?
2. Que metodo ejecuta un `INSERT`?
3. Que metodo ejecuta un `SELECT`?
4. Como se asigna el primer parametro de un `PreparedStatement`?
5. Como lees una columna `String` de un `ResultSet`?
6. Como lees una columna `int` de un `ResultSet`?
7. Que debes cerrar en el `finally`?
8. Como se escribe un `LIKE` seguro con `PreparedStatement`?
9. Para que sirve `Statement.RETURN_GENERATED_KEYS`?
10. Como decides que tablas, columnas e ids usar en una consulta JDBC?

## Examen anterior

1. En el ejercicio de numeros, que dos sumas hay que calcular?
2. En el ejercicio de letras, como evitas ficheros duplicados?
3. En el ejercicio de webs, que columna se filtra con `LIKE`?
4. En el ejercicio de ventas, por que necesitas ids de supermercado y producto?
5. Que pasa si haces todo en `main` aunque el algoritmo sea correcto?
