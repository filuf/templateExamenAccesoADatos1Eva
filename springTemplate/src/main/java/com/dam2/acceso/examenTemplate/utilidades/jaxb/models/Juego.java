package com.dam2.acceso.examenTemplate.utilidades.jaxb.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
// anotaciones Lombok
@Getter
@Setter
public class Juego {

    @XmlElement(name = "ID_Juego")
    private String idJuego;
    private String titulo;
    private String consola;
    private String estado;


}
