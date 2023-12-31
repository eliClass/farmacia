/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CapaPresentacion;

import CapaDatos.Asistencia;
import CapaDatos.Turno;
import CapaDatos.Usuario;
import CapaNegocio.AsistenciaBD;
import CapaNegocio.TurnoBD;
import CapaNegocio.UsuarioBD;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class Loguin_IU extends javax.swing.JFrame {

    public static String dni_publico;
    public static String usuario;
    public static String tienda;
    public static int idasistencia;

    /**
     * Creates new form Loguin_IU
     */
    public Loguin_IU() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private String obtener_Fecha_actual() {

        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int anio = calendario.get(Calendar.YEAR);
        String fecha = anio + "-" + mes + "-" + dia;

        return fecha;
    }

    private String obtener_hora() {

        Calendar calendario = Calendar.getInstance();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minuto = calendario.get(Calendar.MINUTE);
        int segundo = calendario.get(Calendar.SECOND);
        String tiempo = hora + ":" + minuto + ":" + segundo;

        return tiempo;
    }

    private void exito(String mensaje) {
        JOptionPane.showConfirmDialog(this, mensaje, "MENSAJE", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

    private void error(String mensaje) {
        JOptionPane.showConfirmDialog(this, mensaje, "ERROR", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
    }

    private void advertencia(String mensaje) {
        JOptionPane.showConfirmDialog(this, mensaje, "ADVERTENCIA", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        txtClave = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        jLabel1.setText("BIENVENIDOS");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("DNI");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("CLAVE");

        txtDni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDniFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDniFocusLost(evt);
            }
        });
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDniKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });

        btnIngresar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Proyecto/llave.png"))); // NOI18N
        btnIngresar.setText("INGRESAR");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        btnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Proyecto/cancel.png"))); // NOI18N
        btnCerrar.setText("CERRAR");

        txtClave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtClave.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtClaveFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtClaveFocusLost(evt);
            }
        });
        txtClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClaveKeyPressed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_Proyecto/logo.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIngresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrar))
                    .addComponent(txtDni)
                    .addComponent(txtClave))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnIngresar)
                            .addComponent(btnCerrar)))
                    .addComponent(jLabel5))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtClave.requestFocus();
        }
    }//GEN-LAST:event_txtDniKeyPressed

    private void txtClaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnIngresar.requestFocus();
        }
    }//GEN-LAST:event_txtClaveKeyPressed

    private void txtDniFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDniFocusGained
        // TODO add your handling code here:
        txtDni.setBackground(Color.green);
    }//GEN-LAST:event_txtDniFocusGained

    private void txtDniFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDniFocusLost
        // TODO add your handling code here:
        txtDni.setBackground(Color.white);
    }//GEN-LAST:event_txtDniFocusLost

    private void txtClaveFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtClaveFocusGained
        // TODO add your handling code here:
        txtClave.setBackground(Color.green);
    }//GEN-LAST:event_txtClaveFocusGained

    private void txtClaveFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtClaveFocusLost
        // TODO add your handling code here:
        txtClave.setBackground(Color.white);
    }//GEN-LAST:event_txtClaveFocusLost

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || txtDni.getText().length() >= 8) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDniKeyTyped

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        // TODO add your handling code here:
       if (txtDni.getText().length() > 0) {
            if (txtClave.getText().length() > 0) {
                String dni = txtDni.getText().trim();
                String clave = txtClave.getText();
                UsuarioBD oUsuarioBD = new UsuarioBD();
                List<Usuario> lista_usuarios = oUsuarioBD.login(dni, clave);
                if (lista_usuarios.size() > 0) {
                    String nombres = lista_usuarios.get(0).getuNombre();
                    String apellidos = lista_usuarios.get(0).getuApellido();
                    tienda = lista_usuarios.get(0).getTienda();
                    dni_publico = dni;
                    usuario = nombres + " " + apellidos;
                    TurnoBD oTurno = new TurnoBD();
                    String hora_actual = obtener_hora();
                    List<Turno> lista_turno = oTurno.buscarTurno(hora_actual, hora_actual, dni);
                    if (lista_turno.size() > 0) {
                        Menu_IU oMenu = new Menu_IU();
                        String fecha = obtener_Fecha_actual();
                        String hora = obtener_hora();
                        Asistencia oAsistencia = new Asistencia();
                        AsistenciaBD oAsistenciaBD = new AsistenciaBD();

                        int tipo = lista_usuarios.get(0).getuTipo();
                        if (tipo == 5) {
                            oMenu.menu_mantenimiento.setEnabled(true);
                            oMenu.menu_compras.setEnabled(true);
                            oMenu.menu_inventario.setEnabled(true);
                            oMenu.btnProductos.setEnabled(true);
                            oMenu.btnCompras.setEnabled(true);
                            oMenu.btnCerrar.setEnabled(true);

                            oMenu.setVisible(true);
                            oMenu.setTitle("TIENDA: " + tienda + "- USUARIO: " + usuario);
                            this.setVisible(false);

                        }

                        if (tipo == 3) {
                            oMenu.menu_ventas.setEnabled(true);
                            oMenu.menu_caja.setEnabled(true);
                            oMenu.btnVentas.setEnabled(true);
                            oMenu.btnIngresos.setEnabled(true);
                            oMenu.btnEgresos.setEnabled(true);
                            oMenu.btnCaja.setEnabled(true);
                            oMenu.btnCerrar.setEnabled(true);

                            List<Asistencia> lista = oAsistenciaBD.buscarAsistenciaUsuario(dni, tienda, fecha);
                            if (lista.size() > 0) {
                                String estado = lista.get(0).getaEstado();
                                if (estado.equals("CERRAD0")) {
                                    advertencia("Ya no puedes entrar al sistema porque ya cerraste caja");
                                } else {
                                    idasistencia = lista.get(0).getIdasistencia();
                                    oMenu.setVisible(true);
                                    oMenu.setTitle("TIENDA: " + tienda + "- USUARIO: " + usuario);
                                    this.setVisible(false);
                                }
                            } else {
                                oAsistencia.setaFechaE(fecha);
                                oAsistencia.setaHoraE(hora);
                                oAsistencia.setaHoraS("00:00:00");
                                oAsistencia.setuDni(dni);
                                oAsistencia.setaTurno("");
                                oAsistencia.setaEstado("ABIERTO");
                                oAsistencia.setaTienda(tienda);

                                idasistencia = oAsistenciaBD.registrarAsistencia(oAsistencia);
                                oMenu.setVisible(true);
                                oMenu.setTitle("TIENDA: " + tienda + "- USUARIO: " + usuario);
                                this.setVisible(false);
                            }

                        }

                    } else {
                        advertencia("No puedes entrar al sistema, porque ya no es tu turno");
                    }
                } else {
                    error("No perteneces al sistema");
                }

            } else {
                advertencia("Ingrese su clave");
                txtClave.requestFocus();
            }
        } else {
            advertencia("Ingrese el dni");
            txtDni.requestFocus();
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Loguin_IU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loguin_IU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loguin_IU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loguin_IU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loguin_IU().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtDni;
    // End of variables declaration//GEN-END:variables
}
