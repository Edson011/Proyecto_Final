
package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Cliente extends javax.swing.JFrame {
    
    
     private Socket socket;
    

    
    public Cliente() {
        initComponents();
        this.setLocationRelativeTo(null);
             
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        chkEnviarArchivo = new javax.swing.JCheckBox();
        txtMensaje = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cliente");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 11, -1, 30));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        chkEnviarArchivo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkEnviarArchivo.setText("Seleccionar Archivo");

        btnEnviar.setBackground(new java.awt.Color(0, 102, 102));
        btnEnviar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEnviar.setForeground(new java.awt.Color(255, 255, 255));
        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chkEnviarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEnviar))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(chkEnviarArchivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed

        try{
            Socket socket = new Socket ("192.168.142.1",1212);

            DataOutputStream enviar_mensaje = new DataOutputStream(socket.getOutputStream());

            if (chkEnviarArchivo.isSelected()) {//---------------------
                // Abrir el diálogo de selección de archivo
                JFileChooser fileChooser = new JFileChooser();
                int seleccion = fileChooser.showOpenDialog(this);

                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File archivo = fileChooser.getSelectedFile();

                    // Enviar el identificador 'A' al servidor para indicar que es un archivo
                    enviar_mensaje.writeChar('A');//++++

                    // Enviar el nombre del archivo al servidor
                    enviar_mensaje.writeUTF(archivo.getName());

                    // Enviar el archivo al servidor
                    FileInputStream fis = new FileInputStream(archivo);
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        enviar_mensaje.write(buffer, 0, bytesRead);
                    }
                    fis.close();

                } else {
                    // No se seleccionó ningún archivo, mostrar un mensaje de error
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún archivo.");
                }
            } else {
                // Es un mensaje de texto
                // Enviar el identificador 'M' al servidor para indicar que es un mensaje
                enviar_mensaje.writeChar('M');//++++
                // Enviar el mensaje de texto al servidor
                enviar_mensaje.writeUTF(txtMensaje.getText());

            }
            //------------------------------------------------------------------------
            txtMensaje.setText("");

            socket.close();
            //enviar_mensaje.close();
            //recibirMensaje.close();

        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error : " +ex + " No se pudo realizar la conexión . ");
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    
 //-----------------------------------------------------------------------------------------
   
//-----------------------------------------------------------------------------------------
    }
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JCheckBox chkEnviarArchivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables

public void recibirDatos() {
        try {
            
            DataInputStream recibir_mensaje = new DataInputStream(socket.getInputStream());

            String nombreArchivo = recibir_mensaje.readUTF();
            
            if (nombreArchivo.isEmpty()) {
                // Es un mensaje de texto
                String mensaje = recibir_mensaje.readUTF();
                String mensajes = recibir_mensaje.readUTF();
                txtArea.append("Servidor:  " + mensajes +"         ");
            
                Date fechaActual = new Date();
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                String hora = formatoHora.format(fechaActual);
            
                String mensajeConHora = "[" + hora + "] "; 
                txtArea.append(mensajeConHora + "\n\n");
                
            } else {
                // Es un archivo
                FileOutputStream fos = new FileOutputStream("C:\\Users\\HP\\Desktop\\RECIBIDOS SERVER\\" + nombreArchivo);

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = recibir_mensaje.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }

                fos.close();
                
            }
            recibir_mensaje.close();
            
        } catch (Exception e) {
            
        }
    }    

}


