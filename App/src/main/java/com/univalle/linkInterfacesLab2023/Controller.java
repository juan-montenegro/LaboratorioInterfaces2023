/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.linkInterfacesLab2023;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camilo
 */
public class Controller implements SerialPortDataListener
{
	private final SerialPort puertoSerie;
	private byte[] myNewData;

        private static int estadoFSM = 0;

        static final byte HEADER1 = 0x7a;
        static final byte HEADER2 = 0x7b;
        static final byte HEADER3 = 0x7c;
        
        byte B1;
        byte B2;
        
        public int readAnalog = 0;
        public int readDigital = 0;

        public boolean newDigitalByte = false;
        public boolean newAnalogData = false;
        public boolean newTime = false;
        
    public Controller(String COM) {
        puertoSerie = SerialPort.getCommPort(COM);        
        init();
    }

    private void init() {
        puertoSerie.setComPortParameters(
                9600, 
                8, 
                SerialPort.ONE_STOP_BIT, 
                SerialPort.NO_PARITY
        
        );
        puertoSerie.openPort();
        puertoSerie.getInputStream();
    }

    public void addDataListener() {
        //Registrar eventos
        puertoSerie.addDataListener(this);
    }
    

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED; 
    }

    @Override
    public void serialEvent(SerialPortEvent spe) {
        if (spe.getEventType() == SerialPort.LISTENING_EVENT_DATA_RECEIVED)
        {
            myNewData = spe.getReceivedData();
            
            for (int i = 0; i < myNewData.length; ++i){
                fsmRX(myNewData[i]);                
            }

        } 
    }
   
    public SerialPort getPuertoSerie() {
        return puertoSerie;
    }
    
    public boolean isOpen(){
        return puertoSerie.isOpen();
    } 
    
    public void openPort(){
        this.puertoSerie.openPort();
    }
    
    public boolean closePort() {
        return puertoSerie.closePort();
    }
    
    public void fsmRX(byte trama){
        switch(estadoFSM){
            case 0:
                if(trama == HEADER1){
                    estadoFSM = 1;
                }
                break;
            case 1:
                if(trama == HEADER2){
                    estadoFSM = 2;
                }
                break;
            case 2:
                B1 = trama;
                estadoFSM = 3; 
                break;
            case 3:
                if(trama == HEADER3){
                    estadoFSM = 0;
                    readDigital = B1;
                    newDigitalByte = true;
                    System.out.println("readDigital: " + readDigital);
                }
                else{
                    B2 = trama;
                    estadoFSM = 4;
                }   
                    
                break;
                
            case 4:
                if(trama == HEADER3){
                    estadoFSM = 0;
                    readAnalog = 0; 
                    readAnalog = (B1)<< 8 | ((B2) & 0xFF) ; 
                    newAnalogData = true;
                    newTime = true;
                    
                    System.out.println("readAnalog: " + readAnalog);
                }
                break;
          }

    }
    
    public String byteArrayToString(byte[] byteArray) {
        return new String(byteArray);
    }

    
    public static float byteArrayToFloat(byte[] bytes) {
        int intBits = 
        bytes[0] << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);
        return Float.intBitsToFloat(intBits);  
    }
    
    
    
    public void enviarTexto(String data) {
        if (!puertoSerie.isOpen()) {
            System.out.println("El puerto COM no está abierto.");
            return;
        }
        OutputStream out = puertoSerie.getOutputStream();
        try {
            // Envía la cadena como un array de bytes
            out.write(data.getBytes()); 
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
    }
    
}
