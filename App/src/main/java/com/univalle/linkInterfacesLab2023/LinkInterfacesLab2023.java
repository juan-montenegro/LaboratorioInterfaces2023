/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.univalle.linkInterfacesLab2023;
import com.fazecast.jSerialComm.SerialPort;
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
 * @author Juan Esteban Montenegro
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
    private static Runnable outputsDRunnable;        
    private static Runnable inputsADRunnable;
        
    private static LocalDate hoy;
    private static LocalTime horaInicio = null;
    private static LocalTime horaFin = null;
    
    private static volatile int iDseñalSelected = 0;
    private static volatile double timeMues = 0;
    private static volatile double t = 0;
    private static volatile double valorAmp = 0;
    

    private static int stateDo0 = 0;
    private static int stateDo1 = 0;
    private static int stateDo2 = 0;
    private static int stateDo3 = 0;

    private static int prevStateDo0 = 0;
    private static int prevStateDo1 = 0;
    private static int prevStateDo2 = 0;
    private static int prevStateDo3 = 0;

    private static int_proceso_vars processVar;
    private static List<int_proceso_refs> procesoRef;
    private static int_proceso proceso;
    private static int_usuarios_proceso regisUsuarioProceso;

    public static void main(String args[]){
        System.out.println("Iniciando servicio.....");
        executorService = Executors.newScheduledThreadPool(1);
        
        System.out.println("Iniciando SerialPort.....");  
        arduino = new Controller(COMM_PORT);
        
        waitForOpenSerialPort();
        System.out.println("SerialPort abierto.....");
        
        arduino.addDataListener();
        
        setAPI();
        
        hoy = LocalDate.now();
        
        horaInicio = null;
        horaFin = null;

        proceso = labApi.proceso.getProcess(3);
        
        //PRIMERA LECTURA
        //LECTURA DE LA TABLA USUARIOS_PROCESO
        System.out.println("Obteniendo ultimo usuario.....");
        getLastUserSession();
        
        System.out.println("Esperando nuevo usuario.....");
        waitForNewUserSession();
        
        outputsDRunnable = new OutputsDRunnable();
        inputsADRunnable = new InputsADRunnable();

        executorService.scheduleAtFixedRate(
            outputsDRunnable, 
            0, 
            200, 
            TimeUnit.MILLISECONDS
        );
        
        executorService.scheduleAtFixedRate(
            inputsADRunnable, 
            100, 
            200, 
            TimeUnit.MILLISECONDS
        );
    }

    private static synchronized void getLastUserSession() {
        regisUsuarioProceso = labApi.usuariosProcesos.getLastRecord();
        while (regisUsuarioProceso == null){
            regisUsuarioProceso = labApi.usuariosProcesos.getLastRecord();
        }
        
        horaInicio = regisUsuarioProceso.getStartTime();
        horaFin = regisUsuarioProceso.getEndTime();
        System.out.println(regisUsuarioProceso);
    }

    public static synchronized void waitForNewUserSession() {
        while (horaInicio.compareTo(horaFin)!= 0){
            //USUARIOS_PROCESOS
            regisUsuarioProceso = labApi.usuariosProcesos.getLastRecord();
            horaInicio = regisUsuarioProceso.getStartTime();
            horaFin = regisUsuarioProceso.getEndTime();
        }
        System.out.println("New login detected: ");
        System.out.println(regisUsuarioProceso);
    }

    private static void waitForOpenSerialPort() {
        while(!arduino.isOpen()){
            arduino.openPort();
        }
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
    
    private static synchronized void detectCloseSession(){
        try {
            labApi.database.closeConnection();
            arduino.closePort();
            if(!labApi.database.isConnectionValid()){
                System.exit(0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LinkInterfacesLab2023.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Runnable que controla las entradas analogas y digitales de Arduino
     */    
    private static class InputsADRunnable implements Runnable{

        @Override
        public void run() {
            setAPI();
            
            //VUELVE A LEER LA BASE DE DATOS POR SI HAY UN CAMBIO TANTO EN LAS SALIDAS DIGI Y LAS ENTRADAS A/D
            processVar = labApi.procesoVars.getProcessVars(true);
            proceso = labApi.proceso.getProcess(3);
            
            //LECTURA DE LA TABLA PROCESOS_VARS
            if (processVar != null) {
                señalSelected = processVar.getName(); 
                iDseñalSelected = processVar.getId();
                timeMues = proceso.getSampleTime();
            } else {
                señalSelected = "No hay registros con flag=true";
                iDseñalSelected = 0;
                System.out.println(señalSelected);
                return;
            }
            
            //COMPROBAR SI SE CAMBIO LA SEÑAL SELECCIONADA
            if(!señalTemp.equals(señalSelected)){
                t = 0;
                System.out.println("IS ANALOG/DIGIAL");
                
                //ENVIAR SEÑALES DE ENTRADA
                System.out.println("T"+timeMues+","+señalSelected);
                arduino.enviarTexto("T"+timeMues+","+señalSelected);
                
                señalTemp = señalSelected;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LinkInterfacesLab2023.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
                return;
            }
            
            
            if(arduino.newAnalogData || arduino.newDigitalByte){
                System.out.println("NEW DATA");

                switch (señalSelected.charAt(0)) {
                    case 'A':
                        valorAmp = (double) arduino.readAnalog;
                        break;
                    case 'D':
                        valorAmp = (double) arduino.readDigital;
                        break;
                    default:
                        return;
                }
                
                int_proceso_vars_data varsData = new int_proceso_vars_data(
                        iDseñalSelected, 
                        valorAmp, 
                        t, 
                        Date.valueOf(hoy),
                        Time.valueOf(LocalTime.now())
                );

                int insertSeñal = labApi.procesoVarsData
                        .insertVarData(
                                varsData.getProcessVarId(), 
                                varsData.getValue(), 
                                varsData.getTime(), 
                                varsData.getDate(), 
                                varsData.getClockTime()
                        );    
                if (insertSeñal > 0) {
                    t += timeMues;
                    System.out.println("Dato enviado.");
                    System.out.println(varsData.toString());
                }

                if(señalSelected.charAt(0)=='A'){
                    arduino.newAnalogData = false;
                } else if(señalSelected.charAt(0)=='D'){
                    arduino.newDigitalByte = false;
                }
                
            }
        }
    }

    /**
     * Runnable que controla las salidas digitales de Arduino.
     * En este caso un valor de 1 o true represen un LED
     * encendido.
     */
    private static class OutputsDRunnable implements Runnable{

        @Override
        public void run() {
            setAPI();
            regisUsuarioProceso = labApi.usuariosProcesos.getLastRecord();
            
            horaInicio = regisUsuarioProceso.getStartTime();
            horaFin = regisUsuarioProceso.getEndTime();  
            
            if (horaInicio.compareTo(horaFin)!= 0){
                detectCloseSession();
                return;
            } 

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
            
            // Actualizar valores
            prevStateDo0 = stateDo0;
            prevStateDo1 = stateDo1;
            prevStateDo2 = stateDo2;
            prevStateDo3 = stateDo3;
        }
    }
}
