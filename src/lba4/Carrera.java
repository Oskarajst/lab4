/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lba4;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author oskar
 */
public class Carrera implements Evento {
    private final DatosEvento datos;

    
    public Carrera(String codigo, Torneo torneo, ArrayList<Participante> participantes) {
        datos = new DatosEvento(codigo, torneo, participantes);
    }

    
    
    @Override public String getCodigo() { return datos.getCodigo(); }
    @Override public Torneo getTorneo() { return datos.getTorneo(); }
    @Override public ArrayList<Participante> getParticipantes() { return datos.getParticipantes(); }
    @Override public LocalDate getFecha() { return datos.getFecha(); }
    @Override public int getHora() { return datos.getHora(); }
    @Override public String getSede() { return datos.getSede(); }
    @Override public String getEstado() { return datos.getEstado(); }
    @Override public boolean tieneResultado() { return datos.tieneResultado(); }
    @Override public ArrayList<Aporte> getAportes() { return datos.getAportes(); }
    @Override public String[] getPreguntas() { return datos.getPreguntas(); }
    @Override public void programar(LocalDate fecha, int hora, String sede) { datos.programar(fecha, hora, sede); }
    @Override public void registrarResultado(ArrayList<String> resultado) { datos.registrarResultado(resultado); }
    @Override public int compareTo(Evento otro) { return datos.comparar(otro); }
    @Override public String getResumen() { return datos.resumen("Carrera"); }
    
    
}