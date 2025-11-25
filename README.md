# templateExamenAccesoADatos1Eva
Preparando las dependencias y utilidades requeridas para presentarse al examen de acceso a datos


## Pruebas de Log con Log4j2

En la ruta `com.dam2.acceso.examenTemplate.utilidades.log4j2` se incluyen varios elementos para trabajar con logs usando **Log4j2**:

- **Main.java**: clase de prueba que demuestra cómo funciona el logging. Incluye ejemplos de `debug`, `info`, `warn`, `error` y `fatal`.
- **GetLoggerEasyUtil.java**: clase de utilidades que facilita la obtención de loggers asociados a cualquier clase, evitando tener que declarar manualmente `LogManager.getLogger()` en cada una.
- **log4j2.xml**: archivo de configuración listo para copiar a tu proyecto. Define appenders para consola y archivos rotativos, patrones de log y loggers específicos o root.