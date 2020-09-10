package View;

import DAO.ConnectionModel;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class MainMenu extends javax.swing.JFrame {
    
    Connection connection = null;
    
    public MainMenu() {
        initComponents();
        connection = ConnectionModel.conector();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUser = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        menRegister = new javax.swing.JMenu();
        menRegCli = new javax.swing.JMenuItem();
        menRegOs = new javax.swing.JMenuItem();
        menRegUser = new javax.swing.JMenuItem();
        menReport = new javax.swing.JMenu();
        menReportlCli = new javax.swing.JMenuItem();
        menReportSer = new javax.swing.JMenuItem();
        menHelp = new javax.swing.JMenu();
        menHelpAbout = new javax.swing.JMenuItem();
        menOpt = new javax.swing.JMenu();
        menOptExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Menu");
        setMinimumSize(new java.awt.Dimension(915, 523));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUser.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblUser.setForeground(new java.awt.Color(0, 0, 0));
        lblUser.setText("Usuario");
        getContentPane().add(lblUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, -1, -1));

        lblDate.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblDate.setForeground(new java.awt.Color(0, 0, 0));
        lblDate.setText("Data");
        getContentPane().add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 60)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Czila");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, -1, 70));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Try something new");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/mainmenu.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 500));

        menRegister.setText("Cadastro");

        menRegCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        menRegCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/icons/cliente-icon.png"))); // NOI18N
        menRegCli.setText("Cliente");
        menRegCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRegCliActionPerformed(evt);
            }
        });
        menRegister.add(menRegCli);

        menRegOs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        menRegOs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/icons/relatorioTrabalho-icon.png"))); // NOI18N
        menRegOs.setText("OS");
        menRegOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRegOsActionPerformed(evt);
            }
        });
        menRegister.add(menRegOs);

        menRegUser.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        menRegUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/images/icons/usuario-icon.png"))); // NOI18N
        menRegUser.setText("Usuários");
        menRegUser.setEnabled(false);
        menRegUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRegUserActionPerformed(evt);
            }
        });
        menRegister.add(menRegUser);

        Menu.add(menRegister);

        menReport.setText("Relatório");
        menReport.setEnabled(false);

        menReportlCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        menReportlCli.setText("Clientes");
        menReportlCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menReportlCliActionPerformed(evt);
            }
        });
        menReport.add(menReportlCli);

        menReportSer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        menReportSer.setText("Serviço");
        menReportSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menReportSerActionPerformed(evt);
            }
        });
        menReport.add(menReportSer);

        Menu.add(menReport);

        menHelp.setText("Ajuda");

        menHelpAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.ALT_MASK));
        menHelpAbout.setText("Sobre");
        menHelpAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menHelpAboutActionPerformed(evt);
            }
        });
        menHelp.add(menHelpAbout);

        Menu.add(menHelp);

        menOpt.setText("Opções");

        menOptExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        menOptExit.setText("Sair");
        menOptExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menOptExitActionPerformed(evt);
            }
        });
        menOpt.add(menOptExit);

        Menu.add(menOpt);

        setJMenuBar(Menu);

        setSize(new java.awt.Dimension(931, 562));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menRegOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRegOsActionPerformed
        // When I click ''Cadastro - OS''
        OSScreen os = new OSScreen();
        os.setVisible(true);
    }//GEN-LAST:event_menRegOsActionPerformed

    private void menRegCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRegCliActionPerformed
        // When I click ''Cadastro - Cliente''
        ClientScreen client = new ClientScreen();
        client.setVisible(true);
    }//GEN-LAST:event_menRegCliActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // Change lblData to current date.
        Date date = new Date();
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT);
        lblDate.setText(formatter.format(date));
    }//GEN-LAST:event_formWindowActivated

    private void menOptExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menOptExitActionPerformed
        // When I click ''Opções - Sair''
        int exit = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (exit == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_menOptExitActionPerformed

    private void menHelpAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menHelpAboutActionPerformed
        // When I click ''Ajuda - Sobre''
        AboutScreen about = new AboutScreen();
        about.setVisible(true);
    }//GEN-LAST:event_menHelpAboutActionPerformed

    private void menRegUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRegUserActionPerformed
        // When I click ''Cadastro - Usuarios''
        UserScreen user = new UserScreen();
        user.setVisible(true);
    }//GEN-LAST:event_menRegUserActionPerformed

    private void menReportlCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menReportlCliActionPerformed
        // When I click ''Relatório - Clientes''
        int confirm = JOptionPane.showConfirmDialog(null, "Confirma a emissão desse relatório?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                JasperPrint print = JasperFillManager.fillReport("C:\\reports\\clientes.jasper", null, connection);
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_menReportlCliActionPerformed

    private void menReportSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menReportSerActionPerformed
        // When I click ''Relatório - Serviços''
        int confirm = JOptionPane.showConfirmDialog(null, "Confirma a emissão desse relatório?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                JasperPrint print = JasperFillManager.fillReport("C:\\reports\\servicos.jasper", null, connection);
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_menReportSerActionPerformed


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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar Menu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblDate;
    public static javax.swing.JLabel lblUser;
    private javax.swing.JMenu menHelp;
    private javax.swing.JMenuItem menHelpAbout;
    private javax.swing.JMenu menOpt;
    private javax.swing.JMenuItem menOptExit;
    private javax.swing.JMenuItem menRegCli;
    private javax.swing.JMenuItem menRegOs;
    public static javax.swing.JMenuItem menRegUser;
    private javax.swing.JMenu menRegister;
    public static javax.swing.JMenu menReport;
    private javax.swing.JMenuItem menReportSer;
    private javax.swing.JMenuItem menReportlCli;
    // End of variables declaration//GEN-END:variables
}
