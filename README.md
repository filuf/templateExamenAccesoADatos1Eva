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

## Utilidades JAXB para trabajar con XML y objetos Java

En la ruta `com.dam2.acceso.examenTemplate.utilidades.jaxb.utils` se incluye la clase **`JaxbUtils`**, diseñada para facilitar la serialización y deserialización de objetos Java a XML y viceversa usando **JAXB**:

- **JaxbUtils.java**: clase de utilidades genéricas para manejar XML mediante JAXB.  
  Entre sus métodos destacan:
  - `readXml(Path path, Class<T> clazz)` / `readXml(File file, Class<T> clazz)`: leen un archivo XML y devuelven un objeto Java del tipo indicado. Permiten manejar archivos desde ruta (`Path`) o desde un objeto `File`.
  - `writeXml(T object, Path path)`: guarda un objeto Java en un archivo XML, aplicando sangría y formato legible para mantener el archivo limpio.

- **Main.java** (en `logic`): ejemplo práctico de uso de JAXB:
  - Carga un XML de ejemplo (`juegos.xml`) en un objeto `BibliotecaVideojuegos`.
  - Muestra cómo realizar operaciones lógicas sobre los datos, como:
    - Consultar el intercambio de un juego específico.
    - Eliminar un usuario y todos sus intercambios asociados.
  - Guarda los cambios de vuelta en el archivo XML, demostrando la persistencia de manera sencilla.

### Modelos de datos incluidos

En `com.dam2.acceso.examenTemplate.utilidades.jaxb.models` se incluyen tres clases de modelo que representan la estructura del XML:

- **BibliotecaVideojuegos.java**: representa la colección de videojuegos, usuarios e intercambios.
- **Intercambio.java**: representa un intercambio de un videojuego entre dos usuarios.
- **Usuario.java**: representa un usuario dentro de la biblioteca.

## Utilidades Jackson para trabajar con JSON

En la ruta `com.dam2.acceso.examenTemplate.utilidades.jackson.utils` se incluye la clase **`JacksonUtils`**, diseñada para facilitar la serialización y deserialización de objetos Java a JSON y viceversa usando **Jackson**:

- **JacksonUtils.java**: clase de utilidades para trabajar con JSON de forma genérica.  
  Entre sus métodos destacan:
  - `serialize(T objectToSerialize)`: convierte un objeto Java a su representación JSON y devuelve un `Optional<String>`. Retorna vacío si ocurre un error.
  - `deserialize(String json, Class<T> clazz)`: convierte un JSON en un objeto Java del tipo indicado y devuelve un `Optional<T>`. Retorna vacío si ocurre un error.
  - `writeToFile(Path path, T object)`: guarda un objeto Java en un archivo JSON con formato legible.

- **Main.java**: ejemplo práctico de uso de `JacksonUtils`:
  - Crea un objeto de ejemplo (`PersonaExample`) y lo serializa a JSON.
  - Deserializa el JSON de vuelta a un objeto Java.
  - Muestra cómo verificar si el JSON está presente usando `Optional.isPresent()` o `Optional.ifPresent()`.
  - Permite imprimir el JSON y los atributos del objeto deserializado en consola.

### Observaciones
- Manejan errores de forma segura utilizando `Optional`, evitando `null` y excepciones inesperadas.
- Permiten guardar JSON en archivos con formato legible (*pretty print*).

## Utilidades JasperReports y Thymeleaf para generación de informes

En la ruta `com.dam2.acceso.examenTemplate.utilidades.thymeleafJasper` se incluyen clases para **generar informes PDF a partir de XML/JSON usando JasperReports** y servirlos mediante un controlador Spring:

### Generación de informes PDF con JasperReports

- **GestorAlumno.java** (fragmento relevante):
  - `generarInforme(List<Alumno> pedidos, String ubFichero, String ubPlantilla)`: genera un informe PDF a partir de una lista de objetos `Alumno`.  
    - Convierte la lista de alumnos a una lista de DTOs (`DatosInforme`) que JasperReports puede usar.
    - Define parámetros para el informe (como la cantidad de alumnos).
    - Llena la plantilla `.jasper` con los datos y exporta el informe a PDF.
    - Lanza un `RuntimeException` si ocurre algún error durante la generación.

### Controlador Spring para manejo de archivos y generación de informes

- **ControllerFichero.java**: maneja la subida de archivos XML, su conversión a JSON y la generación de informes PDF.
  - `@PostMapping("/upload")`:
    - Recibe un archivo XML subido por el usuario.
    - Lo guarda en el servidor.
    - Convierte el XML a objeto Java (`Alumnos`) y luego a JSON.
    - Genera un informe PDF usando JasperReports a partir de los datos del XML.
    - Añade al modelo los nombres de los archivos JSON y PDF para poder descargarlos desde la vista.
  - `@PostMapping("/download")` y `@PostMapping("/download-pdf")`:
    - Permiten descargar los archivos JSON y PDF generados, con encabezados `Content-Disposition` para forzar la descarga.

### Rutas importantes

- `JASPER_DIR_OUTPUT`: directorio donde se guardan los informes generados.
- `JASPER_DIR_TEMPLATES`: directorio donde se almacenan las plantillas Jasper (`.jasper`).
- `JASPER_TEMPLATE_NAME`: nombre de la plantilla utilizada (`reportAlumno.jasper`).
