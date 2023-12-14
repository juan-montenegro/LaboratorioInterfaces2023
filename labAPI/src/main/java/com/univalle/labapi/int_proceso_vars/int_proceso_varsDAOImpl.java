/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso_vars;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementación del DAO (Data Access Object) para la entidad int_proceso_vars.
 * Proporciona métodos para interactuar con la base de datos en relación con las variables de procesos.
 */
public class int_proceso_varsDAOImpl implements int_proceso_varsDAO {
    // Constantes para las columnas y consultas SQL.
    private static final String ID = "id";
    private static final String INT_PROCESO_ID = "int_proceso_id";
    private static final String NOMBRE = "nombre";
    private static final String DESCRIPCION = "descripcion";
    private static final String MAX_2 = "max_2";
    private static final String MIN = "min";
    private static final String FLAG = "flag";
    
    // Consultas SQL predefinidas. 
    private static final String GET_ALL_PROCESS_VARS 
            = "SELECT id, int_proceso_id, nombre, descripcion, max_2, min, flag "
            + "FROM int_proceso_vars";
    private static final String GET_PROCESS_VARS_A 
            = "SELECT id, int_proceso_id, nombre, descripcion, max_2, min, flag "
            + "FROM int_proceso_vars WHERE id=?";    
    private static final String GET_PROCESS_VARS_B 
            = "SELECT id, int_proceso_id, nombre, descripcion, max_2, min, flag "
            + "FROM int_proceso_vars WHERE nombre=?";    
    private static final String INSERT_PROCESS_VARS 
            = "INSERT INTO int_proceso_vars "
            + "SET int_proceso_id=?, nombre=?, descripcion=?, max_2=?, min=?, flag=?";
    private static final String UPDATE_PROCESS_VARS 
            = "UPDATE int_proceso_vars "
            + "SET int_proceso_id=?, nombre=?, descripcion=?, max_2=?, min=? , flag=? "
            + "WHERE id=?";        
    private static final String DELETE_PROCESS_VARS 
            = "DELETE FROM int_proceso_vars "
            + "WHERE id=?";
    private static final String GET_PROCESS_VARS_FLAG 
            = "SELECT id, int_proceso_id, nombre, descripcion, max_2, min, flag "
            + "FROM int_proceso_vars WHERE flag=?";  
    
    private Connection connection = null;
    private final List<int_proceso_vars> procesosVars;
    
    /**
     * Constructor que inicializa la conexión a la base de datos y carga las variables de proceso existentes.
     *
     * @param connection La conexión a la base de datos.
     */
    public int_proceso_varsDAOImpl(Connection connection) {
        this.connection = connection;
        this.procesosVars = new ArrayList<>();
        // Carga inicial de las variables de proceso.
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_ALL_PROCESS_VARS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso_vars processVar = new int_proceso_vars(
                        rs.getInt(INT_PROCESO_ID), 
                        rs.getString(NOMBRE),
                        rs.getString(DESCRIPCION), 
                        rs.getInt(MAX_2),
                        rs.getInt(MIN),
                        rs.getBoolean(FLAG)
                );
                this.procesosVars.add(processVar);
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_varsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }        
    }
    /**
     * Obtiene una lista de todos los objetos int_proceso_vars desde la base de datos.
     * Borra la lista existente de variables de proceso y la llena con los datos recuperados.
     *
     * @return Una lista de todos los objetos int_proceso_vars.
     */
    @Override
    public List<int_proceso_vars> getAllProcessVars() {
        this.procesosVars.clear();
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_ALL_PROCESS_VARS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso_vars processVar = new int_proceso_vars(
                        rs.getInt(INT_PROCESO_ID), 
                        rs.getString(NOMBRE),
                        rs.getString(DESCRIPCION), 
                        rs.getInt(MAX_2),
                        rs.getInt(MIN),
                        rs.getBoolean(FLAG)
                );
                processVar.setId(rs.getInt(ID));
                this.procesosVars.add(processVar);
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_varsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return this.procesosVars;
    }
    /**
     * Obtiene un objeto int_proceso_vars específico por su ID desde la base de datos.
     *
     * @param varId El ID del objeto int_proceso_vars que se desea obtener.
     * @return El objeto int_proceso_vars con el ID especificado, o null si no se encuentra.
     */
    @Override
    public int_proceso_vars getProcessVar(int varId) {
        int_proceso_vars processVar = null;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_PROCESS_VARS_A);
            statement.setInt(1, varId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso_vars tempVars = new int_proceso_vars(
                        rs.getInt(INT_PROCESO_ID), 
                        rs.getString(NOMBRE),
                        rs.getString(DESCRIPCION), 
                        rs.getInt(MAX_2),
                        rs.getInt(MIN),
                        rs.getBoolean(FLAG)
                );
                processVar = tempVars;
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_varsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return processVar;        
    }
    /**
     * Obtiene un objeto int_proceso_vars específico por su nombre desde la base de datos.
     *
     * @param name El nombre del objeto int_proceso_vars que se desea obtener.
     * @return El objeto int_proceso_vars con el nombre especificado, o null si no se encuentra.
     */
    @Override
    public int_proceso_vars getProcessVar(String name) {
        int_proceso_vars processVar = null;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_PROCESS_VARS_B);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso_vars tempVar = new int_proceso_vars(
                        rs.getInt(INT_PROCESO_ID), 
                        rs.getString(NOMBRE),
                        rs.getString(DESCRIPCION), 
                        rs.getInt(MAX_2),
                        rs.getInt(MIN),
                        rs.getBoolean(FLAG)
                );
                tempVar.setId(rs.getInt(ID));
                processVar = tempVar;
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_varsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return processVar;
    }
    /**
     * Inserta un nuevo objeto int_proceso_vars en la base de datos y lo agrega a la lista interna.
     *
     * @param processId    El ID del proceso al que se relacionará el objeto int_proceso_vars.
     * @param name         El nombre del objeto int_proceso_vars.
     * @param description  La descripción del objeto int_proceso_vars.
     * @param max          El valor máximo del objeto int_proceso_vars.
     * @param min          El valor mínimo del objeto int_proceso_vars.
     * @return El número de filas afectadas en la base de datos (debería ser 1 si la inserción tiene éxito).
     */
    @Override
    public int insertProcessVar(int processId, String name, 
            String description, double max, double min) {
        int resRows = 0;
        int index = this.procesosVars.size();
        int id = this.procesosVars.get(index).getId() + 1;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(INSERT_PROCESS_VARS);
            statement.setInt(1, processId);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setDouble(4, max);
            statement.setDouble(5, min);
            statement.setBoolean(6, false);
            int_proceso_vars tempVar = new int_proceso_vars(
                    processId,
                    name,
                    description, 
                    max,
                    min
            );
            tempVar.setId(id);
            this.procesosVars.add(tempVar);
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_varsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resRows;
        
    }
    /**
     * Actualiza un objeto int_proceso_vars existente en la base de datos y en la lista interna.
     *
     * @param processVar El objeto int_proceso_vars que se desea actualizar.
     * @return El número de filas afectadas en la base de datos (debería ser 1 si la actualización tiene éxito).
     */
    @Override
    public int updateProcessVar(int_proceso_vars processVar) {
        int resRows = 0;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(UPDATE_PROCESS_VARS);
            statement.setInt(1, processVar.getProcessId());
            statement.setString(2, processVar.getName());
            statement.setString(3, processVar.getDescription());
            statement.setDouble(4, processVar.getMax());
            statement.setDouble(5, processVar.getMin());
            statement.setBoolean(6, processVar.isFlag());
            statement.setInt(7, processVar.getId());
            statement.toString();
            this.procesosVars.add(processVar);
            resRows = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_varsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resRows;
    }
    /**
     * Elimina un objeto int_proceso_vars existente de la base de datos y lo elimina de la lista interna.
     *
     * @param processVar El objeto int_proceso_vars que se desea eliminar.
     * @return El número de filas afectadas en la base de datos (debería ser 1 si la eliminación tiene éxito).
     */
    @Override
    public int deleteProcessVar(int_proceso_vars processVar) {
        int resRows = 0;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(DELETE_PROCESS_VARS);
            statement.setInt(1, processVar.getId());
            
            int idVar = this.procesosVars.indexOf(processVar);
            this.procesosVars.remove(idVar);
            
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_varsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return resRows;
    }
    /**
     * Elimina un objeto int_proceso_vars por su ID de la base de datos y lo elimina de la lista interna.
     *
     * @param varId El ID del objeto int_proceso_vars que se desea eliminar.
     * @return El número de filas afectadas en la base de datos (debería ser 1 si la eliminación tiene éxito).
     */
    @Override
    public int deleteProcessVar(int varId) {
        int resRows = 0;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(DELETE_PROCESS_VARS);
            statement.setInt(1, varId);
            
            Iterator i = this.procesosVars.iterator();
            int varIndex = 0;
            while (i.hasNext()) {
                int idVarDB = ((int_proceso_vars) i.next()).getId();
                if (idVarDB == varId) {
                    this.procesosVars.remove(varIndex);
                    break;
                }
                varIndex++;
            }
            
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_varsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return resRows;
    }
    /**
     * Obtiene un objeto int_proceso_vars con una bandera específica desde la base de datos.
     *
     * @param flag La bandera que se utiliza para buscar el objeto int_proceso_vars.
     * @return El objeto int_proceso_vars con la bandera especificada, o null si no se encuentra.
     */
    @Override
    public int_proceso_vars getProcessVars(boolean flag) {
        int_proceso_vars processVar = null;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_PROCESS_VARS_FLAG);
            statement.setBoolean(1, flag);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso_vars tempVar = new int_proceso_vars(
                        rs.getInt(INT_PROCESO_ID), 
                        rs.getString(NOMBRE),
                        rs.getString(DESCRIPCION), 
                        rs.getInt(MAX_2),
                        rs.getInt(MIN),
                        rs.getBoolean(FLAG)
                );
                tempVar.setName(rs.getString(NOMBRE));
                tempVar.setId(rs.getInt(ID));
                processVar = tempVar;
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_varsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return processVar;
    }
}
