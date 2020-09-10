package View;

import java.sql.*;
import DAO.ConnectionModel;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class ClientScreen extends javax.swing.JFrame {

    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public ClientScreen() {
        initComponents();
        connection = ConnectionModel.conector();
    }

    private void addClient() {
        String sql = "insert into tbclientes(nomecli,fonecli,emailcli) values (?,?,?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, txtCliName.getText());
            pst.setString(2, txtCliPhone.getText());
            pst.setString(3, txtCliEmail.getText());

            if ((txtCliName.getText().isEmpty()) || txtCliPhone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int added = pst.executeUpdate();
                if (added > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                    clearFields();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void changeClient() {
        String sql = "update tbclientes set nomecli=?,fonecli=?,emailcli=? where idcli=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, txtCliName.getText());
            pst.setString(2, txtCliPhone.getText());
            pst.setString(3, txtCliEmail.getText());
            pst.setString(4, txtCliId.getText());
            if ((txtCliName.getText().isEmpty()) || txtCliPhone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            } else {
                int changed = pst.executeUpdate();
                if (changed > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do cliente alterado com sucesso");
                    clearFields();
                    txtCliId.setText("");
                    searchClient();
                    btnCliCreate.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void delClient() {
        int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este cliente?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "delete from tbclientes where idcli=?";

            try {
                pst = connection.prepareStatement(sql);
                pst.setString(1, txtCliId.getText());

                if ((txtCliId.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Adicione o usuário que deseja remover");
                } else {
                    int deleted = pst.executeUpdate();
                    if (deleted > 0) {
                        JOptionPane.showMessageDialog(null, "Cliente removido com sucesso");
                        clearFields();
                        txtCliId.setText("");
                        searchClient();
                        btnCliCreate.setEnabled(true);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    //advanced search
    private void searchClient() {
        String sql = "select * from tbclientes where nomecli like ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, txtCliSearch.getText() + "%"); //% because of 'like' from sql
            rs = pst.executeQuery();
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void set_fields() { //when I click on the table
        int setting = tblClientes.getSelectedRow();
        txtCliId.setText(tblClientes.getModel().getValueAt(setting, 0).toString());
        txtCliName.setText(tblClientes.getModel().getValueAt(setting, 1).toString());
        txtCliPhone.setText(tblClientes.getModel().getValueAt(setting, 2).toString());
        txtCliEmail.setText(tblClientes.getModel().getValueAt(setting, 3).toString());

        //turn off add button
        btnCliCreate.setEnabled(false);
    }

    private void clearFields() {
        txtCliName.setText("");
        txtCliPhone.setText("");
        txtCliEmail.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        btnCliCreate = new javax.swing.JButton();
        btnCliUpdate = new javax.swing.JButton();
        btnCliDelete = new javax.swing.JButton();
        txtCliId = new javax.swing.JTextField();
        txtCliSearch = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        txtCliPhone = new javax.swing.JTextField();
        txtCliName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(1920, 1080));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 730, 90));

        btnCliCreate.setBackground(new java.awt.Color(0, 0, 0));
        btnCliCreate.setForeground(new java.awt.Color(0, 0, 0));
        btnCliCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/icons/add.png"))); // NOI18N
        btnCliCreate.setToolTipText("Adicionar");
        btnCliCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliCreate.setPreferredSize(new java.awt.Dimension(50, 50));
        btnCliCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliCreateActionPerformed(evt);
            }
        });
        getContentPane().add(btnCliCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, 50, 50));

        btnCliUpdate.setBackground(new java.awt.Color(0, 0, 0));
        btnCliUpdate.setForeground(new java.awt.Color(0, 0, 0));
        btnCliUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/icons/document.png"))); // NOI18N
        btnCliUpdate.setToolTipText("Alterar");
        btnCliUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCliUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnCliUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 440, 50, 50));

        btnCliDelete.setBackground(new java.awt.Color(0, 0, 0));
        btnCliDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnCliDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/icons/trash.png"))); // NOI18N
        btnCliDelete.setToolTipText("Deletar");
        btnCliDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCliDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnCliDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 440, 50, 50));

        txtCliId.setBackground(new java.awt.Color(255, 255, 255));
        txtCliId.setForeground(new java.awt.Color(0, 0, 0));
        txtCliId.setEnabled(false);
        getContentPane().add(txtCliId, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 100, 30));

        txtCliSearch.setBackground(new java.awt.Color(255, 255, 255));
        txtCliSearch.setForeground(new java.awt.Color(0, 0, 0));
        txtCliSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliSearchKeyReleased(evt);
            }
        });
        getContentPane().add(txtCliSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 310, 30));

        txtCliEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtCliEmail.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(txtCliEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 310, 30));

        txtCliPhone.setBackground(new java.awt.Color(255, 255, 255));
        txtCliPhone.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(txtCliPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 310, 30));

        txtCliName.setBackground(new java.awt.Color(255, 255, 255));
        txtCliName.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(txtCliName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 310, 30));

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setText("(11)98765-4321");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, -1, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Id");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/icons/searchClientes.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Email");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("*Telefone");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("*Nome");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Campos obrigatórios (*)");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/mainmenu.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(851, 604));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCliCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliCreateActionPerformed
        // When I click in the button create
        addClient();
    }//GEN-LAST:event_btnCliCreateActionPerformed

    private void btnCliUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliUpdateActionPerformed
        // When I click in the button update
        changeClient();
    }//GEN-LAST:event_btnCliUpdateActionPerformed

    private void btnCliDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliDeleteActionPerformed
        // When I click in the button delete
        delClient();
    }//GEN-LAST:event_btnCliDeleteActionPerformed

    private void txtCliSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliSearchKeyReleased
        // when the user types something in the search field
        searchClient();
    }//GEN-LAST:event_txtCliSearchKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // When I click on the table
        set_fields();
    }//GEN-LAST:event_tblClientesMouseClicked

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
            java.util.logging.Logger.getLogger(ClientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliCreate;
    private javax.swing.JButton btnCliDelete;
    private javax.swing.JButton btnCliUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliName;
    private javax.swing.JTextField txtCliPhone;
    private javax.swing.JTextField txtCliSearch;
    // End of variables declaration//GEN-END:variables
}
