/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.guiInterfacesLab2023.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camilo, montenegro
 */
public class SignalData {
    
    private final ArrayList<Double> signal;
    private final ArrayList<Double> time;

    public SignalData(){
        signal = new ArrayList<>();
        time = new ArrayList<>();
    }


    public void clear(){
        signal.clear();
        time.clear();
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

    
    
}
