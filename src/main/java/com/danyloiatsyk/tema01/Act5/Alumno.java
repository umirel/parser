package com.danyloiatsyk.tema01.Act5;
public class Alumno {
    private final String nombre;
    private final boolean matriculado;
    private final String id;
    private final String fecha;
    private final Nota[] notas;

    public Alumno(String id, String nombre, boolean matriculado, String fecha, Nota[] notas) {
        this.nombre = nombre;
        this.id = id;
        this.matriculado = matriculado;
        this.fecha = fecha;
        this.notas = notas;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean getMatriculado() {
        return matriculado;
    }

    public String getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public Nota[] getNotas() {
        return notas;
    }

}
