package ventanas;

import franquiciaapp.FrameImagen;
import franquiciaapp.Inventario;
import franquiciaapp.Nodo;
import franquiciaapp.PanelImagen;
import franquiciaapp.XMLInventario;
import franquiciaapp.XMLNodoCoordinador;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que permite la visualizacion de los inventarios
 *
 * @author Diego Alienware
 */
public class GestionInventario extends javax.swing.JFrame {

    /**
     * Creates new form GestionProducto
     */
    int selected = -1;
    private String archivo = "inventarioProductos.xml";
    private String producto;
    private Socket cliente;
    private String sucursal;
     
      
      String imagen;

    /**
     * Creates new form GestionInventario
     */
    public GestionInventario() {
        initComponents();
//        XMLInventario xml = new XMLInventario();
//        xml.listarInventario(this, archivo);
//        
    }
    
    public GestionInventario(String sucursal) {
        initComponents();
        XMLInventario xml = new XMLInventario(sucursal);
        this.sucursal = sucursal;
        
        File f = new File(sucursal+".xml");
        if (f.exists() == true) 
                
        {
              xml.LeerInventario(sucursal);
        }        
else
            //System.out.println("else");  
            xml.CrearInventario(sucursal);   
        
        xml.listarInventario(this, sucursal);
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
        jBCrear = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        gestor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Descripcion", "Costo", "Cantidad", "Estado", "Imagen"
            }
        ));
        jScrollPane1.setViewportView(gestor);

        jScrollPane1.setBounds(50, 100, 490, 150);
        jLayeredPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jBCrear.setFont(new java.awt.Font("Lucida Grande", 1, 11)); // NOI18N
        jBCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit.png"))); // NOI18N
        jBCrear.setText("Actualizar");
        jBCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCrearActionPerformed(evt);
            }
        });
        jBCrear.setBounds(60, 280, 170, 60);
        jLayeredPane1.add(jBCrear, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setFont(new java.awt.Font("Lucida Grande", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ojo.png"))); // NOI18N
        jButton1.setText("VerImagen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(420, 280, 110, 60);
        jLayeredPane1.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carrinho supermercado.png"))); // NOI18N
        jLabel1.setBounds(330, 0, 90, 100);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel2.setText("Inventario SuperMercados XYZ");
        jLabel2.setBounds(50, 60, 285, 22);
        jLayeredPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondos-verdes.jpg"))); // NOI18N
        jLabel3.setBounds(0, 0, 590, 370);
        jLayeredPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCrearActionPerformed
         int selectedRow = this.gestor.getSelectedRow();
        if (selectedRow != -1) {
            String producto = (String) this.gestor.getModel().getValueAt(selectedRow, 0);
             String cantidad = (String) this.gestor.getModel().getValueAt(selectedRow, 3);
             XMLInventario xml = new XMLInventario(sucursal);
             xml.actualizarInventario(sucursal, producto,cantidad);
             JOptionPane.showMessageDialog(null, "Producto creado correctamente", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
             this.dispose();
             
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun producto", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBCrearActionPerformed

    
     
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         int selectedRow = this.gestor.getSelectedRow();
        if (selectedRow != -1) {
          imagen = (String) this.gestor.getModel().getValueAt(selectedRow, 5);
            
            
            FrameImagen Frame = new FrameImagen(imagen);
            Frame.main(imagen);
            
            
          
          
             
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun producto", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(GestionInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionInventario().setVisible(true);
            }
        });
    }

    /**
     * Carga la tabla con los productos disponibles
     *
     * @param nombre
     * @param descripcion
     * @param foto
     * @param cantidad
     * @param status
     */
    public void agregarfila(String nombre, String descripcion, String costo, String cantidad, String status, String imagen) {
        ((DefaultTableModel) this.gestor.getModel()).addRow(new Object[]{nombre, descripcion, costo, cantidad, status, imagen});
    }

    public ArrayList<String> getInventario() {
        ArrayList<String> inventario = new ArrayList<String>();
        for (int i = 0; i < this.gestor.getRowCount(); i++) {
            inventario.add((String) this.gestor.getModel().getValueAt(i, 0));
        }
        return inventario;
    }

    public Inventario getProductoInventarioSeleccionado() {
        int selectedRow = this.gestor.getSelectedRow();
        String productoNombre = (String) this.gestor.getModel().getValueAt(selectedRow, 0);
        String productoDesc = (String) this.gestor.getModel().getValueAt(selectedRow, 1);
        String productoCosto = (String) this.gestor.getModel().getValueAt(selectedRow, 2);
        String productoCantidad = (String) this.gestor.getModel().getValueAt(selectedRow, 3);
        //String productoStatus = (String) this.gestor.getModel().getValueAt(selectedRow, 4);
         
        Inventario inven = new Inventario(productoNombre, productoDesc, productoCosto, productoCantidad);
        return inven;
    }
    
    public int getRowCount() {
        return gestor.getRowCount();
    }

    private void gestorMouseClicked(java.awt.event.MouseEvent evt) {
        this.selected = gestor.getSelectedRow();
        System.out.print(selected);
        System.out.println("click");


        // TODO add your handling code here:
        // TODO add your handling code here:
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable gestor;
    private javax.swing.JButton jBCrear;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
