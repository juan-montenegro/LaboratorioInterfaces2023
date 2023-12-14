/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso;

import java.sql.Blob;

/**
 * Representa un proceso en el sistema, incluyendo detalles como tipo de proceso,
 * nombre, descripción, tiempo de muestreo y archivos asociados.
 * Es una entidad que se corresponde con una tabla en la base de datos.
 */

public class int_proceso {
    
/**
     * Identificador único del proceso, clave primaria en la base de datos.
     */
    private int idpk;
    
      /**
     * Identificador del tipo de proceso, clave foránea de la tabla int_proceso_tipo.
     */
    
    private int int_proceso_tipo_id_fk;
    
    /**
     * Nombre del proceso.
     */
    
    
    private String nombre;
    /**
     * Descripción del proceso.
     */
    private String descripcion;
      
    /**
     * Archivo de especificaciones del proceso.
     */
    
    private double tiempo_muestreo;
    
     /**
     * Archivo de especificaciones del proceso.
     */
    
    private Blob archivo_especificaciones;
    
    /**
     * Archivo del manual del proceso.
     */
    
    private Blob archivo_manual;
/**
     * Constructor para crear una instancia de int_proceso con valores iniciales.
     *
     * @param processTypeId Identificador del tipo de proceso.
     * @param name          Nombre del proceso.
     */
    
    public int_proceso(int processTypeId, String name) {
        this.int_proceso_tipo_id_fk = processTypeId;
        this.nombre = name;
        this.descripcion = "";
        this.tiempo_muestreo = 100;
        this.archivo_especificaciones = null;
        this.archivo_manual = null;
        
    }
    
     /**
     * Constructor para crear una instancia de int_proceso con todos los detalles.
     *
     * @param processTypeId        Identificador del tipo de proceso.
     * @param name                 Nombre del proceso.
     * @param description          Descripción del proceso.
     * @param sampleTime           Tiempo de muestreo del proceso.
     * @param textFile             Archivo de especificaciones del proceso.
     * @param manualFile           Archivo del manual del proceso.
     */
    
    public int_proceso(int processTypeId, String name, String description, 
            double sampleTime, Blob textFile, Blob manualFile) {
        this.int_proceso_tipo_id_fk = processTypeId;
        this.nombre = name;
        this.descripcion = description;
        this.tiempo_muestreo = sampleTime;
        this.archivo_especificaciones = textFile;
        this.archivo_manual = manualFile;
    }
    
    

       /**
     * Obtiene el identificador único del proceso.
     * 
     * @return El identificador único del proceso.
     */
    public int getId() {
        return this.idpk;
    }

    /**
     * Establece el identificador único del proceso.
     * 
     * @param id El nuevo identificador del proceso.
     */
    public void setId(int id) {
        this.idpk = id;
    }

    /**
     * Obtiene el identificador del tipo de proceso.
     * 
     * @return El identificador del tipo de proceso.
     */
    public int getProcessTypeId() {
        return this.int_proceso_tipo_id_fk;
    }

    /**
     * Establece el identificador del tipo de proceso.
     * 
     * @param processTypeId El nuevo identificador del tipo de proceso.
     */
    public void setProcessTypeId(int processTypeId) {
        this.int_proceso_tipo_id_fk = processTypeId;
    }

    /**
     * Obtiene el nombre del proceso.
     * 
     * @return El nombre del proceso.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Establece el nombre del proceso.
     * 
     * @param nombre El nuevo nombre del proceso.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del proceso.
     * 
     * @return La descripción del proceso.
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Establece la descripción del proceso.
     * 
     * @param descripcion La nueva descripción del proceso.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el tiempo de muestreo del proceso.
     * 
     * @return El tiempo de muestreo del proceso.
     */
    public double getSampleTime() {
        return this.tiempo_muestreo;
    }

    /**
     * Establece el tiempo de muestreo del proceso.
     * 
     * @param tiempo_muestreo El nuevo tiempo de muestreo del proceso.
     */
    public void setSampleTime(double tiempo_muestreo) {
        this.tiempo_muestreo = tiempo_muestreo;
    }

    /**
     * Obtiene el archivo de especificaciones del proceso.
     * 
     * @return El archivo de especificaciones del proceso.
     */
    public Blob getArchivo_especificacion() {
        return this.archivo_especificaciones;
    }

    /**
     * Establece el archivo de especificaciones del proceso.
     * 
     * @param archivo_especificacion El nuevo archivo de especificaciones del proceso.
     */
    public void setArchivo_especificacion(Blob archivo_especificacion) {
        this.archivo_especificaciones = archivo_especificacion;
    }

    /**
     * Obtiene el archivo manual del proceso.
     * 
     * @return El archivo manual del proceso.
     */
    public Blob getArchivo_manual() {
        return this.archivo_manual;
    }

    /**
     * Establece el archivo manual del proceso.
     * 
     * @param archivo_manual El nuevo archivo manual del proceso.
     */
    public void setArchivo_manual(Blob archivo_manual) {
        this.archivo_manual = archivo_manual;
    }
    @Override
    public String toString(){
        return "int_proceso("
                + this.idpk + ","
                + this.int_proceso_tipo_id_fk + ","
                + this.nombre +"," 
                + this.descripcion +","
                + this.tiempo_muestreo +","
                + this.archivo_especificaciones.toString() +","
                + this.archivo_manual.toString() 
                + ")";
    }
}

