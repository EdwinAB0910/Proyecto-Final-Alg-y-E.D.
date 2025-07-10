/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import Modelo.Pedido;
import Modelo.Cliente;
import Modelo.Producto;

public class DialogEditarPedido extends JDialog {
    private JTextField txtCliente, txtTelefono, txtFecha, txtRuta, txtPago, txtTotal;
    private JTextArea taProductos;
    private JButton btnGuardar, btnCancelar;
    private Pedido pedido;

    public DialogEditarPedido(JFrame parent, Pedido pedido) {
        super(parent, "Editar Pedido", true);
        this.pedido = pedido;
        setLayout(new GridLayout(9, 2, 10, 10));

        Cliente cli = pedido.getCliente();

        add(new JLabel("Cliente:"));
        txtCliente = new JTextField(cli.getNombre());
        add(txtCliente);

        add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField(String.valueOf(cli.getTelefono()));
        add(txtTelefono);

        add(new JLabel("Fecha de pedido:"));
        txtFecha = new JTextField(pedido.getFechadepedido());
        add(txtFecha);

        add(new JLabel("Ruta:"));
        txtRuta = new JTextField(cli.getRuta());
        add(txtRuta);

        add(new JLabel("Forma de pago:"));
        txtPago = new JTextField(pedido.getFpago());
        add(txtPago);

        add(new JLabel("Productos (nombre,cantidad,precio por línea):"));
        taProductos = new JTextArea();
        taProductos.setRows(5);
        JScrollPane scrollProductos = new JScrollPane(taProductos);
        cargarProductosEnTextArea();
        add(scrollProductos);

        add(new JLabel("Total:"));
        txtTotal = new JTextField(String.valueOf(pedido.getTotal()));
        txtTotal.setEditable(false); // Se calcula automáticamente
        add(txtTotal);

        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        add(btnGuardar);
        add(btnCancelar);

        btnGuardar.addActionListener(e -> guardarCambios());
        btnCancelar.addActionListener(e -> dispose());

        setSize(500, 450);
        setLocationRelativeTo(parent);
    }

    private void cargarProductosEnTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Producto p : pedido.getProductos()) {
            sb.append(p.getNombre()).append(",").append(p.getCantidad()).append(",").append(p.getPrecio()).append("\n");
        }
        taProductos.setText(sb.toString());
    }

    private void guardarCambios() {
        try {
            Cliente cli = pedido.getCliente();
            cli.setNombre(txtCliente.getText());
            cli.setTelefono(Integer.parseInt(txtTelefono.getText()));
            cli.setRuta(txtRuta.getText());

            pedido.setCliente(cli);
            pedido.setFechadepedido(txtFecha.getText());
            pedido.setFpago(txtPago.getText());

            // Limpiar y cargar productos
            List<Producto> nuevaLista = new java.util.ArrayList<>();
            String[] lineas = taProductos.getText().split("\\n");
            for (String linea : lineas) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    int cantidad = Integer.parseInt(partes[1].trim());
                    double precio = Double.parseDouble(partes[2].trim());
                    nuevaLista.add(new Producto(nombre, cantidad, precio));
                }
            }
            pedido.setProductos(nuevaLista); // recalcula el total automáticamente

            txtTotal.setText(String.valueOf(pedido.getTotal()));

            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar cambios: " + ex.getMessage());
        }
    }
}


