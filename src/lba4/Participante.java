/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lba4;

/**
 *
 * @author oskar
 */
public class Participante {
    private final String codigo;
    private final String nombre;
    private final String empresa;

    public Participante(String codigo, String nombre, String empresa) {
        this.codigo = Validacion.texto(codigo, "Codigo").toUpperCase();
        this.nombre = Validacion.texto(nombre, "Nombre");
        this.empresa = Validacion.texto(empresa, "Empresa");
    }

    
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getEmpresa() { return empresa; }

    
    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + empresa + ")";
    }
    
    
}

