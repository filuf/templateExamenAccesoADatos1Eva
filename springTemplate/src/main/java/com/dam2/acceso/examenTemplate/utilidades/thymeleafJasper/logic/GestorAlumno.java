package com.dam2.acceso.examenTemplate.utilidades.thymeleafJasper.logic;

import com.dam2.acceso.examenTemplate.utilidades.thymeleafJasper.models.Alumno;
import com.dam2.acceso.examenTemplate.utilidades.thymeleafJasper.models.Alumnos;
import com.dam2.acceso.examenTemplate.utilidades.thymeleafJasper.models.DatosInforme;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorAlumno {

    static Logger log = LogManager.getLogger(GestorAlumno.class);


    /* --------------------- JaxB ---------------------- */

    /**
     * Lee un archivo XML y lo convierte en un objeto Alumnos usando JAXB.
     *
     * @param path Ruta del archivo XML a leer.
     * @return Objeto Alumnos con los datos del XML, o null si ocurre un error.
     */
    public static Alumnos leerXmlJavaX(Path path) {

        Alumnos uni = null;
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Alumnos.class);

            Unmarshaller unMarshall = context.createUnmarshaller();
            uni = (Alumnos) unMarshall.unmarshal(path.toFile());
        } catch (JAXBException e) {
            log.error(e.getMessage());
        }

        return uni;
    }

    /**
     * Lee un archivo JSON subido por el usuario y lo convierte en un objeto Alumnos.
     *
     * @param fichero Archivo JSON como MultipartFile.
     * @return Objeto Alumnos con los datos del JSON, o null si ocurre un error.
     */
    public static Alumnos leerJSON(MultipartFile fichero) {
        Alumnos al = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            al = mapper.readValue(fichero.getInputStream(), Alumnos.class);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return al;
    }

    /* ----------------- XML A JSON --------------------*/

    /**
     * Convierte un archivo XML en un archivo JSON equivalente.
     *
     * @param fileXml Archivo XML a convertir.
     * @return Archivo JSON generado en la misma carpeta que el XML.
     * @throws IOException Si ocurre un error al leer o escribir los archivos.
     */
    public static File xmlAJson(File fileXml) throws IOException {

        // Mapper para leer XML
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode node = xmlMapper.readTree(fileXml);

        // Construir nombre del archivo JSON a partir del XML
        String nameJson = fileXml.getName().substring(0,fileXml.getName().lastIndexOf("."))+".json";
        File fileJson = new File(fileXml.getParent(),nameJson);

        // Mapper para escribir JSON
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(fileJson,node);

        return fileJson;
    }

    /* ---------------- JASPER REPORTS ------------------*/

    /**
     * Genera un informe PDF a partir de una lista de alumnos usando JasperReports.
     *
     * @param pedidos   Lista de objetos Alumno que se incluirán en el informe.
     * @param ubFichero Ruta completa donde se guardará el PDF generado.
     * @param ubPlantilla Ruta completa de la plantilla Jasper (.jasper) que se usará para generar el informe.
     * @throws RuntimeException Si ocurre algún error durante la generación del informe.
     */
    public static void generarInforme(List<Alumno> pedidos, String ubFichero, String ubPlantilla) {

        log.info(pedidos.toString());
        log.info(ubFichero);

        // Convertir lista de alumnos a lista de DatosInforme (DTO para JasperReports)
        List<DatosInforme> datos = pedidos.stream()
                .map(a -> new DatosInforme(a.getId(), a.getNombre(), a.getDireccion()))
                .toList();

        // Parámetros que se pasan al informe
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("cantidad",pedidos.size()+"");

        try {
            // Crear datasource para JasperReports
            JRBeanCollectionDataSource camposInf = new JRBeanCollectionDataSource(datos);
            // Cargar plantilla Jasper
            JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile(ubPlantilla);
            // Llenar el informe con datos
            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, camposInf); //SIN PARAMETROS ADICIONALES
            // Exportar a PDF
            JasperExportManager.exportReportToPdfFile(jp, ubFichero);

        } catch (JRException e) {
            throw new RuntimeException("Error generando informe", e);
        }

    }

}
