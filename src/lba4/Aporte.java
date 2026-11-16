/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lba4;

/**
 *
 * @author oskar
 */
public class Aporte {
    private final Participante participante;
    private final int puntos;
    private final long diferencia;
    private final int victorias;

    public Aporte(Participante participante, int puntos, long diferencia, int victorias) {
        this.participante = participante;
        this.puntos = puntos;
        this.diferencia = diferencia;
        this.victorias = victorias;
    }

    public Participante getParticipante() { return participante; }
    public int getPuntos() { return puntos; }
    public long getDiferencia() { return diferencia; }
    public int getVictorias() { return victorias; }
}
