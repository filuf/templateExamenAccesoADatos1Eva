package com.dam2.acceso.examenTemplate.utilidades.dom.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Utilidades para trabajar con documentos XML mediante DOM.
 */
 public class DomDocumentUtils {

    /**
     * Carga un documento XML desde la ruta indicada.
     *
     * @param ficheroUri ruta del archivo XML en el sistema de archivos.
     * @return un objeto {@link Document} que representa el contenido del XML.
     *
     * @throws RuntimeException si ocurre un error al configurar el parser,
     *                          si el XML es inválido o si ocurre un error de E/S.
     */
    public static Document getDocument(String ficheroUri) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder.parse(new File(ficheroUri));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Carga un documento XML desde un objeto {@link File}.
     *
     * @param fichero archivo XML a cargar.
     * @return un objeto {@link Document} que representa el contenido del XML.
     *
     * @throws RuntimeException si ocurre un error al configurar el parser,
     *                          si el XML es inválido o si ocurre un error de E/S.
     */
    public static Document getDocument(File fichero) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder.parse(fichero);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Actualiza el archivo XML asociado a un {@link Document}.
     * <p>
     * Se aplica una indentación estándar para mejorar la legibilidad y se elimina el exceso
     * de saltos de línea configurando la propiedad {@code indent-amount}.
     * </p>
     *
     * @param document documento XML ya modificado que se desea guardar en disco.
     * @param file archivo XML de destino.
     *
     * @throws RuntimeException si ocurre un error durante la transformación o escritura del archivo.
     */
    public static void updateFile(Document document, File file) {
        try {
            // Limpia espacios/saltos sobrantes que generan líneas en blanco
            removeWhitespaceNodes(document.getDocumentElement());

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            DOMSource domSource = new DOMSource(document);
            StreamResult sr = new StreamResult(file);

            transformer.transform(domSource, sr);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static void removeWhitespaceNodes(Node node) {
        NodeList children = node.getChildNodes();
        for (int i = children.getLength() - 1; i >= 0; i--) {
            Node child = children.item(i);

            if (child.getNodeType() == Node.TEXT_NODE) {
                if (child.getTextContent().trim().isEmpty()) {
                    node.removeChild(child);
                }
            } else if (child.getNodeType() == Node.ELEMENT_NODE) {
                removeWhitespaceNodes(child);
            }
        }
    }
}
