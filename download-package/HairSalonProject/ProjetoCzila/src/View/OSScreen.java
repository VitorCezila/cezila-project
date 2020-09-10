package View;

import java.sql.*;
import DAO.ConnectionModel;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
//import org.hsqldb.lib.HashMap;
import java.util.HashMap;

public class OSScreen extends javax.swing.JFrame {

    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public OSScreen() {
        initComponents();
        connection = ConnectionModel.conector();
    }

    private void create_os() {
        String sql = "insert into tbos(horamarcada,servico,funcionario,valor,idcli) values (?,?,?,?,?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, txtOsDayHour.getText());
            pst.setString(2, txtOsService.getText());
            pst.setString(3, txtOsEmployee.getText());
            pst.setString(4, txtOsValue.getText().replace(",", "."));
            pst.setString(5, txtCliId.getText());

            if ((txtCliId.getText().isEmpty()) || (txtOsDayHour.getText().isEmpty()) || (txtOsService.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int added = pst.executeUpdate();
                if (added > 0) {
                    JOptionPane.showMessageDialog(null, "Ordem de Serviço emitida com sucesso");
                    clearFields();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void search_os() {
        String num_os = JOptionPane.showInputDialog("Número da OS");
        String sql = "select * from tbos where os= " + num_os;

        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                txtOs.setText(rs.getString(1));
                txtData.setText(rs.getString(2));
                txtOsDayHour.setText(rs.getString(3));
                txtOsService.setText(rs.getString(4));
                txtOsEmployee.setText(rs.getString(5));
                txtOsValue.setText(rs.getString(6));
                txtCliId.setText(rs.getString(7));

                btnOsCreate.setEnabled(false);
                txtCliPesquisar.setEnabled(false);
                tblClientes.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Essa Ordem de Serviço ainda não foi cadastrada");
            }
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "Ordem de serviço inválida");
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2);
        }
    }

    private void change_os() {
        String sql = "update tbos set horamarcada=?,servico=?,funcionario=?,valor=? where os=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, txtOsDayHour.getText());
            pst.setString(2, txtOsService.getText());
            pst.setString(3, txtOsEmployee.getText());
            pst.setString(4, txtOsValue.getText().replace(",", "."));
            pst.setString(5, txtOs.getText());

            if ((txtCliId.getText().isEmpty()) || (txtOsDayHour.getText().isEmpty()) || (txtOsService.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int added = pst.executeUpdate();
                if (added > 0) {
                    JOptionPane.showMessageDialog(null, "Ordem de Serviço alterada com sucesso");
                    clearFields();
                    txtOs.setText("");
                    txtData.setText("");

                    btnOsCreate.setEnabled(true);
                    txtCliPesquisar.setEnabled(true);
                    tblClientes.setVisible(true);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void delete_os() {
        int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esta Ordem de Serviço?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "delete from tbos where os=?";

            try {
                pst = connection.prepareStatement(sql);
                pst.setString(1, txtOs.getText());

                if ((txtOs.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Insira uma OS");
                } else {
                    int deleted = pst.executeUpdate();
                    if (deleted > 0) {
                        JOptionPane.showMessageDialog(null, "OS removida com sucesso");
                        clearFields();
                        txtOs.setText("");
                        txtData.setText("");

                        btnOsCreate.setEnabled(true);
                        txtCliPesquisar.setEnabled(true);
                        tblClientes.setVisible(true);

                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void print_os() {
        int confirm = JOptionPane.showConfirmDialog(null, "Confirma a impressão desta OS?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                HashMap filtro = new HashMap();
                filtro.put("os", Integer.parseInt(txtOs.getText()));

                JasperPrint print = JasperFillManager.fillReport("C:/reports/os.jasper", filtro, connection);
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    //advanced search
    private void search_client() {
        String sql = "select idcli as ID, nomecli as Nome, fonecli as Telefone from tbclientes where nomecli like ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, txtCliPesquisar.getText() + "%"); //% because of 'like' from sql
            rs = pst.executeQuery();
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void set_fields() {
        int setting = tblClientes.getSelectedRow();
        txtCliId.setText(tblClientes.getModel().getValueAt(setting, 0).toString());
    }

    private void clearFields() {
        txtCliId.setText("");
        txtOsDayHour.setText("");
        txtOsService.setText("");
        txtOsValue.setText("");
        txtOsEmployee.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        txtCliPesquisar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtOs = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        btnOsPrint = new javax.swing.JButton();
        btnOsCreate = new javax.swing.JButton();
        btnOsRead = new javax.swing.JButton();
        btnOsUpdate = new javax.swing.JButton();
        btnOsDelete = new javax.swing.JButton();
        txtOsValue = new javax.swing.JTextField();
        txtOsEmployee = new javax.swing.JTextField();
        txtOsService = new javax.swing.JTextField();
        txtOsDayHour = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ordem de Serviço");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/icons/searchClientes.png"))); // NOI18N

        jLabel5.setBackground(new java.awt.Color(153, 153, 153));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("*Id");

        txtCliId.setEditable(false);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "Fone"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(67, 67, 67)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 500, 240));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("N° OS");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Data");

        txtOs.setEditable(false);

        txtData.setEditable(false);
        txtData.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(120, 120, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtData)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtData)
                        .addGap(1, 1, 1)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 270, 70));

        btnOsPrint.setBackground(new java.awt.Color(0, 0, 0));
        btnOsPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/icons/print.png"))); // NOI18N
        btnOsPrint.setToolTipText("Imprimir");
        btnOsPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsPrint.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsPrintActionPerformed(evt);
            }
        });
        getContentPane().add(btnOsPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 440, 50, 50));

        btnOsCreate.setBackground(new java.awt.Color(0, 0, 0));
        btnOsCreate.setForeground(new java.awt.Color(0, 0, 0));
        btnOsCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/icons/add.png"))); // NOI18N
        btnOsCreate.setToolTipText("Adicionar");
        btnOsCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsCreate.setPreferredSize(new java.awt.Dimension(50, 50));
        btnOsCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsCreateActionPerformed(evt);
            }
        });
        getContentPane().add(btnOsCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, 50, 50));

        btnOsRead.setBackground(new java.awt.Color(0, 0, 0));
        btnOsRead.setForeground(new java.awt.Color(0, 0, 0));
        btnOsRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/icons/search.png"))); // NOI18N
        btnOsRead.setToolTipText("Pesquisar");
        btnOsRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsRead.setPreferredSize(new java.awt.Dimension(50, 50));
        btnOsRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsReadActionPerformed(evt);
            }
        });
        getContentPane().add(btnOsRead, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 440, 50, 50));

        btnOsUpdate.setBackground(new java.awt.Color(0, 0, 0));
        btnOsUpdate.setForeground(new java.awt.Color(0, 0, 0));
        btnOsUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/icons/document.png"))); // NOI18N
        btnOsUpdate.setToolTipText("Alterar");
        btnOsUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnOsUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 440, 50, 50));

        btnOsDelete.setBackground(new java.awt.Color(0, 0, 0));
        btnOsDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnOsDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/icons/trash.png"))); // NOI18N
        btnOsDelete.setToolTipText("Deletar");
        btnOsDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOsDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOsDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnOsDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 440, 50, 50));

        txtOsValue.setText("0");
        getContentPane().add(txtOsValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 140, 30));
        getContentPane().add(txtOsEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, 130, 30));
        getContentPane().add(txtOsService, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 320, 30));
        getContentPane().add(txtOsDayHour, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 320, 30));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel10.setText("dd/mm/yy - hh:mm");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, -1, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Valor Total R$");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Funcionário");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 270, -1, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("*Dia / Hora");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("*Serviço");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/mainmenu.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(851, 604));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOsCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsCreateActionPerformed
        // When I click in the button create
        create_os();
    }//GEN-LAST:event_btnOsCreateActionPerformed

    private void btnOsReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsReadActionPerformed
        // When I click in the button read
        search_os();
    }//GEN-LAST:event_btnOsReadActionPerformed

    private void btnOsUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsUpdateActionPerformed
        // When I click in the button update
        change_os();
    }//GEN-LAST:event_btnOsUpdateActionPerformed

    private void btnOsDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsDeleteActionPerformed
        // When I click in the button delete
        delete_os();
    }//GEN-LAST:event_btnOsDeleteActionPerformed

    private void btnOsPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsPrintActionPerformed
        // When I click in the button print
        print_os();
    }//GEN-LAST:event_btnOsPrintActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // when the user types something in the search field
        search_client();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // When I click on the table
        set_fields();
    }//GEN-LAST:event_tblClientesMouseClicked

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
            java.util.logging.Logger.getLogger(OSScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OSScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OSScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OSScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OSScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOsCreate;
    private javax.swing.JButton btnOsDelete;
    private javax.swing.JButton btnOsPrint;
    private javax.swing.JButton btnOsRead;
    private javax.swing.JButton btnOsUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtOs;
    private javax.swing.JTextField txtOsDayHour;
    private javax.swing.JTextField txtOsEmployee;
    private javax.swing.JTextField txtOsService;
    private javax.swing.JTextField txtOsValue;
    // End of variables declaration//GEN-END:variables
}
