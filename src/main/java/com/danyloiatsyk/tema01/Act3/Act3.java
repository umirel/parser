package com.danyloiatsyk.tema01.Act3;

import com.danyloiatsyk.tema01.XmlUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.Map;
import java.util.TreeMap;

public class Act3 {
    private static final String ARCHIVO = "src/main/java/com/danyloiatsyk/tema01/res/biblioteca.xml";

    public static void main(String[] args) throws Exception {
        NodeList libros = XmlUtils.cargar(ARCHIVO).getElementsByTagName("libro");
        Map<String, Integer> generos = new TreeMap<>();
        System.out.println("Títulos:");
        for (int i = 0; i < libros.getLength(); i++) {
            Element libro = (Element) libros.item(i);
            System.out.println("- " + texto(libro, "titulo"));
            NodeList generosLibro = libro.getElementsByTagName("genero");
            for (int j = 0; j < generosLibro.getLength(); j++) {
                generos.merge(generosLibro.item(j).getTextContent().trim(), 1, Integer::sum);
            }
        }
        System.out.println("Géneros:");
        generos.forEach((genero, cantidad) -> System.out.printf("%s: %d%n", genero, cantidad));
    }

    private static String texto(Element elemento, String etiqueta) {
        return elemento.getElementsByTagName(etiqueta).item(0).getTextContent().trim();
    }
}
