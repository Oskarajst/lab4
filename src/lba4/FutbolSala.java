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
public class FutbolSala extends DisciplinaEncuentros {
    public FutbolSala() { this("Futbol sala", 600); }

    protected FutbolSala(String nombre, double cuota) {
        super(nombre, 4, 8, cuota);
    }

    
    @Override
    public ArrayList<Aporte> evaluar(ArrayList<String> datos, ArrayList<Participante> participantes) {
        Validacion.cantidad(datos, 2);
        int a = Validacion.entero(datos.get(0));
        int b = Validacion.entero(datos.get(1));
        if (a > b) { return aportesPartido(participantes, 3, 0, (long) a - b, 0); }
        if (b > a) { return aportesPartido(participantes, 0, 3, (long) a - b, 1); }
        return aportesPartido(participantes, 1, 1, 0, -1);
    }
}
