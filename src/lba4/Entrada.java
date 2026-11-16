/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lba4;

import java.util.Scanner;

/**
 *
 * @author oskar
 */
public class Entrada {
    private final Scanner scanner;

    public Entrada(Scanner scanner) { this.scanner = scanner; }

    public String leer(String mensaje) {
        if (!scanner.hasNextLine()) {
            throw new IllegalStateException("Fin de la entrada");
        }
        return scanner.nextLine().trim();
    }

    
    public int entero(String mensaje, int minimo, int maximo) {
        while (true) {
            try {
                int valor = Integer.parseInt(leer(mensaje));
                if (valor >= minimo && valor <= maximo) { return valor; }
                System.out.println("Escriba un numero entre " + minimo + " y " + maximo);
            } catch (NumberFormatException ex) {
                System.out.println("Escriba un numero entero valido");
            }
        }
    }
}
