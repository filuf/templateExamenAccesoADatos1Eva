package com.dam2.acceso.examenTemplate.utilidades.properties.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utilidad para trabajar con archivos .properties en Java.
 * Permite cargar, leer y modificar propiedades de un archivo de configuración.
 */
public class PropertiesUtils {

    /**
     * Carga un archivo .properties desde la ruta indicada.
     *
     * @param path La ruta del archivo .properties a cargar.
     * @return Un objeto {@link Properties} con las propiedades cargadas.
     */
    public static Properties getPropertiesFile(String path) {

        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(path)) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    /**
     * Obtiene el valor de una propiedad específica.
     *
     * @param propsFile El objeto {@link Properties} que contiene las propiedades.
     * @param key       La clave de la propiedad que se desea obtener.
     * @return El valor de la propiedad o {@code null} si la clave no existe.
     */
    public static String getPropertieValue(Properties propsFile, String key) {
        return propsFile.getProperty(key);
    }


    /**
     * Establece o actualiza el valor de una propiedad y guarda los cambios en el archivo.
     *
     * @param propsFile El objeto {@link Properties} que contiene las propiedades.
     * @param key       La clave de la propiedad a establecer o actualizar.
     * @param value     El valor que se asignará a la propiedad.
     * @param path      La ruta del archivo .properties donde se guardarán los cambios.
     */
    public static void setPropertieValue(Properties propsFile, String key, String value, String path) {
        propsFile.setProperty(key, value); // Setea o actualiza la propiedad
        // Guardar cambios en el archivo
        try (FileOutputStream fos = new FileOutputStream(path)) {
            propsFile.store(fos, "Archivo Properties actualizado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
