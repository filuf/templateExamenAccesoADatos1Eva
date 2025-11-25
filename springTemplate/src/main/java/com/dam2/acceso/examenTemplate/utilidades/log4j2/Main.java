package com.dam2.acceso.examenTemplate.utilidades.log4j2;

import com.dam2.acceso.examenTemplate.utilidades.log4j2.utils.GetLoggerEasyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Clase para copiar Logger y probar el funcionamiento de log4j2
 * emplea el fichero en main/resources/log4j2.xml<br><br>
 * existe una copia de ese fichero en este directorio
 *
 */
public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);


    public static void main(String[] args) {

        System.out.println("TEST DE FUNCIONAMIENTO LOG\n");


        log.debug("debug"); // no debe salir por la configuracion del appender

        String nombre = "Aitor";
        Integer edad = 21;

        log.info("logeando nombre: {}, edad: {}", nombre, edad);

        log.warn("warning");

        log.error("error");

        log.fatal("fatal");


        //he creado esta utilidad para obtener el logger de una manera más simple si os es necesario

        var milogger = GetLoggerEasyUtil.getLogger(Main.class); // simplemente, escribid vuestra clase .class

        milogger.info("logeando con este otro logger nombre: {}, edad: {}", nombre, edad);
    }
}
