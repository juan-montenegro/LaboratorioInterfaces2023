/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso_refs;

import com.univalle.labapi.int_usuarios.int_usuariosDAOImp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    private final String GET_ALL_PROCESS_REFS = "SELECT id, int_proceso_id,"
            + " nombre, descripcion, max_2, min FROM int_proceso_refs";
    private final String GET_PROCESS_REFS_A = "SELECT id, int_proceso_id,"
            + " nombre, descripcion, max_2, min FROM int_proceso_refs WHERE id=?";    
    private final String GET_PROCESS_REFS_B = "SELECT id, int_proceso_id,"
            + " nombre, descripcion, max_2, min FROM int_proceso_refs WHERE nombre=?";    
    private final String INSERT_PROCESS_REFS = "INSERT INTO int_proceso_refs SET"
            + " id=?, int_proceso_id=?, nombre=?, descripcion=?, max_2=?, min=?";    
    
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
    public int_proceso_refs getProcessRef(int processId) {
        int_proceso_refs processRef = null;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_PROCESS_REFS_A);
            statement.setInt(1, processId);
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
    public void insertProcessRef(int processId, String name, 
            String description, double max, double min) {
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
            statement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void updateProcessRef(int_proceso_refs processRef) {
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(INSERT_PROCESS_REFS);
            statement.setInt(1, processRef.getId());
            statement.setInt(2, processRef.getProcessId());
            statement.setString(3, processRef.getName());
            statement.setString(4, processRef.getDescription());
            statement.setDouble(5, processRef.getMax());
            statement.setDouble(6, processRef.getMin());
            this.procesosRefs.add(processRef);
            statement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
}
