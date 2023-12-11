/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.univalle.guiInterfacesLab2023.view;
import com.univalle.guiInterfacesLab2023.controller.MainFrameController;
import com.univalle.guiInterfacesLab2023.view.components.LineChartPanel;
import com.univalle.guiInterfacesLab2023.controller.SerialController;
import com.univalle.guiInterfacesLab2023.model.SignalData;
import java.awt.BorderLayout;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author juane
 */
public class MainView extends JFrame {
    private final MainFrameController mainFrameController;
    private final String COMM_PORT = "COM9";
    private int timeMues = 0;
    private String selectedAnSignal = "";
    private String selectedDgSignal = "";
    private String tittleChart = "Signal";
    private final SignalData data;
    
    
    private final ScheduledExecutorService scheduler;
    
    private Runnable plotRunnable;
    private final SerialController serialController;
    private boolean primero = true;
    
    private final XYSeries grafica;
    private XYSeriesCollection dataset;
    private final LineChartPanel newChart; 
    private final int NUM_VALUES = 200;
    
    double t = 0;
    
    /**
     * Creates new form myGui
     */
    public MainView() {
        initComponents();
        data = new SignalData();
        
        scheduler = Executors.newScheduledThreadPool(1);
        
        newChart = new LineChartPanel(timeMues,tittleChart, dataset );
        grafica = new XYSeries("Signal");
        
        mainFrameController = new MainFrameController(this);
        mainFrameController.addActionListeners();
        mainFrameController.addItemListeners();
        mainFrameController.setSerialCom();
        
        grafica.add(0,0);
        
        dataset = new XYSeriesCollection();
        dataset.addSeries(grafica);
        
        serialController = new SerialController (COMM_PORT);
        
        //LineChartP newChart = new LineChartP(timeMues,tittleChart, dataset );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        seleccionSenal = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        comboSignalD = new javax.swing.JComboBox<>();
        comboSignalA = new javax.swing.JComboBox<>();
        sampleRateTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        signalD = new javax.swing.JRadioButton();
        signalA = new javax.swing.JRadioButton();
        SelectButton = new javax.swing.JButton();
        DO0 = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        DO1 = new javax.swing.JToggleButton();
        DO2 = new javax.swing.JToggleButton();
        DO3 = new javax.swing.JToggleButton();
        jLabel4 = new javax.swing.JLabel();
        lineChartPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        comboSignalD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "D0", "D1", "D2", "D3" }));

        comboSignalA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7" }));

        sampleRateTextField.setText("100");
        sampleRateTextField.setToolTipText("");

        jLabel3.setText("Tiempo de muestreo");

        seleccionSenal.add(signalD);
        signalD.setText("Señales Digitales");

        seleccionSenal.add(signalA);
        signalA.setText("Señales Analógicas");

        SelectButton.setBackground(new java.awt.Color(204, 255, 204));
        SelectButton.setText("Seleccionar");
        SelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectButtonActionPerformed(evt);
            }
        });

        DO0.setText("DO0");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel1.setText("Inputs Signals");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel2.setText("Outputs DIgital Signals");

        DO1.setText("DO1");

        DO2.setText("DO2");

        DO3.setText("DO3");

        jLabel4.setText("ms");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(signalD, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(comboSignalA, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(comboSignalD, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(DO2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(DO3))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(DO0)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(DO1)))))
                        .addGap(0, 24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(sampleRateTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(SelectButton))
                .addGap(17, 17, 17))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(signalA))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(signalA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboSignalA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(signalD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboSignalD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sampleRateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(12, 12, 12)
                .addComponent(SelectButton)
                .addGap(35, 35, 35)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DO0, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DO1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DO2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DO3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout lineChartPanelLayout = new javax.swing.GroupLayout(lineChartPanel);
        lineChartPanel.setLayout(lineChartPanelLayout);
        lineChartPanelLayout.setHorizontalGroup(
            lineChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 547, Short.MAX_VALUE)
        );
        lineChartPanelLayout.setVerticalGroup(
            lineChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 436, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lineChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lineChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectButtonActionPerformed
        data.clear();
        
        timeMues = (int) getSampleRate();  
        
        System.out.println("Tiempo de Muestreo: " + timeMues);  // Imprime el valor
        
        lineChartPanel.removeAll();   // 2. Elimina todos los componentes del lineChartPanel
        lineChartPanel.add(newChart); // 3. Añade la nueva instancia de LineChartP a lineChartPanel
        lineChartPanel.revalidate();  // 4. Revalida el lineChartPanel para que se tenga en cuenta cualquier cambio en la estructura de componentes.
        lineChartPanel.repaint();
        lineChartPanel.setLayout(new BorderLayout());
        lineChartPanel.add(newChart, BorderLayout.CENTER);

        if(signalA.isSelected()){
           System.out.println("Señal seleccionada: "+ selectedAnSignal);
           tittleChart = selectedAnSignal;
                   
        }
        else if(signalD.isSelected()){
            System.out.println("Señal seleccionada: "+ selectedDgSignal);
            tittleChart = selectedDgSignal;
        }
        else{JOptionPane.showMessageDialog(null, "Error:  Seleccione una señal", "Señal Error", JOptionPane.ERROR_MESSAGE);}
        
        serialController.sendText("T"+timeMues+","+tittleChart);
        System.out.println("T"+timeMues+","+tittleChart);
        
        if(primero){
            grafica.remove(0);

            plotRunnable = () -> {
                if(serialController.newAnalogData || serialController.newDigitalByte){
                    
                    data.addTime(t);
                    
                    if(tittleChart.charAt(0)=='A'){
                        System.out.println("PLOT");
                        data.addSignal((double)serialController.readAnalog);
                        grafica.add(t ,(double)serialController.readAnalog);
                        
                        
                    }
                    
                    else if(tittleChart.charAt(0)=='D'){
                        data.addSignal((double)serialController.readDigital);
                        grafica.add(t ,(double)serialController.readDigital);
                    }
                    
                    if (grafica.getItemCount() > NUM_VALUES) {
                        grafica.remove(0);
                    }
                    newChart.updateDataset(tittleChart, dataset);
                    
                    lineChartPanel.repaint();
                    
                    t += timeMues;
                    System.out.println(t);
                    
                    if(tittleChart.charAt(0)=='A'){
                        serialController.newAnalogData = false;
                    } else if(tittleChart.charAt(0)=='D'){
                        serialController.newDigitalByte = false;
                    }
                    
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
    }//GEN-LAST:event_SelectButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton DO0;
    private javax.swing.JToggleButton DO1;
    private javax.swing.JToggleButton DO2;
    private javax.swing.JToggleButton DO3;
    private javax.swing.JButton SelectButton;
    private javax.swing.JComboBox<String> comboSignalA;
    private javax.swing.JComboBox<String> comboSignalD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel lineChartPanel;
    private javax.swing.JTextField sampleRateTextField;
    private javax.swing.ButtonGroup seleccionSenal;
    private javax.swing.JRadioButton signalA;
    private javax.swing.JRadioButton signalD;
    // End of variables declaration//GEN-END:variables
    
    
    private double getSampleRate() {
        String timeS = sampleRateTextField.getText();
        double tiempoMues = 0.0;
        try {
            tiempoMues = Double.parseDouble(timeS);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: '" + timeS+ "' no es un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return tiempoMues;
    }

    public JToggleButton getDO0() {
        return DO0;
    }

    public JToggleButton getDO1() {
        return DO1;
    }

    public JToggleButton getDO2() {
        return DO2;
    }

    public JToggleButton getDO3() {
        return DO3;
    }

    public JButton getSelectButton() {
        return SelectButton;
    }

    public JComboBox<String> getComboSignalA() {
        return comboSignalA;
    }

    public JComboBox<String> getComboSignalD() {
        return comboSignalD;
    }

    public JTextField getSampleRateTextField() {
        return sampleRateTextField;
    }

    public ButtonGroup getSeleccionSenal() {
        return seleccionSenal;
    }

    public JRadioButton getSignalA() {
        return signalA;
    }

    public JRadioButton getSignalD() {
        return signalD;
    }
    
    public void setSelectedAnSignal(String selectedAnSignal){
        this.selectedAnSignal = selectedAnSignal;
    }
    
    public void setSelectedDgSignal(String selectedDgSignal){
        this.selectedDgSignal = selectedDgSignal;
    }
}