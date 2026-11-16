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
public interface Evento extends Comparable<Evento> {
    String getCodigo();
    Torneo getTorneo();
    ArrayList<Participante> getParticipantes();
    LocalDate getFecha();
    int getHora();
    String getSede();
    String getEstado();
    boolean tieneResultado();
    void programar(LocalDate fecha, int hora, String sede);
    String[] getPreguntas();
    void registrarResultado(ArrayList<String> datos);
    ArrayList<Aporte> getAportes();
    String getResumen();
}
