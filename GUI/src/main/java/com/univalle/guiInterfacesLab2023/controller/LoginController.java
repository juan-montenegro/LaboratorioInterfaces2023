/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.guiInterfacesLab2023.controller;

import com.univalle.guiInterfacesLab2023.view.LoginView;
import com.univalle.labapi.LabAPI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author juane
 */
public class LoginController implements ActionListener {
    private LoginView loginView;
    
    public LoginController(LoginView loginView){
        this.loginView = loginView;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = "camilo";
        String password = "1234";
        LabAPI labApi = new LabAPI(user, password);
        labApi.usuarios.getUser(user, "pepito");
    }
    
    private void addActionListeners(){
        loginView.getLoginButton().addActionListener(this);
    }
    
    
}
