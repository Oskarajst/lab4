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
public abstract class Disciplina {
    private final String nombre;
    private final int minimo;
    private final int maximo;
    private final double cuota;

    protected Disciplina(String nombre, int minimo, int maximo, double cuota) {
        this.nombre = nombre;
        this.minimo = minimo;
        this.maximo = maximo;
        this.cuota = cuota;
    }

    public String getNombre() { return nombre; }
    public int getMinimo() { return minimo; }
    public int getMaximo() { return maximo; }
    public double getCuota() { return cuota; }

    public double cotizar(int cantidad) {
        if (cantidad <= 0 || cantidad > maximo) {
            throw new IllegalArgumentException("La cantidad debe estar entre 1 y " + maximo + ".");
        }
        return cuota * cantidad;
    }

    
    
    public void validarInscripcion(ArrayList<Participante> inscritos, Participante nuevo) {
        if (inscritos.size() >= maximo) {
            throw new IllegalArgumentException("El torneo ya alcanzo el maximo de participantes.");
        }
        int empresa = 0;
        for (Participante p : inscritos) {
            if (p.getCodigo().equalsIgnoreCase(nuevo.getCodigo())) {
                throw new IllegalArgumentException("El participante ya esta inscrito.");
            }
            if (p.getEmpresa().equalsIgnoreCase(nuevo.getEmpresa())) { empresa++; }
        }
        if (empresa >= 2) {
            throw new IllegalArgumentException("La empresa ya tiene dos participantes en el torneo.");
        }
    }

    protected ArrayList<Aporte> aportesPartido(ArrayList<Participante> participantes,
            int puntosA, int puntosB, long diferencia, int ganador) {
        ArrayList<Aporte> aportes = new ArrayList<>();
        aportes.add(new Aporte(participantes.get(0), puntosA, diferencia, ganador == 0 ? 1 : 0));
        aportes.add(new Aporte(participantes.get(1), puntosB, -diferencia, ganador == 1 ? 1 : 0));
        return aportes;
    }

    public abstract ArrayList<Evento> crearEventos(Torneo torneo);
    public abstract String[] getPreguntas(ArrayList<Participante> participantes);
    public abstract ArrayList<Aporte> evaluar(ArrayList<String> datos,
            ArrayList<Participante> participantes);
    
        
    }