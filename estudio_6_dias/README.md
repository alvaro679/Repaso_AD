# Pack de estudio de 6 dias para Repaso_AD

Este pack implementa el plan de 6 dias para dominar los dos proyectos del repo:

- `FicherosAD4`: patron de consola + objetos + listas + ficheros.
- `AD_JDBC_MVC`: patron de consola + objetos + listas + fichero + JDBC.

La idea no es leer pasivamente. La idea es reconstruir codigo a mano hasta que puedas adaptar el patron a enunciados nuevos.

## Como usar este pack

Cada dia repite este ciclo:

1. Mira el mapa o plantilla correspondiente durante pocos minutos.
2. Cierra el material.
3. Escribe el codigo a mano o en un archivo temporal sin copiar.
4. Compara contra el proyecto real y marca errores.
5. Repite solo las partes que has fallado.

Si solo lees, no estas estudiando para este examen. Estas reconociendo codigo, que es mucho mas facil que producirlo.

## Orden recomendado

1. `CHECKLIST_6_DIAS.md`: que hacer cada dia y cuando puedes pasar al siguiente.
2. `MAPA_PROYECTOS.md`: como se conectan las clases de los dos proyectos.
3. `PLANTILLAS_EXAMEN_A_MANO.md`: patrones que debes poder escribir sin mirar.
4. `SIMULACROS_Y_CORRECCION.md`: ejercicios cronometrados y rubrica de autocorreccion.
5. `TARJETAS_MEMORIA.md`: preguntas rapidas para comprobar si lo tienes de verdad.

## Regla clave de JDBC

No estudies `CREATE TABLE` como codigo que tengas que escribir en Java.

En el examen, la estructura de tablas se usa como informacion: nombres de tablas, columnas, ids y relaciones. Tu codigo JDBC debe consultar, insertar, actualizar o borrar datos en tablas que ya existen.

