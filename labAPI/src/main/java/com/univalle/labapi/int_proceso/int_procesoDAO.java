/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso;

import java.sql.Blob;

/**
 *
 * @author Juan Esteban Montenegro
 * @author Juan Camilo Chavez
 * @author Juan David Beltran
 */
public interface int_procesoDAO {

    /**
     *
     * @param idProcess
     */
    public int_proceso getProcess(int idProcess);

    /**
     *
     * @param name
     */
    public int_proceso getProcess(String name);

    /**
     *
     * @param name
     * @param description
     * @param sampleTime
     * @param processTypeId
     */
    public int insertProcess(int processTypeId, String name, String description,
            double sampleTime, Blob textFile, Blob manualFile);

    /**
     *
     * @param process2Update
     */
    public int updateProcess(int_proceso process2Update);

    /**
     *
     * @param processDB
     */
    public int deleteProcess(int_proceso processDB);

    /**
     *
     * @param userID
     */
    public int deleteProcess(int userID);
}
