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

/**
 * Implementación del DAO (Data Access Object) para la entidad int_proceso.
 * Proporciona métodos para interactuar con la base de datos respecto a los procesos.
 */
public class int_procesoDAOImpl implements int_procesoDAO {
    private static final String ID = "id";
    private static final String INT_PROCESO_TIPO_ID = "int_proceso_tipo_id";
    private static final String NOMBRE = "nombre";
    private static final String DESCRIPCION = "descripcion";
    private static final String TIEMPO_MUESTREO = "tiempo_muestreo";
    private static final String ARCHIVO_ESPECIFICACIONES = "archivo_especificaciones";
    private static final String ARCHIVO_MANUAL = "archivo_manual";
    
//    private static final String GET_ALL_PROCESS_ 
//            = "SELECT id, int_proceso_tipo_id, nombre, descripcion, tiempo_muestreo, archivo_especificaciones, archivo_manual "
//            + "FROM int_proceso";

    private static final String GET_PROCESS_A 
            = "SELECT id, int_proceso_tipo_id, nombre, descripcion, tiempo_muestreo, archivo_especificaciones, archivo_manual "
            + "FROM int_proceso "
            + "WHERE id=?";    
    private static final String GET_PROCESS_B 
            = "SELECT id, int_proceso_tipo_id, nombre, descripcion, tiempo_muestreo, archivo_especificaciones, archivo_manual "
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
    
    /**
     * Constructor para crear una instancia del DAO con una conexión a la base de datos.
     *
     * @param connection La conexión a la base de datos.
     */
    
    public int_procesoDAOImpl(Connection connection) {
        this.connection = connection;
        
    }

//    @Override
//    public List<int_proceso> getAllProcess() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    
    /**
     * Recupera un proceso por su identificador.
     *
     * @param id El identificador del proceso.
     * @return El proceso si se encuentra, null en caso contrario.
     */
    @Override
    public int_proceso getProcess(int id) {
        // Implementación del método...
        // Código para recuperar un proceso por su ID.
        int_proceso process = null;
        try {
            System.out.println("PREPARE STATEMENT = " + GET_PROCESS_A);
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_PROCESS_A);
            System.out.println("ID = " + id);
            statement.setInt(1, id);
            System.out.println("GET RESULT");
            
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                process = new int_proceso(
                    rs.getInt(INT_PROCESO_TIPO_ID),
                    rs.getString(NOMBRE)                    
                );
                process.setDescripcion(rs.getString(DESCRIPCION));
                process.setSampleTime(rs.getDouble(TIEMPO_MUESTREO));
                process.setArchivo_especificacion(rs.getBlob(ARCHIVO_ESPECIFICACIONES));
                process.setArchivo_manual(rs.getBlob(ARCHIVO_MANUAL));

                process.setId(rs.getInt(ID));
                System.out.println(process.toString());
                break;
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(int_procesoDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        this.currentProcess = process;
        return process;  
    }
    /**
     * Recupera un proceso por su nombre.
     *
     * @param name El nombre del proceso.
     * @return El proceso si se encuentra, null en caso contrario.
     */
    @Override
    public int_proceso getProcess(String name) {
        // Implementación del método...
        // Código para recuperar un proceso por su nombre
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

        /**
     * Inserta un nuevo proceso en la base de datos.
     *
     * @param processTypeId Identificador del tipo de proceso.
     * @param name Nombre del proceso.
     * @param description Descripción del proceso.
     * @param sampleTime Tiempo de muestreo del proceso.
     * @param textFile Archivo de especificaciones del proceso.
     * @param manualFile Archivo manual del proceso.
     * @return El número de filas afectadas.
     */
    
    @Override
    public int insertProcess(int processTypeId, String name, String description, 
            double sampleTime, Blob textFile, Blob manualFile) {
        // Implementación del método...
        // Código para insertar un nuevo proceso.
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

    /**
     * Actualiza un proceso existente en la base de datos.
     *
     * @param process2Update El proceso con los datos actualizados.
     * @return El número de filas afectadas.
     */
    @Override
    public int updateProcess(int_proceso process2Update) {
        // Implementación del método...
        // Código para actualizar un proceso existente.
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
            statement.setInt(7, process2Update.getId());
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
    /**
     * Elimina un proceso de la base de datos por su objeto.
     *
     * @param processDB El proceso a eliminar.
     * @return El número de filas afectadas.
     */
    @Override
    public int deleteProcess(int_proceso processDB) {
        // Implementación del método...
        // Código para eliminar un proceso por su objeto.
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
    /**
     * Elimina un proceso de la base de datos por su identificador de usuario.
     *
     * @param userID El identificador del usuario del proceso a eliminar.
     * @return El número de filas afectadas.
     */
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

    public int_proceso getCurrentProcess() {
        return currentProcess;
    }
    
    
    
}
