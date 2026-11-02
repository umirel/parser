package com.danyloiatsyk.tema01.Act4;

import com.danyloiatsyk.tema01.XmlUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Act4 {
    private static final String ARCHIVO = "src/main/java/com/danyloiatsyk/tema01/res/pedidos.xml";

    public static void main(String[] args) throws Exception {
        String id = args.length > 0 ? args[0] : pedirId();
        NodeList pedidos = XmlUtils.cargar(ARCHIVO).getElementsByTagName("pedido");
        for (int i = 0; i < pedidos.getLength(); i++) {
            Element pedido = (Element) pedidos.item(i);
            if (id.equalsIgnoreCase(pedido.getAttribute("id"))) {
                mostrarPedido(pedido);
                return;
            }
        }
        System.out.println("No existe el pedido " + id + ".");
    }

    private static void mostrarPedido(Element pedido) {
        BigDecimal calculado = BigDecimal.ZERO;
        NodeList items = pedido.getElementsByTagName("item");
        System.out.println("Items del pedido " + pedido.getAttribute("id") + ":");
        for (int i = 0; i < items.getLength(); i++) {
            Element item = (Element) items.item(i);
            int cantidad = Integer.parseInt(texto(item, "cantidad"));
            BigDecimal precio = new BigDecimal(texto(item, "precioUnitario"));
            BigDecimal subtotal = precio.multiply(BigDecimal.valueOf(cantidad));
            calculado = calculado.add(subtotal);
            System.out.printf("%s | %s | %d x %s = %s EUR%n", item.getAttribute("sku"), texto(item, "descripcion"),
                    cantidad, precio, subtotal);
        }
        BigDecimal indicado = new BigDecimal(texto(pedido, "total"));
        calculado = calculado.setScale(2, RoundingMode.HALF_UP);
        System.out.printf("Total indicado: %s EUR%nTotal calculado: %s EUR%nCoincide: %s%n", indicado, calculado,
                indicado.compareTo(calculado) == 0 ? "sí" : "no");
    }

    private static String pedirId() {
        System.out.print("Id del pedido: ");
        return new Scanner(System.in).nextLine().trim();
    }

    private static String texto(Element elemento, String etiqueta) {
        return elemento.getElementsByTagName(etiqueta).item(0).getTextContent().trim();
    }
}
