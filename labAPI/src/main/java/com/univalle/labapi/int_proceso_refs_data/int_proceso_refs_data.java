/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso_refs_data;

import java.sql.Date;
import java.sql.Time;



/**
 * Clase que representa los datos de una referencia de proceso en el sistema.
 * Contiene información detallada de las referencias de proceso como valor, tiempo, fecha y hora.
 */
public class int_proceso_refs_data {
    private int idpk;
    private int int_proceso_refs_id_fk;
    private double valor;
    private double tiempo;
    private Date fecha;
    private Time hora;

   /**
     * Constructor para crear una instancia de int_proceso_refs_data con valores específicos.
     *
     * @param processRefId ID de la referencia del proceso.
     * @param value        Valor asociado a la referencia del proceso.
     * @param time         Tiempo asociado a la referencia del proceso.
     * @param date         Fecha de registro de los datos.
     * @param uploadTime   Hora de registro de los datos.
     */
    public int_proceso_refs_data(int processRefId, double value, 
            double time, Date date, Time uploadTime) {   
        this.int_proceso_refs_id_fk = processRefId;
        this.valor = value;
        this.tiempo = time;
        this.fecha = date;
        this.hora = uploadTime;
    }


    /**
     * Obtiene el ID de la instancia de int_proceso_refs_data.
     *
     * @return El ID de la instancia.
     */
    
    public int getId() {
        return idpk;
    }


    /**
     * Establece el ID de la instancia de int_proceso_refs_data.
     *
     * @param id El nuevo ID para la instancia.
     */
    
    public void setId(int id) {
        this.idpk = id;
    }

    /**
     * Obtiene el ID de la referencia del proceso.
     *
     * @return El ID de la referencia del proceso.
     */
    
    public int getProcessRefId() {
        return int_proceso_refs_id_fk;
    }

       /**
     * Establece el ID de la referencia del proceso.
     *
     * @param processRefId El nuevo ID para la referencia del proceso.
     */
    
    public void getProcessVarId(int processRefId) {
        this.int_proceso_refs_id_fk = processRefId;
    }

   /**
     * Obtiene el valor asociado a la referencia del proceso.
     *
     * @return El valor de la referencia del proceso.
     */
    
    
    public double getValue() {
        return valor;
    }

    /**
     * Establece el valor asociado a la referencia del proceso.
     *
     * @param value El nuevo valor para la referencia del proceso.
     */
    
    
    public void setValue(double value) {
        this.valor = value;
    }

    /**
     * Obtiene el tiempo asociado a la referencia del proceso.
     *
     * @return El tiempo de la referencia del proceso.
     */
    
    
    public double getTime() {
        return tiempo;
    }

    /**
     * Establece el tiempo asociado a la referencia del proceso.
     *
     * @param time El nuevo tiempo para la referencia del proceso.
     */
    
    public void setTime(double time) {
        this.tiempo = time;
    }
    /**
     * Obtiene la fecha de registro de los datos.
     *
     * @return La fecha de registro.
     */
    
    public Date getDate() {
        return fecha;
    }
    /**
     * Establece la fecha de registro de los datos.
     *
     * @param date La nueva fecha de registro.
     */
    
    public void setDate(Date date) {
        this.fecha = date;
    }

     /**
     * Obtiene la hora de registro de los datos.
     *
     * @return La hora de registro.
     */
    
    public Time getClockTime() {
        return hora;
    }
    
    /**
     * Establece la hora de registro de los datos.
     *
     * @param time La nueva hora de registro.
     */
    
    public void setClockTime(Time time) {
        this.hora = time;
    }
        
}
