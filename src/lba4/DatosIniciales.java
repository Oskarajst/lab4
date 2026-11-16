/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lba4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author oskar
 */
public class DatosIniciales {
    private DatosIniciales() { }

    
    
    static void cargar(Sistema sistema) {
        sistema.registrarParticipante("P1", "Aguilas", "Empresa Norte");
        sistema.registrarParticipante("P2", "Buhos", "Empresa Norte");
        sistema.registrarParticipante("P3", "Condor", "Empresa Centro");
        sistema.registrarParticipante("P4", "Delfines", "Empresa Centro");
        sistema.registrarParticipante("P5", "Estrellas", "Empresa Sur");
        sistema.registrarParticipante("P6", "Fuerza", "Empresa Sur");
        String[] nombres = {"Copa Sala", "Copa Penales", "Copa Canasta", "Copa Sets", "Copa Relevos"};
        
        for (int i = 0; i < sistema.getDisciplinas().size(); i++) {
            Torneo torneo = sistema.crearTorneo("T" + (i + 1), nombres[i], sistema.getDisciplinas().get(i));
            
            int cantidad = (i == 1 || i == 2) ? 4 : 6;
            for (int j = 0; j < cantidad; j++) {

                torneo.cargarParticipante(sistema.getParticipantes().get(j));
            }
            torneo.cerrarInscripciones();

            for (int j = 0; j < torneo.getEventos().size(); j++) {
                torneo.getEventos().get(j).programar(LocalDate.of(2027, i + 1, j + 1), 10, "Sede Central");
            }
        }

        resultado(sistema, "T1-E1", "0", "0");
        resultado(sistema, "T1-E2", "0", "0");
        resultado(sistema, "T1-E3", "0", "0");
   
        resultado(sistema, "T1-E8", "1", "0");
        resultado(sistema, "T1-E12", "4", "0");
        resultado(sistema, "T1-E15", "1", "0");
       
        resultado(sistema, "T2-E1", "2", "2", "4", "3");
        resultado(sistema, "T2-E2", "3", "1", "", "");
        resultado(sistema, "T3-E1", "80", "70");
        resultado(sistema, "T4-E1", "3", "0");
        resultado(sistema, "T4-E2", "3", "1");
        resultado(sistema, "T4-E3", "3", "2");
        resultado(sistema, "T5-E1", "50.1", "51.9", "52.4", "55.0", "DQ", "59.0");
    }

    private static void resultado(Sistema sistema, String codigo, String... valores) {
        sistema.buscarEvento(codigo).registrarResultado(new ArrayList<>(Arrays.asList(valores)));
    }
}