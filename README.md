# templateExamenAccesoADatos1Eva
Preparando las dependencias y utilidades requeridas para presentarse al examen de acceso a datos


## Pruebas de Log con Log4j2

En la ruta `com.dam2.acceso.examenTemplate.utilidades.log4j2` se incluyen varios elementos para trabajar con logs usando **Log4j2**:

- **Main.java**: clase de prueba que demuestra cómo funciona el logging. Incluye ejemplos de `debug`, `info`, `warn`, `error` y `fatal`.
- **GetLoggerEasyUtil.java**: clase de utilidades que facilita la obtención de loggers asociados a cualquier clase, evitando tener que declarar manualmente `LogManager.getLogger()` en cada una.
- **log4j2.xml**: archivo de configuración listo para copiar a tu proyecto. Define appenders para consola y archivos rotativos, patrones de log y loggers específicos o root.

## Utilidades DOM para trabajar con XML

En la ruta `com.dam2.acceso.examenTemplate.utilidades.dom.utils` se incluye la clase **`DomDocumentUtils`**, diseñada para facilitar el manejo de documentos XML mediante **DOM**:

- **DomDocumentUtils.java**: clase de utilidades para cargar, modificar y guardar documentos XML.  
  Entre sus métodos destacan:
  - `getDocument(String ficheroUri)` / `getDocument(File fichero)`: cargan un archivo XML desde ruta o `File` y devuelven un objeto `Document` listo para manipulación.
  - `updateFile(Document document, File file)`: guarda un `Document` modificado en disco, aplicando indentación legible y eliminando saltos de línea innecesarios para mantener la limpieza del XML.
  - `removeWhitespaceNodes(Node node)`: método interno que elimina nodos de texto vacíos o con solo espacios, evitando líneas en blanco no deseadas al guardar el XML.

- **Main.java**: clase de prueba que demuestra cómo usar las utilidades DOM:
  - Carga un XML de ejemplo.
  - Inserta, lista y borra nodos.
  - Guarda los cambios en un archivo nuevo.
  - Permite observar cómo las utilidades mantienen el formato limpio y legible del XML después de inserciones o borrados.

Estas utilidades permiten trabajar con XML de manera **segura y consistente**, evitando problemas comunes como:

- Inserciones que generan un archivo en una sola línea (*one-liner*).
- Saltos de línea o espacios excesivos entre etiquetas.
- Necesidad de recorrer manualmente el DOM para limpiar nodos de texto vacíos.

## Utilidades para trabajar con archivos .properties

En la ruta `com.dam2.acceso.examenTemplate.utilidades.properties.utils` se incluyen varias utilidades para manejar archivos **.properties** en Java:

- **PropertiesUtils.java**: clase de utilidades para cargar, leer y modificar archivos de configuración `.properties`.  
  Entre sus métodos destacan:
  - `getPropertiesFile(String path)`: carga un archivo `.properties` desde la ruta indicada y devuelve un objeto `Properties`.
  - `getPropertieValue(Properties propsFile, String key)`: obtiene el valor de una clave específica desde un objeto `Properties`.
  - `setPropertieValue(Properties propsFile, String key, String value, String path)`: establece o actualiza el valor de una propiedad y guarda los cambios en el archivo.

- **Main.java**: clase de prueba que demuestra cómo usar las utilidades `PropertiesUtils`:
  - Carga un archivo de propiedades de ejemplo.
  - Muestra cómo obtener valores de configuración.
  - Modifica propiedades y guarda los cambios en el archivo.
  - Permite verificar que los cambios se reflejan tanto en el objeto `Properties` como en el archivo físico.

- **example.properties**: archivo de ejemplo.
