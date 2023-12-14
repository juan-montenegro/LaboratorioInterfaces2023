/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.guiInterfacesLab2023.controller;

import com.univalle.guiInterfacesLab2023.view.MainView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;

/**
 *
 * @author juane
 */
public class MainViewController implements ActionListener, ItemListener {
    private final Color OFF_COLOR = new Color(187,187,187);
    private final Color ON_COLOR = new Color(251, 208, 62);
    private final String COMM_PORT = "COM9";
    
    private final MainView mainView;
    private SerialController controller;
    
    public MainViewController(MainView mainView){
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainView.getSelectButton()){
        
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {        
        if (e.getSource()== mainView.getDO0()){
            stateChanged(e, mainView.getDO0(), controller);
            
        } else if (e.getSource() == mainView.getDO1()){
            stateChanged(e, mainView.getDO1(), controller);
            
        } else if (e.getSource() == mainView.getDO2()){
            stateChanged(e, mainView.getDO2(), controller);
            
        } else if (e.getSource() == mainView.getDO3()){
            stateChanged(e, mainView.getDO3(), controller);
            
        } else if (e.getSource() == mainView.getComboSignalA()){
            JComboBox cb = (JComboBox)e.getSource();
            String item = (String)cb.getSelectedItem();
            mainView.setSelectedAnSignal(item);
            
        } else if (e.getSource() == mainView.getComboSignalD()){
            JComboBox cb = (JComboBox)e.getSource();
            String item = (String)cb.getSelectedItem();
            mainView.setSelectedDgSignal(item);
            
        }
    }
    
    public void addActionListeners(){
        mainView.getSelectButton().addActionListener(this);
    }
    
    public void addItemListeners(){              
        mainView.getDO0().addItemListener(this);
        mainView.getDO1().addItemListener(this);
        mainView.getDO2().addItemListener(this);
        mainView.getDO3().addItemListener(this);
        
        mainView.getComboSignalA().addItemListener(this);
        mainView.getComboSignalD().addItemListener(this);
    }
    
    private void stateChanged(ItemEvent e, JToggleButton item, SerialController controller){
        if(e.getStateChange() == ItemEvent.SELECTED){
            item.setBackground(ON_COLOR);
            controller.sendText(item.getText()+"1");
            System.out.println(item.getText()+"1");
        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
            item.setBackground(OFF_COLOR);
            controller.sendText(item.getText()+"0");
            System.out.println(item.getText()+"0");            
        }
    }
    
    public void setSerialCom(){
        controller = new SerialController(COMM_PORT);
    }
}
