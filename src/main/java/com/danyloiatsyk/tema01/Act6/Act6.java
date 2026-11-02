package com.danyloiatsyk.tema01.Act6;

import com.danyloiatsyk.tema01.Act5.Act5;
import com.danyloiatsyk.tema01.Act5.Alumno;

import java.util.Comparator;

public class Act6 {
    private static final String ARCHIVO = "src/main/java/com/danyloiatsyk/tema01/res/alumnos.json";

    public static void main(String[] args) {
        Alumno[] alumnos = Act5.alumnos(ARCHIVO);
        MejorNota mejor = null;
        Alumno mejorMedia = null;
        double mediaMayor = Double.NEGATIVE_INFINITY;
        for (Alumno alumno : alumnos) {
            double suma = 0;
            for (var nota : alumno.getNotas()) {
                suma += nota.getNota();
                if (mejor == null || nota.getNota() > mejor.nota) {
                    mejor = new MejorNota(alumno.getNombre(), nota.getAsignatura(), nota.getNota());
                }
            }
            double media = suma / alumno.getNotas().length;
            if (media > mediaMayor) {
                mediaMayor = media;
                mejorMedia = alumno;
            }
        }
        System.out.printf("Nota más alta: %s — %s (%.1f)%n", mejor.alumno, mejor.asignatura, mejor.nota);
        System.out.printf("Mayor nota media: %s (%.2f)%n", mejorMedia.getNombre(), mediaMayor);
    }

    private record MejorNota(String alumno, String asignatura, double nota) {
    }
}
