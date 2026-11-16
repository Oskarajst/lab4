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
public class Torneo {
    
    
    private final Sistema sistema;
    private final String codigo;
    private final String nombre;
    private final Disciplina disciplina;
    private final ArrayList<Participante> participantes;
    private ArrayList<Evento> eventos;
    private String estado;
    private double ingresos;
    private double premioEntregado;

    Torneo(Sistema sistema, String codigo, String nombre, Disciplina disciplina) {
        this.sistema = sistema;
        this.codigo = Validacion.texto(codigo, "Codigo").toUpperCase();
        this.nombre = Validacion.texto(nombre, "Nombre");
        this.disciplina = disciplina;
        this.participantes = new ArrayList<>();
        this.eventos = new ArrayList<>();
        this.estado = "EN INSCRIPCION";
    }

    
    Sistema getSistema() { return sistema; }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public Disciplina getDisciplina() { return disciplina; }
    public String getEstado() { return estado; }
    public double getIngresos() { return ingresos; }
    public double getPremioEntregado() { return premioEntregado; }
    public ArrayList<Participante> getParticipantes() { return new ArrayList<>(participantes); }
    public ArrayList<Evento> getEventos() { return new ArrayList<>(eventos); }

    
    public void inscribir(Participante participante) {
        agregarParticipante(participante);
        ingresos += disciplina.getCuota();
    }

    
    void cargarParticipante(Participante participante) { agregarParticipante(participante); }

    private void agregarParticipante(Participante participante) {
        if (!estado.equals("EN INSCRIPCION")) {
            throw new IllegalArgumentException("Las inscripciones de este torneo estan cerradas.");
        }
        if (participante == null || !sistema.getParticipantes().contains(participante)) {
            throw new IllegalArgumentException("Primero registre el participante en el sistema.");
        }
        disciplina.validarInscripcion(getParticipantes(), participante);
        participantes.add(participante);
    }

    public void cerrarInscripciones() {
        if (!estado.equals("EN INSCRIPCION")) {
            throw new IllegalArgumentException("El torneo ya cerro sus inscripciones.");
        }
        if (participantes.size() < disciplina.getMinimo()) {
            throw new IllegalArgumentException("Se necesitan al menos " + disciplina.getMinimo() + " participantes.");
        }
        ArrayList<Evento> nuevos = disciplina.crearEventos(this);
        eventos = nuevos;
        estado = "EN JUEGO";
    }

    
    
    public ArrayList<Posicion> getTabla() {
        ArrayList<Posicion> tabla = new ArrayList<>();
        for (Participante participante : participantes) {
            Posicion posicion = new Posicion(participante);
            for (Evento evento : eventos) {
                for (Aporte aporte : evento.getAportes()) {
                    if (aporte.getParticipante() == participante) { posicion.sumar(aporte); }
                }
            }
            tabla.add(posicion);
        }
        Collections.sort(tabla);
        return tabla;
    }

    void revisarFinalizacion() {
        if (!estado.equals("EN JUEGO") || eventos.isEmpty()) { return; }
        for (Evento evento : eventos) {
            if (!evento.tieneResultado()) { return; }
        }
        estado = "FINALIZADO";
        if (!sistema.estaCargando()) {
            premioEntregado = disciplina.getCuota() * participantes.size() * 0.50;
        }
    }

    public Participante getCampeon() {
        if (!estado.equals("FINALIZADO")) { return null; }
        return getTabla().get(0).getParticipante();
    }

    
    @Override
    public String toString() {
        return codigo + nombre + " " + disciplina.getNombre()+ " " + estado + " inscritos: " + participantes.size();
    }
}



