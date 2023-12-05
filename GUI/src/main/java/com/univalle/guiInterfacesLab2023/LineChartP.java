/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.guiInterfacesLab2023;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @authores: 
 * Juan David Beltran
 * Juan Esteban Montenegro
 *Juan Camilo Chavez 
 * Fecha: 4/10/2023
 */
public final class LineChartP extends JPanel{
    JFreeChart lineChart;
    
    //private final int NUM_VALUES = 100;

    //private double[][] sineValues;
    //private double[][] cosineValues;
    //private DefaultXYDataset dataset;

    /*public void createDataset(double tiempoMues, CircularBuffer buffer) {
        double[][] chartData = new double[2][NUM_VALUES]; // Two rows: one for X, one for Y
        double[] bufferData = buffer.getAllValues(); // The Y-axis data
        double t = 0.0;

        // Iterate over the buffer data to create the data points for the chart
        for (int i = 0; i < bufferData.length; i++) {
            chartData[0][i] = t;             // X-axis will be incremented by tiempoMues each time
            chartData[1][i] = bufferData[i]; // Y-axis data from buffer
            t += tiempoMues;
        }

        dataset = new DefaultXYDataset();
        dataset.addSeries("COM Port Data", chartData);
    }*/
    
   /*  public void createDataset(double tiempoMues) {

        sineValues = new double[2][NUM_VALUES];
        cosineValues = new double[2][NUM_VALUES];
        double t = 0.0;
        // X values
        for (int i = 0; i < NUM_VALUES; i++) {
            sineValues[0][i] = t;
            cosineValues[0][i] = t;
            t+=tiempoMues;
        }
        // Y values
        for (int i = 0; i < NUM_VALUES; i++) {
            sineValues[1][i] = Math.sin(sineValues[0][i]);
            cosineValues[1][i] = Math.cos(cosineValues[0][i]);
        }

        dataset = new DefaultXYDataset();
        dataset.addSeries("Sine", sineValues);
        dataset.addSeries("Cosine", cosineValues);
    }*/
    public LineChartP(double tiempoMues, String tittle, XYSeriesCollection dataset) {
        
        setLayout(new BorderLayout());
        
        //this.createDataset(tiempoMues, buffer);//HEY CUIDADO AQUI!!!
        //this.createDataset(tiempoMues);
        
        // Create the chart using the dataset
        lineChart = ChartFactory.createXYLineChart(
                tittle, // The chart title
                "Tiempo", // x axis label
                "Amplitud", // y axis label
                dataset, // The dataset for the chart
                PlotOrientation.VERTICAL,
                true, // Is a legend required?
                false, // Use tooltips
                false // Configure chart to generate URLs?
        );
        
         // Personalización del gráfico (solo un ejemplo)
        lineChart.getPlot().setBackgroundPaint(Color.white);
        //... (otras personalizaciones)
        XYPlot plot = lineChart.getXYPlot();
        XYItemRenderer renderer = plot.getRenderer();
        if(tittle.charAt(0)=='A'){
            renderer.setSeriesPaint(0, Color.RED); 
        } 
        else if(tittle.charAt(0)=='D'){
            renderer.setSeriesPaint(0, Color.BLUE); 
        }else{
            renderer.setSeriesPaint(0, Color.BLACK);
        }
        
        plot.setDomainGridlinePaint(Color.black);  // Color negro para las líneas de cuadrícula del dominio (eje X)
        plot.setRangeGridlinePaint(Color.black);  // Color negro para las líneas de cuadrícula del rango (eje Y)
        
       ChartPanel chartPanel = new ChartPanel(lineChart);
        add(chartPanel, BorderLayout.CENTER);
       
    }
    
    public void updateDataset(String title, XYSeriesCollection dataset){
        // Add Chart Series
        lineChart.getXYPlot().setDataset(dataset);
        
        lineChart.setTitle(title);
        XYPlot plot = lineChart.getXYPlot();
        
        // Add Chart Series
        //plot.setDataset(dataset);
        
        XYItemRenderer renderer = plot.getRenderer();
        
        if(title.startsWith("A")){
            renderer.setSeriesPaint(0, Color.RED); 
            
        } 
        else if(title.charAt(0)=='D'){
            renderer.setSeriesPaint(0, Color.BLUE); 
        }else{
            renderer.setSeriesPaint(0, Color.BLACK);
        }
    }
    
     /*public DefaultXYDataset getDataset(){
         return this.dataset;
     }*/
}
