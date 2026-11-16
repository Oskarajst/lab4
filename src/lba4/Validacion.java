/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lba4;

/**
 *
 * @author oskar
 */
public class Validacion {
    private Validacion() { }

    public static String texto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacio");
        }
        
        return valor.trim();
    }

    
    public static int entero(String texto) {
        try {
            int valor = Integer.parseInt(texto.trim());
            if (valor < 0) {
                throw new IllegalArgumentException("El marcador no puede ser negativo");
            }
            
            
            return valor;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Escriba un entero no negativo");
        }
    }
    
    
    

    public static double tiempo(String texto) {
        try {
            double valor = Double.parseDouble(texto.trim());
            if (!Double.isFinite(valor) || valor <= 0) {
                throw new IllegalArgumentException("El tiempo debe ser mayor que cero");
            }
            
            return valor;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Escriba un tiempo valido");
        }
        
    }

    public static void cantidad(java.util.List<String> datos, int cantidad) {
        if (datos == null || datos.size() != cantidad || datos.contains(null)) {
            throw new IllegalArgumentException("La cantidad de datos del resultado es incorrecto");
        }
    }
}

