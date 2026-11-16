/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lba4;

import java.util.ArrayList;

/**
 *
 * @author oskar
 */
public class Baloncesto extends DisciplinaEncuentros {
    public Baloncesto() { super("Baloncesto", 4, 6, 500); }

    @Override
    public ArrayList<Aporte> evaluar(ArrayList<String> datos, ArrayList<Participante> participantes) {
        Validacion.cantidad(datos, 2);
        int a = Validacion.entero(datos.get(0));
        int b = Validacion.entero(datos.get(1));
        if (a == b) { throw new IllegalArgumentException("Baloncesto no permite empates."); }
        if (a > b) { return aportesPartido(participantes, 2, 1, (long) a - b, 0); }
        return aportesPartido(participantes, 1, 2, (long) a - b, 1);
    }
}