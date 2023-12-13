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
import java.util.logging.Level;
import java.util.logging.Logger;


public class int_usuarios_procesoDAOImpl implements int_usuarios_procesoDAO {

    private Connection conectionDb = null;
    private String queryTotal = "SELECT `id`, `int_proceso_id`, `int_usuarios_id`, `fecha`, `hora_inicio`, `hora_fin`, `hits` FROM `int_usuarios_proceso`";
    private String queryInsert = "INSERT INTO `int_usuarios_proceso`(`id`, `int_proceso_id`, `int_usuarios_id`, `fecha`, `hora_inicio`, `hora_fin`, `hits`) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private PreparedStatement stmP = null;
    private Statement stmS = null;
    private List <int_usuarios_proceso> UsersProcess;
    private List <int_usuarios_proceso> procesos;
    private List <int_usuarios_proceso> usuarios;
    private List <int_usuarios_proceso> procesosFecha;
    private List <int_usuarios_proceso> usuariosFecha;
    private int hitsForDate = 0;
    
    public int_usuarios_procesoDAOImpl(Connection conectionDb){
		
        this.conectionDb = conectionDb;

        try
        {
            this.stmS = this.conectionDb.createStatement();
            ResultSet rs = stmS.executeQuery(this.queryTotal+";");
            while (rs.next())
            {
                int_usuarios_proceso intRegister = new int_usuarios_proceso(rs.getInt("int_proceso_id"), rs.getInt("int_usuarios_id"), rs.getDate("fecha"), rs.getTime("hora_inicio"), rs.getTime("hora_fin"), rs.getInt("hits"));
                intRegister.setUserId(rs.getInt("int_usuarios_id"));
                intRegister.setProcessId(rs.getInt("int_proceso_id"));
                intRegister.setDate(rs.getDate("fecha").toLocalDate());
                intRegister.setStartTime(rs.getTime("hora_inicio").toLocalTime());

                this.UsersProcess.add(intRegister);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(int_usuarios_procesoDAOImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
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

    @Override
    public int insertNewRegister(int usuario, int proceso, LocalDate fecha, LocalTime hora_inicio, LocalTime hora_fin, int hits) {
        int result = 0;
        try{
            this.stmP = this.conectionDb.prepareStatement(this.queryInsert);
            // Estableciendo los valores de los parámetros
            stmP.setInt(1, usuario);
            stmP.setInt(2, proceso);
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
