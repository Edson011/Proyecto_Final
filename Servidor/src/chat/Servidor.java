
package chat;

        
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
 
public class Servidor extends javax.swing.JFrame implements Runnable{

    
    
    public Servidor() {
        initComponents();
        Thread hilo = new Thread(this);
        hilo.start();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txtMensaje = new javax.swing.JTextField();
        Enviar = new javax.swing.JButton();
        chkEnviarArchivo = new javax.swing.JCheckBox();
        Guardar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 204, 255));
        setPreferredSize(new java.awt.Dimension(450, 400));
        getContentPane().setLayout(null);

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 44, 400, 170);

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Servidor");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(170, 10, 80, 29);

        txtMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMensajeActionPerformed(evt);
            }
        });
        getContentPane().add(txtMensaje);
        txtMensaje.setBounds(20, 290, 280, 40);

        Enviar.setBackground(new java.awt.Color(0, 153, 153));
        Enviar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Enviar.setForeground(new java.awt.Color(255, 255, 255));
        Enviar.setText("Enviar");
        Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarActionPerformed(evt);
            }
        });
        getContentPane().add(Enviar);
        Enviar.setBounds(310, 290, 80, 40);

        chkEnviarArchivo.setText("Seleccionar Archivo");
        getContentPane().add(chkEnviarArchivo);
        chkEnviarArchivo.setBounds(280, 260, 140, 20);

        Guardar.setBackground(new java.awt.Color(0, 102, 102));
        Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Guardar.setForeground(new java.awt.Color(255, 255, 255));
        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        getContentPane().add(Guardar);
        Guardar.setBounds(20, 220, 90, 21);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 450, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMensajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMensajeActionPerformed

    private void EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarActionPerformed

    /*
        try {
        Socket socket = new Socket("localhost", 1212); // Conexión al cliente
        DataOutputStream enviar_mensaje = new DataOutputStream(socket.getOutputStream());
        
     
         if (chkEnviarArchivo.isSelected()) {//---------------------
            // Abrir el diálogo de selección de archivo
               JFileChooser fileChooser = new JFileChooser();
               int seleccion = fileChooser.showOpenDialog(this);
            
               if (seleccion == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();
                
                enviar_mensaje.writeChar('A');
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
            // Enviar el mensaje de texto al servidor
            enviar_mensaje.writeChar('M');
            enviar_mensaje.writeUTF(txtMensaje.getText());
            
            }
            //------------------------------------------------------------------------
            txtMensaje.setText("");

            socket.close();
                      
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error : " +ex + " No se pudo realizar la conexión . ");
        }*/
       
    }//GEN-LAST:event_EnviarActionPerformed


    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        // TODO add your handling code here:
        guardarMensajes();
    }//GEN-LAST:event_GuardarActionPerformed

    private void guardarMensajes() {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showSaveDialog(this);
        
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            try {
                String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath() + ".txt";
                BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo));

                String contenido = txtArea.getText();
                //writer.write(contenido);
                String[] lineas = contenido.split("\n");
                
                for (String linea : lineas) {
                    if (!linea.contains(".") || !linea.matches(".*\\.[a-zA-Z0-9]{1,}")) {
                        writer.write(linea + "\n");
                    }
                }
                

                writer.close();
            } catch (IOException e) {
                // Manejo de errores al guardar el archivo
                txtArea.append("\n Error al guardar los mensajes: " + e.getMessage() + "\n\n");
            }
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Servidor().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Enviar;
    private javax.swing.JButton Guardar;
    private javax.swing.JCheckBox chkEnviarArchivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables

  
    @Override
    public void run() {
        try {
        ServerSocket servidor = new ServerSocket(1212);

        while (true) {
            Socket misocket = servidor.accept();
            DataInputStream recibir_mensaje = new DataInputStream(misocket.getInputStream());
            
            char identificador = recibir_mensaje.readChar();

            if (identificador == 'A') {
                // Es un archivo
                String nombreArchivo = recibir_mensaje.readUTF();
                
                Date fechaActual = new Date();
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                String hora = formatoHora.format(fechaActual);
                String mensajeConHora = "[" + hora + "] ";
                txtArea.append("Cliente: "+ nombreArchivo + "         "+mensajeConHora + "\n\n");
                
                FileOutputStream fos = new FileOutputStream("C:\\Users\\HP\\Desktop\\CAR. PARA PRUEBAS\\" + nombreArchivo);

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = recibir_mensaje.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }

                fos.close();
            
             } else if (identificador == 'M') {   
                // Es un mensaje de texto
                String mensaje = recibir_mensaje.readUTF();
                // Mostrar el mensaje de texto en el txtArea
                txtArea.append("Cliente: " + mensaje + "         ");

                Date fechaActual = new Date();
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                String hora = formatoHora.format(fechaActual);

                String mensajeConHora = "[" + hora + "] ";
                txtArea.append(mensajeConHora + "\n\n");
            }

            misocket.close();
        }
    } catch (Exception e) {
        String errorMsg = "Error al mostrar los mensajes en el JTextArea: " + e.getMessage();
        txtArea.append(errorMsg);
        txtArea.append("\n");
    }
}
    
        
       
    
}
