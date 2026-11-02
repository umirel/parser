package com.danyloiatsyk.tema01.Act7;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.util.Scanner;

public class Act7 {
    private static final String ARCHIVO = "src/main/java/com/danyloiatsyk/tema01/res/inventario.json";

    public static void main(String[] args) throws Exception {
        String id = args.length > 0 ? args[0] : pedirId();
        try (FileReader reader = new FileReader(ARCHIVO)) {
            JSONArray productos = new JSONObject(new JSONTokener(reader)).getJSONArray("productos");
            for (int i = 0; i < productos.length(); i++) {
                JSONObject producto = productos.getJSONObject(i);
                if (id.equalsIgnoreCase(producto.getString("id"))) {
                    JSONObject ubicacion = producto.getJSONObject("ubicacion");
                    System.out.printf("%s: pasillo %d, estante %s%n", producto.getString("nombre"),
                            ubicacion.getInt("pasillo"), ubicacion.getString("estante"));
                    return;
                }
            }
        }
        System.out.println("No existe el producto " + id + ".");
    }

    private static String pedirId() {
        System.out.print("Id del producto: ");
        return new Scanner(System.in).nextLine().trim();
    }
}
