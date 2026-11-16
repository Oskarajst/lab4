/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lba4;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author oskar
 */
public class Main {
    private final Sistema sistema;
    private final Entrada entrada;

    public Main() {
        sistema = new Sistema(true);
        entrada = new Entrada(new Scanner(System.in));
    }
public static void main(String[] args) { new Main().ejecutar(); }

    private void ejecutar() {
        boolean salir = false;
        while (!salir) {
            try {
                System.out.println("\n1. Registrar participante\n2. Crear torneo\n3. Cotizar inscripcion"
                        + "\n4. Inscribir participante\n5. Cerrar inscripciones"
                        + "\n6. Programar o reprogramar evento\n7. Registrar resultado"
                        + "\n8. Consultar participantes\n9. Consultar torneos y sus eventos"
                        + "\n10. Calendarios\n11. Tabla de posiciones y campeon"
                        + "\n12. Ingresos y premios\n13. Ranking de empresas\n0. Salir");
                int opcion = entrada.entero("Opcion", 0, 13);
                switch (opcion) {
                    case 1: registrarParticipante(); break;
                    case 2: crearTorneo(); break;
                    case 3: cotizar(); break;
                    case 4: inscribir(); break;
                    case 5: elegirTorneo().cerrarInscripciones(); System.out.println("Inscripciones cerradas."); break;
                    case 6: programar(); break;
                    case 7: registrarResultado(); break;
                    case 8: mostrarParticipantes(); break;
                    case 9: mostrarTorneos(); break;
                    case 10: calendarios(); break;
                    case 11: tabla(); break;
                    case 12: finanzas(); break;
                    case 13:
                        for (EmpresaPuntos empresa : sistema.rankingEmpresas()) { System.out.println(empresa); }
                        break;
                    case 0: salir = true; break;
                    default: break;
                }
            } catch (DateTimeParseException ex) {
                System.out.println("Fecha invalida");
            } catch (IllegalArgumentException ex) {
                System.out.println("No se pudo realizar la operacio: " + ex.getMessage());
            } catch (IllegalStateException ex) {
                System.out.println(ex.getMessage());
                salir = true;
            }
        }
        System.out.println("Programa terminado.");
    }

    private void registrarParticipante() {
        String codigo = entrada.leer("Codigo del participante");
        String nombre = entrada.leer("Nombre");
        String empresa = entrada.leer("Empresa");
        sistema.registrarParticipante(codigo, nombre, empresa);
        System.out.println("Participante registrado");
    }

    private Disciplina elegirDisciplina() {
        ArrayList<Disciplina> disciplinas = sistema.getDisciplinas();
        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina disciplina = disciplinas.get(i);
            System.out.println((i + 1)  + disciplina.getNombre() + "cuota: "
                    + dinero(disciplina.getCuota()) + " minimo: " + disciplina.getMinimo()+ "maximo: " + disciplina.getMaximo());
        }
        return disciplinas.get(entrada.entero("Disciplina", 1, disciplinas.size()) - 1);
    }

    private void crearTorneo() {
        String codigo = entrada.leer("Codigo del torneo");
        String nombre = entrada.leer("Nombre");
        sistema.crearTorneo(codigo, nombre, elegirDisciplina());
        System.out.println("Torneo creado.");
    }

    private void cotizar() {
        Disciplina disciplina = elegirDisciplina();
        int cantidad = entrada.entero("Cantidad de participantes", 1, disciplina.getMaximo());
        System.out.println("Cotizacion: " + dinero(disciplina.cotizar(cantidad)));
    }

    private Torneo elegirTorneo() {
        for (Torneo torneo : sistema.getTorneos()) { System.out.println(torneo); }
        return sistema.buscarTorneo(entrada.leer("Codigo del torneo"));
    }

    private void inscribir() {
        Torneo torneo = elegirTorneo();
        mostrarParticipantes();
        Participante participante = sistema.buscarParticipante(entrada.leer("Codigo del participante"));
        torneo.inscribir(participante);
        System.out.println("Inscripcion realizada. Cobro: " + dinero(torneo.getDisciplina().getCuota()));
    }

    private Evento elegirEvento() {
        Torneo torneo = elegirTorneo();
        mostrarEventos(torneo);
        Evento evento = sistema.buscarEvento(entrada.leer("Codigo del evento"));
        if (evento.getTorneo() != torneo) {
            throw new IllegalArgumentException("El evento no pertenece al torneo seleccionado.");
        }
        return evento;
    }

    private void programar() {
        Evento evento = elegirEvento();
        LocalDate fecha = LocalDate.parse(entrada.leer("Fecha (AAAA-MM-DD)"));
        int hora = entrada.entero("Hora de inicio", 0, 23);
        String sede = entrada.leer("Sede");
        evento.programar(fecha, hora, sede);
        System.out.println("Evento programado.");
    }

    private void registrarResultado() {
        Evento evento = elegirEvento();
        ArrayList<String> datos = new ArrayList<>();
        for (String pregunta : evento.getPreguntas()) { datos.add(entrada.leer(pregunta)); }
        evento.registrarResultado(datos);
        System.out.println("Resultado registrado.");
        Torneo torneo = evento.getTorneo();
        if (torneo.getCampeon() != null) {
            System.out.println("Torneo finalizado. Campeon: " + torneo.getCampeon().getNombre()
                    + "premio: " + dinero(torneo.getPremioEntregado()));
        }
    }

    private void mostrarParticipantes() {
        for (Participante participante : sistema.getParticipantes()) { System.out.println(participante); }
    }

    private void mostrarTorneos() {
        for (Torneo torneo : sistema.getTorneos()) {
            System.out.println("\n" + torneo);
            mostrarEventos(torneo);
        }
    }

    private void mostrarEventos(Torneo torneo) {
        for (Evento evento : sistema.calendarioTorneo(torneo)) { System.out.println(evento.getResumen()); }
        // Los eventos sin fecha se muestran aparte porque aun no pertenecen al calendario.
        for (Evento evento : torneo.getEventos()) {
            if (evento.getFecha() == null) { System.out.println(evento.getResumen()); }
        }
    }

    private void calendarios() {
        int opcion = entrada.entero("1. General, 2. De torneo, 3. De participante", 1, 3);
        Calendario calendario;
        if (opcion == 1) { calendario = sistema.calendarioGeneral(); }
        else if (opcion == 2) { calendario = sistema.calendarioTorneo(elegirTorneo()); }
        else {
            mostrarParticipantes();
            Participante participante = sistema.buscarParticipante(entrada.leer("Codigo del participante"));
            calendario = sistema.calendarioParticipante(participante);
        }
        boolean vacio = true;
        for (Evento evento : calendario) { System.out.println(evento.getResumen()); vacio = false; }
        if (vacio) { System.out.println("No hay eventos programados"); }
    }

    private void tabla() {
        Torneo torneo = elegirTorneo();
        int lugar = 1;
        for (Posicion posicion : torneo.getTabla()) { System.out.println(lugar++ + ". " + posicion); }
        if (torneo.getCampeon() != null) {
            System.out.println("Campeon: " + torneo.getCampeon().getNombre());
        }
    }

    private void finanzas() {
        System.out.println("Ingresos totales: " + dinero(sistema.ingresosTotales()));
        for (Disciplina disciplina : sistema.getDisciplinas()) {
            System.out.println(disciplina.getNombre() + ": " + dinero(sistema.ingresosDisciplina(disciplina)));
        }
        System.out.println("Premios entregados: " + dinero(sistema.premiosTotales()));
        for (Torneo torneo : sistema.getTorneos()) {
            if (torneo.getCampeon() != null) {
                System.out.println(torneo.getNombre() + " | " + torneo.getCampeon().getNombre()
                        + " | premio registrado: " + dinero(torneo.getPremioEntregado()));
            }
        }
    }

    private String dinero(double monto) { return String.format(Locale.US, "Q%.2f", monto); }
}

