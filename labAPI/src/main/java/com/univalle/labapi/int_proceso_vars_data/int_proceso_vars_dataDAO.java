/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univalle.labapi.int_proceso_vars_data;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 *
 * @author juane
 */
public interface int_proceso_vars_dataDAO {
    
    /**
     *
     * @param processId
     * @return
     */
    public List<int_proceso_vars_data> getAllVarDataForProcess(int processId);

    /**
     *
     * @param processId
     * @return
     */
    public List<int_proceso_vars_data> getVarDataForProcess(int processId);

    /**
     *
     * @param Id
     * @return
     */
    public int_proceso_vars_data getVarDataForId(int Id);
    
    /**
     *
     * @param processVarId
     * @param value
     * @param time
     * @param date
     * @param localTime
     * @return
     */
    public int insertVarData(int processVarId, double value, 
            double time, Date date, Time localTime);

    /**
     *
     * @param refData
     * @return
     */
    public int updateVarData(int_proceso_vars_data refData);

    /**
     *
     * @param refData
     * @return
     */
    public int deleteVarData(int_proceso_vars_data refData);

    /**
     *
     * @param id
     * @return
     */
    public int deleteVarData(int id);
}
