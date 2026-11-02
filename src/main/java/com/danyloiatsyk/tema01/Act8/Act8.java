package com.danyloiatsyk.tema01.Act8;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Act8 {
    private static final String ARCHIVO = "src/main/java/com/danyloiatsyk/tema01/res/peliculas.json";

    public static void main(String[] args) throws Exception {
        try (FileReader reader = new FileReader(ARCHIVO)) {
            JSONArray datos = new JSONObject(new JSONTokener(reader)).getJSONArray("peliculas");
            var peliculas = new ArrayList<Pelicula>();
            Set<String> generos = new TreeSet<>();
            for (int i = 0; i < datos.length(); i++) {
                JSONObject pelicula = datos.getJSONObject(i);
                JSONObject puntuaciones = pelicula.getJSONObject("puntuaciones");
                double imdb = puntuaciones.getDouble("imdb");
                double rtNormalizada = puntuaciones.getDouble("rt") / 10.0;
                peliculas.add(new Pelicula(pelicula.getString("titulo"), (imdb + rtNormalizada) / 2));
                JSONArray generosPelicula = pelicula.getJSONArray("generos");
                for (int j = 0; j < generosPelicula.length(); j++) generos.add(generosPelicula.getString(j));
            }
            peliculas.stream().sorted(Comparator.comparingDouble(Pelicula::media).reversed())
                    .forEach(p -> System.out.printf("%s: %.2f%n", p.titulo(), p.media()));
            System.out.println("Géneros: " + String.join(", ", generos));
        }
    }

    private record Pelicula(String titulo, double media) {
    }
}
