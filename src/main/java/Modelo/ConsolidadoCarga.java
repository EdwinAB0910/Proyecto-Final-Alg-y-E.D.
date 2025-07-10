package Modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConsolidadoCarga {
    private final Map<String, Queue<Pedido>> mapaPedidos;
    private final List<RegistroEntrega> historial = new ArrayList<>();
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ConsolidadoCarga() {
        mapaPedidos = new TreeMap<>();
        sdf.setLenient(false); // Validación estricta de fechas
    }

    ConsolidadoCarga(String fechaHoy) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean esFechaValida(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) return false;
        try {
            sdf.parse(fecha.trim());
            return true;
        } catch (ParseException e) {
            System.err.println("Error: Fecha con formato inválido -> " + fecha);
            return false;
        }
    }

    public void agregarPedido(String fecha, Pedido pedido) {
        if (!esFechaValida(fecha)) {
            System.err.println("Error: Fecha del pedido nula, vacía o inválida. Pedido no agregado.");
            return;
        }
        fecha = fecha.trim();
        mapaPedidos.putIfAbsent(fecha, new LinkedList<>());
        mapaPedidos.get(fecha).add(pedido);
    }

    public void agregarPedidoInicio(String fecha, Pedido pedido) {
        if (!esFechaValida(fecha)) {
            System.err.println("Error: Fecha del pedido nula, vacía o inválida. Pedido no agregado.");
            return;
        }
        fecha = fecha.trim();
        mapaPedidos.putIfAbsent(fecha, new LinkedList<>());
        LinkedList<Pedido> temp = new LinkedList<>(mapaPedidos.get(fecha));
        temp.addFirst(pedido);
        mapaPedidos.put(fecha, temp); // Actualiza la cola con el nuevo orden
    }

    public Queue<Pedido> getPedidosPorFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) return new LinkedList<>();
        return mapaPedidos.getOrDefault(fecha.trim(), new LinkedList<>());
    }

    public Set<String> getFechas() {
        return mapaPedidos.keySet();
    }

    public void agregarHistorial(RegistroEntrega registro) {
        if (registro != null)
            historial.add(registro);
    }

    public List<RegistroEntrega> getHistorial() {
        return historial;
    }

    Pedido obtenerSiguientePedido() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}