/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import franquiciaapp.XMLSucursal;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que permite la visualizacion y eliminacion de las sucursales
 *
 * @author daniel
 */
public class GestionSucursal extends javax.swing.JFrame {

    /**
     * Creates new form GestionProducto
     */
    int selected = -1;
    static String nombrearchivo;
    private String archivo = "listaProductos.xml";
    private String producto;
    private JButton btn1;

    public GestionSucursal() {
        initComponents();
        XMLSucursal xml = new XMLSucursal();
        xml.listarSucursales(this, archivo);
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
        jBConfig = new javax.swing.JButton();
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
                "Nombre", "Ubicacion", "Telefono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(gestor);

        jScrollPane1.setBounds(50, 100, 490, 150);
        jLayeredPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jBConfig.setFont(new java.awt.Font("Lucida Grande", 1, 11)); // NOI18N
        jBConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/network.png"))); // NOI18N
        jBConfig.setText("IP/Puertos");
        jBConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConfigActionPerformed(evt);
            }
        });
        jBConfig.setBounds(410, 270, 125, 60);
        jLayeredPane1.add(jBConfig, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jBModificar.setFont(new java.awt.Font("Lucida Grande", 1, 11)); // NOI18N
        jBModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Inventory.png"))); // NOI18N
        jBModificar.setText("Inventario");
        jBModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBModificarActionPerformed(evt);
            }
        });
        jBModificar.setBounds(250, 270, 130, 60);
        jLayeredPane1.add(jBModificar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jBCrear.setFont(new java.awt.Font("Lucida Grande", 1, 11)); // NOI18N
        jBCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/new.png"))); // NOI18N
        jBCrear.setText("Nueva Sucursal");
        jBCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCrearActionPerformed(evt);
            }
        });
        jBCrear.setBounds(50, 270, 170, 60);
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
    private void jBConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConfigActionPerformed
        InfoCoordinador config = new InfoCoordinador(this);
        config.setVisible(true);
    }//GEN-LAST:event_jBConfigActionPerformed

    private void jBModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBModificarActionPerformed
        int selectedRow = this.gestor.getSelectedRow();
        if (selectedRow != -1) {
            String sucursal = (String) this.gestor.getModel().getValueAt(selectedRow, 0);
            GestionInventario inventario2 = new GestionInventario(sucursal);
            Seleccionado(); 
            
            
            
            inventario2.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna sucursal", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBModificarActionPerformed

    public void Seleccionado() {
        selected = gestor.getSelectedRow();
        if (selected >= 0) {
            nombrearchivo = (String) gestor.getValueAt(selected, 0);
        }
    }

    public static String getNombrearchivo() {
        return nombrearchivo;
    }

    private void jBCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCrearActionPerformed
        RegistroSucursal Registro = new RegistroSucursal();
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
            java.util.logging.Logger.getLogger(GestionSucursal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionSucursal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionSucursal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionSucursal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionSucursal().setVisible(true);
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
    public void agregarfila(String nombre, String ubicacion, String telefono) {
        ((DefaultTableModel) this.gestor.getModel()).addRow(new Object[]{nombre, ubicacion, telefono});

    }

    public ArrayList<String> getSucursales() {
        ArrayList<String> sucursales = new ArrayList<String>();
        for (int i = 0; i < this.gestor.getRowCount(); i++) {
            sucursales.add((String) this.gestor.getModel().getValueAt(i, 0));
        }
        return sucursales;
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
    private javax.swing.JTable gestor;
    private javax.swing.JButton jBConfig;
    private javax.swing.JButton jBCrear;
    private javax.swing.JButton jBModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
