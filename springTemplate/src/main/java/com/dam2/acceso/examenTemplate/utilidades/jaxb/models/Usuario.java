package com.dam2.acceso.examenTemplate.utilidades.jaxb.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
//anotaciones Lombok
@Getter
@Setter
public class Usuario {

    @XmlAttribute(name = "ID_Usuario")
    private String idUsuario;

    @XmlElement(name = "nombre")
    private String nombre;
}
