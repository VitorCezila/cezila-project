package View;

import java.sql.*;
import DAO.ConnectionModel;
import javax.swing.JOptionPane;

public class UserScreen extends javax.swing.JFrame {

    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public UserScreen() {
        initComponents();
        connection = ConnectionModel.conector();
    }

    private void consult() {
        String sql = "select * from tbusuarios where idusuarios=?";

        try {
            if (txtUserId.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite um ID válido");
            } else {
                pst = connection.prepareStatement(sql);
                pst.setString(1, txtUserId.getText());
                rs = pst.executeQuery();

                if (rs.next()) {
                    txtUserName.setText(rs.getString(2));
                    txtUserPhone.setText(rs.getString(3));
                    txtUserLogin.setText(rs.getString(4));
                    txtUserPassword.setText(rs.getString(5));
                    cboUsuProfile.setSelectedItem(rs.getString(6));
                } else {
                    JOptionPane.showMessageDialog(null, "Esse ID não foi cadastrado");
                    clearFields();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void addUser() {
        String sql = "insert into tbusuarios(idusuarios, usuario, telefone, login, senha, perfil) values (?, ?, ?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, txtUserId.getText());
            pst.setString(2, txtUserName.getText());
            pst.setString(3, txtUserPhone.getText());
            pst.setString(4, txtUserLogin.getText());
            pst.setString(5, String.valueOf(txtUserPassword.getPassword()));
            pst.setString(6, cboUsuProfile.getSelectedItem().toString());

            if ((txtUserId.getText().isEmpty()) || (txtUserName.getText().isEmpty()) || (txtUserLogin.getText().isEmpty())
                    || (String.valueOf(txtUserPassword.getPassword()).isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int added = pst.executeUpdate();
                if (added > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    clearFields();
                    txtUserId.setText("");
                }
            }
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "ID ou Login já cadastrado");
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2);
        }
    }

    private void changeUser() {
        String sql = "update tbusuarios set usuario=?,telefone=?,login=?,senha=?,perfil=? where idusuarios=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, txtUserName.getText());
            pst.setString(2, txtUserPhone.getText());
            pst.setString(3, txtUserLogin.getText());
            pst.setString(4, String.valueOf(txtUserPassword.getPassword()));
            pst.setString(5, cboUsuProfile.getSelectedItem().toString());
            pst.setString(6, txtUserId.getText());

            if ((txtUserId.getText().isEmpty()) || (txtUserName.getText().isEmpty()) || (txtUserPhone.getText().isEmpty())
                    || (txtUserLogin.getText().isEmpty()) || (String.valueOf(txtUserPassword.getPassword()).isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            } else {
                int changed = pst.executeUpdate();
                if (changed > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterado com sucesso");
                    clearFields();
                    txtUserId.setText("");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void deleteUser() {
        int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "delete from tbusuarios where idusuarios=?";

            try {
                pst = connection.prepareStatement(sql);
                pst.setString(1, txtUserId.getText());

                if ((txtUserId.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Adicione o usuário que deseja remover");
                } else {
                    int deleted = pst.executeUpdate();
                    if (deleted > 0) {
                        JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");
                        clearFields();
                        txtUserId.setText("");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void clearFields() {
        txtUserName.setText("");
        txtUserPhone.setText("");
        txtUserLogin.setText("");
        txtUserPassword.setText("");
        cboUsuProfile.setSelectedItem("user");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboUsuProfile = new javax.swing.JComboBox<>();
        txtUserId = new javax.swing.JTextField();
        txtUserName = new javax.swing.JTextField();
        txtUserLogin = new javax.swing.JTextField();
        txtUserPassword = new javax.swing.JPasswordField();
        txtUserPhone = new javax.swing.JTextField();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuRead = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        btnUsuDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Usuário");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("*Id");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("*Nome");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("*Login");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("*Senha");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("*Perfil");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Telefone");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, -1, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Campos obrigatórios (*)");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, -1, -1));

        cboUsuProfile.setBackground(new java.awt.Color(255, 255, 255));
        cboUsuProfile.setForeground(new java.awt.Color(0, 0, 0));
        cboUsuProfile.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));
        getContentPane().add(cboUsuProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 150, -1));

        txtUserId.setBackground(new java.awt.Color(255, 255, 255));
        txtUserId.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(txtUserId, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 90, 30));

        txtUserName.setBackground(new java.awt.Color(255, 255, 255));
        txtUserName.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 310, 30));

        txtUserLogin.setBackground(new java.awt.Color(255, 255, 255));
        txtUserLogin.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(txtUserLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 150, 30));

        txtUserPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtUserPassword.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(txtUserPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 150, 30));

        txtUserPhone.setBackground(new java.awt.Color(255, 255, 255));
        txtUserPhone.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(txtUserPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 150, 30));

        btnUsuCreate.setBackground(new java.awt.Color(0, 0, 0));
        btnUsuCreate.setForeground(new java.awt.Color(0, 0, 0));
        btnUsuCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/icons/add.png"))); // NOI18N
        btnUsuCreate.setToolTipText("Adicionar");
        btnUsuCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuCreate.setPreferredSize(new java.awt.Dimension(50, 50));
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 50, 50));

        btnUsuRead.setBackground(new java.awt.Color(0, 0, 0));
        btnUsuRead.setForeground(new java.awt.Color(0, 0, 0));
        btnUsuRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/icons/search.png"))); // NOI18N
        btnUsuRead.setToolTipText("Pesquisar");
        btnUsuRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuRead.setPreferredSize(new java.awt.Dimension(50, 50));
        btnUsuRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuReadActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuRead, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, 50, 50));

        btnUsuUpdate.setBackground(new java.awt.Color(0, 0, 0));
        btnUsuUpdate.setForeground(new java.awt.Color(0, 0, 0));
        btnUsuUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/icons/document.png"))); // NOI18N
        btnUsuUpdate.setToolTipText("Alterar");
        btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 50, 50));

        btnUsuDelete.setBackground(new java.awt.Color(0, 0, 0));
        btnUsuDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnUsuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/icons/trash.png"))); // NOI18N
        btnUsuDelete.setToolTipText("Deletar");
        btnUsuDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 370, 50, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/mainmenu.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(851, 604));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
        // When I click in the button create
        addUser();
    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuReadActionPerformed
        // When I click in the button read
        consult();
    }//GEN-LAST:event_btnUsuReadActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        // When I click in the button update
        changeUser();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
        // When I click in the button delete
        deleteUser();
    }//GEN-LAST:event_btnUsuDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(UserScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuRead;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JComboBox<String> cboUsuProfile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtUserId;
    private javax.swing.JTextField txtUserLogin;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JPasswordField txtUserPassword;
    private javax.swing.JTextField txtUserPhone;
    // End of variables declaration//GEN-END:variables

}
