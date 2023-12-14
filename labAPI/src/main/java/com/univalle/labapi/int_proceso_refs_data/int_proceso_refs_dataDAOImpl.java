/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso_refs_data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementación del DAO (Data Access Object) para la entidad int_proceso_refs_data.
 * Proporciona métodos para interactuar con la base de datos en relación con los datos de las referencias de procesos.
 */
public class int_proceso_refs_dataDAOImpl implements int_proceso_refs_dataDAO {
    // Constantes para las columnas y consultas SQL.
    private static final String ID = "id";
    private static final String INT_PROCESO_REFS_ID = "int_proceso_refs_id";
    private static final String VALOR = "valor";
    private static final String TIEMPO = "tiempo";
    private static final String FECHA = "fecha";
    private static final String HORA = "hora";
    
//    private static final String GET_ALL_PROCESS_REFS_DATA 
//            = "SELECT id, int_proceso_refs_id, valor, tiempo, fecha, hora "
//            + "FROM int_proceso_refs_data";
    private static final String GET_PROCESS_REFS_DATA_A
            = "SELECT id, int_proceso_refs_id, valor, tiempo, fecha, hora "
            + "FROM int_proceso_refs_data "
            + "WHERE id=?";    
    private static final String GET_PROCESS_REFS_DATA_B 
            = "SELECT id, int_proceso_refs_id, valor, tiempo, fecha, hora "
            + "FROM int_proceso_refs_data "
            + "WHERE int_proceso_refs_id=?";    
    private static final String INSERT_PROCESS_REFS_DATA 
            = "INSERT INTO int_proceso_refs_data "
            + "SET int_proceso_refs_id=?, valor=?, tiempo=?, fecha=?, hora=?";
    private static final String UPDATE_PROCESS_REFS_DATA 
            = "UPDATE int_proceso_refs_data "
            + "SET int_proceso_refs_id=?, valor=?, tiempo=?, fecha=?, hora=? "
            + "WHERE id=?";        
    private static final String DELETE_PROCESS_REFS_DATA 
            = "DELETE FROM int_proceso_refs_data "
            + "WHERE id=?";
    
    private Connection connection = null;
    private final List<int_proceso_refs_data> processRefsData;

    /**
     * Constructor para inicializar la conexión a la base de datos y la lista de datos de referencias de procesos.
     *
     * @param connection La conexión a la base de datos.
     */
    public int_proceso_refs_dataDAOImpl(Connection connection) {
        this.connection = connection;
        this.processRefsData = new ArrayList<>();
    }
    /**
    * Recupera todos los datos de referencia de proceso para un ID de proceso específico.
    * 
    * @param processId El identificador del proceso para el cual se están buscando los datos de referencia.
    * @return Una lista de objetos int_proceso_refs_data que representan los datos de referencia para el proceso especificado.
    */
    @Override
    public List<int_proceso_refs_data> getAllRefDataForProcess(int processId) {
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_PROCESS_REFS_DATA_B);
            statement.setInt(1, processId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso_refs_data tempRef = new int_proceso_refs_data(
                        rs.getInt(INT_PROCESO_REFS_ID), 
                        rs.getDouble(VALOR),
                        rs.getDouble(TIEMPO), 
                        rs.getDate(FECHA),
                        rs.getTime(HORA)
                );
                tempRef.setId(rs.getInt(ID));
                this.processRefsData.add(tempRef);
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refs_dataDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return this.processRefsData;
    }

    /**
     * Obtiene los datos de referencia de un proceso específico por su identificador.
     * 
     * @param processId El identificador del proceso.
     * @return Un objeto int_proceso_refs_data que representa los datos de referencia del proceso o null si no se encuentra.
     */
    @Override
    public int_proceso_refs_data getRefDataForProcess(int processId) {
        int_proceso_refs_data processRef = null;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_PROCESS_REFS_DATA_B);
            statement.setInt(1, processId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso_refs_data tempRef = new int_proceso_refs_data(
                        rs.getInt(INT_PROCESO_REFS_ID), 
                        rs.getDouble(VALOR),
                        rs.getDouble(TIEMPO), 
                        rs.getDate(FECHA),
                        rs.getTime(HORA)
                );
                tempRef.setId(rs.getInt(ID));
                processRef = tempRef;
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refs_dataDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return processRef;
    }

    /**
     * Obtiene los datos de referencia de un proceso específico por su ID.
     * 
     * @param id El identificador de los datos de referencia del proceso.
     * @return Un objeto int_proceso_refs_data que representa los datos de referencia o null si no se encuentra.
     */
    @Override
    public int_proceso_refs_data getRefDataForId(int id) {
        int_proceso_refs_data processRef = null;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_PROCESS_REFS_DATA_A);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso_refs_data tempRef = new int_proceso_refs_data(
                        rs.getInt(INT_PROCESO_REFS_ID), 
                        rs.getDouble(VALOR),
                        rs.getDouble(TIEMPO), 
                        rs.getDate(FECHA),
                        rs.getTime(HORA)
                );
                tempRef.setId(rs.getInt(ID));
                processRef = tempRef;
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refs_dataDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return processRef;
    }


    /**
     * Inserta nuevos datos de referencia de proceso en la base de datos.
     * 
     * @param processRefId El identificador del proceso.
     * @param value El valor asociado con los datos de referencia.
     * @param time El tiempo asociado con los datos de referencia.
     * @param date La fecha de los datos.
     * @param uploadTime La hora de los datos.
     * @return El número de filas afectadas por la operación.
     */
    @Override
    public int insertRefData(int processRefId, double value, double time, 
            Date date, Time uploadTime) {
        int resRows = 0;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(INSERT_PROCESS_REFS_DATA);
            statement.setInt(1, processRefId);
            statement.setDouble(2, value);
            statement.setDouble(3, time);
            statement.setDate(4, date);
            statement.setTime(5, uploadTime);
            int_proceso_refs_data tempRef = new int_proceso_refs_data(
                    processRefId, value, time, 
                    date, uploadTime
            );
            this.processRefsData.add(tempRef);
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refs_dataDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resRows;
        
    }
    /**
     * Actualiza los datos de referencia de un proceso existente en la base de datos.
     * 
     * @param refData Los datos de referencia del proceso a actualizar.
     * @return El número de filas afectadas por la operación.
     */
    @Override
    public int updateRefData(int_proceso_refs_data refData) {
        int resRows = 0;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(UPDATE_PROCESS_REFS_DATA);
            statement.setInt(1, refData.getProcessRefId());
            statement.setDouble(2, refData.getValue());
            statement.setDouble(3, refData.getTime());
            statement.setDate(4, refData.getDate());
            statement.setTime(5, refData.getClockTime());
            statement.setInt(6, refData.getId());
            this.processRefsData.add(refData);
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refs_dataDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resRows;
    }
    /**
     * Elimina los datos de referencia de un proceso específico en la base de datos.
     * 
     * @param refData Los datos de referencia del proceso a eliminar.
     * @return El número de filas afectadas por la operación.
     */
    @Override
    public int deleteRefData(int_proceso_refs_data refData) {
        int resRows = 0;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(DELETE_PROCESS_REFS_DATA);
            statement.setInt(1, refData.getId());
            
            int idRef = this.processRefsData.indexOf(refData);
            this.processRefsData.remove(idRef);
            
            
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refs_dataDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return resRows;
    }

    /**
     * Elimina los datos de referencia de un proceso específico por su ID.
     * 
     * @param id El identificador de los datos de referencia del proceso a eliminar.
     * @return El número de filas afectadas por la operación.
     */
    @Override
    public int deleteRefData(int id) {
        int resRows = 0;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(DELETE_PROCESS_REFS_DATA);
            statement.setInt(1, id);
            
            Iterator i = this.processRefsData.iterator();
            int varIndex = 0;
            while (i.hasNext()) {
                int idVarDB = ((int_proceso_refs_data) i.next()).getId();
                if (idVarDB == id) {
                    this.processRefsData.remove(varIndex);
                    break;
                }
                varIndex++;
            }
            
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refs_dataDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return resRows;
    }
    
    
}
