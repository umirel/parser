package com.danyloiatsyk.tema01.Act2;

import com.danyloiatsyk.tema01.XmlUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Act2 {
    private static final String ARCHIVO = "src/main/java/com/danyloiatsyk/tema01/res/empleados.xml";

    public static void main(String[] args) throws Exception {
        List<Empleado> empleados = cargarEmpleados();
        Map<String, List<Empleado>> porDepartamento = empleados.stream()
                .collect(Collectors.groupingBy(Empleado::departamento));
        System.out.printf("%-15s | %-12s | %s%n", "Departamento", "Nº empleados", "Salario medio");
        porDepartamento.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEach(entrada -> System.out.printf("%-15s | %-12d | %.2f EUR%n", entrada.getKey(),
                        entrada.getValue().size(), entrada.getValue().stream()
                                .mapToDouble(Empleado::salario).average().orElse(0)));
    }

    public static List<Empleado> cargarEmpleados() throws Exception {
        NodeList nodos = XmlUtils.cargar(ARCHIVO).getElementsByTagName("empleado");
        List<Empleado> resultado = new ArrayList<>();
        for (int i = 0; i < nodos.getLength(); i++) {
            Element empleado = (Element) nodos.item(i);
            resultado.add(new Empleado(empleado.getAttribute("id"), texto(empleado, "nombre"),
                    texto(empleado, "departamento"), Double.parseDouble(texto(empleado, "salario"))));
        }
        return resultado;
    }

    private static String texto(Element elemento, String etiqueta) {
        return elemento.getElementsByTagName(etiqueta).item(0).getTextContent().trim();
    }
}
