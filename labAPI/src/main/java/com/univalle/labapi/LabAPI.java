/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.univalle.labapi;

import com.univalle.labapi.database.Database;
import com.univalle.labapi.int_proceso.int_procesoDAOImpl;
import com.univalle.labapi.int_proceso_refs.int_proceso_refsDAOImpl;
import com.univalle.labapi.int_proceso_refs_data.int_proceso_refs_dataDAOImpl;
import com.univalle.labapi.int_proceso_vars.int_proceso_varsDAOImpl;
import com.univalle.labapi.int_proceso_vars_data.int_proceso_vars_dataDAOImpl;
import com.univalle.labapi.int_usuarios.int_usuariosDAOImp;
import com.univalle.labapi.int_usuarios_proceso.int_usuarios_procesoDAOImpl;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juane
 */
public class LabAPI {
    public Database database;
    public int_usuariosDAOImp usuarios;
    public int_usuarios_procesoDAOImpl usuariosProcesos;
    public int_procesoDAOImpl proceso;
    public int_proceso_refsDAOImpl procesoRefs;
    public int_proceso_refs_dataDAOImpl procesoRefsData;
    public int_proceso_varsDAOImpl procesoVars;
    public int_proceso_vars_dataDAOImpl procesoVarsData;

    public LabAPI(String user, String password) throws SQLException {
        database = new Database(user, password);
        try {
            database.initConnection();
        } catch (SQLException ex) {
            Logger.getLogger(LabAPI.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        Connection connection = database.getConnection();
        usuarios = new int_usuariosDAOImp(connection);
        usuariosProcesos = new int_usuarios_procesoDAOImpl(connection);
        
        proceso = new int_procesoDAOImpl(connection);
        procesoRefs = new int_proceso_refsDAOImpl(connection);
        procesoRefsData = new int_proceso_refs_dataDAOImpl(connection);
        procesoVars = new int_proceso_varsDAOImpl(connection);
        procesoVarsData = new int_proceso_vars_dataDAOImpl(connection);
        
    }
    
    

//    public static void main(String[] args) {
//        System.out.println("API START!");
//    }
    
}
