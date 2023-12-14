/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.univalle.guiInterfacesLab2023;
import com.univalle.guiInterfacesLab2023.controller.DatabaseController;
import com.univalle.guiInterfacesLab2023.view.LoginView;
import com.univalle.guiInterfacesLab2023.view.MainView;
import com.univalle.labapi.int_proceso_refs.int_proceso_refs;
import com.univalle.labapi.int_proceso_vars.int_proceso_vars;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juane
 */
public class MainApp {
    
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
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        //java.awt.EventQueue.invokeLater(() -> {
        //    new MainApp().setVisible(true);
        //});
        
        java.awt.EventQueue.invokeLater(() -> {
            MainView mainFrame = new MainView();
            LoginView loginView = new LoginView(mainFrame,true);
            mainFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int_proceso_vars vars = DatabaseController.getAPI().procesoVars.getProcessVars(true);
                    List<int_proceso_refs> refs = DatabaseController.getAPI().procesoRefs.getNamesFlags(true);
                    if (refs != null) {
                        for (int_proceso_refs next : refs) {
                            next.setFlag(false);
                            int res = DatabaseController.getAPI().procesoRefs.updateProcessRef(next);
                            if (res > 0) {                            
                                System.out.println(refs.toString());
                            }
                        }   
                    }
                    if (vars != null) {
                        vars.setFlag(Boolean.FALSE);
                        int res = DatabaseController.getAPI().procesoVars.updateProcessVar(vars);
                        if (res > 0) {                            
                            System.out.println(vars.toString());
                        }
                    }
                    DatabaseController.getAPI()
                        .usuariosProcesos
                        .updateHoraFin(LocalTime.now());
                    try {
                        DatabaseController.getAPI().database.closeConnection();
                    } catch (SQLException ex) {
                        Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.exit(0);
                }
            });
            loginView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            mainFrame.setVisible(false);
            loginView.setVisible(true);
        });
    }
}
