package com.dam2.acceso.examenTemplate.utilidades.jaxb.utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.nio.file.Path;

public class JaxbUtils {

    /**
     * Utilidad genérica para leer un archivo XML y convertirlo en un objeto Java
     * utilizando JAXB.
     *
     * @param <T>   El tipo de objeto que se espera obtener del XML.
     * @param path  La ruta del archivo XML a leer.
     * @param clazz La clase de tipo T que representa la estructura del XML.
     * @return Un objeto de tipo T con los datos deserializados desde el XML,
     *         o null si ocurre un error durante la deserialización.
     */
    public static <T> T readXml(Path path, Class<T> clazz) {
        T result = null;

        JAXBContext context;
        try {
            context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            result = (T) unmarshaller.unmarshal(path.toFile());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Utilidad genérica para leer un archivo XML y convertirlo en un objeto Java
     * utilizando JAXB.
     *
     * @param <T>   El tipo de objeto que se espera obtener del XML.
     * @param file  El archivo XML a leer.
     * @param clazz La clase de tipo T que representa la estructura del XML.
     * @return Un objeto de tipo T con los datos deserializados desde el XML,
     *         o null si ocurre un error durante la deserialización.
     */
    public static <T> T readXml(File file, Class<T> clazz) {
        T result = null;

        JAXBContext context;
        try {
            context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            result = (T) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Guarda un objeto Java en un archivo XML usando JAXB.
     *
     * @param object El objeto a serializar.
     * @param path   La ruta del archivo donde se guardará el XML.
     * @param <T>    Tipo del objeto.
     */
    public static <T> void writeXml(T object, Path path) {
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();

            // Formatea el XML con sangría para que sea legible
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Escribe el XML en el archivo
            marshaller.marshal(object, path.toFile());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
