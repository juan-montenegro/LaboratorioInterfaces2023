/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.laboratoriointerfaces2023;

import com.univalle.laboratoriointerfaces2023.presentation.MyGui;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultXYDataset;

/**
 *
 * @authores: 
 * Juan David Beltran
 * Juan Esteban Montenegro
 *Juan Camilo Chavez 
 * Fecha: 4/10/2023
 */
public class LineChartP extends JPanel{
    
    public static final int NUM_VALUES = 100;

    public static double[][] sineValues;
    public static double[][] cosineValues;

    static public DefaultXYDataset createDataset(double tiempoMues) {

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

        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("Sine", sineValues);
        dataset.addSeries("Cosine", cosineValues);
        return dataset;
    }

    public LineChartP(double tiempoMues) {
        
        setLayout(new BorderLayout());
        // Create the chart using the dataset
        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "Sine / Cosine Curves", // The chart title
                "X", // x axis label
                "Y", // y axis label
                createDataset(tiempoMues), // The dataset for the chart
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
        renderer.setSeriesPaint(0, Color.RED);   // Color rojo para "Sine"
        renderer.setSeriesPaint(1, Color.BLUE);  // Color azul para "Cosine"
        plot.setDomainGridlinePaint(Color.black);  // Color verde para las líneas de cuadrícula del dominio (eje X)
        plot.setRangeGridlinePaint(Color.black);  // Color amarillo para las líneas de cuadrícula del rango (eje Y)
        
       ChartPanel chartPanel = new ChartPanel(lineChart);
        add(chartPanel, BorderLayout.CENTER);
       
    }
}
