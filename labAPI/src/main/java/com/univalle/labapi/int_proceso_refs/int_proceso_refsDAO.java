/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso_refs;

import java.util.List;

/**
 *
 * @author juane
 */
public interface int_proceso_refsDAO {

    /**
     *
     * @return
     */
    public List<int_proceso_refs> getAllProcessRefs();

    /**
     *
     * @param refId
     * @return
     */
    public int_proceso_refs getProcessRef(int refId);

    /**
     *
     * @param name
     * @return
     */
    public int_proceso_refs getProcessRef(String name);

    /**
     *
     * @param processId
     * @param name
     * @param description
     * @param max
     * @param min
     * @return
     */
    public int insertProcessRef(int processId, String name, 
            String description, double max, double min);

    /**
     *
     * @param processRef
     * @return
     */
    public int updateProcessRef(int_proceso_refs processRef);

    /**
     *
     * @param processRef
     * @return
     */
    public int deleteProcessRef(int_proceso_refs processRef);

    /**
     *
     * @param refId
     * @return
     */
    public int deleteProcessRef(int refId);
}
