/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso_vars_data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author juane
 */
public class int_proceso_vars_data {
    private int idpk;
    private int int_proceso_refs_id_fk;
    private double valor;
    private double tiempo;
    private LocalDate fecha;
    private LocalTime time;

    /**
     *
     */
    public int_proceso_vars_data() {    
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
    public int getInt_proceso_refs_id_fk() {
        return int_proceso_refs_id_fk;
    }

    /**
     *
     * @param int_proceso_refs_id_fk
     */
    public void setInt_proceso_refs_id_fk(int int_proceso_refs_id_fk) {
        this.int_proceso_refs_id_fk = int_proceso_refs_id_fk;
    }

    /**
     *
     * @return
     */
    public double getValor() {
        return valor;
    }

    /**
     *
     * @param valor
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     *
     * @return
     */
    public double getTiempo() {
        return tiempo;
    }

    /**
     *
     * @param tiempo
     */
    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    /**
     *
     * @return
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     *
     * @param fecha
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     *
     * @return
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }
    
}
