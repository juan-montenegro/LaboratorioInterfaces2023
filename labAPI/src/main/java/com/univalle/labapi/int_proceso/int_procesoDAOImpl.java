/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class int_procesoDAOImpl implements int_procesoDAO {
    private static final String ID = "id";
    private static final String INT_PROCESO_TIPO_ID = "int_proceso_tipo_id";
    private static final String NOMBRE = "nombre";
    private static final String DESCRIPCION = "descripcion";
    private static final String TIEMPO_MUESTREO = "tiempo_muestreo";
    private static final String ARCHIVO_ESPECIFICACIONES = "archivo_especificaciones";
    private static final String ARCHIVO_MANUAL = "archivo_manual";
    
//    private static final String GET_ALL_PROCESS_ 
//            = "SELECT id, int_proceso_tipo_id, nombre, descripcion, tiempo_muestreo, archivo_especificacion, archivo_manual "
//            + "FROM int_proceso";
    private static final String GET_PROCESS_A 
            = "SELECT id, int_proceso_tipo_id, nombre, descripcion, tiempo_muestreo, archivo_especificacion, archivo_manual "
            + "FROM int_proceso "
            + "WHERE id=?";    
    private static final String GET_PROCESS_B 
            = "SELECT id, int_proceso_id, nombre, descripcion, max_2, min, flag "
            + "FROM int_proceso "
            + "WHERE nombre=?";
    private static final String INSERT_PROCESS
            = "INSERT INTO int_proceso "
            + "SET int_proceso_tipo_id=?, nombre=?, descripcion=?, tiempo_muestreo=?, archivo_especificaciones=?, archivo_manual=?";
    private static final String UPDATE_PROCESS 
            = "UPDATE int_proceso "
            + "SET int_proceso_tipo_id=?, nombre=?, descripcion=?, tiempo_muestreo=?, archivo_especificaciones=?, archivo_manual=? "
            + "WHERE id=?";        
    private static final String DELETE_PROCESS 
            = "DELETE FROM int_proceso "
            + "WHERE id=?";
    
    private Connection connection = null;
    private int_proceso currentProcess;

    public int_procesoDAOImpl(Connection connection) {
        this.connection = connection;
        
    }
    
    
    

//    @Override
//    public List<int_proceso> getAllProcess() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public int_proceso getProcess(int id) {
        int_proceso process = null;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_PROCESS_A);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso tempRef = new int_proceso(
                        rs.getInt(INT_PROCESO_TIPO_ID),
                        rs.getString(NOMBRE)
                        
                );
                tempRef.setId(rs.getInt(ID));
                process = tempRef;
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_procesoDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return process;  
    }

    @Override
    public int_proceso getProcess(String name) {
        int_proceso process = null;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_PROCESS_B);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso tempRef = new int_proceso(
                        rs.getInt(INT_PROCESO_TIPO_ID),
                        rs.getString(NOMBRE)
                        
                );
                tempRef.setId(rs.getInt(ID));
                process = tempRef;
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_procesoDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return process; 
    }

    @Override
    public int insertProcess(int processTypeId, String name, String description, 
            double sampleTime, Blob textFile, Blob manualFile) {
        int resRows = 0;

        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(INSERT_PROCESS);
            statement.setInt(1, processTypeId);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setDouble(4, sampleTime);
            statement.setBlob(5, textFile);
            statement.setBlob(6, manualFile);
            int_proceso tempRef = new int_proceso(
                    processTypeId,
                    name
            );
            this.currentProcess = tempRef;
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_procesoDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resRows;
    }

    @Override
    public int updateProcess(int_proceso process2Update) {
        int resRows = 0;

        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(UPDATE_PROCESS);
            statement.setInt(1, process2Update.getProcessTypeId());
            statement.setString(2, process2Update.getNombre());
            statement.setString(3, process2Update.getDescripcion());
            statement.setDouble(4, process2Update.getSampleTime());
            statement.setBlob(5, process2Update.getArchivo_especificacion());
            statement.setBlob(6, process2Update.getArchivo_manual());
            int_proceso tempRef = new int_proceso(
                    process2Update.getProcessTypeId(),
                    process2Update.getNombre()
            );
            this.currentProcess = tempRef;
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_procesoDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resRows;        
    }

    @Override
    public int deleteProcess(int_proceso processDB) {
        int resRows = 0;

        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(DELETE_PROCESS);
            statement.setInt(1, processDB.getProcessTypeId());
            
            this.currentProcess = null;
            
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_procesoDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resRows;  
    }

    @Override
    public int deleteProcess(int userID) {
        int resRows = 0;

        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(DELETE_PROCESS);
            statement.setInt(1, userID);
            
            this.currentProcess = null;
            
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_procesoDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resRows;  
    }
    
}
