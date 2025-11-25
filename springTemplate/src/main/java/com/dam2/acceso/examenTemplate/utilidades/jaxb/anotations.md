# JAXB Annotations Cheat Sheet

Este documento enumera **algunas de las anotaciones de JAXB (creo que estas son las que hemos visto en clase)**, organizadas por nivel (clase, campo, método) y sus posibles parámetros.

---

## 1. Anotaciones a nivel de clase

| Anotación          | Descripción                                                            | Parámetros comunes                                        | Ejemplo                                                                                  |
| ------------------ | ---------------------------------------------------------------------- | --------------------------------------------------------- | ---------------------------------------------------------------------------------------- |
| `@XmlRootElement`  | Define la raíz del XML para la clase                                   | `name`, `namespace`                                       | `@XmlRootElement(name="persona")`                                                        |
| `@XmlType`         | Define el orden de los elementos y el tipo XML                         | `name`, `namespace`, `propOrder`                          | `@XmlType(propOrder={"nombre", "edad"})`                                                 |
| `@XmlAccessorType` | Define cómo JAXB accede a la clase                                     | `XmlAccessType` (FIELD, PROPERTY, PUBLIC_MEMBER, NONE)    | `@XmlAccessorType(XmlAccessType.FIELD)`                                                  |

---

## 2. Anotaciones a nivel de campo o propiedad

| Anotación             | Descripción                                                         | Parámetros comunes                             | Ejemplo                                                 |
| --------------------- | ------------------------------------------------------------------- | ---------------------------------------------- | ------------------------------------------------------- |
| `@XmlElement`         | Define un elemento XML                                              | `name`, `required`, `nillable`, `defaultValue` | `@XmlElement(name="nombre_completo", required=true)`    |
| `@XmlAttribute`       | Define un atributo XML                                              | `name`, `required`                             | `@XmlAttribute(name="id")`                              |
| `@XmlValue`           | Define el valor textual de un elemento                              | Ninguno                                        | `@XmlValue private String valor;`                       |
| `@XmlElementWrapper`  | Envuelve una colección en un contenedor                             | `name`, `nillable`, `required`                 | `@XmlElementWrapper(name="telefonos")`                  |
| `@XmlTransient`       | Excluye el campo del XML                                            | Ninguno                                        | `@XmlTransient private int interno;`                    |
| `@XmlList`            | Convierte listas en un solo elemento de texto con valores separados | Ninguno                                        | `@XmlList private List<String> colores;`                |
| `@XmlAnyElement`      | Permite elementos XML no declarados                                 | `lax`                                          | `@XmlAnyElement(lax=true)`                              |
| `@XmlAnyAttribute`    | Permite atributos XML no declarados                                 | Ninguno                                        | `@XmlAnyAttribute private Map<QName,String> atributos;` |
| `@XmlID`              | Identificador único                                                 | Ninguno                                        | `@XmlID private String id;`                             |
| `@XmlIDREF`           | Referencia a un campo anotado con `@XmlID`                          | Ninguno                                        | `@XmlIDREF private Persona amigo;`                      |
| `@XmlMimeType`        | Define tipo MIME para datos binarios                                | `value`                                        | `@XmlMimeType("image/png") private DataHandler imagen;` |

---

## 3. Notas y buenas prácticas

* Siempre especificar `@XmlAccessorType(XmlAccessType.FIELD)` si se quieren mapear directamente los campos, especialmente con Lombok.
* Para listas, usar `@XmlElementWrapper` para envolver los elementos en un contenedor.
* `@XmlAttribute` se usa para atributos; `@XmlElement` para elementos.
* `@XmlTransient` sirve para excluir campos internos que no deben aparecer en XML.
* `@XmlRootElement` da por hecho que la primera letra de tu clase se convierte a minúscula, si el elemento está en mayúscula es conveniente especificar en name.
---