/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso_vars_data;

import java.sql.Date;
import java.sql.Time;


/**
 *
 * @author juane
 */
public class int_proceso_vars_data {
    private int idpk;
    private int int_proceso_vars_id_fk;
    private double valor;
    private double tiempo;
    private Date fecha;
    private Time hora;

    /**
     *
     * @param processVarId
     * @param value
     * @param time
     * @param date
     * @param uploadTime
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
    public int getProcessVarId() {
        return int_proceso_vars_id_fk;
    }

    /**
     *
     * @param processVarId
     */
    public void getProcessVarId(int processVarId) {
        this.int_proceso_vars_id_fk = processVarId;
    }

    /**
     *
     * @return
     */
    public double getValue() {
        return valor;
    }

    /**
     *
     * @param value
     */
    public void setValue(double value) {
        this.valor = value;
    }

    /**
     *
     * @return
     */
    public double getTime() {
        return tiempo;
    }

    /**
     *
     * @param time
     */
    public void setTime(double time) {
        this.tiempo = time;
    }

    /**
     *
     * @return
     */
    public Date getDate() {
        return fecha;
    }

    /**
     *
     * @param date
     */
    public void setDate(Date date) {
        this.fecha = date;
    }

    /**
     *
     * @return
     */
    public Time getClockTime() {
        return hora;
    }

    /**
     *
     * @param time
     */
    public void setClockTime(Time time) {
        this.hora = time;
    }
}
