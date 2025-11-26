package com.dam2.acceso.examenTemplate.utilidades.thymeleafJasper.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Nota {

    @XmlAttribute
    private String mat;

    @XmlElement
    private double nota;

    public Nota() {}

    public Nota(String mat, double nota) {
        this.mat = mat;
        this.nota = nota;
    }
}
