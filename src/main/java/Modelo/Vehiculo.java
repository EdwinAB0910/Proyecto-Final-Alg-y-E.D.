
package Modelo;

import javax.swing.JTextArea;

public class Vehiculo {
    private String tipov;
    private String marca;
    private int aniomodelo;
    private String color;
    private String placa;
    private String fregistro;
    private String tipoc;

    public Vehiculo() {
    }

    public Vehiculo(String tipov, String marca, int aniomodelo, String color, String placa, String fregistro, String tipoc) {
        this.tipov = tipov;
        this.marca = marca;
        this.aniomodelo = aniomodelo;
        this.color = color;
        this.placa = placa;
        this.fregistro = fregistro;
        this.tipoc = tipoc;
    }

    public String getTipoc() {
        return tipoc;
    }

    public void setTipoc(String tipoc) {
        this.tipoc = tipoc;
    }

    public String getTipov() {
        return tipov;
    }

    public void setTipov(String tipov) {
        this.tipov = tipov;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAniomodelo() {
        return aniomodelo;
    }

    public void setAniomodelo(int aniomodelo) {
        this.aniomodelo = aniomodelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getFregistro() {
        return fregistro;
    }

    public void setFregistro(String fregistro) {
        this.fregistro = fregistro;
    }

    public void mostrar(JTextArea at){
        at.append("Tipo de vehiculo: "+tipov);
        at.append("\n Marca de vehiculo: "+marca);
        at.append("\n Año de modelo: "+aniomodelo);
        at.append("\n Color: "+color);
        at.append("\n Placa: "+placa);
        at.append("\n Fecha de Registro: "+fregistro);
        at.append("\n Tipo de combustible: "+tipoc);
    }
}
