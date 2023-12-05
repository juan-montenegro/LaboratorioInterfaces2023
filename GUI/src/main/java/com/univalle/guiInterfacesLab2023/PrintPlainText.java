/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.guiInterfacesLab2023;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.jfree.data.xy.XYSeriesCollection;


/**
 *
 * @author juane
 */
public class PrintPlainText {
    
    public static void saveToPlainText(String fileName, int serie, XYSeriesCollection dataset) throws IOException {
        BufferedWriter outputWriter;
        int len = dataset.getItemCount(0);
        double stamp = dataset.getYValue(serie, len);
        outputWriter = new BufferedWriter(new FileWriter(fileName+"_"+stamp+".txt"));
        for (int i = 0; i < dataset.getItemCount(0); i++) {
            // Maybe:
            outputWriter.write(dataset.getXValue(serie, i)+"\t\t");
            outputWriter.write(dataset.getYValue(serie, i)+"\t\t");
            outputWriter.newLine();
         }
        outputWriter.flush();  
        outputWriter.close();                 
    }
    
}
