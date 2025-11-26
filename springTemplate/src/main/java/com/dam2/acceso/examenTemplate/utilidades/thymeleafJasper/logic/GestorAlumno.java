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
    public static File xmlAJson(File fileXml) throws IOException {

        XmlMapper xmlMapper = new XmlMapper();
        JsonNode node = xmlMapper.readTree(fileXml);

        String nameJson = fileXml.getName().substring(0,fileXml.getName().lastIndexOf("."))+".json";

        File fileJson = new File(fileXml.getParent(),nameJson);

        ObjectMapper mapper = new ObjectMapper();

        mapper.writerWithDefaultPrettyPrinter().writeValue(fileJson,node);

        return fileJson;
    }

    /* ---------------- JASPER REPORTS ------------------*/

    public static void generarInforme(List<Alumno> pedidos, String ubFichero, String ubPlantilla) {

        log.info(pedidos.toString());
        log.info(ubFichero);

        List<DatosInforme> datos = pedidos.stream()
                .map(a -> new DatosInforme(a.getId(), a.getNombre(), a.getDireccion()))
                .toList();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("cantidad",pedidos.size()+"");

        try {

            JRBeanCollectionDataSource camposInf = new JRBeanCollectionDataSource(datos);
            JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile(ubPlantilla);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, camposInf); //SIN PARAMETROS ADICIONALES
            JasperExportManager.exportReportToPdfFile(jp, ubFichero);

        } catch (JRException e) {
            throw new RuntimeException("Error generando informe", e);
        }

    }

}
