package com.dam2.acceso.examenTemplate.utilidades.dom;

import com.dam2.acceso.examenTemplate.utilidades.dom.utils.DomDocumentUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class GestionUniversidad {

    private File fichero;

    private Document documento;

    public GestionUniversidad(String fichero) {

        this.fichero = new File(fichero);
        // this.document = this.cargarDocument();

        this.documento = DomDocumentUtils.getDocument(fichero);
    }

    private void cargarDocument() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            documento = documentBuilder.parse(this.fichero);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void listarCarrerasPorNombreDeFacultad(String facultadABuscar) {
        System.out.println("Listando carreras de la facultad: " + facultadABuscar);

        NodeList facultades = documento.getElementsByTagName("facultad");

        for (int i = 0; i < facultades.getLength(); i++) {
            Element facultad = (Element) facultades.item(i);

            if (facultad.getAttribute("nombre").equals(facultadABuscar)) {

                NodeList carreras =  facultad.getElementsByTagName("carrera");
                System.out.println("Carreras:");
                for (int j = 0; j < carreras.getLength(); j++) {
                    Element carrera = (Element) carreras.item(j);

                    String nombreCarrera = carrera.getElementsByTagName("nombre").item(0).getTextContent();
                    System.out.println(nombreCarrera);
                }
            }
        }
    }

    // la gracia de borrar es que necesitas eliminar desde el padre
    public void eliminarEstudiantesMenoresDeNAniosPorDepartamento(Integer topeEdad, String departamento) {

        NodeList departamentos = documento.getElementsByTagName("departamento");

        for (int i = 0; i < departamentos.getLength(); i++) {
            Node departamentoNode = departamentos.item(i);

            if (departamentoNode.getTextContent().equals(departamento)) {
                Element carrera = (Element) departamentoNode.getParentNode();
                NodeList estudiantes = carrera.getElementsByTagName("estudiante");

                // RECORREMOS LA LISTA A LA INVERSA PARA NO RECORTAR SU TAMAÑO EN EL CAMINO
                for (int j = estudiantes.getLength() - 1; j >= 0; j--) {
                    Element estudiante = (Element) estudiantes.item(j);
                    Element edad = (Element) estudiante.getElementsByTagName("edad").item(0);

                    if (Integer.parseInt(edad.getTextContent()) < topeEdad) {
                        System.out.println("Eliminando al estudiante " + estudiante.getElementsByTagName("nombre").item(0).getTextContent() + " con edad " + edad.getTextContent());
                        estudiante.getParentNode().removeChild(estudiante); // siempre removemos desde el padre dandole el nodo hijo
                    }

                }
                DomDocumentUtils.updateFile(documento, fichero);
                return; // sale
            }

                /* Siempre podemos agregar a una lista y borrar después

                List<Node> estudiantesAEliminar = new LinkedList<>();
                for (int j = 0; j < estudiantes.getLength(); j++) {
                    Element estudiante = (Element) estudiantes.item(j);
                    Element edad = (Element) estudiante.getElementsByTagName("edad").item(0);

                    if (Integer.parseInt(edad.getTextContent()) < topeEdad) {
                        System.out.println("miau");
                        estudiantesAEliminar.add(estudiante);
                    }

                }
                estudiantesAEliminar.stream()
                        .peek(e -> System.out.println("Eliminando al estudiante " + ((Element) e).getElementsByTagName("nombre").item(0).getTextContent() + " con edad " + ((Element) e).getElementsByTagName("edad").item(0).getTextContent()))
                        .forEach(e -> e.getParentNode().removeChild(e));
            }

                 */
        }

    }

    public void aniadirMateriaAEstudiantePorIDEstudiante(String idEstudiante, String codigoMateria, String nombreMateria, double notaMateria) {
        NodeList estudiantes = documento.getElementsByTagName("estudiante");

        for (int i = 0; i < estudiantes.getLength(); i++) {
            Element estudiante = (Element) estudiantes.item(i);
            if (estudiante.getAttribute("id").equals(idEstudiante)) {
                // encuentra la lista de materias del estudiante
                NodeList materiasDeEstudiante = estudiante.getElementsByTagName("materias");

                // crea una materia y sus elementos hijo
                Element nuevaMateria = documento.createElement("materia");
                Element nombreNuevaMateria = documento.createElement("nombre");
                Element notaNuevaMateria = documento.createElement("nota");

                // setea el atributo de la materia y el content de los hijos
                nuevaMateria.setAttribute("codigo", codigoMateria);
                nombreNuevaMateria.setTextContent(nombreMateria);
                notaNuevaMateria.setTextContent(String.valueOf(notaMateria));

                // añade los hijos a la materia
                nuevaMateria.appendChild(nombreNuevaMateria);
                nuevaMateria.appendChild(notaNuevaMateria);

                // añade la materia a la lista de materias
                materiasDeEstudiante.item(0).appendChild(nuevaMateria);

                DomDocumentUtils.updateFile(documento, fichero);
                return; // sale
            }
        }
    }
}
