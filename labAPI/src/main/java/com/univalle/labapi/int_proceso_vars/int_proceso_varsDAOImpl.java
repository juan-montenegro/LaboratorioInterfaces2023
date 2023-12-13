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


public class int_proceso_varsDAOImpl implements int_proceso_varsDAO {
    private static final String ID = "id";
    private static final String INT_PROCESO_ID = "int_proceso_id";
    private static final String NOMBRE = "nombre";
    private static final String DESCRIPCION = "descripcion";
    private static final String MAX_2 = "max_2";
    private static final String MIN = "min";
    
    private static final String GET_ALL_PROCESS_VARS 
            = "SELECT id, int_proceso_id, nombre, descripcion, max_2, min "
            + "FROM int_proceso_vars";
    private static final String GET_PROCESS_VARS_A 
            = "SELECT id, int_proceso_id, nombre, descripcion, max_2, min "
            + "FROM int_proceso_vars WHERE id=?";    
    private static final String GET_PROCESS_VARS_B 
            = "SELECT id, int_proceso_id, nombre, descripcion, max_2, min "
            + "FROM int_proceso_vars WHERE nombre=?";    
    private static final String INSERT_PROCESS_VARS 
            = "INSERT INTO int_proceso_vars "
            + "SET int_proceso_id=?, nombre=?, descripcion=?, max_2=?, min=?";
    private static final String UPDATE_PROCESS_VARS 
            = "UPDATE int_proceso_vars "
            + "SET int_proceso_id=?, nombre=?, descripcion=?, max_2=?, min=? "
            + "WHERE id=?";        
    private static final String DELETE_PROCESS_VARS 
            = "DELETE FROM int_proceso_vars "
            + "WHERE id=?";
    
    private Connection connection = null;
    private final List<int_proceso_vars> procesosVars;
    
    /**
     *
     * @param connection
     */
    public int_proceso_varsDAOImpl(Connection connection) {
        this.connection = connection;
        this.procesosVars = new ArrayList<>();
        
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
                        rs.getInt(MIN)
                );
                this.procesosVars.add(processVar);
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_varsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }        
    }
    
    
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
                        rs.getInt(MIN)
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
                        rs.getInt(MIN)
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
                        rs.getInt(MIN)
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
            statement.setInt(6, processVar.getId());
            this.procesosVars.add(processVar);
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_varsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resRows;
    }

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
}
