
package Modelo;

public class Producto {
    private String nombre;
    private double precio;
    private int cantidad;
    
    // Constructor 
    public Producto (String nombre, int cantidad, double precio){
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    public Producto (){}
    // Getters y setters
    public void setNombre(String nombre){ this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    
    @Override
    public String toString() {
        return nombre + " (Cantidad: " + cantidad + ", Precio: " + precio + ")";
    }
}   