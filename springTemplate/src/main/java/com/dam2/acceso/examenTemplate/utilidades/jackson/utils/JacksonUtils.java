package com.dam2.acceso.examenTemplate.utilidades.jackson.utils;

import tools.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.util.Optional;

public class JacksonUtils {


    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Serializa un objeto a su representación JSON.
     *
     * @param <T>               el tipo del objeto a serializar
     * @param objectToSerialize el objeto que se desea convertir a JSON
     * @return un {@link Optional} que contiene el JSON si la serialización fue exitosa,
     *         o {@link Optional#empty()} si ocurrió un error
     */
    public static <T> Optional<String> serialize(T objectToSerialize) {
        try {
            return Optional.of(mapper.writeValueAsString(objectToSerialize));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Deserializa un JSON a un objeto del tipo especificado.
     *
     * @param <T>   el tipo del objeto a deserializar
     * @param json  el JSON que se desea convertir a objeto
     * @param clazz la clase del objeto esperado
     * @return un {@link Optional} que contiene el objeto si la deserialización fue exitosa,
     *         o {@link Optional#empty()} si ocurrió un error
     */
    public static <T> Optional<T> deserialize(String json, Class<T> clazz) {
        try {
            return Optional.of(mapper.readValue(json, clazz));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static <T> void writeToFile(Path path, T object) {
        mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), object);
    }
}
