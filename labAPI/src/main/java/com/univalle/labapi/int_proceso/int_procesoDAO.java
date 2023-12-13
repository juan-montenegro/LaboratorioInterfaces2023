/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso;

import java.sql.Blob;

/**
 *
 * @author juane
 */
public interface int_procesoDAO {

//    /**
//     *
//     * @return
//     */
//    public List<int_proceso> getAllProcess();

    /**
     *
     * @param idProcess
     * @return
     */
    public int_proceso getProcess(int idProcess);

    /**
     *
     * @param name
     * @return
     */
    public int_proceso getProcess(String name);

    /**
     *
     * @param name
     * @param description
     * @param sampleTime
     * @param processTypeId
     * @return
     */
    public int insertProcess(int processTypeId, String name, String description,
            double sampleTime, Blob textFile, Blob manualFile);

    /**
     *
     * @param process2Update
     * @return
     */
    public int updateProcess(int_proceso process2Update);

    /**
     *
     * @param processDB
     * @return
     */
    public int deleteProcess(int_proceso processDB);

    /**
     *
     * @param userID
     * @return
     */
    public int deleteProcess(int userID);
}
