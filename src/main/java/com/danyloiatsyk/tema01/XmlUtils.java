package com.danyloiatsyk.tema01;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public final class XmlUtils {
    private XmlUtils() {
    }

    public static Document cargar(String archivo) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        fabrica.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        fabrica.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        fabrica.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        Document documento = fabrica.newDocumentBuilder().parse(new File(archivo));
        documento.getDocumentElement().normalize();
        return documento;
    }
}
