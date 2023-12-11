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
public class int_proceso {
    /**
     * Field id, primary key.
     */
    private int idpk;
    /**
     * Field int_proceso_tipo_id, foreign key of table int_proceso_tipo
     */
    private int int_proceso_tipo_id_fk;
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
    private double tiempo_muestreo;
    /**
     * 
     */
    private Blob archivo_especificacion;
    /**
     * 
     */
    private Blob archivo_manual;

    /**
     *
     * @param name
     * @param processTypeId
     */
    public int_proceso(String name, int processTypeId) {
        this.nombre = name;
        this.int_proceso_tipo_id_fk = processTypeId;
    }
    
    

    /**
     *
     * @return
     */
    public int getId() {
        return this.idpk;
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
    public int getProcessTypeId() {
        return this.int_proceso_tipo_id_fk;
    }

    /**
     *
     * @param processTypeId
     */
    public void setProcessTypeId(int processTypeId) {
        this.int_proceso_tipo_id_fk = processTypeId;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return this.nombre;
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
        return this.descripcion;
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
    public double getSampleTime() {
        return this.tiempo_muestreo;
    }

    /**
     *
     * @param tiempo_muestreo
     */
    public void setSampleTime(double tiempo_muestreo) {
        this.tiempo_muestreo = tiempo_muestreo;
    }

    /**
     *
     * @return
     */
    public Blob getArchivo_especificacion() {
        return this.archivo_especificacion;
    }

    /**
     *
     * @param archivo_especificacion
     */
    public void setArchivo_especificacion(Blob archivo_especificacion) {
        this.archivo_especificacion = archivo_especificacion;
    }

    /**
     *
     * @return
     */
    public Blob getArchivo_manual() {
        return this.archivo_manual;
    }

    /**
     *
     * @param archivo_manual
     */
    public void setArchivo_manual(Blob archivo_manual) {
        this.archivo_manual = archivo_manual;
    }
    
}
