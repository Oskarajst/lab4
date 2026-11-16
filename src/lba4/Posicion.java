/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lba4;

/**
 *
 * @author oskar
 */
public class Posicion implements Comparable<Posicion> {
    private final Participante participante;
    private int puntos;
    private long diferencia;
    private int victorias;

    public Posicion(Participante participante) {
        this.participante = participante;
    }

    void sumar(Aporte aporte) {
        puntos += aporte.getPuntos();
        diferencia += aporte.getDiferencia();
        victorias += aporte.getVictorias();
    }

    public Participante getParticipante() { return participante; }
    public int getPuntos() { return puntos; }
    public long getDiferencia() { return diferencia; }
    public int getVictorias() { return victorias; }

    
    @Override
    public int compareTo(Posicion otra) {
        int orden = Integer.compare(otra.puntos, puntos);
        if (orden == 0) { orden = Long.compare(otra.diferencia, diferencia); }
        if (orden == 0) { orden = Integer.compare(otra.victorias, victorias); }
        if (orden == 0) {orden = participante.getNombre().compareToIgnoreCase(otra.participante.getNombre());
        }
        return orden;
    }

    @Override
    public String toString() {
        return participante.getNombre() + " puntos: " + puntos
                + " diferencia: " + diferencia + " victorias: " + victorias;
    }
    
}

