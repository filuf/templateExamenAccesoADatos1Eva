package com.dam2.acceso.examenTemplate.utilidades.properties;

import com.dam2.acceso.examenTemplate.utilidades.properties.utils.PropertiesUtils;

import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties properties = PropertiesUtils.getPropertiesFile("src/main/java/com/dam2/acceso/examenTemplate/utilidades/properties/example.properties");

        String value = PropertiesUtils.getPropertieValue(properties, "hola.que.tal");
        System.out.println(value);

        System.out.println(PropertiesUtils.getPropertieValue(properties, "examenAcceso"));

        //setear y leer

        PropertiesUtils.setPropertieValue(properties, "miNuevaClave", "miNuevoValor","src/main/java/com/dam2/acceso/examenTemplate/utilidades/properties/example.properties");
        System.out.println(PropertiesUtils.getPropertieValue(properties, "miNuevaClave"));

    }
}
