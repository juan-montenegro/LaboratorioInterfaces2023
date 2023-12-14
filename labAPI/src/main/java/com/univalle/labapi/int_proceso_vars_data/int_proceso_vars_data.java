/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso_vars_data;

import java.sql.Date;
import java.sql.Time;


/**
 * Representa los datos asociados a una variable de proceso en el sistema.
 * Incluye detalles como valor, tiempo, fecha y hora de registro.
 */
public class int_proceso_vars_data {
    private int idpk;
    private int int_proceso_vars_id_fk;
    private double valor;
    private double tiempo;
    private Date fecha;
    private Time hora;

   /**
     * Constructor para crear una instancia de int_proceso_vars_data con valores específicos.
     *
     * @param processVarId ID de la variable de proceso.
     * @param value        Valor asociado a la variable de proceso.
     * @param time         Tiempo asociado a la variable de proceso.
     * @param date         Fecha de registro de los datos.
     * @param uploadTime   Hora de registro de los datos.
     */
    public int_proceso_vars_data(int processVarId, double value, 
            double time, Date date, Time uploadTime) {   
        this.int_proceso_vars_id_fk = processVarId;
        this.valor = value;
        this.tiempo = time;
        this.fecha = date;
        this.hora = uploadTime;
    }

    /**
     * Obtiene el ID de la instancia de int_proceso_vars_data.
     *
     * @return El ID de la instancia.
     */
    
    public int getId() {
        return idpk;
    }

    /**
     * Establece el ID de la instancia de int_proceso_vars_data.
     *
     * @param id El nuevo ID para la instancia.
     */
    public void setId(int id) {
        this.idpk = id;
    }

    /**
     * Obtiene el ID de la variable de proceso asociada.
     *
     * @return El ID de la variable de proceso.
     */
    public int getProcessVarId() {
        return int_proceso_vars_id_fk;
    }

   /**
     * Establece el ID de la variable de proceso asociada.
     *
     * @param processVarId El nuevo ID para la variable de proceso.
     */
    public void getProcessVarId(int processVarId) {
        this.int_proceso_vars_id_fk = processVarId;
    }

     /**
     * Obtiene el valor asociado a la variable de proceso.
     *
     * @return El valor de la variable de proceso.
     */
    public double getValue() {
        return valor;
    }

  
    /**
     * Establece el valor asociado a la variable de proceso.
     *
     * @param value El nuevo valor para la variable de proceso.
     */
    public void setValue(double value) {
        this.valor = value;
    }

   /**
     * Obtiene el tiempo asociado a la variable de proceso.
     *
     * @return El tiempo de la variable de proceso.
     */
    public double getTime() {
        return tiempo;
    }

       /**
     * Establece el tiempo asociado a la variable de proceso.
     *
     * @param time El nuevo tiempo para la variable de proceso.
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

    @Override
    public String toString() {
        return "int_proceso_vars_data{" 
                + "idpk=" + idpk 
                + ", int_proceso_vars_id_fk=" + int_proceso_vars_id_fk 
                + ", valor=" + valor 
                + ", tiempo=" + tiempo 
                + ", fecha=" + fecha 
                + ", hora=" + hora
                + '}';
    }
    
    
}
