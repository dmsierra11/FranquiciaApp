/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franquiciaapp;

import Sockets.Replicador;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que permite la visualizacion y eliminacion de los productos
 *
 * @author gracielalucena
 */
public class GestionProducto extends javax.swing.JFrame {

    /**
     * Creates new form GestionProducto
     */
    int selected = -1;
    private String archivo = "listaProductos.xml";
    private String producto;

    public GestionProducto() {
        initComponents();
        XMLProducto xml = new XMLProducto();
        xml.listarProductos(this, archivo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        gestor = new javax.swing.JTable();
        eliminar = new javax.swing.JButton();
        jBModificar = new javax.swing.JButton();
        jBCrear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        gestor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Descripcion", "Imagen", "Costo", "Estatus"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(gestor);

        jScrollPane1.setBounds(50, 100, 490, 150);
        jLayeredPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        eliminar.setFont(new java.awt.Font("Lucida Grande", 1, 11)); // NOI18N
        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete.png"))); // NOI18N
        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        eliminar.setBounds(420, 270, 120, 60);
        jLayeredPane1.add(eliminar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jBModificar.setFont(new java.awt.Font("Lucida Grande", 1, 11)); // NOI18N
        jBModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit.png"))); // NOI18N
        jBModificar.setText("Modificar");
        jBModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBModificarActionPerformed(evt);
            }
        });
        jBModificar.setBounds(260, 270, 130, 60);
        jLayeredPane1.add(jBModificar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jBCrear.setFont(new java.awt.Font("Lucida Grande", 1, 11)); // NOI18N
        jBCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/new.png"))); // NOI18N
        jBCrear.setText("Nuevo Producto");
        jBCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCrearActionPerformed(evt);
            }
        });
        jBCrear.setBounds(60, 270, 170, 60);
        jLayeredPane1.add(jBCrear, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carrinho supermercado.png"))); // NOI18N
        jLabel1.setBounds(210, 0, 90, 100);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel2.setText("SuperMercados XYZ");
        jLabel2.setBounds(50, 60, 177, 24);
        jLayeredPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondos-verdes.jpg"))); // NOI18N
        jLabel3.setBounds(-5, 0, 600, 370);
        jLayeredPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLayeredPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLayeredPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Permite eliminar los productos, que seleccione en la lista dinamica
     *
     * @param evt
     */
    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
        System.out.print("estoy aqui");
        System.out.print(selected);

        selected = gestor.getSelectedRow();

        System.out.print(gestor.getSelectedRow());
        if (selected >= 0) {
            String nombrearchivo = (String) gestor.getValueAt(selected, 0);
            System.out.print(nombrearchivo);
            XMLProducto xml = new XMLProducto();
            xml.borrarProducto(archivo, nombrearchivo);

            ((DefaultTableModel) this.gestor.getModel()).removeRow(selected);

            //replica el archivo de productos para actualizar
            Replicador replicador = new Replicador("listaProductos.xml");
            replicador.run();

        } else {
            System.out.print("AHORA ACA");
        }
        System.out.print(selected);

    }//GEN-LAST:event_eliminarActionPerformed

    private void jBModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBModificarActionPerformed

        if (this.gestor.getSelectedRow() != -1) {
            ActualizarProducto ventanaActualizar = new ActualizarProducto(this);
            ventanaActualizar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun producto", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jBModificarActionPerformed

    private void jBCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCrearActionPerformed
        RegistroProducto Registro = new RegistroProducto();
        Registro.setVisible(true);
    }//GEN-LAST:event_jBCrearActionPerformed

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
            java.util.logging.Logger.getLogger(GestionProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionProducto().setVisible(true);
            }
        });
    }

    /**
     * Carga la tabla con los productos disponibles
     *
     * @param nombre
     * @param descripcion
     * @param foto
     * @param costo
     */
    public void agregarfila(String nombre, String descripcion, String foto, String costo, String status) {
        ((DefaultTableModel) this.gestor.getModel()).addRow(new Object[]{nombre, descripcion, foto, costo, status});

    }

    public Producto getProductoSeleccionado() {
        int selectedRow = this.gestor.getSelectedRow();
        String productoNombre = (String) this.gestor.getModel().getValueAt(selectedRow, 0);
        String productoDesc = (String) this.gestor.getModel().getValueAt(selectedRow, 1);
        String productoFoto = (String) this.gestor.getModel().getValueAt(selectedRow, 2);
        String productoCosto = (String) this.gestor.getModel().getValueAt(selectedRow, 3);
        String productoStatus = (String) this.gestor.getModel().getValueAt(selectedRow, 4);

        Producto product = new Producto(productoNombre, productoDesc, productoFoto,
                productoCosto, productoStatus);

        return product;
    }

    public int getRowCount() {
        return gestor.getRowCount();
    }

    private void gestorMouseClicked(java.awt.event.MouseEvent evt) {
        this.selected = gestor.getSelectedRow();
        System.out.print(selected);


        // TODO add your handling code here:
        // TODO add your handling code here:
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton eliminar;
    private javax.swing.JTable gestor;
    private javax.swing.JButton jBCrear;
    private javax.swing.JButton jBModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
