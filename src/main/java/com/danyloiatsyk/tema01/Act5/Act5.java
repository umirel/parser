package com.danyloiatsyk.tema01.Act5;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.IOException;

public class Act5 {
    public static Alumno[] alumnos(String archivo) {
        Alumno[] alumnos = null;
        try (FileReader reader = new FileReader(archivo)) {
            JSONArray jsonArray = new JSONArray(new JSONTokener(reader));
            alumnos = new Alumno[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONArray array2 = jsonObject.getJSONArray("notas");
                Nota[] notas = new Nota[array2.length()];
                for (int j = 0; j < array2.length(); j++) {
                    JSONObject object2 = array2.getJSONObject(j);
                    notas[j] = new Nota(object2.getString("asignatura"), object2.getDouble("nota"));
                }
                alumnos[i] = new Alumno(jsonObject.getString("id"), jsonObject.getString("nombre"),
                        jsonObject.getBoolean("matriculado"), jsonObject.getString("fechaNacimiento"), notas);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("No se pudo leer " + archivo, e);
        }
        return alumnos;
    }
    public static void main(String[] args) {
        Alumno[] alumnos = alumnos("src/main/java/com/danyloiatsyk/tema01/res/alumnos.json");
        for (Alumno alumno : alumnos) {
            System.out.printf("%s | %s%n", alumno.getNombre(), alumno.getFecha());
        }
    }
}
