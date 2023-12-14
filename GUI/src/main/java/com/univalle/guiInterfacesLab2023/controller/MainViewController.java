/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.guiInterfacesLab2023.controller;

import com.univalle.guiInterfacesLab2023.model.SignalData;
import com.univalle.guiInterfacesLab2023.view.MainView;
import com.univalle.guiInterfacesLab2023.view.LineChartPanel;
import com.univalle.labapi.LabAPI;
import com.univalle.labapi.int_proceso.int_proceso;
import com.univalle.labapi.int_proceso_refs.int_proceso_refs;
import com.univalle.labapi.int_proceso_refs_data.int_proceso_refs_data;
import com.univalle.labapi.int_proceso_refs_data.int_proceso_refs_dataDAOImpl;
import com.univalle.labapi.int_proceso_vars.int_proceso_vars;
import com.univalle.labapi.int_proceso_vars_data.int_proceso_vars_data;
import com.univalle.labapi.int_proceso_vars_data.int_proceso_vars_dataDAOImpl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author juane
 */
public class MainViewController implements ActionListener, ItemListener  {
    private final Color OFF_COLOR = new Color(187,187,187);
    private final Color ON_COLOR = new Color(251, 208, 62);
    private LabAPI api;
    private int_proceso_refs_dataDAOImpl refsController;
    private int_proceso_vars_dataDAOImpl varsController;
    private int_proceso_vars vars;
    private int_proceso_refs refs;
    private int timeMues = 0;
    private String selectedAnSignal = "";
    private String selectedDgSignal = "";
    private String tittleChart = "Signal";
    private final SignalData data;
    
    
    private final ScheduledExecutorService scheduler;
    
    private Runnable plotRunnable;
//    private final SerialController serialController;
    private boolean primero = true;
    
    private final XYSeries grafica;
    private final XYSeriesCollection dataset;
    private final LineChartPanel newChart; 
    private final int NUM_VALUES = 200;
   
    double t = 0;

    private final MainView mainView;
    
    public MainViewController(MainView mainView){
        this.mainView = mainView;
        
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.grafica = new XYSeries("Signal");
        this.grafica.add(0,0);
        this.dataset = new XYSeriesCollection();
        this.dataset.addSeries(grafica);
        this.newChart = new LineChartPanel(timeMues,tittleChart, dataset);
        
        this.data = new SignalData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(varsController == null) setVarsController();
        if (e.getSource() == mainView.getSelectButton()){
            buttonPressed();
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(refsController == null)setRefsController();
        if (e.getSource()== mainView.getDO0()){
            stateChanged(e, mainView.getDO0());
            
        } else if (e.getSource() == mainView.getDO1()){
            stateChanged(e, mainView.getDO1());
            
        } else if (e.getSource() == mainView.getDO2()){
            stateChanged(e, mainView.getDO2());
            
        } else if (e.getSource() == mainView.getDO3()){
            stateChanged(e, mainView.getDO3());
            
        } else if (e.getSource() == mainView.getComboSignalA()){
            JComboBox cb = (JComboBox)e.getSource();
            String item = (String)cb.getSelectedItem();
            setSelectedAnSignal(item);
            
        } else if (e.getSource() == mainView.getComboSignalD()){
            JComboBox cb = (JComboBox)e.getSource();
            String item = (String)cb.getSelectedItem();
            setSelectedDgSignal(item);
            
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
    
    private void stateChanged(ItemEvent e, JToggleButton item){
        int_proceso_refs_data refData = new int_proceso_refs_data(
                0, 
                0, 
                0, 
                Date.valueOf(LocalDate.now()), 
                Time.valueOf(LocalTime.now())
        );
        if(e.getStateChange() == ItemEvent.SELECTED){
            item.setBackground(ON_COLOR);
            
            refData.setValue(1);
            refsController.updateRefData(refData);
            System.out.println(item.getText()+"1");
            
        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
            item.setBackground(OFF_COLOR);
            refData.setValue(0);
            refsController.updateRefData(refData);
            System.out.println(item.getText()+"0");            
        }
        
    }
    
    private void buttonPressed(){
        data.clear();
        JPanel lineChartPanel = mainView.getLineChartPanel();
        
        if(vars != null) {
            vars.setFlag(false);
            api.procesoVars.updateProcessVar(vars);
        }
        
        
        
        timeMues = (int) getSampleRate();  
        
        System.out.println("Tiempo de Muestreo: " + timeMues);  // Imprime el valor
        
        lineChartPanel.removeAll();   // 2. Elimina todos los componentes del lineChartPanel
        lineChartPanel.setLayout(new BorderLayout());
        lineChartPanel.add(newChart); // 3. Añade la nueva instancia de LineChartP a lineChartPanel
        lineChartPanel.revalidate();  // 4. Revalida el lineChartPanel para que se tenga en cuenta cualquier cambio en la estructura de componentes.
        lineChartPanel.repaint();
        
        if(mainView.getSignalA().isSelected()){
           System.out.println("Señal seleccionada: "+ selectedAnSignal);
           tittleChart = selectedAnSignal;
                   
        } else if(mainView.getSignalD().isSelected()){
            System.out.println("Señal seleccionada: "+ selectedDgSignal);
            tittleChart = selectedDgSignal;
            
        } else{
            int result = JOptionPane.showConfirmDialog(
                    null, 
                    "Error:  Seleccione una señal", 
                    "Señal Error", 
                    JOptionPane.OK_OPTION
            );
            if (result == JOptionPane.OK_OPTION) return;
        }
        
        if (api == null){
            setAPI();
        }
        int_proceso proceso = api.proceso.getProcess(3);
        proceso.setSampleTime(timeMues);
        api.proceso.updateProcess(proceso);
        System.out.println(proceso.toString());
        
        vars = api.procesoVars.getProcessVar(tittleChart);
        if (refs != null) {
            refs.setFlag(false);
            api.procesoRefs.updateProcessRef(refs);
        }
        vars.setFlag(true);

        System.out.println(vars.toString());
        
        api.procesoVars.updateProcessVar(vars);
        
        
//        for (int_proceso_vars_data data : varsData) {
//            System.out.println(data.toString());   
//        }
       
//        serialController.sendText("T"+timeMues+","+tittleChart);
        System.out.println("T"+timeMues+","+tittleChart);
        int_proceso_vars_dataDAOImpl vDAOImpl = DatabaseController.getAPI().procesoVarsData;
        
        if(primero){
            grafica.remove(0);

            plotRunnable = () -> {
                if(vars.isFlag()){
                    int_proceso_vars_data varsData = api.procesoVarsData.getVarDataForId(vars.getId());

                    data.addTime(t);
                    
                    System.out.println("PLOT");
                    data.addSignal(varsData.getValue());
                    grafica.add(t ,varsData.getValue());
                    
                    if (grafica.getItemCount() > NUM_VALUES) {
                        grafica.remove(0);
                    }
                    newChart.updateDataset(tittleChart, dataset);
                    
                    lineChartPanel.repaint();
                    
                    t += timeMues;
                    System.out.println(t);
                    
                }
            };
            scheduler.scheduleAtFixedRate(plotRunnable, 0, 200, TimeUnit.MILLISECONDS);
            primero = false;
        }
        
        
        //try {
          //  PrintPlainText.saveToPlainText("seno", 0,newChart.getDataset());
          //  PrintPlainText.saveToPlainText("coseno", 1,newChart.getDataset());
        //} catch (IOException ex) {
          //  Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }
    
    private double getSampleRate() {
        String timeS = mainView.getSampleRateTextField().getText();
        double tiempoMues = 0.0;
        try {
            tiempoMues = Double.parseDouble(timeS);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: '" + timeS+ "' no es un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return tiempoMues;
    }
    
    private void setRefsController(){
        LabAPI labAPI = DatabaseController.getAPI();
        if (labAPI == null) return;
        refsController = labAPI.procesoRefsData;
    }
    
    private void setVarsController(){
        LabAPI labAPI = DatabaseController.getAPI();
        if (labAPI == null) return;
        varsController = labAPI.procesoVarsData;
    }
    
    private boolean setAPI(){
        api = DatabaseController.getAPI();
        return api != null;
        
    }
    
    private void setSelectedAnSignal(String selectedAnSignal){
        this.selectedAnSignal = selectedAnSignal;
    }
    
    private void setSelectedDgSignal(String selectedDgSignal){
        this.selectedDgSignal = selectedDgSignal;
    }

       
    
}
