package com.dam2.acceso.examenTemplate.utilidades.jackson;

import com.dam2.acceso.examenTemplate.utilidades.jackson.utils.JacksonUtils;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        PersonaExample persona = new PersonaExample("Paco", 38);

        // Serializar
        Optional<String> jsonOpt = JacksonUtils.serialize(persona);
        jsonOpt.ifPresent(json -> System.out.println("JSON: " + json));

        // Deserializar
        jsonOpt.ifPresent(json -> {
            Optional<PersonaExample> personaOpt = JacksonUtils.deserialize(json, PersonaExample.class);
            personaOpt.ifPresent(p -> System.out.println("Nombre: " + p.getNombre() + ", Edad: " + p.getEdad()));
        });

        //se puede manejar con isPresent = ¿Está presente?
        if (jsonOpt.isPresent()) {
            System.out.println(jsonOpt.get());
        }

    }
}
