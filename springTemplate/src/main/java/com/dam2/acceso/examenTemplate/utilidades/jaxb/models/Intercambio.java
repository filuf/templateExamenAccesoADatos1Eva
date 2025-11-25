package com.dam2.acceso.examenTemplate.utilidades.jaxb.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
//Lombok
@Getter
@Setter
public class Intercambio {

    @XmlElement(name = "ID_Intercambio")
    private String idIntercambio;
    @XmlElement(name = "ID_Usuario_Emisor")
    private String idUsuarioEmisor;
    @XmlElement(name = "ID_Usuario_Receptor")
    private String idUsuarioReceptor;
    @XmlElement(name = "ID_Juego")
    private String idJuego;
}