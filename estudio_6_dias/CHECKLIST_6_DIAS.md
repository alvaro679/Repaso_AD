# Checklist de 6 dias

Plan pensado para 3-4 horas netas al dia. Si un dia haces menos de 3 horas, no lo maquilles: ese dia no cuenta completo.

## Estructura diaria recomendada

- 20 min: repasar mapa del proyecto o plantilla.
- 60 min: escribir codigo sin mirar.
- 15 min: descanso.
- 70 min: ejercicio tipo examen.
- 30 min: corregir errores con el codigo real.
- 20 min: repetir de memoria la parte fallada.

## Dia 1 - Mapa general y modelos

Objetivo: poder crear clases de datos y listas sin pensar demasiado.

- [ ] Dibujar de memoria la estructura comun: `Main`, `controlador`, `modelo`, `servicio`, `repositorio`, `vista`, `util`.
- [ ] Escribir a mano el flujo de `FicherosAD4`.
- [ ] Escribir a mano el flujo de `AD_JDBC_MVC`.
- [ ] Escribir sin mirar `Incidencia`.
- [ ] Escribir sin mirar `ListaIncidencias`.
- [ ] Escribir sin mirar `Pregunta`.
- [ ] Escribir sin mirar `ListaPreguntas`.
- [ ] Comparar con el repo y marcar errores de atributos, constructores, getters y listas.

No pases al dia 2 si no puedes escribir una clase modelo y una clase lista sin mirar.

## Dia 2 - Ficheros

Objetivo: dominar lectura/escritura de ficheros y conversion texto-objeto.

- [ ] Escribir sin mirar `Fichero.escribirLinea`.
- [ ] Escribir sin mirar `Fichero.leerFichero`.
- [ ] Escribir sin mirar el patron `objeto -> linea con ;`.
- [ ] Escribir sin mirar el patron `linea.split(";") -> objeto`.
- [ ] Resolver ejercicio de numeros: leer hasta `Fin`, filtrar 100-1000, escribir fichero, calcular totales.
- [ ] Resolver ejercicio de letras: crear ficheros unicos y escribir palabras por inicial.
- [ ] Corregir si has usado toda la logica en `main`; eso seria mala senal para el examen.

No pases al dia 3 si no controlas `FileReader`, `BufferedReader`, `FileWriter`, `BufferedWriter`, `try/catch/finally`.

## Dia 3 - Controlador y flujo

Objetivo: organizar ejercicios completos sin meter todo en un unico metodo.

- [ ] Rehacer `ControladorIncidencias.iniciar` sin mirar.
- [ ] Practicar `while`, `switch`, opcion de salida y opcion por defecto.
- [ ] Practicar busqueda por usuario recorriendo una lista.
- [ ] Practicar busqueda por fechas recorriendo una lista.
- [ ] Sacar metodos con parametros para cada accion importante.
- [ ] Rehacer el ejercicio de letras usando clases y listas.

No pases al dia 4 si tus soluciones dependen de un `main` gigante.

## Dia 4 - JDBC base

Objetivo: escribir JDBC para tablas ya existentes.

- [ ] Escribir sin mirar `DBConnection`.
- [ ] Escribir plantilla de `INSERT` con `PreparedStatement`.
- [ ] Escribir plantilla de `SELECT` con `ResultSet`.
- [ ] Escribir cierre de `Connection`, `PreparedStatement` y `ResultSet`.
- [ ] Estudiar `ServicioPregunta.guardarListaEnBD`.
- [ ] Estudiar `ServicioPregunta.obtenerPreguntasAleatorias`.
- [ ] Practicar recuperar ids con `Statement.RETURN_GENERATED_KEYS` si el ejercicio necesita relacionar tablas.

No pases al dia 5 si no puedes escribir un `SELECT` JDBC completo sin autocompletado.

## Dia 5 - JDBC aplicado a examen

Objetivo: adaptar JDBC a enunciados nuevos.

- [ ] Resolver ejercicio de webs: leer palabras y buscar registros con `LIKE ?`.
- [ ] Resolver ejercicio de ventas: agrupar por supermercado/producto.
- [ ] Practicar buscar id de supermercado por nombre.
- [ ] Practicar buscar id de producto por nombre.
- [ ] Practicar insertar o actualizar totales en una tabla de ventas ya creada.
- [ ] Revisar que no has escrito `CREATE TABLE` como parte del codigo Java.

No pases al dia 6 si no sabes usar la estructura de tablas del enunciado para construir consultas.

## Dia 6 - Simulacro a mano

Objetivo: producir codigo bajo presion.

- [ ] Simulacro 1: proyecto de ficheros completo en 90 minutos.
- [ ] Descanso de 15 minutos.
- [ ] Simulacro 2: proyecto JDBC completo en 120 minutos.
- [ ] Corregir imports, excepciones, cierres, nombres de metodos, listas y SQL.
- [ ] Reescribir solo las partes falladas.
- [ ] Hacer tarjetas de memoria hasta fallar menos de 3.

Estas listo si puedes escribir una solucion incompleta pero estructurada antes que una solucion larga y caotica. En este examen, el caos penaliza mucho.

