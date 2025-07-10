/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Enrique
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GstorPedidos {
    private ConsolidadoCarga hoy;
    private ConsolidadoCarga manana;
    private List<Pedido> historialEntregas;
    private int contadorPedidos = 1;

    public GstorPedidos() {
        hoy = new ConsolidadoCarga(getFechaHoy());
        manana = new ConsolidadoCarga(getFechaManana());
        historialEntregas = new ArrayList<>();
    }
    
    public void registrarPedido(String cliente, String direccion, List<ItemPedido> items) {
    }

    public Pedido obtenerSiguientePedido() {
        return hoy.obtenerSiguientePedido();
    }

    public void procesarEntrega(String estado) {
        
    }

    public void verHistorialEntregas() {
   
    }

    public List<Pedido> getHistorialEntregas() {
        return historialEntregas;
    }

    public ConsolidadoCarga getConsolidadoHoy() {
        return hoy;
    }

    public ConsolidadoCarga getConsolidadoManana() {
        return manana;
    }

    private String getFechaHoy() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    private String getFechaManana() {
        Date d = new Date(System.currentTimeMillis() + 86400000); // +1 día
        return new SimpleDateFormat("dd/MM/yyyy").format(d);
    }
}
