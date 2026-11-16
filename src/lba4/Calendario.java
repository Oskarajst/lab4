/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lba4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author oskar
 */
public class Calendario implements Iterable<Evento> {
    private final ArrayList<Evento> eventos;

    public Calendario(ArrayList<Evento> origen) {
        eventos = new ArrayList<>();
        
        for (Evento evento : origen) {
            
            if (evento.getFecha() != null) { eventos.add(evento); }
        }
        Collections.sort(eventos);
    }

    
    
    @Override
    public Iterator<Evento> iterator() {
        ArrayList<Evento> copia = new ArrayList<>(eventos);
        Collections.sort(copia);
        return Collections.unmodifiableList(copia).iterator();
    }
}
