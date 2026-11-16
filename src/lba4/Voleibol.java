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
public class Voleibol extends DisciplinaEncuentros {
    
    public Voleibol() { super("Voleibol", 4, 6, 450); }

    
    @Override
    public ArrayList<Aporte> evaluar(ArrayList<String> datos, ArrayList<Participante> participantes) {
        Validacion.cantidad(datos, 2);
        int a = Validacion.entero(datos.get(0));
        int b = Validacion.entero(datos.get(1));
        if (!((a == 3 && b <= 2) || (b == 3 && a <= 2))) {
            throw new IllegalArgumentException("El ganador debe tener 3 sets y el perdedor entre 0 y 2.");
        }
        
        
        
        int perdedor = Math.min(a, b);
        int puntosGanador = perdedor == 2 ? 2 : 3;
        int puntosPerdedor = perdedor == 2 ? 1 : 0;
        if (a > b) {
            return aportesPartido(participantes, puntosGanador, puntosPerdedor, a - b, 0);
        }
        
        
        return aportesPartido(participantes, puntosPerdedor, puntosGanador, a - b, 1);
    }
}

