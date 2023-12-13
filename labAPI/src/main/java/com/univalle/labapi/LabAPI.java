/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.univalle.labapi;

import com.univalle.labapi.database.Database;
import com.univalle.labapi.int_usuarios_proceso.int_usuarios_procesoDAOImpl;

/**
 *
 * @author juane
 */
public class LabAPI {
    public Database database;
    public int_usuarios_procesoDAOImpl usuarios;
    public int_usuarios_procesoDAOImpl usuariosProcesos;

    public LabAPI(String user, String password) {
        database = new Database(user, password);
    }
    
    

//    public static void main(String[] args) {
//        System.out.println("Hello World!");
//    }
    
}
