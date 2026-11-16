/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lba4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author oskar
 */
public class Sistema {
    private final ArrayList<Participante> participantes;
    private final ArrayList<Torneo> torneos;
    private final ArrayList<Disciplina> disciplinas;
    private boolean cargando;

    public Sistema() { this(false); }

    public Sistema(boolean cargarEjemplos) {
        participantes = new ArrayList<>();
        torneos = new ArrayList<>();
        disciplinas = new ArrayList<>();
        disciplinas.add(new FutbolSala());
        disciplinas.add(new FutbolPenales());
        disciplinas.add(new Baloncesto());
        disciplinas.add(new Voleibol());
        disciplinas.add(new Relevos());
        cargando = true;
        if (cargarEjemplos) { DatosIniciales.cargar(this); }
        cargando = false;
    }

    
    boolean estaCargando() { return cargando; }
    public ArrayList<Participante> getParticipantes() { return new ArrayList<>(participantes); }
    public ArrayList<Torneo> getTorneos() { return new ArrayList<>(torneos); }
    public ArrayList<Disciplina> getDisciplinas() { return new ArrayList<>(disciplinas); }

    
    
    
    
    
    public Participante registrarParticipante(String codigo, String nombre, String empresa) {
        Participante nuevo = new Participante(codigo, nombre, empresa);
        for (Participante participante : participantes) {
            if (participante.getCodigo().equalsIgnoreCase(nuevo.getCodigo())) {
                throw new IllegalArgumentException("Ya existe un participante con ese codigo.");
            }
        }
        participantes.add(nuevo);
        return nuevo;
    }

    
    
    public Torneo crearTorneo(String codigo, String nombre, Disciplina disciplina) {
        String valido = Validacion.texto(codigo, "Codigo");
        if (disciplina == null) { throw new IllegalArgumentException("Seleccione una disciplina."); }
        for (Torneo torneo : torneos) {
            if (torneo.getCodigo().equalsIgnoreCase(valido)) {
                throw new IllegalArgumentException("Ya existe un torneo con ese codigo.");
            }
        }
        Torneo nuevo = new Torneo(this, valido, nombre, disciplina);
        torneos.add(nuevo);
        return nuevo;
    }

    public Participante buscarParticipante(String codigo) {
        String valido = Validacion.texto(codigo, "Codigo");
        for (Participante participante : participantes) {
            if (participante.getCodigo().equalsIgnoreCase(valido)) { return participante; }
        }
        throw new IllegalArgumentException("No existe ese participante.");
    }

    public Torneo buscarTorneo(String codigo) {
        String valido = Validacion.texto(codigo, "Codigo");
        for (Torneo torneo : torneos) {
            if (torneo.getCodigo().equalsIgnoreCase(valido)) { return torneo; }
        }
        throw new IllegalArgumentException("No existe ese torneo.");
    }

    public ArrayList<Evento> getEventos() {
        ArrayList<Evento> eventos = new ArrayList<>();
        for (Torneo torneo : torneos) { eventos.addAll(torneo.getEventos()); }
        return eventos;
    }

    public Evento buscarEvento(String codigo) {
        String valido = Validacion.texto(codigo, "Codigo");
        for (Evento evento : getEventos()) {
            if (evento.getCodigo().equalsIgnoreCase(valido)) { return evento; }
        }
        throw new IllegalArgumentException("No existe ese evento.");
    }

    void validarProgramacion(String codigo, ArrayList<Participante> inscritos,
            LocalDate fecha, int hora, String sede) {
        for (Evento otro : getEventos()) {
            if (!otro.getCodigo().equalsIgnoreCase(codigo) && fecha.equals(otro.getFecha())
                    && hora == otro.getHora()) {
                if (sede.equalsIgnoreCase(otro.getSede())) {
                    throw new IllegalArgumentException("La sede ya esta ocupada en esa fecha y hora.");
                }
                for (Participante participante : inscritos) {
                    if (otro.getParticipantes().contains(participante)) {
                        throw new IllegalArgumentException("Un participante ya tiene otro evento en esa fecha y hora.");
                    }
                }
            }
        }
    }

    public Calendario calendarioGeneral() { return new Calendario(getEventos()); }
    public Calendario calendarioTorneo(Torneo torneo) { return new Calendario(torneo.getEventos()); }

    public Calendario calendarioParticipante(Participante participante) {
        ArrayList<Evento> encontrados = new ArrayList<>();
        for (Evento evento : getEventos()) {
            if (evento.getParticipantes().contains(participante)) { encontrados.add(evento); }
        }
        return new Calendario(encontrados);
    }

    public double ingresosTotales() {
        double total = 0;
        for (Torneo torneo : torneos) { total += torneo.getIngresos(); }
        return total;
    }

    public double ingresosDisciplina(Disciplina disciplina) {
        double total = 0;
        for (Torneo torneo : torneos) {
            if (torneo.getDisciplina().getNombre().equals(disciplina.getNombre())) {
                total += torneo.getIngresos();
            }
        }
        return total;
    }

    public double premiosTotales() {
        double total = 0;
        for (Torneo torneo : torneos) { total += torneo.getPremioEntregado(); }
        return total;
    }

    public ArrayList<EmpresaPuntos> rankingEmpresas() {
        ArrayList<EmpresaPuntos> ranking = new ArrayList<>();
        for (Participante participante : participantes) {
            boolean existe = false;
            for (EmpresaPuntos empresa : ranking) {
                if (empresa.getNombre().equalsIgnoreCase(participante.getEmpresa())) { existe = true; }
            }
            if (!existe) { ranking.add(new EmpresaPuntos(participante.getEmpresa())); }
        }
        for (Torneo torneo : torneos) {
            for (Posicion posicion : torneo.getTabla()) {
                for (EmpresaPuntos empresa : ranking) {
                    if (empresa.getNombre().equalsIgnoreCase(posicion.getParticipante().getEmpresa())) {
                        empresa.sumar(posicion.getPuntos());
                    }
                }
            }
        }
        Collections.sort(ranking);
        return ranking;
    }
    
}
