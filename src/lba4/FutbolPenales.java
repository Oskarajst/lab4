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
public class FutbolPenales extends FutbolSala {
    public FutbolPenales() { super("Futbol sala con penales", 750); }

    
    @Override
    public String[] getPreguntas(ArrayList<Participante> participantes) {
        return new String[] { "Goles de " + participantes.get(0).getNombre(),
            "Goles de " + participantes.get(1).getNombre(),
            "Penales del primero ",
            "Penales del segundo " };
    }

    
    @Override
    public ArrayList<Aporte> evaluar(ArrayList<String> datos, ArrayList<Participante> participantes) {
        Validacion.cantidad(datos, 4);
        int a = Validacion.entero(datos.get(0));
        int b = Validacion.entero(datos.get(1));
        if (a != b) {
            if (!datos.get(2).trim().isEmpty() || !datos.get(3).trim().isEmpty()) {
                throw new IllegalArgumentException("Solo se permiten penales si hay empate en goles.");
            }
            ArrayList<String> goles = new ArrayList<>();
            goles.add(datos.get(0));
            goles.add(datos.get(1));
            return super.evaluar(goles, participantes);
        }
        int penalA = Validacion.entero(datos.get(2));
        int penalB = Validacion.entero(datos.get(3));
        if (penalA == penalB) {
            throw new IllegalArgumentException("Los penales no pueden quedar empatados.");
        }
        if (penalA > penalB) { return aportesPartido(participantes, 2, 1, 0, 0); }
        return aportesPartido(participantes, 1, 2, 0, 1);
    }
}