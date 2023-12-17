/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso_vars_data;

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
 * Implementación del DAO (Data Access Object) para la entidad int_proceso_vars_data.
 * Proporciona métodos para interactuar con la base de datos en relación con los datos de las variables de procesos.
 */
public class int_proceso_vars_dataDAOImpl implements int_proceso_vars_dataDAO {
    // Constantes para las columnas y consultas SQL.
    private static final String ID = "id";
    private static final String INT_PROCESO_VARS_ID = "int_proceso_vars_id";
    private static final String VALOR = "valor";
    private static final String TIEMPO = "tiempo";
    private static final String FECHA = "fecha";
    private static final String HORA = "hora";
    
    // Consultas SQL predefinidas.
    private static final String GET_PROCESS_VARS_DATA_A
            = "SELECT id, int_proceso_vars_id, valor, tiempo, fecha, hora "
            + "FROM int_proceso_vars_data "
            + "WHERE id=?";    
    private static final String GET_PROCESS_VARS_DATA_B 
            = "SELECT id, int_proceso_vars_id, valor, tiempo, fecha, hora "
            + "FROM int_proceso_vars_data "
            + "WHERE int_proceso_vars_id=?";    
    private static final String INSERT_PROCESS_VARS_DATA 
            = "INSERT INTO int_proceso_vars_data "
            + "SET int_proceso_vars_id=?, valor=?, tiempo=?, fecha=?, hora=?";
    private static final String UPDATE_PROCESS_VARS_DATA 
            = "UPDATE int_proceso_vars_data "
            + "SET int_proceso_id=?, valor=?, tiempo=?, fecha=?, hora=? "
            + "WHERE id=?";        
    private static final String DELETE_PROCESS_VARS_DATA 
            = "DELETE FROM int_proceso_vars_data "
            + "WHERE id=?";
    
    private static final String GET_LAST_PROCESS
            = "SELECT * FROM int_proceso_vars_data ORDER BY Id DESC LIMIT 1";
    
    private Connection connection = null;
    private final List<int_proceso_vars_data> processVarsData;

    
    /**
     * Constructor que inicializa la conexión a la base de datos y la lista de datos de variables de proceso.
     *
     * @param connection La conexión a la base de datos.
     */
    public int_proceso_vars_dataDAOImpl(Connection connection) {
        this.connection = connection;
        this.processVarsData = new ArrayList<>();
    }
    

    /**
    * Obtiene una lista de todos los datos de variables de proceso relacionados con un proceso específico desde la base de datos.
    *
    * @param processId El ID del proceso para el cual se desean los datos de las variables de proceso.
    * @return Una lista de objetos int_proceso_vars_data relacionados con el proceso especificado.
    */
    @Override
    public List<int_proceso_vars_data> getAllVarDataForProcess(int processId) {
        this.processVarsData.clear();
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_PROCESS_VARS_DATA_B);
            statement.setInt(1, processId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso_vars_data tempVar = new int_proceso_vars_data(
                        rs.getInt(INT_PROCESO_VARS_ID), 
                        rs.getDouble(VALOR),
                        rs.getDouble(TIEMPO), 
                        rs.getDate(FECHA),
                        rs.getTime(HORA)
                );
                tempVar.setId(rs.getInt(ID));
                this.processVarsData.add(tempVar);
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_vars_dataDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return this.processVarsData;
    }
    /**
     * Obtiene un objeto int_proceso_vars_data específico relacionado con un proceso específico desde la base de datos.
     *
     * @param processId El ID del proceso para el cual se desea el dato de variable de proceso.
     * @return El objeto int_proceso_vars_data relacionado con el proceso y su ID, o null si no se encuentra.
     */
    @Override
    public int_proceso_vars_data getVarDataForProcess(int processId) {
       int_proceso_vars_data varsData = null;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_PROCESS_VARS_DATA_B);
            statement.setInt(1, processId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                varsData = new int_proceso_vars_data(
                        rs.getInt(INT_PROCESO_VARS_ID), 
                        rs.getDouble(VALOR),
                        rs.getDouble(TIEMPO), 
                        rs.getDate(FECHA),
                        rs.getTime(HORA)
                );
                varsData.setId(rs.getInt(ID));
                this.processVarsData.add(varsData);
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_vars_dataDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return varsData;
    }
    /**
     * Obtiene un objeto int_proceso_vars_data específico por su ID desde la base de datos.
     *
     * @param id El ID del objeto int_proceso_vars_data que se desea obtener.
     * @return El objeto int_proceso_vars_data con el ID especificado, o null si no se encuentra.
     */
    @Override
    public int_proceso_vars_data getVarDataForId(int id) {
        int_proceso_vars_data processVar = null;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_PROCESS_VARS_DATA_A);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso_vars_data tempVar = new int_proceso_vars_data(
                        rs.getInt(INT_PROCESO_VARS_ID), 
                        rs.getDouble(VALOR),
                        rs.getDouble(TIEMPO), 
                        rs.getDate(FECHA),
                        rs.getTime(HORA)
                );
                tempVar.setId(rs.getInt(ID));
                processVar = tempVar;
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_vars_dataDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return processVar;
    }
    
    @Override
    public int_proceso_vars_data getLastProcess() {
        int_proceso_vars_data processVar = null;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_LAST_PROCESS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                processVar = new int_proceso_vars_data(
                        rs.getInt(INT_PROCESO_VARS_ID), 
                        rs.getDouble(VALOR),
                        rs.getDouble(TIEMPO), 
                        rs.getDate(FECHA),
                        rs.getTime(HORA)
                );
                processVar.setId(rs.getInt(ID));
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_vars_dataDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return processVar;
    }
    
    /**
     * Inserta nuevos datos de variable de proceso en la base de datos y los agrega a la lista interna.
     *
     * @param processVarId El ID de la variable de proceso a la que se relacionarán los datos.
     * @param value        El valor de la variable de proceso.
     * @param time         El tiempo relacionado con la variable de proceso.
     * @param date         La fecha relacionada con la variable de proceso.
     * @param uploadTime   La hora de carga relacionada con la variable de proceso.
     * @return El número de filas afectadas en la base de datos (debería ser 1 si la inserción tiene éxito).
     */
    @Override
    public int insertVarData(int processVarId, double value, double time, 
            Date date, Time uploadTime) {
        int resRows = 0;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(INSERT_PROCESS_VARS_DATA);
            statement.setInt(1, processVarId);
            statement.setDouble(2, value);
            statement.setDouble(3, time);
            statement.setDate(4, date);
            statement.setTime(5, uploadTime);
            int_proceso_vars_data tempVar = new int_proceso_vars_data(
                    processVarId, value, time, 
                    date, uploadTime
            );
            this.processVarsData.add(tempVar);
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_vars_dataDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resRows;
        
    }
    /**
     * Actualiza un objeto int_proceso_vars_data existente en la base de datos y en la lista interna.
     *
     * @param varData El objeto int_proceso_vars_data que se desea actualizar.
     * @return El número de filas afectadas en la base de datos (debería ser 1 si la actualización tiene éxito).
     */
    @Override
    public int updateVarData(int_proceso_vars_data varData) {
        int resRows = 0;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(UPDATE_PROCESS_VARS_DATA);
            statement.setInt(1, varData.getProcessVarId());
            statement.setDouble(2, varData.getValue());
            statement.setDouble(3, varData.getTime());
            statement.setDate(4, varData.getDate());
            statement.setTime(5, varData.getClockTime());
            statement.setInt(6, varData.getId());
            this.processVarsData.add(varData);
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_vars_dataDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resRows;
    }

    /**
     * Elimina un objeto int_proceso_vars_data existente de la base de datos y lo elimina de la lista interna.
     *
     * @param varData El objeto int_proceso_vars_data que se desea eliminar.
     * @return El número de filas afectadas en la base de datos (debería ser 1 si la eliminación tiene éxito).
     */
    @Override
    public int deleteVarData(int_proceso_vars_data varData) {
        int resRows = 0;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(DELETE_PROCESS_VARS_DATA);
            statement.setInt(1, varData.getId());
            
            int idVar = this.processVarsData.indexOf(varData);
            this.processVarsData.remove(idVar);
            
            
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_vars_dataDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return resRows;
    }
    /**
     * Elimina un objeto int_proceso_vars_data por su ID de la base de datos y lo elimina de la lista interna.
     *
     * @param id El ID del objeto int_proceso_vars_data que se desea eliminar.
     * @return El número de filas afectadas en la base de datos (debería ser 1 si la eliminación tiene éxito).
     */
    @Override
    public int deleteVarData(int id) {
        int resRows = 0;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(DELETE_PROCESS_VARS_DATA);
            statement.setInt(1, id);
            
            Iterator i = this.processVarsData.iterator();
            int varIndex = 0;
            while (i.hasNext()) {
                int idVarDB = ((int_proceso_vars_data) i.next()).getId();
                if (idVarDB == id) {
                    this.processVarsData.remove(varIndex);
                    break;
                }
                varIndex++;
            }
            
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_vars_dataDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return resRows;
    }
    
}
