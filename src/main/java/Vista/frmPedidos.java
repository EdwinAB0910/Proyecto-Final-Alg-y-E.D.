
package Vista;
import Modelo.DialogEditarPedido;
import Modelo.Cliente;
import Modelo.ConsolidadoCarga;
import Modelo.Pedido;
import Modelo.Producto;
import java.util.*;
import javax.swing.JOptionPane;
import Modelo.Pedido;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class frmPedidos extends javax.swing.JFrame {
    List<Pedido> lista=new ArrayList<>();
private List<Producto> productosDelPedido = new ArrayList<>();
private double totalPedido = 0.0;
    
    String []productos={"Galletas *Field*","Snacks *Lays*","Cereales *Kellogg´s*","Caramelos *Ambrosoli*","Gelatina *Universal*"
                ,"Gomitas *Colombina*","Chocolates *Nestlé*","Yogurt *Gloria*"};
    
    double []precio={2.8,8.5,11.4,4.7,2.1,12.5,5.8,8.0};
    
    String []ruta={"Local","Interdistrital","Interprovincial"};
    
    String []formapago={"Efectivo","Tarjeta","Transferencia bancaria","Yape/Plin"};
    
    private boolean ascNumero = true;
    private boolean ascFecha = true;
    private boolean ascCliente = true;
    
    public frmPedidos(){
        initComponents();
        llena_combo1();
        llena_combo2();
        llena_combo3();
        llena_combo4();
    }
    private ConsolidadoCarga consolidado;

    public frmPedidos(ConsolidadoCarga consolidado) {
    this(); // Llama al constructor vacío para inicializar componentes
    this.consolidado = consolidado;
    txtNumeroPedido.setText(String.valueOf(pe.getId()));
}
    
    void llena_combo1(){
        for (String p : productos) {
            cbxProductos.addItem(p);
        }
    }
    
    void llena_combo2(){
        for (String r : ruta) {
            cbxruta.addItem(r);
        }
    }
    
    void llena_combo3(){
        for (String fp : formapago) {
            cbxfpago.addItem(fp);
        }
    }
    public void llena_combo4(){
        cbxFiltar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
        "Ordenar por Numero de pedido",
        "Ordenar por Fecha de pedido",
        "Ordenar por Cliente"
        }));
     }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taHistorial = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        btnmostrarpedidos = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        cbxFiltar = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        cbxProductos = new javax.swing.JComboBox<>();
        txtCantidad = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taProductos = new javax.swing.JTextArea();
        btnAgregar = new javax.swing.JButton();
        txtNumeroPedido = new javax.swing.JTextField();
        txtCliente = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        cbxfpago = new javax.swing.JComboBox<>();
        cbxruta = new javax.swing.JComboBox<>();
        btnnuevo = new javax.swing.JButton();
        jdFecha = new com.toedter.calendar.JDateChooser();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        taHistorial.setColumns(20);
        taHistorial.setRows(5);
        jScrollPane2.setViewportView(taHistorial);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel11.setText("Pedidos");

        btnmostrarpedidos.setText("Mostrar");
        btnmostrarpedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmostrarpedidosActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar pedido");
        btnEditar.setToolTipText("");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar Pedido");
        btnEliminar.setToolTipText("");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        cbxFiltar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filtar" }));
        cbxFiltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFiltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnmostrarpedidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxFiltar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnmostrarpedidos)
                        .addComponent(btnEditar)
                        .addComponent(btnEliminar)
                        .addComponent(cbxFiltar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("HISTORIAL DE PEDIDOS", jPanel3);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel1.setText("REGISTRO DE PEDIDOS");

        jLabel2.setText("Número de pedido");

        jLabel3.setText("Fecha de pedido");

        jLabel4.setText("Cliente");

        jLabel5.setText("Ruta");

        jLabel6.setText("Teléfono");

        jLabel7.setText("Detalle de Pedido:");

        jLabel8.setText("Producto");

        jLabel9.setText("Cantidad");

        jLabel10.setText("Forma de pago");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        cbxProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir" }));
        cbxProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxProductosActionPerformed(evt);
            }
        });

        taProductos.setColumns(20);
        taProductos.setRows(5);
        jScrollPane1.setViewportView(taProductos);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        txtNumeroPedido.setEnabled(false);

        btnnuevo.setText("Nuevo Pedido");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        jdFecha.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10))
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCliente)
                            .addComponent(txtNumeroPedido)
                            .addComponent(txtTelefono)
                            .addComponent(cbxfpago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxruta, 0, 143, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(402, 402, 402))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(166, 166, 166)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnnuevo)
                                                .addGap(61, 61, 61)
                                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(112, 112, 112))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(cbxProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(141, 141, 141))))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(61, 61, 61)
                                        .addComponent(btnAgregar)
                                        .addContainerGap())
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addGap(30, 30, 30)))
                                        .addGap(132, 132, 132))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jdFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumeroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jdFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnnuevo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(44, 44, 44)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnAgregar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbxProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbxruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cbxfpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(56, 56, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("REGISTRO DE PEDIDOS", jPanel2);

        btnCerrar.setFont(new java.awt.Font("Tempus Sans ITC", 3, 14)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(255, 0, 0));
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 956, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrar))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(btnCerrar)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
Pedido pe=new Pedido();
        
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
                                          
    Cliente cli = new Cliente();

    if (productosDelPedido.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe agregar al menos un producto al pedido.");
        return;
    }

    cli.setNombre(txtCliente.getText());
    if (txtCliente==null) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el nombre del cliente.");
            return;
        }

    String telefonoTexto = txtTelefono.getText().trim();
    if (telefonoTexto.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor ingrese el número de teléfono.");
        return;
    }

    if (!telefonoTexto.matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "El teléfono debe contener solo números.");
        return;
    }

    int telefono = Integer.parseInt(telefonoTexto);
    cli.setTelefono(telefono);

    // Obtener la fecha del JDateChooser
    Date fechaDate = jdFecha.getDate();
    if (fechaDate == null) {
        JOptionPane.showMessageDialog(this, "Por favor seleccione una fecha.");
        return;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String fecha = sdf.format(fechaDate);  // Convertir a String con formato

    cli.setRuta(cbxruta.getSelectedItem().toString());

    
    pe.setCliente(cli);
    pe.setFechadepedido(fecha);
    pe.setFpago(cbxfpago.getSelectedItem().toString());
    
    if (!productosDelPedido.isEmpty()) {
        pe.setProductos(new ArrayList<>(productosDelPedido));
        pe.calcularTotal();
    }

    pe.setTotal(totalPedido);
    lista.add(pe); // Añadir a la lista principal

    if (consolidado != null) {
        consolidado.agregarPedido(fecha, pe); // Agregar al consolidado con datos válidos
    }

    JOptionPane.showMessageDialog(this, "Pedido guardado correctamente.");
                   
    }//GEN-LAST:event_btnGuardarActionPerformed
////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
        int index = cbxProductos.getSelectedIndex();
        
        if (index == 0) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un producto.");
            return;
        }
        
        String cantidadTexto = txtCantidad.getText().trim();
        if (cantidadTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese una cantidad.");
            return;
        }
        
        int cantidad = Integer.parseInt(cantidadTexto);
        
        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor que 0");
            return;
        }
        
        Producto pr = new Producto();
        String nombreProducto = productos[index-1];
        double precioUnitario = precio[index-1]; 
        
        pr.setNombre(nombreProducto);
        pr.setPrecio(precioUnitario);
        pr.setCantidad(cantidad);
        
        productosDelPedido.add(pr);
        
        double subtotal = precioUnitario * cantidad;
        totalPedido += subtotal;
        
        taProductos.append("\n" + nombreProducto + " x " + cantidad + " = S/. " + subtotal);
        taProductos.append("\n=======================");
        
        txtCantidad.setText("");
        cbxProductos.setSelectedIndex(0);
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido para la cantidad.");
    }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void cbxProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxProductosActionPerformed

    }//GEN-LAST:event_cbxProductosActionPerformed

    private void btnmostrarpedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmostrarpedidosActionPerformed
        StringBuilder sb = new StringBuilder();
        for (Pedido p : lista) {
            sb.append("Pedido N° ").append(p.getId()).append(":\n")
              .append(p.toString()) 
              .append("\n----------------------\n");
        }
        taHistorial.setText(sb.toString());
    }//GEN-LAST:event_btnmostrarpedidosActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        pe=new Pedido();
        txtNumeroPedido.setText(String.valueOf(pe.getId()));   
        
        txtCliente.setText("");
        txtTelefono.setText("");
        jdFecha.setDate(null);
        txtCantidad.setText("");
        cbxProductos.setSelectedIndex(0);
        cbxruta.setSelectedIndex(0);
        cbxfpago.setSelectedIndex(0);
        taProductos.setText("");
        
        productosDelPedido.clear();
        totalPedido = 0.0;
    }//GEN-LAST:event_btnnuevoActionPerformed
////editar pedido
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
       String input = JOptionPane.showInputDialog(this, "Ingrese el número de pedido a editar:");
    
    if (input == null || input.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe ingresar un número de pedido.");
        return;
    }

    try {
        int numeroPedido = Integer.parseInt(input.trim());
        Pedido pedidoSeleccionado = null;

        for (Pedido p : lista) {
            if (p.getId() == numeroPedido) {
                pedidoSeleccionado = p;
                break;
            }
        }

        if (pedidoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Pedido no encontrado.");
            return;
        }

        // Abre una ventana emergente de edición
        DialogEditarPedido editar = new DialogEditarPedido(this, pedidoSeleccionado);
        editar.setVisible(true);

        // Luego de editar, actualizar historial
        btnmostrarpedidosActionPerformed(null);

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Número inválido.");
    }
    }//GEN-LAST:event_btnEditarActionPerformed
///////////////eliminar pedido
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        String input = JOptionPane.showInputDialog(this, "Ingrese el número del pedido que desea eliminar:");

    if (input == null || input.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe ingresar un número de pedido.");
        return;
    }

    try {
        int id = Integer.parseInt(input.trim());
        Pedido pedidoAEliminar = null;

        for (Pedido p : lista) {
            if (p.getId() == id) {
                pedidoAEliminar = p;
                break;
            }
        }

        if (pedidoAEliminar == null) {
            JOptionPane.showMessageDialog(this, "No se encontró el pedido con ese número.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el pedido N° " + id + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            lista.remove(pedidoAEliminar);
            JOptionPane.showMessageDialog(this, "Pedido eliminado correctamente.");
            btnmostrarpedidosActionPerformed(null); // Actualiza historial
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Número de pedido inválido.");
    }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void cbxFiltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFiltarActionPerformed

        String opcion = cbxFiltar.getSelectedItem().toString();

        switch (opcion) {
            case "Ordenar por Numero de pedido":
            lista.sort((p1, p2) -> {
                return ascNumero ? Integer.compare(p1.getId(), p2.getId())
                : Integer.compare(p2.getId(), p1.getId());
            });
            ascNumero = !ascNumero;
            break;

            case "Ordenar por Fecha de pedido":
            lista.sort((p1, p2) -> {
                return ascFecha ? p1.getFechadepedido().compareTo(p2.getFechadepedido())
                : p2.getFechadepedido().compareTo(p1.getFechadepedido());
            });
            ascFecha = !ascFecha;
            break;

            case "Ordenar por Cliente":
            lista.sort((p1, p2) -> {
                return ascCliente ? p1.getCliente().getNombre().compareToIgnoreCase(p2.getCliente().getNombre())
                : p2.getCliente().getNombre().compareToIgnoreCase(p1.getCliente().getNombre());
            });
            ascCliente = !ascCliente;
            break;

            default:
            break;
        }

        // Mostrar resultados ordenados
        StringBuilder sb = new StringBuilder();
        for (Pedido p : lista) {
            sb.append("Pedido N° ").append(p.getId()).append(":\n")
            .append(p.toString()) // Asegúrate que Pedido tenga un buen toString
            .append("\n----------------------\n");
        }
        taHistorial.setText(sb.toString());

    }//GEN-LAST:event_cbxFiltarActionPerformed
    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {                                     
        if (txtTelefono.getText().length()>=9) {
            evt.consume();
        }
    } 
////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnmostrarpedidos;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JComboBox<String> cbxFiltar;
    private javax.swing.JComboBox<String> cbxProductos;
    private javax.swing.JComboBox<String> cbxfpago;
    private javax.swing.JComboBox<String> cbxruta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdFecha;
    private javax.swing.JTextArea taHistorial;
    private javax.swing.JTextArea taProductos;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtNumeroPedido;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
