/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lba4;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author oskar
 */
public class Relevos extends Disciplina {
    public Relevos() { super("Relevos", 3, 8, 300); }

    @Override
    public ArrayList<Evento> crearEventos(Torneo torneo) {
        ArrayList<Evento> eventos = new ArrayList<>();
        eventos.add(new Carrera(torneo.getCodigo() + "-E1", torneo, torneo.getParticipantes()));
        return eventos;
    }

    
    @Override
    public String[] getPreguntas(ArrayList<Participante> participantes) {
        String[] preguntas = new String[participantes.size()];
        for (int i = 0; i < preguntas.length; i++) {
            preguntas[i] = "Tiempo en segundos de " + participantes.get(i).getNombre() + " (DQ si fue descalificado)";
        }
        return preguntas;
    }

    
    @Override
    public ArrayList<Aporte> evaluar(ArrayList<String> datos, ArrayList<Participante> participantes) {
        Validacion.cantidad(datos, participantes.size());
        ArrayList<Double> tiempos = new ArrayList<>();
        ArrayList<Double> ordenados = new ArrayList<>();
        for (String dato : datos) {
            if (dato.trim().equalsIgnoreCase("DQ")) {
                tiempos.add(null);
            } else {
                double tiempo = Validacion.tiempo(dato);
                if (ordenados.contains(tiempo)) {
                    throw new IllegalArgumentException("Los tiempos no pueden repetirse.");
                }
                tiempos.add(tiempo);
                ordenados.add(tiempo);
            }
        }
        
        Collections.sort(ordenados);
        int[] puntosLugar = {10, 8, 6, 5, 4, 3, 2, 1};
        ArrayList<Aporte> aportes = new ArrayList<>();
        for (int i = 0; i < participantes.size(); i++) {
            int puntos = 0;
            int victorias = 0;
            if (tiempos.get(i) != null) {
                int lugar = ordenados.indexOf(tiempos.get(i));
                puntos = puntosLugar[lugar];
                if (lugar == 0) { victorias = 1; }
            }
            aportes.add(new Aporte(participantes.get(i), puntos, 0, victorias));
        }
        return aportes;
    }
}

