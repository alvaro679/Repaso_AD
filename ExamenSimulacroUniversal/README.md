# ExamenSimulacroUniversal

Proyecto Java de consola con la solucion del examen simulacro universal.

## Como abrirlo en IntelliJ

1. Abre IntelliJ.
2. File -> Open.
3. Selecciona la carpeta `ExamenSimulacroUniversal`.
4. Abre `src/Main.java`.
5. Ejecuta `Main`.

## Base de datos

La clase `util.DBConnection` usa esta URL:

```text
jdbc:mysql://localhost:3306/examen_simulacro?useSSL=false&serverTimezone=UTC
```

Si tu base de datos tiene otro nombre, cambia la constante `URL`.

El proyecto no crea tablas. Usa las tablas indicadas por el enunciado.

