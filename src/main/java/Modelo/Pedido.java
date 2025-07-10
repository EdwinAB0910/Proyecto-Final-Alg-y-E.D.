package Modelo;

import java.util.*;

public class Pedido {
    private int id;
    private String fechadepedido;
    private Cliente cliente;
    private String fpago;
    private List<Producto> productos = new ArrayList<>();
    private double total;
    private static int cuenta = 1000;

    public Pedido(String fechadepedido, Cliente cliente, String fpago, List<Producto> productos) {
        this.id = cuenta++;
        this.fechadepedido = fechadepedido;
        this.cliente = cliente;
        this.fpago = fpago;
        this.productos = productos;
        calcularTotal();
    }

    public Pedido() {
        this.id = cuenta++;
        this.total = 0.0;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        calcularTotal();
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
        total += p.getPrecio() * p.getCantidad();
    }

    public String getFpago() {
        return fpago;
    }

    public void setFpago(String fpago) {
        this.fpago = fpago;
    }

    public String getFechadepedido() {
        return fechadepedido;
    }

    public void setFechadepedido(String fechadepedido) {
        this.fechadepedido = fechadepedido;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void calcularTotal() {
        total = 0.0;
        for (Producto p : productos) {
            total += p.getPrecio() * p.getCantidad();
        }
    }

    public double envio() {
        String ruta = cliente != null ? cliente.getRuta() : "";
        return switch (ruta) {
            case "Local" -> 5.6;
            case "Interdistrital" -> 11.0;
            case "Interprovincial" -> 37.5;
            default -> 0;
        };
    }

    public String resumenPedido() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(cliente.getNombre()).append("\n")
          .append("Teléfono: ").append(cliente.getTelefono()).append("\n")
          .append("Ruta: ").append(cliente.getRuta()).append("\n")
          .append("Fecha: ").append(fechadepedido).append("\n")
          .append("Forma de pago: ").append(fpago).append("\n")
          .append("Productos:\n");
        for (Producto p : productos) {
            sb.append("- ").append(p.getNombre()).append(" x ").append(p.getCantidad())
              .append(" = S/. ").append(String.format("%.2f", p.getCantidad() * p.getPrecio())).append("\n");
        }
        sb.append("Total: S/. ").append(String.format("%.2f", total));
        return sb.toString();
    }

    @Override
    public String toString() {
        return resumenPedido();
    }
}