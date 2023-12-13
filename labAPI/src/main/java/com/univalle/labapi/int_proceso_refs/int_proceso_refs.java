/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso_refs;

/**
 *
 * @author juane
 */
public class int_proceso_refs {
    /**
     * Field id, primary key.
     */
    private int idpk;
    /**
     * 
     */
    private int int_proceso_id_fk;
    /**
     * 
     */
    private String nombre;
    /**
     * 
     */
    private String descripcion;
    /**
     * 
     */
    private double max_2;
    /**
     * 
     */
    private double min;
    /**
     * 
     */
    private boolean flag;

    /**
     *
     * @param processId
     * @param name
     * @param description
     * @param max
     * @param min
     */
    public int_proceso_refs(int processId, String name, 
            String description, double max, double min) {    
        this.int_proceso_id_fk = processId;
        this.nombre = name;
        this.descripcion = description;
        this.max_2 = max;
        this.min = min;
        this.flag = false;
    }
    
    /**
     *
     * @param processId
     * @param name
     * @param description
     * @param max
     * @param min
     * @param flag
     */
    public int_proceso_refs(int processId, String name, 
            String description, double max, double min, boolean  flag) {    
        this.int_proceso_id_fk = processId;
        this.nombre = name;
        this.descripcion = description;
        this.max_2 = max;
        this.min = min;
        this.flag = flag;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return idpk;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.idpk = id;
    }

    /**
     *
     * @return
     */
    public int getProcessId() {
        return int_proceso_id_fk;
    }

    /**
     *
     * @param processId
     */
    public void setProcessId(int processId) {
        this.int_proceso_id_fk = processId;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return nombre;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.nombre = name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return descripcion;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.descripcion = description;
    }

    /**
     *
     * @return
     */
    public double getMax() {
        return max_2;
    }

    /**
     *
     * @param max
     */
    public void setMax(double max) {
        this.max_2 = max;
    }

    /**
     *
     * @return
     */
    public double getMin() {
        return min;
    }

    /**
     *
     * @param min
     */
    public void setMin(double min) {
        this.min = min;
    }

    /**
     *
     * @return
     */
    public boolean isFlag() {
        return this.flag;
    }

    /**
     *
     * @param flag
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
       
    
}
