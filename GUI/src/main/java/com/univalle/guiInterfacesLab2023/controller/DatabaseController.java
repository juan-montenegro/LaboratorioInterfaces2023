/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.guiInterfacesLab2023.controller;

import com.univalle.labapi.LabAPI;
import com.univalle.labapi.int_usuarios.int_usuarios;

/**
 *
 * @author juane 
 */
public class DatabaseController {
    private static DatabaseController instance = null;
    private static LabAPI labAPI = null;
    private static int_usuarios currentUser = null;
    

    private DatabaseController(LabAPI api) {
        labAPI = api;
    }
    
    public static DatabaseController initController(LabAPI labAPI){
        if (instance == null){
            instance = new DatabaseController(labAPI);
        }
        return instance;
    }
    
    public static LabAPI getAPI() {
        return labAPI;
    }

    public static void setAPI(LabAPI api) {
        labAPI = api;
    }
    

    public static int_usuarios getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(int_usuarios user) {
        currentUser = user;
    }
    
    
}
