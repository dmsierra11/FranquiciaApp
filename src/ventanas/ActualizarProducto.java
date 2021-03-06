package ventanas;

import Sockets.Replicador;
import franquiciaapp.Producto;
import franquiciaapp.XMLProducto;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.*;

/**
 * Clase que permite la actualización de datos de los productos en la
 * franquicia.
 *
 * @author daniel
 */
public class ActualizarProducto extends javax.swing.JFrame {

    /**
     * Creates new form RegistroProducto
     */
    private String vacio = null;
    private String producto = "";
    private GestionProducto padre = null;
    JFileChooser filechooser;
    File imagencita;
    String[] nombreImagen;
    String imagenName;

    public ActualizarProducto() {
        initComponents();
        //XMLProducto xml = new XMLProducto();
    }

    public ActualizarProducto(GestionProducto padre) {
        this.padre = padre;
        initComponents();
        //XMLProducto xml = new XMLProducto();
        inicializar();
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
        jLabel3 = new javax.swing.JLabel();
        jLNombre = new javax.swing.JLabel();
        jLDescripcion = new javax.swing.JLabel();
        jLImagen = new javax.swing.JLabel();
        jLCosto = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTNombre = new javax.swing.JTextField();
        jTDescripcion = new javax.swing.JTextField();
        jTCosto = new javax.swing.JTextField();
        jBActualizar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        imagen = new javax.swing.JTextField();
        examinar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carrinho supermercado.png"))); // NOI18N
        jLabel3.setText("Logo");
        jLabel3.setBounds(320, 50, 90, 100);
        jLayeredPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLNombre.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLNombre.setText("Nombre:");
        jLNombre.setBounds(150, 160, 60, 20);
        jLayeredPane1.add(jLNombre, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLDescripcion.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLDescripcion.setText("Descripcion:");
        jLDescripcion.setBounds(150, 200, 90, 20);
        jLayeredPane1.add(jLDescripcion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLImagen.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLImagen.setText("Imagen: ");
        jLImagen.setBounds(150, 240, 60, 16);
        jLayeredPane1.add(jLImagen, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLCosto.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLCosto.setText("Costo:");
        jLCosto.setBounds(150, 280, 45, 16);
        jLayeredPane1.add(jLCosto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setText("SuperMercados XYZ");
        jLabel1.setBounds(130, 100, 200, 30);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTNombre.setEnabled(false);
        jTNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreActionPerformed(evt);
            }
        });
        jTNombre.setBounds(240, 150, 250, 30);
        jLayeredPane1.add(jTNombre, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jTDescripcion.setBounds(240, 190, 250, 30);
        jLayeredPane1.add(jTDescripcion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTCosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTCostoActionPerformed(evt);
            }
        });
        jTCosto.setBounds(240, 270, 250, 30);
        jLayeredPane1.add(jTCosto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jBActualizar.setText("Actualizar");
        jBActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBActualizarActionPerformed(evt);
            }
        });
        jBActualizar.setBounds(220, 360, 97, 29);
        jLayeredPane1.add(jBActualizar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jBCancelar.setText("Cancelar");
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });
        jBCancelar.setBounds(340, 360, 97, 29);
        jLayeredPane1.add(jBCancelar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imagenActionPerformed(evt);
            }
        });
        imagen.setBounds(240, 230, 200, 30);
        jLayeredPane1.add(imagen, javax.swing.JLayeredPane.DEFAULT_LAYER);

        examinar.setText("...");
        examinar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                examinarMouseClicked(evt);
            }
        });
        examinar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examinarActionPerformed(evt);
            }
        });
        examinar.setBounds(450, 230, 40, 30);
        jLayeredPane1.add(examinar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondos-verdes.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setBounds(0, 0, 650, 430);
        jLayeredPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTNombreActionPerformed

    private void jTCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTCostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTCostoActionPerformed

    private void jBActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarActionPerformed
        // TODO add your handling code here:
        if ((this.jTNombre.getText().equals("")) || (this.jTDescripcion.getText().equals("")) || (this.jTCosto.getText().equals("")) || (this.imagen.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Algun campo esta VACIO", "ERROR", JOptionPane.ERROR_MESSAGE);
            ActualizarProducto Registro = new ActualizarProducto();
            Registro.setVisible(true);

        } else {
            XMLProducto productos = new XMLProducto();
            boolean flag = productos.actualizarProducto(this.producto, this.jTNombre.getText(),
                    this.jTDescripcion.getText(), this.jTCosto.getText(), this.imagen.getText());
            if (flag = false) {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar Producto", "ERROR", JOptionPane.ERROR_MESSAGE);
                ActualizarProducto Registro = new ActualizarProducto();
                Registro.setVisible(true);
                this.dispose();
            } else {
                this.padre.dispose();
                GestionProducto Gestion = new GestionProducto();
                Gestion.setVisible(true);
                this.dispose();
                JOptionPane.showMessageDialog(null, "Producto modificado correctamente", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);


                //replica el archivo de productos para actualizar
                Replicador replicador = new Replicador("listaProductos.xml");
                new Thread(replicador).start();
            }





        }
    }//GEN-LAST:event_jBActualizarActionPerformed

    private void imagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imagenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_imagenActionPerformed

    private void examinarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examinarActionPerformed
        // TODO add your handling code here:


        filechooser = new JFileChooser();
        int value = filechooser.showOpenDialog(this);
        if (value == JFileChooser.APPROVE_OPTION) {
            System.out.println(filechooser.getApproveButtonText());

            imagencita = filechooser.getSelectedFile();
            System.out.println(imagencita.toString());

            nombreImagen = imagencita.toString().split("/");

            System.out.println(nombreImagen[7]);

            imagenName = nombreImagen[7];

            imagen.setText(imagenName);
        }



    }//GEN-LAST:event_examinarActionPerformed

    private void examinarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_examinarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_examinarMouseClicked

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(ActualizarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ActualizarProducto().setVisible(true);
            }
        });
    }

    /**
     * Inicializa los componentes principales de la ventana
     */
    private void inicializar() {
        Producto productoSelec = this.padre.getProductoSeleccionado();
        this.producto = productoSelec.getNombre();
        jTNombre.setText(productoSelec.getNombre());
        jTDescripcion.setText(productoSelec.getDescripcion());
        jTCosto.setText(productoSelec.getCosto());
        imagen.setText(productoSelec.getFoto());

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton examinar;
    private javax.swing.JTextField imagen;
    private javax.swing.JButton jBActualizar;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JLabel jLCosto;
    private javax.swing.JLabel jLDescripcion;
    private javax.swing.JLabel jLImagen;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JTextField jTCosto;
    private javax.swing.JTextField jTDescripcion;
    private javax.swing.JTextField jTNombre;
    // End of variables declaration//GEN-END:variables
}
