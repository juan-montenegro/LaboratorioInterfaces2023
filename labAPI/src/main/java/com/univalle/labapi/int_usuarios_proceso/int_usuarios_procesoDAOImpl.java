/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_usuarios_proceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementa las operaciones CRUD para la entidad int_usuarios_proceso en la base de datos.
 * Permite insertar, actualizar, borrar y consultar registros de procesos asociados a usuarios.
 */

public class int_usuarios_procesoDAOImpl implements int_usuarios_procesoDAO {

    private Connection conectionDb = null; // Conexión a la base de datos.
    private String queryTotal = "SELECT id, int_proceso_id, int_usuarios_id, fecha, hora_inicio, hora_fin, hits FROM int_usuarios_proceso";// Consulta para obtener todos los registros.
//    private String queryInsert = "INSERT INTO `int_usuarios_proceso`(`id`, `int_proceso_id`, `int_usuarios_id`, `fecha`, `hora_inicio`, `hora_fin`, `hits`) VALUES (?, ?, ?, ?, ?, ?, ?)";// Consulta para insertar un nuevo registro.
    private String queryInsert = "INSERT INTO `int_usuarios_proceso`(`int_proceso_id`, `int_usuarios_id`, `fecha`, `hora_inicio`, `hora_fin`, `hits`) VALUES (?, ?, ?, ?, ?, ?)";// Consulta para insertar un nuevo registro.
    private PreparedStatement stmP = null;// Declaración preparada para ejecución de consultas.
    private Statement stmS = null;// Declaración para ejecución de consultas.
    private List <int_usuarios_proceso> UsersProcess;// Lista de registros de procesos de usuarios.
    private List <int_usuarios_proceso> procesos;// Lista de procesos.
    private List <int_usuarios_proceso> usuarios;// Lista de usuarios.
    private List <int_usuarios_proceso> procesosFecha; // Lista de procesos por fecha.
    private List <int_usuarios_proceso> usuariosFecha; // Lista de usuarios por fecha.
    private int hitsForDate = 0; // Total de hits en una fecha específica.
    
    /**
     * Constructor que inicializa la conexión a la base de datos y prepara las consultas.
     *
     * @param conectionDb La conexión a la base de datos.
     */
    
    public int_usuarios_procesoDAOImpl(Connection conectionDb){
		
        this.conectionDb = conectionDb;
        this.UsersProcess = new ArrayList();
        this.procesos= new ArrayList();
        this.usuarios = new ArrayList();
        this.procesosFecha = new ArrayList();
        this.usuariosFecha = new ArrayList();
                
        try
        {
            this.stmS = this.conectionDb.createStatement();
            ResultSet rs = stmS.executeQuery(this.queryTotal);
            while (rs.next())
            {
                int_usuarios_proceso intRegister = new int_usuarios_proceso(rs.getInt("int_proceso_id"), rs.getInt("int_usuarios_id"), rs.getDate("fecha"), rs.getTime("hora_inicio"), rs.getTime("hora_fin"), rs.getInt("hits"));
                intRegister.setUserId(rs.getInt("int_usuarios_id"));
                intRegister.setProcessId(rs.getInt("int_proceso_id"));
                intRegister.setDate(rs.getDate("fecha").toLocalDate());
                intRegister.setStartTime(rs.getTime("hora_inicio").toLocalTime());
                
                //System.out.println("userId: "+intRegister.getUserId());

                this.UsersProcess.add(intRegister);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(int_usuarios_procesoDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Obtiene una lista de todos los registros de procesos para un usuario específico desde la base de datos y actualiza la lista interna.
     * 
     * @param usuarioId El ID del usuario para el cual se desean obtener los registros de procesos.
     * @return Una lista de todos los registros de procesos para el usuario especificado.
     */
    
    @Override
    public List<int_usuarios_proceso> getAllProcessForUser(int usuarioId) {
        this.procesos.clear();
        
        try{
            this.stmS = this.conectionDb.createStatement();
            ResultSet rs = stmS.executeQuery(this.queryTotal+"WHERE int_usuarios_id ="+usuarioId+";");
            while(rs.next()){
                int_usuarios_proceso intRegisterProcess = new int_usuarios_proceso (rs.getInt("int_proceso_id"), rs.getInt("int_usuarios_id"),rs.getDate("fecha"), rs.getTime("hora_inicio"), rs.getTime("hora_fin"), rs.getInt("hits"));
                intRegisterProcess.setProcessId(rs.getInt("int_proceso_id"));
                intRegisterProcess.setDate(rs.getDate("fecha").toLocalDate());
                intRegisterProcess.setStartTime(rs.getTime("hora_inicio").toLocalTime());
                
                this.procesos.add(intRegisterProcess);
            }
                 
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return this.procesos;
    }
  /**
     * Obtiene una lista de todos los registros de usuarios en un proceso específico desde la base de datos y actualiza la lista interna.
     * 
     * @param procesoId El ID del proceso para el cual se desean obtener los registros de usuarios.
     * @return Una lista de todos los registros de usuarios en el proceso especificado.
     */
    @Override
    public List<int_usuarios_proceso> getAllUserForProcess(int procesoId) {
       this.usuarios.clear();
        
        try{
            this.stmS = this.conectionDb.createStatement();
            ResultSet rs = stmS.executeQuery(this.queryTotal+"WHERE int_proceso_id ="+procesoId+";");
            while(rs.next()){
                int_usuarios_proceso intRegisterProcess = new int_usuarios_proceso (rs.getInt("int_proceso_id"), rs.getInt("int_usuarios_id"),rs.getDate("fecha"), rs.getTime("hora_inicio"), rs.getTime("hora_fin"), rs.getInt("hits"));
                intRegisterProcess.setUserId(rs.getInt("int_usuarios_id"));
                intRegisterProcess.setDate(rs.getDate("fecha").toLocalDate());
                intRegisterProcess.setStartTime(rs.getTime("hora_inicio").toLocalTime());
                
                this.usuarios.add(intRegisterProcess);
            }
                 
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return this.usuarios;
    }
    /**
     * Obtiene una lista de todos los registros de procesos para una fecha específica desde la base de datos y actualiza la lista interna.
     * 
     * @param fecha La fecha para la cual se desean obtener los registros de procesos.
     * @return Una lista de todos los registros de procesos para la fecha especificada.
     */
    @Override 
    public List<int_usuarios_proceso> getProcessForDate(LocalDate fecha) {
        this.procesosFecha.clear();
        
        Date fechaSql = Date.valueOf(fecha);
        try{
            this.stmS = this.conectionDb.createStatement();
            ResultSet rs = stmS.executeQuery(this.queryTotal+"WHERE fecha='"+fechaSql+"';"); //REVISAR PORQUE NO ESTOY SEGURO SI ES NECESARIOS LAS COMILLAS SIM
            while(rs.next()){
                int_usuarios_proceso intRegisterProcess = new int_usuarios_proceso (rs.getInt("int_proceso_id"), rs.getInt("int_usuarios_id"),rs.getDate("fecha"), rs.getTime("hora_inicio"), rs.getTime("hora_fin"), rs.getInt("hits"));
                intRegisterProcess.setProcessId(rs.getInt("int_proceso_id"));
                intRegisterProcess.setStartTime(rs.getTime("hora_inicio").toLocalTime());
                
                this.procesosFecha.add(intRegisterProcess);
            }
                 
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return this.procesosFecha;
    }

    /**
     * Obtiene una lista de todos los registros de usuarios para una fecha específica desde la base de datos y actualiza la lista interna.
     * 
     * @param fecha La fecha para la cual se desean obtener los registros de usuarios.
     * @return Una lista de todos los registros de usuarios para la fecha especificada.
     */
    @Override 
    public List<int_usuarios_proceso> getUserForDate(LocalDate fecha) {
        this.usuariosFecha.clear();
        
        Date fechaSql = Date.valueOf(fecha);
        try{
            this.stmS = this.conectionDb.createStatement();
            ResultSet rs = stmS.executeQuery(this.queryTotal+"WHERE fecha='"+fechaSql+"';");//REVISAR PORQUE NO ESTOY SEGURO SI ES NECESARIO LAS COMILLAS SIM
            while(rs.next()){
                int_usuarios_proceso intRegisterUsers = new int_usuarios_proceso (rs.getInt("int_proceso_id"), rs.getInt("int_usuarios_id"),rs.getDate("fecha"), rs.getTime("hora_inicio"), rs.getTime("hora_fin"), rs.getInt("hits"));
                intRegisterUsers.setUserId(rs.getInt("int_usuarios_id"));
                intRegisterUsers.setStartTime(rs.getTime("hora_inicio").toLocalTime());
                
                this.usuariosFecha.add(intRegisterUsers);
            }               
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return this.usuariosFecha;
    }
  /**
     * Obtiene el total de hits para una fecha específica desde la base de datos.
     * 
     * @param fecha La fecha para la cual se desea obtener el total de hits.
     * @return El total de hits para la fecha especificada.
     */
    @Override
    public int getHitsForDate(LocalDate fecha) {
        
       this.hitsForDate = 0;
       
       Date fechaSql = Date.valueOf(fecha);
       try{
            this.stmS = this.conectionDb.createStatement();
            ResultSet rs = stmS.executeQuery("SELECT SUM(`hits`) AS total_hits FROM `int_usuarios_proceso` WHERE fecha='" + fechaSql + "';");

            // Comprueba si el resultado tiene al menos una fila y obtiene el valor
            if (rs.next()) {
                // Obtiene el valor de la suma de hits y lo asigna a hitsForDate
                this.hitsForDate = rs.getInt("total_hits");
            }
       }
       catch(SQLException e){
            e.printStackTrace();
       }
       
       return this.hitsForDate;   
    }
    /**
     * Inserta un nuevo registro en la base de datos y lo agrega a la lista interna.
     * 
     * @param usuario     El ID del usuario asociado al registro.
     * @param proceso     El ID del proceso asociado al registro.
     * @param fecha       La fecha del registro.
     * @param hora_inicio La hora de inicio del registro.
     * @param hora_fin    La hora de finalización del registro.
     * @param hits        El número de hits del registro.
     * @return El número de filas afectadas en la base de datos (debería ser 1 si la inserción tiene éxito).
     */
    @Override
    public int insertNewRegister(int usuario, int proceso, LocalDate fecha, LocalTime hora_inicio, LocalTime hora_fin, int hits) {
        int result = 0;
        try{
            this.stmP = this.conectionDb.prepareStatement(this.queryInsert);
            // Estableciendo los valores de los parámetros
            stmP.setInt(1, proceso);
            stmP.setInt(2, usuario);
            stmP.setDate(3, java.sql.Date.valueOf(fecha));
            stmP.setTime(4, java.sql.Time.valueOf(hora_inicio));
            stmP.setTime(5, java.sql.Time.valueOf(hora_fin));
            stmP.setInt(6, hits);
            // Ejecutando la consulta de actualización
            result = stmP.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
}
    /**
     * Obtiene el ID del último registro en la base de datos.
     * 
     * @return El ID del último registro.
     */
    @Override
    public int getLastRecordId() {
        int lastId = -1;
        String query = "SELECT MAX(`id`) AS last_id FROM `int_usuarios_proceso`";

        try (Statement stmt = this.conectionDb.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                lastId = rs.getInt("last_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    return lastId;
    }
        /**
     * Actualiza la hora de finalización de un registro en la base de datos.
     * 
     * @param hora_fin La nueva hora de finalización.
     * @return El número de filas afectadas en la base de datos (debería ser 1 si la actualización tiene éxito).
     */
    @Override
    public int updateHoraFin(LocalTime hora_fin) {
        int result = 0;
        int lastId = getLastRecordId(); // Obtener el ID del último registro

        if (lastId != -1) {
            String queryUpdate = "UPDATE `int_usuarios_proceso` SET `hora_fin` = ? WHERE `id` = ?";

            try (PreparedStatement pstmt = this.conectionDb.prepareStatement(queryUpdate)) {
                // Estableciendo los valores de los parámetros
                pstmt.setTime(1, java.sql.Time.valueOf(hora_fin));
                pstmt.setInt(2, lastId);

                // Ejecutando la consulta de actualización
                result = pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Manejar el caso en que no hay registros en la tabla
            System.out.println("No hay registros para actualizar.");
        }
        return result;
    }
    /**
     * Elimina un registro de la base de datos por ID de usuario, ID de proceso, fecha y hora de inicio.
     * 
     * @param usuarioId   El ID del usuario asociado al registro.
     * @param procesoId   El ID del proceso asociado al registro.
     * @param fecha       La fecha del registro.
     * @param hora_inicio La hora de inicio del registro.
     * @return El número de filas afectadas en la base de datos (debería ser 1 si la eliminación tiene éxito).
     */
    @Override
    public int deleteRegister(int usuarioId, int procesoId, LocalDate fecha, LocalTime hora_inicio) {
        int result = 0;
        String queryDelete = "DELETE FROM `int_usuarios_proceso` WHERE `int_usuarios_id` = ? AND `int_proceso_id` = ? AND `fecha` = ? AND `hora_inicio` = ?";

        try (PreparedStatement pstmt = this.conectionDb.prepareStatement(queryDelete)) {
            // Estableciendo los valores de los parámetros
            pstmt.setInt(1, usuarioId);
            pstmt.setInt(2, procesoId);
            pstmt.setDate(3, java.sql.Date.valueOf(fecha));
            pstmt.setTime(4, java.sql.Time.valueOf(hora_inicio));

            // Ejecutando la consulta de eliminación
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    /**
     * Elimina todos los registros de un usuario específico de la base de datos.
     * 
     * @param usuarioId El ID del usuario del cual se desean eliminar los registros.
     * @return El número de filas afectadas en la base de datos.
     */
    @Override
    public int deleteRegistersOfUser(int usuarioId) {
         int result = 0;
        String queryDelete = "DELETE FROM `int_usuarios_proceso` WHERE `int_usuarios_id` = ?";

        try (PreparedStatement pstmt = this.conectionDb.prepareStatement(queryDelete)) {
            // Estableciendo el valor del parámetro
            pstmt.setInt(1, usuarioId);

            // Ejecutando la consulta de eliminación
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;   
    }

    /**
     * Elimina todos los registros de un proceso específico de la base de datos.
     * 
     * @param procesoId El ID del proceso del cual se desean eliminar los registros.
     * @return El número de filas afectadas en la base de datos.
     */
    @Override
    public int deleteRegistersOfProcess(int procesoId) {
        int result = 0;
        String queryDelete = "DELETE FROM `int_usuarios_proceso` WHERE `int_proceso_id` = ?";

        try (PreparedStatement pstmt = this.conectionDb.prepareStatement(queryDelete)) {
            // Estableciendo el valor del parámetro
            pstmt.setInt(1, procesoId);

            // Ejecutando la consulta de eliminación
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;    
    }
    /**
     * Elimina todos los registros para una fecha específica de la base de datos.
     * 
     * @param fecha La fecha para la cual se desean eliminar los registros.
     * @return El número de filas afectadas en la base de datos.
     */
    @Override
    public int deleteRegistersOfDate(LocalDate fecha) {
        int result = 0;
        String queryDelete = "DELETE FROM `int_usuarios_proceso` WHERE `fecha` = ?";

        try (PreparedStatement pstmt = this.conectionDb.prepareStatement(queryDelete)) {
            // Estableciendo el valor del parámetro
            pstmt.setDate(1, java.sql.Date.valueOf(fecha));

            // Ejecutando la consulta de eliminación
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;   
    }    
    /**
     * Obtiene una lista de todos los registros de procesos de usuarios desde la base de datos y actualiza la lista interna.
     * 
     * @return Una lista de todos los registros de procesos de usuarios.
     */
    @Override
    public List<int_usuarios_proceso> getAllOfUsariosProceso() {
        this.UsersProcess.clear();
        try{
            this.stmS = this.conectionDb.createStatement();
            ResultSet rs = stmS.executeQuery(this.queryTotal+";");
            while (rs.next()){
                int_usuarios_proceso intRegister = new int_usuarios_proceso(rs.getInt("int_proceso_id"), rs.getInt("int_usuarios_id"), rs.getDate("fecha"), rs.getTime("hora_inicio"), rs.getTime("hora_fin"), rs.getInt("hits"));
                intRegister.setUserId(rs.getInt("int_usuarios_id"));
                intRegister.setProcessId(rs.getInt("int_proceso_id"));
                intRegister.setDate(rs.getDate("fecha").toLocalDate());
                intRegister.setStartTime(rs.getTime("hora_inicio").toLocalTime());

                this.UsersProcess.add(intRegister);
            }
        }
        catch (SQLException e){
                e.printStackTrace();
        }
        
        return this.UsersProcess;
    }
}
