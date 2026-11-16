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
public class DatosEvento {
    private final String codigo;
    private final Torneo torneo;
    private final ArrayList<Participante> participantes;
    private LocalDate fecha;
    private int hora;
    private String sede;
    private String estado;
    private ArrayList<Aporte> aportes;
    private String resultado;

    
    DatosEvento(String codigo, Torneo torneo, ArrayList<Participante> participantes) {
        this.codigo = codigo;
        this.torneo = torneo;
        this.participantes = new ArrayList<>(participantes);
        this.hora = -1;
        this.sede = "";
        this.estado = "SIN PROGRAMAR";
        this.aportes = new ArrayList<>();
        this.resultado = "";
    }

    
    public String getCodigo() { return codigo; }
    public Torneo getTorneo() { return torneo; }
    public ArrayList<Participante> getParticipantes() { return new ArrayList<>(participantes); }
    public LocalDate getFecha() { return fecha; }
    public int getHora() { return hora; }
    public String getSede() { return sede; }
    public String getEstado() { return estado; }
    public boolean tieneResultado() { return estado.equals("FINALIZADO"); }
    public ArrayList<Aporte> getAportes() { return new ArrayList<>(aportes); }

    
    
    
    
    void programar(LocalDate fecha, int hora, String sede) {
        if (tieneResultado()) {
            throw new IllegalArgumentException("Un evento finalizado no se puede reprogramar.");
        }
        if (!torneo.getEstado().equals("EN JUEGO")) {
            throw new IllegalArgumentException("El torneo debe estar en juego.");
        }
        if (fecha == null || hora < 0 || hora > 23) {
            throw new IllegalArgumentException("La fecha debe ser valida y la hora debe estar entre 0 y 23.");
        }
        String sedeValida = Validacion.texto(sede, "Sede");
        torneo.getSistema().validarProgramacion(codigo, participantes, fecha, hora, sedeValida);
        
        this.fecha = fecha;
        this.hora = hora;
        this.sede = sedeValida;
        this.estado = "PROGRAMADO";
    }

    String[] getPreguntas() { return torneo.getDisciplina().getPreguntas(getParticipantes()); }

    void registrarResultado(ArrayList<String> datos) {
        if (!estado.equals("PROGRAMADO")) {
            throw new IllegalArgumentException("El evento debe estar programado y sin resultado.");
        }
        ArrayList<Aporte> nuevos = torneo.getDisciplina().evaluar(datos, getParticipantes());

        aportes = nuevos;
        resultado = datos.toString();
        estado = "FINALIZADO";
        torneo.revisarFinalizacion();
    }

    int comparar(Evento otro) {
        if (fecha == null && otro.getFecha() != null) { return 1; }
        if (fecha != null && otro.getFecha() == null) { return -1; }
        if (fecha == null) { return codigo.compareToIgnoreCase(otro.getCodigo()); }
        int orden = fecha.compareTo(otro.getFecha());
        if (orden == 0) { orden = Integer.compare(hora, otro.getHora()); }
        if (orden == 0) { orden = sede.compareToIgnoreCase(otro.getSede()); }
        return orden;
    }

    
    
    String resumen(String tipo) {
        String texto = codigo +  torneo.getNombre()  + tipo ;
        for (int i = 0; i < participantes.size(); i++) {
            
            if (i > 0) { texto += " / "; }
            texto += participantes.get(i).getNombre();
        }
        
        if (fecha != null) {
            texto +=  fecha + " " + String.format("%02d:00", hora)  + sede;
        }
        texto += " | " + estado;
        if (tieneResultado()) { texto += "resultado: " + resultado; }
        
        return texto;
    }
}