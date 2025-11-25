package com.dam2.acceso.examenTemplate.utilidades.jaxb.models;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@XmlRootElement(name = "BibliotecaVideojuegos")
@XmlType(propOrder = {
        "usuarios",
        "juegos",
        "intercambios"
})
@XmlAccessorType(XmlAccessType.FIELD)
//etiquetas Lombok
@Data
public class BibliotecaVideojuegos {

    @XmlElementWrapper(name = "usuarios")
    @XmlElement(name = "usuario")
    private List<Usuario> usuarios;

    @XmlElementWrapper(name = "juegos")
    @XmlElement(name = "juego")
    private List<Juego> juegos;

    @XmlElementWrapper(name = "intercambios")
    @XmlElement(name = "intercambio")
    private List<Intercambio> intercambios;


}
