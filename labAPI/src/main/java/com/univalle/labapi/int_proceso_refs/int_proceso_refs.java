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
    Field id, primary key.
    */
    private int idpk;
    private int int_proceso_id_fk;
    private String nombre;
    private String descripcion;
    private double max_2;
    private double min;

    /**
     *
     */
    public int_proceso_refs() {
    }

    /**
     *
     * @return
     */
    public int getIdpk() {
        return idpk;
    }

    /**
     *
     * @param idpk
     */
    public void setIdpk(int idpk) {
        this.idpk = idpk;
    }

    /**
     *
     * @return
     */
    public int getInt_proceso_id_fk() {
        return int_proceso_id_fk;
    }

    /**
     *
     * @param int_proceso_id_fk
     */
    public void setInt_proceso_id_fk(int int_proceso_id_fk) {
        this.int_proceso_id_fk = int_proceso_id_fk;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @return
     */
    public double getMax_2() {
        return max_2;
    }

    /**
     *
     * @param max_2
     */
    public void setMax_2(double max_2) {
        this.max_2 = max_2;
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
       
}
