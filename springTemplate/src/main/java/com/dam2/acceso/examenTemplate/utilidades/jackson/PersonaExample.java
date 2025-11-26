package com.dam2.acceso.examenTemplate.utilidades.jackson;

public class PersonaExample {

    private String nombre;
    private int edad;

    // Constructor vacío requerido para la deserialización
    public PersonaExample() {}

    public PersonaExample(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
}
