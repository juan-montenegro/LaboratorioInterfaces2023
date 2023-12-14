/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.univalle.linkInterfacesLab2023;
import com.univalle.labapi.LabAPI;
import com.univalle.labapi.int_proceso.int_proceso;
import com.univalle.labapi.int_proceso_refs.int_proceso_refs;
import com.univalle.labapi.int_proceso_vars.int_proceso_vars;
import com.univalle.labapi.int_proceso_vars_data.int_proceso_vars_data;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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
        
        final String COMM_PORT = "COM3";
        String señalSelected = "";
        String señalTemp = "";
        int iDseñalSelected = 0;
        String tittle = "";
        double timeMues = 0;
        double t = 0;
        double valorAmp = 0;
        ArrayList<String> salidasDigitales = new ArrayList<>();
        int stateDo0 = 0;
        int stateDo1 = 0;
        int stateDo2 = 0;
        int stateDo3 = 0;
        
        
        Controller arduino = new Controller(COMM_PORT);
        
        String user = "camilo";
        String password = "docWHn9LCLk7N98@";
        LabAPI labApi = new LabAPI(user, password);
        
        LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();
        
        int_proceso proceso =labApi.proceso.getProcess(3);
        int_proceso_vars processVar = labApi.procesoVars.getProcessVars(true);
        List<int_proceso_refs> procesoRef = labApi.procesoRefs.getNamesFlags(true);
        
        if (processVar != null) {
           señalSelected = processVar.getName(); 
           iDseñalSelected = processVar.getId();
           timeMues = proceso.getSampleTime();
        }else {
           señalSelected = "No hay registros con flag=true";
           iDseñalSelected = 0;
        }
        señalTemp = señalSelected;
        
        if (!procesoRef.isEmpty()){
            for(int i = 0; i < procesoRef.size(); i++){
                salidasDigitales.add(procesoRef.get(i).toString());
            }
            // Ahora, establecer los estados en base a la presencia de los nombres en la lista salidasDigitales
            stateDo0 = salidasDigitales.contains("DO0") ? 1 : 0;
            stateDo1 = salidasDigitales.contains("DO1") ? 1 : 0;
            stateDo2 = salidasDigitales.contains("DO2") ? 1 : 0;
            stateDo3 = salidasDigitales.contains("DO3") ? 1 : 0;           
        }
        
        
        while(true){
            
            //ENVIAR SEÑALES DE SALIDA
            arduino.enviarTexto("DO0"+stateDo0);
            arduino.enviarTexto("DO1"+stateDo1);
            arduino.enviarTexto("DO2"+stateDo2);
            arduino.enviarTexto("DO3"+stateDo3);
            //ENVIAR SEÑALES DE ENTRADA
            if(señalTemp != señalSelected){ t = 0;}
            señalTemp = señalSelected;
            
            arduino.enviarTexto("T"+timeMues+","+señalSelected);
            
            if(arduino.newAnalogData || arduino.newDigitalByte){
                if (señalSelected.charAt(0)=='A'){
                    valorAmp = (double)arduino.readAnalog;
                    int insertSeñal = labApi.procesoVarsData.insertVarData(iDseñalSelected, valorAmp, t, Date.valueOf(hoy), Time.valueOf(ahora));    
                }
                else if (señalSelected.charAt(0)=='D'){
                    valorAmp = (double)arduino.readDigital;
                    int insertSeñal = labApi.procesoVarsData.insertVarData(iDseñalSelected, valorAmp, t, Date.valueOf(hoy), Time.valueOf(ahora));    
                }
                
                t += timeMues;
            
                if(señalSelected.charAt(0)=='A'){
                    arduino.newAnalogData = false;
                } else if(señalSelected.charAt(0)=='D'){
                    arduino.newDigitalByte = false;
                }
            }
            
            
            processVar = labApi.procesoVars.getProcessVars(true);
            if (processVar != null) {
               señalSelected = processVar.getName(); 
               iDseñalSelected = processVar.getId();
               timeMues = proceso.getSampleTime();
               ahora = LocalTime.now();
            }else {
               señalSelected = "No hay registros con flag=true";
               iDseñalSelected = 0;
            }
            
            if (!procesoRef.isEmpty()){
                for(int i = 0; i < procesoRef.size(); i++){
                    salidasDigitales.add(procesoRef.get(i).toString());
                }
                // Ahora, establecer los estados en base a la presencia de los nombres en la lista salidasDigitales
                stateDo0 = salidasDigitales.contains("DO0") ? 1 : 0;
                stateDo1 = salidasDigitales.contains("DO1") ? 1 : 0;
                stateDo2 = salidasDigitales.contains("DO2") ? 1 : 0;
                stateDo3 = salidasDigitales.contains("DO3") ? 1 : 0;           
            }
 
            System.out.println(señalSelected); 
            System.out.println(iDseñalSelected);
                
        }
        
        
        //java.awt.EventQueue.invokeLater(() -> {  
        //});
    }
}
