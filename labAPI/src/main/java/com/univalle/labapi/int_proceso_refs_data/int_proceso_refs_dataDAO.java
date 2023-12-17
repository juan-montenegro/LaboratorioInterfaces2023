/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univalle.labapi.int_proceso_refs_data;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 *
 * @author Juan Esteban Montenegro
 * @author Juan Camilo Chavez
 * @author Juan David Beltran
 */
public interface int_proceso_refs_dataDAO {

    /**
     *
     * @param processId
     * @return
     */
    public List<int_proceso_refs_data> getAllRefDataForProcess(int processId);

    /**
     *
     * @param processId
     * @return
     */
    public int_proceso_refs_data getRefDataForProcess(int processId);

    /**
     *
     * @param Id
     * @return
     */
    public int_proceso_refs_data getRefDataForId(int Id);
    
    /**
     *
     * @param processRefId
     * @param value
     * @param time
     * @param date
     * @param localTime
     * @return
     */
    public int insertRefData(int processRefId, double value, 
            double time, Date date, Time localTime);

    /**
     *
     * @param refData
     * @return
     */
    public int updateRefData(int_proceso_refs_data refData);

    /**
     *
     * @param refData
     * @return
     */
    public int deleteRefData(int_proceso_refs_data refData);

    /**
     *
     * @param id
     * @return
     */
    public int deleteRefData(int id);
}
