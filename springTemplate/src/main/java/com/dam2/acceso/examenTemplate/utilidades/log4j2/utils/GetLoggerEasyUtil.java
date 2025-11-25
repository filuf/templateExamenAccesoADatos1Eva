package com.dam2.acceso.examenTemplate.utilidades.log4j2.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utilidad para obtener instancias de {@link Logger} de manera sencilla.
 * <p>
 * Esta clase proporciona un método estático que facilita la creación de loggers
 * asociados a una clase específica, evitando la necesidad de declarar manualmente
 * el logger en cada clase.
 * </p>
 */
public class GetLoggerEasyUtil {


    /**
     * Devuelve un {@link Logger} asociado a la clase proporcionada.
     *
     * @param <T>   el tipo de la clase para la cual se obtiene el logger
     * @param clase la clase para la cual se desea obtener el logger
     * @return un {@link Logger} para la clase especificada
     * @throws NullPointerException si {@code clase} es {@code null}
     */
    public static <T> Logger getLogger(Class<T> clase) {
        return LogManager.getLogger(clase);
    }
}
