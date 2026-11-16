/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lba4;

/**
 *
 * @author oskar
 */
public class EmpresaPuntos implements Comparable<EmpresaPuntos> {
    private final String nombre;
    private int puntos;

    public EmpresaPuntos(String nombre) { this.nombre = nombre; }
    void sumar(int cantidad) { puntos += cantidad; }
    public String getNombre() { return nombre; }
    public int getPuntos() { return puntos; }

    
    @Override
    public int compareTo(EmpresaPuntos otra) {
        int orden = Integer.compare(otra.puntos, puntos);
        if (orden == 0) { orden = nombre.compareToIgnoreCase(otra.nombre); }
        return orden;
    }

    @Override
    public String toString() { return nombre + " | puntos: " + puntos; }
}
