/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Enrique
 */
public class RegistroEntrega {
    private String fecha;
    private int idPedido;
    private String cliente;
    private String estado;
    private String observacion;

    public RegistroEntrega(String fecha, int idPedido, String cliente, String estado, String observacion) {
        this.fecha = fecha;
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.estado = estado;
        this.observacion = observacion;
    }

    public String getFecha() {
        return fecha;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getCliente() {
        return cliente;
    }

    public String getEstado() {
        return estado;
    }

    public String getObservacion() {
        return observacion;
    }

    @Override
    public String toString() {
        return "Fecha: " + fecha +
               "\nPedido N°: " + idPedido +
               "\nCliente: " + cliente +
               "\nEstado: " + estado +
               "\nObservación: " + observacion;
    }
}

