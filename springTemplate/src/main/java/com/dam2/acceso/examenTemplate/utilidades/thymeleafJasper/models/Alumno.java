package com.dam2.acceso.examenTemplate.utilidades.thymeleafJasper.models;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Alumno {

    @XmlAttribute
    private int id;

    @XmlElement
    private String nombre;

    @XmlElement
    private String direccion;

    @XmlElementWrapper(name = "notas")
    @XmlElement(name = "nota")
    private List<Nota> notas;

    public Alumno() {}

    public Alumno(int id, String nombre, String direccion, List<Nota> notas) {
        this.direccion = direccion;
        this.id = id;
        this.nombre = nombre;
        this.notas = notas;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "direccion='" + direccion + '\'' +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nota=" + notas +
                '}';
    }
}
