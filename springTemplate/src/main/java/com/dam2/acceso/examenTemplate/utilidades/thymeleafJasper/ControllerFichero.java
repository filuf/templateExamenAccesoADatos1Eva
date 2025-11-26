package com.dam2.acceso.examenTemplate.utilidades.thymeleafJasper;

import com.dam2.acceso.examenTemplate.utilidades.thymeleafJasper.logic.GestorAlumno;
import com.dam2.acceso.examenTemplate.utilidades.thymeleafJasper.models.Alumnos;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ControllerFichero {


    private static String JASPER_DIR_OUTPUT = "files_controller_output"; // donde salen tus reportes (debe estar creado el directorio)
    private static String JASPER_DIR_TEMPLATES = "jasper_templates"; // donde almacenas tus reportes
    private static String JASPER_TEMPLATE_NAME = "reportAlumno.jasper";
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    /**
     * Recibe un archivo XML subido por el usuario, lo guarda en el servidor,
     * lo convierte a JSON y genera un informe PDF usando JasperReports.
     * Agrega los nombres de los archivos JSON y PDF al modelo para su descarga.
     *
     * @param file  El archivo XML subido por el usuario.
     * @param model Modelo Spring para pasar atributos a la vista.
     * @return el nombre de la vista "download" para mostrar los enlaces de descarga.
     * @throws RuntimeException si ocurre un error de E/S durante la escritura o generación de archivos.
     */
    @PostMapping("/upload")
    public String getRequest(@RequestParam("file") MultipartFile file, Model model) {

        try{
            Path fileXML = Paths.get(JASPER_DIR_OUTPUT, file.getOriginalFilename()); // ruta + nombre fichero

            byte [] bytes = file.getBytes();

            Files.write(fileXML, bytes);

            Alumnos als = GestorAlumno.leerXmlJavaX(fileXML);
            File fileJSON = GestorAlumno.xmlAJson(fileXML.toFile());


            String nombrePDF = file.getOriginalFilename().replace(".xml", ".pdf");
            Path pathPDF = Paths.get(JASPER_DIR_OUTPUT, nombrePDF);

            GestorAlumno.generarInforme(als.getAlumnos(), pathPDF.toString(),
                    Paths.get(JASPER_DIR_TEMPLATES, JASPER_TEMPLATE_NAME).toString());

            File filePDF = pathPDF.toFile();

            System.out.println(filePDF.getName());
            model.addAttribute("fileName", fileJSON.getName());
            model.addAttribute("filePDF", filePDF.getName());

            return "download";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Descarga un archivo JSON previamente generado.
     *
     * @param filename Nombre del archivo JSON a descargar.
     * @return ResponseEntity que contiene el recurso a descargar con encabezados de Content-Disposition.
     * @throws MalformedURLException si la URL del recurso no es válida.
     */
    @PostMapping("/download")
    public ResponseEntity<?> descargaFichero(@RequestParam("fileName") String filename) throws MalformedURLException {

        Path filePath = Paths.get(JASPER_DIR_OUTPUT, filename);
        Resource resource = new UrlResource(filePath.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }

    /**
     * Descarga un archivo PDF previamente generado.
     *
     * @param filePDF Nombre del archivo PDF a descargar.
     * @return ResponseEntity que contiene el recurso a descargar con encabezados de Content-Disposition.
     * @throws MalformedURLException si la URL del recurso no es válida.
     */
    @PostMapping("/download-pdf")
    public ResponseEntity<?> descargaPDF(@RequestParam("filePDF") String filePDF) throws MalformedURLException {

        Path filePath = Paths.get(JASPER_DIR_OUTPUT, filePDF);
        Resource resource = new UrlResource(filePath.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
