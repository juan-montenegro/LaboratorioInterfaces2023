/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.guiInterfacesLab2023.model;

import com.univalle.labapi.int_proceso_vars_data.int_proceso_vars_data;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camilo, montenegro
 * 
 */
public class SignalData {
    
    private final ArrayList<Double> signal;
    private final ArrayList<Double> time;
    private final ArrayList<int_proceso_vars_data> dataDB;

    public SignalData(){
        signal = new ArrayList<>();
        time = new ArrayList<>();
        dataDB = new ArrayList<>();
    }


    public void clear(){
        signal.clear();
        time.clear();
        dataDB.clear();
    }
    
    public ArrayList<Double> getSubArrayList(int fromIndex, int toIndex){
        final List<Double> subList = signal.subList(fromIndex, toIndex);
        return new ArrayList(subList);
    }

    public ArrayList<Double> getSignal() {
        return signal;
    }

    public void addSignal(Double s_value) {
        signal.add(s_value);
    }

    public ArrayList<Double> getTime() {
        return time;
    }

    public void addTime(Double t_value) {
        time.add(t_value);
    }

    public ArrayList<int_proceso_vars_data> getDataDB() {
        return dataDB;
    }
    
    public void addDataDB(int_proceso_vars_data dataDB){
        this.dataDB.add(dataDB);
    }
    
    
    
}
