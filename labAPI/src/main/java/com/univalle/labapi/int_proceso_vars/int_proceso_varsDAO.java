/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univalle.labapi.int_proceso_vars;

import java.util.List;

/**
 *
 * @author Juan Esteban Montenegro
 * @author Juan Camilo Chavez
 * @author Juan David Beltran
 */
public interface int_proceso_varsDAO {
    /**
     *
     * @return
     */
    public List<int_proceso_vars> getAllProcessVars();

    /**
     *
     * @param refId
     * @return
     */
    public int_proceso_vars getProcessVar(int refId);

    /**
     *
     * @param name
     * @return
     */
    public int_proceso_vars getProcessVar(String name);
    
    public int_proceso_vars getProcessVars(boolean flag);

    /**
     *
     * @param processId
     * @param name
     * @param description
     * @param max
     * @param min
     * @return
     */
    public int insertProcessVar(int processId, String name, 
            String description, double max, double min);

    /**
     *
     * @param processRef
     * @return
     */
    public int updateProcessVar(int_proceso_vars processRef);

    /**
     *
     * @param processRef
     * @return
     */
    public int deleteProcessVar(int_proceso_vars processRef);

    /**
     *
     * @param refId
     * @return
     */
    public int deleteProcessVar(int refId);
    
}
