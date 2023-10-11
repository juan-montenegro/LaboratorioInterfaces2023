/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.laboratoriointerfaces2023;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.jfree.data.xy.DefaultXYDataset;


/**
 *
 * @author juane
 */
public class PrintPlainText {
    
    public static void saveToPlainText(String fileName, int serie, DefaultXYDataset dataset) throws IOException {
        BufferedWriter outputWriter;
        outputWriter = new BufferedWriter(new FileWriter(fileName+".txt"));
        for (int i = 0; i < dataset.getItemCount(0); i++) {
            // Maybe:
            outputWriter.write(dataset.getX(serie, i)+"\t\t");
            outputWriter.write(dataset.getY(serie, i)+"\t\t");
            outputWriter.newLine();
         }
        outputWriter.flush();  
        outputWriter.close();                 
    }
    
}
