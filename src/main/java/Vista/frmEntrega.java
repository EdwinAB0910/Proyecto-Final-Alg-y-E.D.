
package Vista;
import Modelo.Pedido;
import Modelo.ConsolidadoCarga;
import Modelo.RegistroEntrega;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.text.*;
import java.util.*;
 
public class frmEntrega extends javax.swing.JFrame {
    private ConsolidadoCarga consolidado;
    private JButton btnGuardar, btnHistorial;
    private JRadioButton rbtnEntregado, rbtnAnulado, rbtnReprogramado;
    private JTextField txtObservacion;
    private String nuevaFechaReprogramada = null;
    
    private boolean ascFecha = true;
    private boolean ascPedido = true;
    private boolean ascEstado = true;
    

    public frmEntrega(ConsolidadoCarga consolidado) {
        this.consolidado = consolidado;
        initComponents();
        cargarFechas();
        actualizarHistorial();
        rbtnEntregado = radioEntregado;
        rbtnAnulado = radioAnulado;
        rbtnReprogramado = radioReprogramado;
        txtObservacion = txtobservacion;
        
        rbtnReprogramado.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (rbtnReprogramado.isSelected()) {
                muestra("reprogramado");
                String nuevaFecha = JOptionPane.showInputDialog(frmEntrega.this, "Ingrese nueva fecha (dd/MM/yyyy):");
            if (nuevaFecha != null && !nuevaFecha.trim().isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                try {
                    sdf.parse(nuevaFecha.trim());
                    nuevaFechaReprogramada = nuevaFecha.trim();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(frmEntrega.this, "Fecha inválida. Use formato dd/MM/yyyy.");
                    buttonGroup1.clearSelection(); // desmarcar
                }
                } else {
                buttonGroup1.clearSelection(); // desmarcar si está vacío
                }
            }
        }
        });
        
        
        cbxFiltar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Ordenar por Fecha", "Ordenar por Pedido", "Ordenar por Estado"
        }));
        
        cbxFechas.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
        mostrarPedido();
        //cbxFechas.setEditable(true);
        }
        });
        
        txtPedido.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        buscarPedidoPorId();
            }
            }
        });   
    }
    
        private void cargarFechas() {
            cbxFechas.removeAllItems();
            List<String> fechas = new ArrayList<>(consolidado.getFechas());

            // Ordenar por fecha descendente (más reciente primero)
            Collections.sort(fechas, (a, b) -> {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date da = sdf.parse(a);
                    Date db = sdf.parse(b);
                    return db.compareTo(da); // orden inverso
                } catch (ParseException e) {
                    return b.compareTo(a); // fallback por texto si falla
                }
            });

            for (String fecha : fechas) {
                cbxFechas.addItem(fecha);
            }
        }
        
    private void mostrarPedido() {
        String fecha = (String) cbxFechas.getSelectedItem();
        if (fecha == null) return;

        Queue<Pedido> cola = consolidado.getPedidosPorFecha(fecha);
        if (cola != null && !cola.isEmpty()) {
            Pedido p = cola.peek();
            txtPedido.setText("Pedido N° " + p.getId());
            taResumen.setText(p.toString());
        } else {
            txtPedido.setText("Sin pedidos");
            taResumen.setText("No hay pedidos para esta fecha");
        }
    }
    
    private void registrarEntrega() {
        String fecha = (String) cbxFechas.getSelectedItem();
        if (fecha == null || fecha.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese o seleccione una fecha válida.");
            return;
        }

        fecha = fecha.trim();
        // Validar formato de fecha dd/MM/yyyy
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // validación estricta
        try {
            sdf.parse(fecha);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use dd/MM/yyyy (ej: 04/07/2025).");
            return;
        }

        Queue<Pedido> cola = consolidado.getPedidosPorFecha(fecha);
        if (cola == null || cola.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay pedidos disponibles para esta fecha.");
            return;
        }

        Pedido pedido = cola.poll(); // Elimina el primer pedido de la cola
        if (pedido == null) {
            JOptionPane.showMessageDialog(this, "Error al obtener el pedido.");
            return;
        }

        String estado;
        if (rbtnEntregado.isSelected()) {
            estado = "Entregado";
        } else if (rbtnAnulado.isSelected()) {
            estado = "Anulado";
        } else if (rbtnReprogramado.isSelected()) {    
            estado = "Reprogramado";
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un estado para la entrega.");
            return;
        }

        String obs = txtObservacion.getText();
        
        if (txtObservacion==null) {
            JOptionPane.showMessageDialog(this, "Escriba la observación segun el estado de la entrega.");
            return;
        }

        RegistroEntrega reg = new RegistroEntrega(
            fecha,
            pedido.getId(),
            pedido.getCliente().getNombre(),
            estado,
            obs
        );
        consolidado.agregarHistorial(reg);

        if (estado.equals("Reprogramado")) {
            if (nuevaFechaReprogramada == null) {
                JOptionPane.showMessageDialog(this, "Debe ingresar una nueva fecha válida antes de registrar la reprogramación.");
                return;
            }

            consolidado.agregarPedidoInicio(nuevaFechaReprogramada, pedido);

            // Verifica si la fecha ya está en el combo y la agrega si no está
            boolean existe = false;
            for (int i = 0; i < cbxFechas.getItemCount(); i++) {
                if (cbxFechas.getItemAt(i).equals(nuevaFechaReprogramada)) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                cbxFechas.addItem(nuevaFechaReprogramada);
            }

            cargarFechas(); // volver a cargar todas (si quieres ordenadas)
            cbxFechas.setSelectedItem(nuevaFechaReprogramada); // selecciona la nueva
            JOptionPane.showMessageDialog(this, "Entrega reprogramada para: " + nuevaFechaReprogramada);
            nuevaFechaReprogramada = null;
        }

        txtObservacion.setText("");
        mostrarPedido();
        cargarFechas();
        JOptionPane.showMessageDialog(this, "Entrega registrada correctamente.");
    }
    
    private String obtenerFechaSiguiente(String fechaActual) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = sdf.parse(fechaActual);
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            cal.add(Calendar.DATE, 1);
            return sdf.format(cal.getTime());
        } catch (ParseException e) {
            return fechaActual;
        }
    }

    
    private void actualizarHistorial() {
        StringBuilder sb = new StringBuilder();
        for (RegistroEntrega r : consolidado.getHistorial()) {
        sb.append(r).append("\n-------------------\n");
        }
        taEntregas.setText(sb.toString());
    }
    
    
    private void buscarPedidoPorId() {
    String texto = txtPedido.getText().trim();

    if (texto.isEmpty()) return;

    // Extraer solo el número del pedido si tiene formato "Pedido N° 123"
    int idBuscado;
    try {
        if (texto.toLowerCase().startsWith("pedido")) {
            texto = texto.replaceAll("[^0-9]", ""); // eliminar todo menos números
        }
        idBuscado = Integer.parseInt(texto);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "ID de pedido inválido");
        return;
    }

    // Buscar el pedido en todas las fechas
    for (String fecha : consolidado.getFechas()) {
        Queue<Pedido> pedidos = consolidado.getPedidosPorFecha(fecha);
        if (pedidos != null) {
            for (Pedido p : pedidos) {
                if (p.getId() == idBuscado) {
                    cbxFechas.setSelectedItem(fecha); // selecciona la fecha
                    taResumen.setText(p.toString());  // muestra resumen
                    txtPedido.setText("Pedido N° " + p.getId());
                    return;
                }
            }
        }
    }

    JOptionPane.showMessageDialog(this, "Pedido no encontrado");
    txtPedido.setText("");
}
       void muestra(String foto){
           
        String ruta=new File("src").getAbsolutePath();
        ruta=ruta+"/main/java/imagenes/"+foto+".png";
        ImageIcon img=new ImageIcon(ruta);
        
        Image esc=img.getImage().getScaledInstance(lbFoto.getWidth(),
                lbFoto.getHeight(),Image.SCALE_SMOOTH);
        
        lbFoto.setIcon(new ImageIcon(esc));
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taEntregas = new javax.swing.JTextArea();
        cbxFiltar = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtobservacion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        radioEntregado = new javax.swing.JRadioButton();
        radioAnulado = new javax.swing.JRadioButton();
        radioReprogramado = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        cbxFechas = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        btnGuardarEntrega = new javax.swing.JButton();
        lbFoto = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taResumen = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtPedido = new javax.swing.JTextPane();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        taEntregas.setEditable(false);
        taEntregas.setBackground(new java.awt.Color(255, 255, 255));
        taEntregas.setColumns(20);
        taEntregas.setRows(5);
        jScrollPane1.setViewportView(taEntregas);

        cbxFiltar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filtar" }));
        cbxFiltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFiltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxFiltar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbxFiltar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("HISTORIAL DE ENTREGAS", jPanel1);

        jLabel5.setText("Observación:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("REGISTRO DE ENTREGAS");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado de Entrega"));

        buttonGroup1.add(radioEntregado);
        radioEntregado.setText("Entregado");
        radioEntregado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioEntregadoMouseClicked(evt);
            }
        });
        radioEntregado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioEntregadoActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioAnulado);
        radioAnulado.setText("Anulado");
        radioAnulado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioAnuladoMouseClicked(evt);
            }
        });

        buttonGroup1.add(radioReprogramado);
        radioReprogramado.setText("Reprogramado");
        radioReprogramado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioReprogramadoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioEntregado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioAnulado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioReprogramado, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioEntregado)
                    .addComponent(radioAnulado)
                    .addComponent(radioReprogramado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setText("Fecha Pedido");

        jLabel8.setText("Pedido");

        btnGuardarEntrega.setText("Guardar");
        btnGuardarEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEntregaActionPerformed(evt);
            }
        });

        lbFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        taResumen.setEditable(false);
        taResumen.setBackground(new java.awt.Color(255, 255, 255));
        taResumen.setColumns(20);
        taResumen.setRows(5);
        jScrollPane2.setViewportView(taResumen);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel10.setText("Resumen Pedido");

        jScrollPane6.setViewportView(txtPedido);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(txtobservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(35, 35, 35))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(97, 97, 97)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(149, 149, 149))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxFechas, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(119, 119, 119)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                                .addComponent(btnGuardarEntrega)
                                .addGap(23, 23, 23))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(btnGuardarEntrega)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxFechas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtobservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("REGISTRO DE ENTREGAS", jPanel2);

        btnCerrar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(939, Short.MAX_VALUE)
                .addComponent(btnCerrar)
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(btnCerrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
       this.dispose();

    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnGuardarEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEntregaActionPerformed
            registrarEntrega(); // Ya implementado correctamente
            actualizarHistorial(); // Para mostrarlo en taEntregas
    }//GEN-LAST:event_btnGuardarEntregaActionPerformed

    private void cbxFiltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFiltarActionPerformed

    String criterio = (String) cbxFiltar.getSelectedItem();
    List<RegistroEntrega> historial = new ArrayList<>(consolidado.getHistorial());

    if (criterio == null || historial.isEmpty()) return;

    Comparator<RegistroEntrega> comparador = null;

    switch (criterio) {
        case "Ordenar por Fecha":
            comparador = (a, b) -> {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date fechaA = sdf.parse(a.getFecha());
                    Date fechaB = sdf.parse(b.getFecha());
                    return ascFecha ? fechaA.compareTo(fechaB) : fechaB.compareTo(fechaA);
                } catch (ParseException e) {
                    return 0;
                }
            };
            ascFecha = !ascFecha;
            break;

        case "Ordenar por Pedido":
            comparador = (a, b) -> ascPedido ? Integer.compare(a.getIdPedido(), b.getIdPedido())
                                             : Integer.compare(b.getIdPedido(), a.getIdPedido());
            ascPedido = !ascPedido;
            break;

        case "Ordenar por Estado":
            comparador = (a, b) -> ascEstado ? a.getEstado().compareToIgnoreCase(b.getEstado())
                                             : b.getEstado().compareToIgnoreCase(a.getEstado());
            ascEstado = !ascEstado;
            break;

        default:
            return;
    }

    historial.sort(comparador);

    // Mostrar el resultado en taEntregas
    StringBuilder sb = new StringBuilder();
    for (RegistroEntrega r : historial) {
        sb.append(r).append("\n-------------------\n");
    }
    taEntregas.setText(sb.toString());   

    }//GEN-LAST:event_cbxFiltarActionPerformed

    private void radioEntregadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioEntregadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioEntregadoActionPerformed

    private void radioEntregadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioEntregadoMouseClicked
        muestra("entregado");
    }//GEN-LAST:event_radioEntregadoMouseClicked

    private void radioAnuladoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioAnuladoMouseClicked
        muestra("anulado");
    }//GEN-LAST:event_radioAnuladoMouseClicked

    private void radioReprogramadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioReprogramadoMouseClicked
        
    }//GEN-LAST:event_radioReprogramadoMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    ConsolidadoCarga consolidado = new ConsolidadoCarga(); // O una instancia compartida existente
    new frmEntrega(consolidado).setVisible(true);
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGuardarEntrega;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxFechas;
    private javax.swing.JComboBox<String> cbxFiltar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JRadioButton radioAnulado;
    private javax.swing.JRadioButton radioEntregado;
    private javax.swing.JRadioButton radioReprogramado;
    private javax.swing.JTextArea taEntregas;
    private javax.swing.JTextArea taResumen;
    private javax.swing.JTextPane txtPedido;
    private javax.swing.JTextField txtobservacion;
    // End of variables declaration//GEN-END:variables
}
