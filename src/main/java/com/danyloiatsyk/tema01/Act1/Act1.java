package com.danyloiatsyk.tema01.Act1;

import com.danyloiatsyk.tema01.XmlUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Act1 {
    private static final String ARCHIVO = "src/main/java/com/danyloiatsyk/tema01/res/empleados.xml";

    public static void main(String[] args) throws Exception {
        NodeList empleados = XmlUtils.cargar(ARCHIVO).getElementsByTagName("empleado");
        for (int i = 0; i < empleados.getLength(); i++) {
            Element empleado = (Element) empleados.item(i);
            System.out.printf("%s | %s | %.2f EUR%n", empleado.getAttribute("id"),
                    texto(empleado, "nombre"), Double.parseDouble(texto(empleado, "salario")));
        }
    }

    private static String texto(Element elemento, String etiqueta) {
        return elemento.getElementsByTagName(etiqueta).item(0).getTextContent().trim();
    }
}
