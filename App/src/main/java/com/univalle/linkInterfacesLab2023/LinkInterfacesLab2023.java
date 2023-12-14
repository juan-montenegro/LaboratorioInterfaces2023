/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.univalle.linkInterfacesLab2023;
import com.univalle.labapi.LabAPI;
import com.univalle.labapi.int_proceso.int_proceso;
import com.univalle.labapi.int_proceso_refs.int_proceso_refs;
import com.univalle.labapi.int_proceso_vars.int_proceso_vars;
import com.univalle.labapi.int_proceso_vars_data.int_proceso_vars_data;
import com.univalle.labapi.int_usuarios_proceso.int_usuarios_proceso;
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
    
    public static void main(String args[]) throws SQLException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
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
        
        int prevStateDo0 = 0;
        int prevStateDo1 = 0;
        int prevStateDo2 = 0;
        int prevStateDo3 = 0;
        
        int flagCicloPrincipal  = 0;
        
        Controller arduino = new Controller(COMM_PORT);
        
        String user = "camilo";
        String password = "docWHn9LCLk7N98@";
        LabAPI labApi = new LabAPI(user, password);
        
        LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();
        LocalTime horaInicio = null;
        LocalTime horaFin = null;
        
        int_proceso proceso =labApi.proceso.getProcess(3);
        int_proceso_vars processVar = labApi.procesoVars.getProcessVars(true);
        List<int_proceso_refs> procesoRef = labApi.procesoRefs.getNamesFlags(true);
        int_usuarios_proceso regisUsuarioProceso = labApi.usuariosProcesos.getLastRecord();
        
        //PRIMERA LECTURA
         //LECTURA DE LA TABLA USUARIOS_PROCESO
        if (regisUsuarioProceso != null){
            horaInicio = regisUsuarioProceso.getStartTime();
            horaFin = regisUsuarioProceso.getEndTime();
        }
        
        try{
            while (flagCicloPrincipal != 1 && regisUsuarioProceso != null && horaInicio!=horaFin && horaInicio != null && horaFin !=null){

                //USUARIOS_PROCESOS
                regisUsuarioProceso = labApi.usuariosProcesos.getLastRecord();
                horaFin = regisUsuarioProceso.getEndTime();

                //PROCESO_VARS
                processVar = labApi.procesoVars.getProcessVars(true);
                 //LECTURA DE LA TABLA PROCESOS_VARS
                if (processVar != null) {
                   señalSelected = processVar.getName(); 
                   iDseñalSelected = processVar.getId();
                   timeMues = proceso.getSampleTime();
                }else {
                   señalSelected = "No hay registros con flag=true";
                   iDseñalSelected = 0;
                }
                señalTemp = señalSelected;

                //PROCESO_REFS
                procesoRef = labApi.procesoRefs.getNamesFlags(true);
                 //LECTURA DE LA TABLA PROCESOS_REFS
                if (!procesoRef.isEmpty()){
                    for(int i = 0; i < procesoRef.size(); i++){
                        salidasDigitales.add(procesoRef.get(i).toString());
                    }
                    // Ahora, establecer los estados en base a la presencia de los nombres en la lista salidasDigitales
                    stateDo0 = salidasDigitales.contains("DO0") ? 1 : 0;
                    stateDo1 = salidasDigitales.contains("DO1") ? 1 : 0;
                    stateDo2 = salidasDigitales.contains("DO2") ? 1 : 0;
                    stateDo3 = salidasDigitales.contains("DO3") ? 1 : 0;    

                    //ENVIAR SEÑALES DE SALIDA
                    arduino.enviarTexto("DO0"+stateDo0);
                    arduino.enviarTexto("DO1"+stateDo1);
                    arduino.enviarTexto("DO2"+stateDo2);
                    arduino.enviarTexto("DO3"+stateDo3);

                    prevStateDo0 = stateDo0;
                    prevStateDo1 = stateDo1;
                    prevStateDo2 = stateDo2;
                    prevStateDo3 = stateDo3;
                }

                while(horaInicio == horaFin){


                    //ENVIAR SEÑALES DE SALIDA
                    if (stateDo0 != prevStateDo0){
                        arduino.enviarTexto("DO0"+stateDo0);
                        //REGISTRO PROCESO_REFS_DATA
                        labApi.procesoRefsData.insertRefData(4, stateDo0, t, Date.valueOf(hoy), Time.valueOf(LocalTime.now()));
                        prevStateDo0 = stateDo0;
                    }
                    if (stateDo1 != prevStateDo1){
                        arduino.enviarTexto("DO1"+stateDo1);
                        labApi.procesoRefsData.insertRefData(5, stateDo1, t, Date.valueOf(hoy), Time.valueOf(LocalTime.now()));
                        prevStateDo1 = stateDo1;
                    }
                    if (stateDo2 != prevStateDo2){
                        arduino.enviarTexto("DO2"+stateDo2);
                        labApi.procesoRefsData.insertRefData(6, stateDo2, t, Date.valueOf(hoy), Time.valueOf(LocalTime.now()));
                        prevStateDo2 = stateDo2;
                    }
                    if (stateDo3 != prevStateDo3){
                        arduino.enviarTexto("DO3"+stateDo3);
                        labApi.procesoRefsData.insertRefData(7, stateDo3, t, Date.valueOf(hoy), Time.valueOf(LocalTime.now()));
                        prevStateDo3 = stateDo3;
                    }


                    //COMPROBAR SI SE CAMBIO LA SEÑAL SELECCIONADA
                    if(señalTemp == null ? señalSelected != null : !señalTemp.equals(señalSelected)){ t = 0;}
                    señalTemp = señalSelected;

                    //ENVIAR SEÑALES DE ENTRADA
                    if(señalSelected.charAt(0)=='A' || señalSelected.charAt(0)=='D'){

                        arduino.enviarTexto("T"+timeMues+","+señalSelected);

                        if(arduino.newAnalogData || arduino.newDigitalByte){
                            if (señalSelected.charAt(0)=='A'){
                                valorAmp = (double)arduino.readAnalog;
                                //REGISTRO PROCESO_VARS_DATA
                                int insertSeñal = labApi.procesoVarsData.insertVarData(iDseñalSelected, valorAmp, t, Date.valueOf(hoy), Time.valueOf(LocalTime.now()));    
                            }
                            else if (señalSelected.charAt(0)=='D'){
                                valorAmp = (double)arduino.readDigital;
                                int insertSeñal = labApi.procesoVarsData.insertVarData(iDseñalSelected, valorAmp, t, Date.valueOf(hoy), Time.valueOf(LocalTime.now()));    
                            }

                            t += timeMues;

                            if(señalSelected.charAt(0)=='A'){
                                arduino.newAnalogData = false;
                            } else if(señalSelected.charAt(0)=='D'){
                                arduino.newDigitalByte = false;
                            }
                        }
                    }

                    //VUELVE A LEER LA BASE DE DATOS POR SI HAY UN CAMBIO TANTO EN LAS SALIDAS DIGI Y LAS ENTRADAS A/D
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

                    procesoRef = labApi.procesoRefs.getNamesFlags(true);
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

                    //VERIFICAR SI EL USUARIO SE SALIO DE LA SESION
                    regisUsuarioProceso = labApi.usuariosProcesos.getLastRecord();
                    horaFin = regisUsuarioProceso.getEndTime();

                    if(horaInicio != horaFin){
                        flagCicloPrincipal = 1;
                    }

                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        // Closing...
        finally{
            labApi.database.closeConnection();
        }
        
        
        //java.awt.EventQueue.invokeLater(() -> {  
        //});
    }
}
