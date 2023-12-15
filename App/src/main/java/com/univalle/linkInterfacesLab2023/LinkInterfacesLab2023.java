/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.univalle.linkInterfacesLab2023;
import com.univalle.labapi.LabAPI;
import com.univalle.labapi.int_proceso.int_proceso;
import com.univalle.labapi.int_proceso_refs.int_proceso_refs;
import com.univalle.labapi.int_proceso_vars.int_proceso_vars;
import com.univalle.labapi.int_usuarios_proceso.int_usuarios_proceso;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
//import jdk.javadoc.doclet.DocletEnvironment;

/**
 *
 * @author Juan Camilo Chavez
 */
public class LinkInterfacesLab2023 {
    private static final String COMM_PORT = "COM9";
    private static final String USER = "camilo";
    private static final String PASSWORD = "docWHn9LCLk7N98@"; 
    private static String señalSelected = "";
    private static String señalTemp = "";
    private static Controller arduino;
    
    private static LabAPI labApi;
    
    private static ScheduledExecutorService executorService;
    private static Runnable digitalRunnable;        
    private static Runnable analogRunnable;
        
    private static LocalDate hoy;
    private static LocalTime horaInicio = null;
    private static LocalTime horaFin = null;
    
    private static int iDseñalSelected = 0;
    private static double timeMues = 0;
    private static double t = 0;
    private static double valorAmp = 0;
    

    private static int stateDo0 = 0;
    private static int stateDo1 = 0;
    private static int stateDo2 = 0;
    private static int stateDo3 = 0;

    private static int prevStateDo0 = 0;
    private static int prevStateDo1 = 0;
    private static int prevStateDo2 = 0;
    private static int prevStateDo3 = 0;
    
    private static boolean messagePrev = false;
    private static boolean messageNow = false;
    
    private static int_proceso_vars processVar;
    private static List<int_proceso_refs> procesoRef;
    private static int_proceso proceso;
    private static int_usuarios_proceso regisUsuarioProceso;

    public static void main(String args[]){
        
        executorService = Executors.newScheduledThreadPool(1);
                
        arduino = new Controller(COMM_PORT);
        while(!arduino.isOpen()){
            arduino.init();
        }
        arduino.addDataListener();
        arduino.startThreads();
        setAPI();
        
        hoy = LocalDate.now();
//        ahora = LocalTime.now();
        horaInicio = null;
        horaFin = null;

        proceso = labApi.proceso.getProcess(3);
        
        
        regisUsuarioProceso = labApi.usuariosProcesos.getLastRecord();
        
        //PRIMERA LECTURA
        //LECTURA DE LA TABLA USUARIOS_PROCESO
        while (regisUsuarioProceso == null){
            regisUsuarioProceso = labApi.usuariosProcesos.getLastRecord();
        }
        horaInicio = regisUsuarioProceso.getStartTime();
        horaFin = regisUsuarioProceso.getEndTime();
        
        digitalRunnable = new DigitalRunnable();
        analogRunnable = new AnalogRunnable();
        
        
        try{
            System.out.println(regisUsuarioProceso);
            System.out.println("primer Ciclo");            
            while (horaInicio.compareTo(horaFin)!= 0){
                //USUARIOS_PROCESOS
                regisUsuarioProceso = labApi.usuariosProcesos.getLastRecord();
                horaInicio = regisUsuarioProceso.getStartTime();
                horaFin = regisUsuarioProceso.getEndTime();             
            }
            System.out.println(regisUsuarioProceso);

            executorService.scheduleAtFixedRate(
                digitalRunnable, 
                0, 
                200, 
                TimeUnit.MILLISECONDS
            );
            executorService.scheduleAtFixedRate(
                analogRunnable, 
                100, 
                200, 
                TimeUnit.MILLISECONDS
            );
 
        } catch (Exception e){
            Logger.getLogger(LinkInterfacesLab2023.class.getName())
                    .log(Level.SEVERE, null, e);
        }
        // Closing...
//        finally{
//            labApi.database.closeConnection();
//        }
        
        
        //java.awt.EventQueue.invokeLater(() -> {  
        //});
    }
    private static void setAPI() {
        if (labApi == null) {
            try {
                labApi = new LabAPI(USER, PASSWORD);
            } catch (SQLException ex) {
                Logger.getLogger(LinkInterfacesLab2023.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static class AnalogRunnable implements Runnable{

        @Override
        public void run() {
            setAPI();
            if (horaInicio.compareTo(horaFin)!= 0) return;
            
            //VUELVE A LEER LA BASE DE DATOS POR SI HAY UN CAMBIO TANTO EN LAS SALIDAS DIGI Y LAS ENTRADAS A/D
            processVar = labApi.procesoVars.getProcessVars(true);
            
            señalTemp = señalSelected;

            //LECTURA DE LA TABLA PROCESOS_VARS
            if (processVar != null) {
                messageNow = true;
                señalSelected = processVar.getName(); 
                iDseñalSelected = processVar.getId();
                timeMues = proceso.getSampleTime();
            } else {
                messageNow = false;
                messagePrev = false;
                señalSelected = "No hay registros con flag=true";
                iDseñalSelected = 0;
                System.out.println(señalSelected);
                return;
            }
            
            //COMPROBAR SI SE CAMBIO LA SEÑAL SELECCIONADA
            if(!señalTemp.equals(señalSelected)){
                t = 0;
            }
            
            //ENVIAR SEÑALES DE ENTRADA
            if(messageNow && messagePrev != messageNow){
                System.out.println("IS ANALOG/DIGIAL");
                System.out.println("SIGNAL: " + señalSelected);
                System.out.println("ID: " + iDseñalSelected);

                arduino.enviarTexto("T"+timeMues+","+señalSelected);
                System.out.println("T"+timeMues+","+señalSelected);
                messagePrev = messageNow;
            }

            if(arduino.newAnalogData || arduino.newDigitalByte){
                System.out.println("NEW DATA");

                if (señalSelected.charAt(0)=='A'){
                    valorAmp = (double) arduino.readAnalog;
                    //REGISTRO PROCESO_VARS_DATA
                } else if (señalSelected.charAt(0)=='D'){
                    valorAmp = (double) arduino.readDigital;
                }

                int insertSeñal = labApi.procesoVarsData
                        .insertVarData(
                                iDseñalSelected, 
                                valorAmp, 
                                t, 
                                Date.valueOf(hoy), 
                                Time.valueOf(LocalTime.now())
                        );    
                if (insertSeñal > 0) {
                    System.out.println("Dato enviado.");

                }
                t += timeMues;

                if(señalSelected.charAt(0)=='A'){
                    arduino.newAnalogData = false;
                } else if(señalSelected.charAt(0)=='D'){
                    arduino.newDigitalByte = false;
                }
            }
        }
    }

    
    private static class DigitalRunnable implements Runnable{

        @Override
        public void run() {
            setAPI();
            if (horaInicio.compareTo(horaFin)!= 0) return;

            procesoRef = labApi.procesoRefs.getAllProcessRefs(3);
            if (procesoRef.isEmpty()) return ;
            for(int_proceso_refs next : procesoRef){
                // Ahora, establecer los estados en base a la presencia de los nombres en la lista salidasDigitales
                switch (next.getName()) {
                    case "DO0":
                        stateDo0 = next.isFlag() ? 1 : 0;
                        break;
                    case "DO1":
                        stateDo1 = next.isFlag() ? 1 : 0;
                        break;
                    case "DO2":
                        stateDo2 = next.isFlag() ? 1 : 0;
                        break;
                    case "DO3":
                        stateDo3 = next.isFlag() ? 1 : 0;
                        break;
                    default:
                        break;
                }

            } 

            //ENVIAR SEÑALES DE SALIDA
            if (stateDo0 != prevStateDo0){
                arduino.enviarTexto("DO0"+stateDo0);
                //REGISTRO PROCESO_REFS_DATA
                labApi.procesoRefsData.insertRefData(
                        4, 
                        stateDo0, 
                        t, Date.valueOf(hoy), 
                        Time.valueOf(LocalTime.now())
                );

            }
            if (stateDo1 != prevStateDo1){
                arduino.enviarTexto("DO1"+stateDo1);
                labApi.procesoRefsData.insertRefData(
                        5, 
                        stateDo1, 
                        t, 
                        Date.valueOf(hoy), 
                        Time.valueOf(LocalTime.now())
                );

            }
            if (stateDo2 != prevStateDo2){
                arduino.enviarTexto("DO2"+stateDo2);
                labApi.procesoRefsData.insertRefData(
                        6, 
                        stateDo2, 
                        t, 
                        Date.valueOf(hoy), 
                        Time.valueOf(LocalTime.now())
                );

            }
            if (stateDo3 != prevStateDo3){
                arduino.enviarTexto("DO3"+stateDo3);
                labApi.procesoRefsData.insertRefData(
                        7, 
                        stateDo3, 
                        t, 
                        Date.valueOf(hoy), 
                        Time.valueOf(LocalTime.now())
                );

            }

            
            //VERIFICAR SI EL USUARIO SE SALIO DE LA SESION
            regisUsuarioProceso = labApi.usuariosProcesos.getLastRecord();
            horaFin = regisUsuarioProceso.getEndTime();

            prevStateDo0 = stateDo0;
            prevStateDo1 = stateDo1;
            prevStateDo2 = stateDo2;
            prevStateDo3 = stateDo3;
        }
    }
}
