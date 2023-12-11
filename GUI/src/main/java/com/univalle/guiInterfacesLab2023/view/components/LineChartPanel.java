/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.guiInterfacesLab2023.view.components;

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
 * Juan Camilo Chavez 
 * Fecha: 4/10/2023
 */

public final class LineChartPanel extends JPanel {
    private final JFreeChart lineChart;
       
    public LineChartPanel(double tiempoMues, String tittle, XYSeriesCollection dataset) {
        
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
        switch (tittle.charAt(0)) {
            case 'A':
                renderer.setSeriesPaint(0, Color.RED);
                break;
            case 'D':
                renderer.setSeriesPaint(0, Color.BLUE);
                break;
            default:
                renderer.setSeriesPaint(0, Color.BLACK);
                break;
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
