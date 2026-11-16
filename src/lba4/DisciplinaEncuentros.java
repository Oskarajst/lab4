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
public abstract class DisciplinaEncuentros extends Disciplina {
    protected DisciplinaEncuentros(String nombre, int minimo, int maximo, double cuota) {
        super(nombre, minimo, maximo, cuota);
    }

    @Override
    public ArrayList<Evento> crearEventos(Torneo torneo) {
        ArrayList<Evento> eventos = new ArrayList<>();
        ArrayList<Participante> inscritos = torneo.getParticipantes();
        
        int numero = 1;
        for (int i = 0; i < inscritos.size(); i++) {
            
            for (int j = i + 1; j < inscritos.size(); j++) {
                
                eventos.add(new Encuentro(torneo.getCodigo() + "-E" + numero,torneo, inscritos.get(i), inscritos.get(j)));
                numero++;
            }
        }
        
        return eventos;
    }

    @Override
    public String[] getPreguntas(ArrayList<Participante> participantes) {
        return new String[] { "Marcador de " + participantes.get(0).getNombre(),"Marcador de " + participantes.get(1).getNombre() };
    }
}
