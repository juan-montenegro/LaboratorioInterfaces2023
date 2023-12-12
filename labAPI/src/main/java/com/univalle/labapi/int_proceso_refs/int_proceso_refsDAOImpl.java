/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso_refs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class int_proceso_refsDAOImpl implements int_proceso_refsDAO {
    private static final String ID = "id";
    private static final String INT_PROCESO_ID = "int_proceso_id";
    private static final String NOMBRE = "nombre";
    private static final String DESCRIPCION = "descripcion";
    private static final String MAX_2 = "max_2";
    private static final String MIN = "min";
    
    private static final String GET_ALL_PROCESS_REFS = "SELECT id, int_proceso_id,"
            + " nombre, descripcion, max_2, min FROM int_proceso_refs";
    private static final String GET_PROCESS_REFS_A = "SELECT id, int_proceso_id,"
            + " nombre, descripcion, max_2, min FROM int_proceso_refs WHERE id=?";    
    private static final String GET_PROCESS_REFS_B = "SELECT id, int_proceso_id,"
            + " nombre, descripcion, max_2, min FROM int_proceso_refs WHERE nombre=?";    
    private static final String INSERT_PROCESS_REFS = "INSERT INTO int_proceso_refs SET"
            + " id=?, int_proceso_id=?, nombre=?, descripcion=?, max_2=?, min=?";
    private static final String UPDATE_PROCESS_REFS = "UPDATE int_proceso_refs SET"
            + " int_proceso_id=?, nombre=?, descripcion=?, max_2=?, min=?"
            + " WHERE id=?";        
    private static final String DELETE_PROCESS_REFS = "DELETE FROM int_proceso_refs"
            + " WHERE ID=?";
    
    private Connection connection = null;
    private final List<int_proceso_refs> procesosRefs;
    
    /**
     *
     * @param connection
     */
    public int_proceso_refsDAOImpl(Connection connection) {
        this.connection = connection;
        this.procesosRefs = new ArrayList<>();
        
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_ALL_PROCESS_REFS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso_refs processRef = new int_proceso_refs(
                        rs.getInt(INT_PROCESO_ID), 
                        rs.getString(NOMBRE),
                        rs.getString(DESCRIPCION), 
                        rs.getInt(MAX_2),
                        rs.getInt(MIN)
                );
                this.procesosRefs.add(processRef);
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }        
    }
    
    
    @Override
    public List<int_proceso_refs> getAllProcessRefs() {
        this.procesosRefs.clear();
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_ALL_PROCESS_REFS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso_refs processRef = new int_proceso_refs(
                        rs.getInt(INT_PROCESO_ID), 
                        rs.getString(NOMBRE),
                        rs.getString(DESCRIPCION), 
                        rs.getInt(MAX_2),
                        rs.getInt(MIN)
                );
                processRef.setId(rs.getInt(ID));
                this.procesosRefs.add(processRef);
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return this.procesosRefs;
    }

    @Override
    public int_proceso_refs getProcessRef(int refId) {
        int_proceso_refs processRef = null;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_PROCESS_REFS_A);
            statement.setInt(1, refId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso_refs tempRef = new int_proceso_refs(
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
            Logger.getLogger(int_proceso_refsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return processRef;        
    }

    @Override
    public int_proceso_refs getProcessRef(String name) {
        int_proceso_refs processRef = null;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_PROCESS_REFS_B);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso_refs tempRef = new int_proceso_refs(
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
            Logger.getLogger(int_proceso_refsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return processRef;
    }

    @Override
    public int insertProcessRef(int processId, String name, 
            String description, double max, double min) {
        int resRows = 0;
        int id = (this.procesosRefs.size() + 1) + 1;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(INSERT_PROCESS_REFS);
            statement.setInt(1, id);
            statement.setInt(2, processId);
            statement.setString(3, name);
            statement.setString(4, description);
            statement.setDouble(5, max);
            statement.setDouble(6, min);
            int_proceso_refs tempRef = new int_proceso_refs(
                    processId,
                    name,
                    description, 
                    max,
                    min
            );
            tempRef.setId(id);
            this.procesosRefs.add(tempRef);
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resRows;
        
    }

    @Override
    public int updateProcessRef(int_proceso_refs processRef) {
        int resRows = 0;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(UPDATE_PROCESS_REFS);
            statement.setInt(1, processRef.getProcessId());
            statement.setString(2, processRef.getName());
            statement.setString(3, processRef.getDescription());
            statement.setDouble(4, processRef.getMax());
            statement.setDouble(5, processRef.getMin());
            statement.setInt(6, processRef.getId());
            this.procesosRefs.add(processRef);
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resRows;
    }

    @Override
    public int deleteProcessRef(int_proceso_refs processRef) {
        int resRows = 0;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(DELETE_PROCESS_REFS);
            statement.setInt(1, processRef.getId());
            
            int idRef = this.procesosRefs.indexOf(processRef);
            this.procesosRefs.remove(idRef);
            
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return resRows;
    }

    @Override
    public int deleteProcessRef(int refId) {
        int resRows = 0;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(DELETE_PROCESS_REFS);
            statement.setInt(1, refId);
            
            Iterator i = this.procesosRefs.iterator();
            int refIndex = 0;
            while (i.hasNext()) {
                int idRefDB = ((int_proceso_refs) i.next()).getId();
                if (idRefDB == refId) {
                    this.procesosRefs.remove(refIndex);
                    break;
                }
                refIndex++;
            }
            
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return resRows;
    }
    
}
