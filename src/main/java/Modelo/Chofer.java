
package Modelo;

import javax.swing.JTextArea;


public class Chofer {
    private String nombre;
    private String apellido;
    private int DNI;
    private String genero;
    private String fingreso;
    private String tlicencia;
    private String categoria;

    public Chofer() {
    }

    public Chofer(String nombre, String apellido, int DNI, String genero, String fingreso, String tlicencia, String categoria) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.genero = genero;
        this.fingreso = fingreso;
        this.tlicencia = tlicencia;
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFingreso() {
        return fingreso;
    }

    public void setFingreso(String fingreso) {
        this.fingreso = fingreso;
    }

    public String getTlicencia() {
        return tlicencia;
    }

    public void setTlicencia(String tlicencia) {
        this.tlicencia = tlicencia;
    }
    public void mostrar(JTextArea at){
        at.append("Nombre del chofer: "+nombre);
        at.append("\n Apellido del chofer: "+apellido);
        at.append("\n DNI del chofer: "+DNI);
        at.append("\n Fecha de Ingreso: "+fingreso);
        at.append("\n Tipo de Licencia: "+tlicencia);
        at.append("\n Categoria: "+categoria);
    }
}
