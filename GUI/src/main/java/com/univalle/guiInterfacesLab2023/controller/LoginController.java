/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.guiInterfacesLab2023.controller;

import com.univalle.guiInterfacesLab2023.view.LoginView;
import com.univalle.guiInterfacesLab2023.view.MainView;
import com.univalle.labapi.LabAPI;
import com.univalle.labapi.int_usuarios.int_usuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author juane
 */
public class LoginController extends WindowAdapter implements ActionListener {
    private final LoginView loginView;
    private final MainView mainView;
    
//    private final String userDB = "watz";
//    private final String passwordDB = "LbMzojJXDx_ZYWEq";
    private final String userDB = "camilo";
    private final String passwordDB = "docWHn9LCLk7N98@";
    
    public LoginController(LoginView loginView, MainView mainView){
        this.loginView = loginView;
        this.mainView = mainView;
        addWindowListener();
        
    }

    private void addWindowListener() {
        this.loginView.addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == loginView.getLoginButton()){
            String user = loginView.getUserField().getText();
            String password = String.valueOf(loginView.getPasswordField().getPassword());
            int_usuarios usuario = doLogin(user, password);
            if (usuario != null) {
                DatabaseController.setCurrentUser(usuario);
                DatabaseController.getAPI().usuariosProcesos.insertNewRegister(
                                usuario.getId(), 
                                3, 
                                LocalDate.now(), 
                                LocalTime.now(), 
                                LocalTime.now(), 
                                1
                        );
                
                loginView.setVisible(false);
                mainView.setVisible(true);
            }
        }
        
    }
    
    public void addActionListeners(){
        loginView.getLoginButton().addActionListener(this);
    }
    
    private int_usuarios doLogin(String user, String password){
        LabAPI labAPI;
        int_usuarios usuario = null;
        try {
            labAPI = new LabAPI(userDB, passwordDB);
            DatabaseController.initController(labAPI);
            usuario = labAPI.usuarios.getLoginUser(user, password);
          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(loginView, "Error al iniciar sesión.");
            Logger.getLogger(LoginController.class.getName())
                    .log(Level.SEVERE, null, ex);   
        } 
        
        return usuario;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        System.exit(0);
    }
    
}
