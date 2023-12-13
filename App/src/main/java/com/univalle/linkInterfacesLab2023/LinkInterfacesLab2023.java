/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.univalle.linkInterfacesLab2023;
import com.univalle.labapi.LabAPI;
import com.univalle.labapi.int_proceso_vars.int_proceso_vars;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan Camilo Chavez
 */
public class LinkInterfacesLab2023 {
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        

        /* Create and display the form */
        //java.awt.EventQueue.invokeLater(() -> {
        //    new MyGui().setVisible(true);
        //});
        
        final String COMM_PORT = "COM9";
        String señalSelected = "";
        String tittle = "";
        double timeMues = 0;
        double t = 0;
        ArrayList<String> salidasDigitales = new ArrayList<>();
       // Controller arduino = new Controller(COMM_PORT);
        
        String user = "camilo";
        String password = "docWHn9LCLk7N98@";
        LabAPI labApi = new LabAPI(user, password);
            
        int_proceso_vars processVar = labApi.procesoVars.getProcessVars(true);
        
        if (processVar != null) {
           señalSelected = processVar.getName(); // Asegúrate de que int_proceso_vars tiene un método toString() sobrescrito
        }else {
           señalSelected = "No hay registros con flag=true";
        }
        
        System.out.println(señalSelected);
        //java.awt.EventQueue.invokeLater(() -> {  
        //});
    }
}
