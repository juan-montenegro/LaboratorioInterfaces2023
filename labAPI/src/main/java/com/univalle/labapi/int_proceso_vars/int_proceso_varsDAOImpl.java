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
    
    private static final String GET_ALL_PROCESS_VARS = "SELECT id, int_proceso_id,"
            + " nombre, descripcion, max_2, min FROM int_proceso_vars";
    private static final String GET_PROCESS_VARS_A = "SELECT id, int_proceso_id,"
            + " nombre, descripcion, max_2, min FROM int_proceso_vars WHERE id=?";    
    private static final String GET_PROCESS_VARS_B = "SELECT id, int_proceso_id,"
            + " nombre, descripcion, max_2, min FROM int_proceso_vars WHERE nombre=?";    
    private static final String INSERT_PROCESS_VARS = "INSERT INTO int_proceso_vars SET"
            + " id=?, int_proceso_id=?, nombre=?, descripcion=?, max_2=?, min=?";
    private static final String UPDATE_PROCESS_VARS = "UPDATE int_proceso_refs SET"
            + " int_proceso_id=?, nombre=?, descripcion=?, max_2=?, min=? WHERE id=?";        
    private static final String DELETE_PROCESS_VARS = "DELETE FROM int_proceso_refs WHERE ID=?";
    
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
    public List<int_proceso_vars> getAllProcessRefs() {
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
    public int_proceso_vars getProcessRef(int refId) {
        int_proceso_vars processRef = null;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_PROCESS_VARS_A);
            statement.setInt(1, refId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso_vars tempRef = new int_proceso_vars(
                        rs.getInt(INT_PROCESO_ID), 
                        rs.getString(NOMBRE),
                        rs.getString(DESCRIPCION), 
                        rs.getInt(MAX_2),
                        rs.getInt(MIN)
                );
                tempRef.setId(rs.getInt(ID));
                processRef = tempRef;
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_varsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return processRef;        
    }

    @Override
    public int_proceso_vars getProcessRef(String name) {
        int_proceso_vars processRef = null;
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
                processRef = tempVar;
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_varsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return processRef;
    }

    @Override
    public int insertProcessRef(int processId, String name, 
            String description, double max, double min) {
        int resRows = 0;
        int id = (this.procesosVars.size() + 1) + 1;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(INSERT_PROCESS_VARS);
            statement.setInt(1, id);
            statement.setInt(2, processId);
            statement.setString(3, name);
            statement.setString(4, description);
            statement.setDouble(5, max);
            statement.setDouble(6, min);
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
    public int updateProcessRef(int_proceso_vars processVar) {
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
    public int deleteProcessRef(int_proceso_vars processVar) {
        int resRows = 0;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(DELETE_PROCESS_VARS);
            statement.setInt(1, processVar.getId());
            
            int idRef = this.procesosVars.indexOf(processVar);
            this.procesosVars.remove(idRef);
            
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_varsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return resRows;
    }

    @Override
    public int deleteProcessRef(int refId) {
        int resRows = 0;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(DELETE_PROCESS_VARS);
            statement.setInt(1, refId);
            
            Iterator i = this.procesosVars.iterator();
            int refIndex = 0;
            while (i.hasNext()) {
                int idRefDB = ((int_proceso_vars) i.next()).getId();
                if (idRefDB == refId) {
                    this.procesosVars.remove(refIndex);
                    break;
                }
                refIndex++;
            }
            
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_varsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return resRows;
    }
}
