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


/**
 * Implementación del DAO (Data Access Object) para la entidad int_proceso_refs.
 * Proporciona métodos para interactuar con la base de datos respecto a las referencias de procesos.
 */

public class int_proceso_refsDAOImpl implements int_proceso_refsDAO {
    private static final String ID = "id";
    private static final String INT_PROCESO_ID = "int_proceso_id";
    private static final String NOMBRE = "nombre";
    private static final String DESCRIPCION = "descripcion";
    private static final String MAX_2 = "max_2";
    private static final String MIN = "min";
    private static final String FLAG = "flag";
    
    // Consultas SQL predefinidas.
    
    private static final String GET_ALL_PROCESS_REFS 
            = "SELECT id, int_proceso_id, nombre, descripcion, max_2, min, flag "
            + "FROM int_proceso_refs";
    private static final String GET_PROCESS_REFS_A 
            = "SELECT id, int_proceso_id, nombre, descripcion, max_2, min, flag "
            + "FROM int_proceso_refs "
            + "WHERE id=?";    
    private static final String GET_PROCESS_REFS_B 
            = "SELECT id, int_proceso_id, nombre, descripcion, max_2, min, flag "
            + "FROM int_proceso_refs "
            + "WHERE nombre=?";    
    private static final String INSERT_PROCESS_REFS 
            = "INSERT INTO int_proceso_refs "
            + "SET int_proceso_id=?, nombre=?, descripcion=?, max_2=?, min=?, flag=?";
    private static final String UPDATE_PROCESS_REFS 
            = "UPDATE int_proceso_refs "
            + "SET int_proceso_id=?, nombre=?, descripcion=?, max_2=?, min=?, flag=? "
            + "WHERE id=?";        
    private static final String DELETE_PROCESS_REFS 
            = "DELETE FROM int_proceso_refs "
            + "WHERE ID=?";
    
    private static final String GET_PROCESS_REFS_FLAG 
            = "SELECT id, int_proceso_id, nombre, descripcion, max_2, min, flag "
            + "FROM int_proceso_refs "
            + "WHERE flag=?";   
    
    private Connection connection = null;
    private final List<int_proceso_refs> procesosRefs;
    private final List<int_proceso_refs> procesoRefsFlag;
    
   /**
     * Constructor para inicializar la conexión a la base de datos y las listas de referencias de procesos.
     *
     * @param connection La conexión a la base de datos.
     */
    
    public int_proceso_refsDAOImpl(Connection connection) {
        this.connection = connection;
        this.procesosRefs = new ArrayList<>();
        this.procesoRefsFlag = new ArrayList<>();
        
        // Inicialización y carga de referencias de procesos existentes.
        
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
                        rs.getInt(MIN),
                        rs.getBoolean(FLAG)
                );
                this.procesosRefs.add(processRef);
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }        
    }
    /**
 * Obtiene una lista de todas las referencias de procesos.
 * 
 * @return Una lista de objetos int_proceso_refs que representan todas las referencias de procesos.
 */
    
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
                        rs.getInt(MIN),
                        rs.getBoolean(FLAG)
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

    /**
 * Obtiene una referencia de proceso específica por su ID.
 * 
 * @param refId El ID de la referencia del proceso.
 * @return Un objeto int_proceso_refs que representa la referencia del proceso con el ID especificado.
 */
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
                        rs.getInt(MIN),
                        rs.getBoolean(FLAG)
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
/**
 * Obtiene una referencia de proceso específica por su nombre.
 * 
 * @param name El nombre de la referencia del proceso.
 * @return Un objeto int_proceso_refs que representa la referencia del proceso con el nombre especificado.
 */
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
                        rs.getInt(MIN),
                        rs.getBoolean(FLAG)
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
/**
 * Inserta una nueva referencia de proceso en la base de datos.
 * 
 * @param processId El ID del proceso.
 * @param name El nombre de la referencia del proceso.
 * @param description La descripción de la referencia del proceso.
 * @param max Valor máximo asociado a la referencia del proceso.
 * @param min Valor mínimo asociado a la referencia del proceso.
 * @return El número de filas afectadas por la operación.
 */
    @Override
    public int insertProcessRef(int processId, String name, 
            String description, double max, double min) {
        int resRows = 0;
        int index = this.procesosRefs.size();
        int id = this.procesosRefs.get(index).getId() + 1;
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(INSERT_PROCESS_REFS);
            statement.setInt(1, processId);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setDouble(4, max);
            statement.setDouble(5, min);
            statement.setBoolean(6, false);
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
/**
 * Actualiza una referencia de proceso existente en la base de datos.
 * 
 * @param processRef Objeto int_proceso_refs que representa la referencia del proceso a actualizar.
 * @return El número de filas afectadas por la operación.
 */
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
            statement.setBoolean(6, processRef.isFlag());
            statement.setInt(7, processRef.getId());
            this.procesosRefs.add(processRef);
            resRows = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return resRows;
    }
/**
 * Elimina una referencia de proceso específica de la base de datos.
 * 
 * @param processRef Objeto int_proceso_refs que representa la referencia del proceso a eliminar.
 * @return El número de filas afectadas por la operación.
 */
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
/**
 * Elimina una referencia de proceso específica de la base de datos por su ID.
 * 
 * @param refId El ID de la referencia del proceso a eliminar.
 * @return El número de filas afectadas por la operación.
 */
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
/**
 * Obtiene una lista de referencias de proceso filtrada por un indicador booleano.
 * 
 * @param flag El indicador booleano para filtrar las referencias de proceso.
 * @return Una lista de objetos int_proceso_refs que cumplen con el criterio de filtro.
 */
    @Override
    public List<int_proceso_refs> getNamesFlags(boolean flag) {
     
      this.procesoRefsFlag.clear();
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(GET_PROCESS_REFS_FLAG);
            statement.setBoolean(1, flag);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int_proceso_refs processRef = new int_proceso_refs(
                        rs.getInt(INT_PROCESO_ID), 
                        rs.getString(NOMBRE),
                        rs.getString(DESCRIPCION), 
                        rs.getInt(MAX_2),
                        rs.getInt(MIN),
                        rs.getBoolean(FLAG)
                );
                processRef.setName(rs.getString(NOMBRE));
                this.procesoRefsFlag.add(processRef);
            }
        } catch (SQLException ex) {
            Logger.getLogger(int_proceso_refsDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return this.procesoRefsFlag;  
    }
}
