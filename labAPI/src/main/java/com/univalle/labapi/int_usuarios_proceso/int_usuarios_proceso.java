/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_usuarios_proceso;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author juane
 */
public class int_usuarios_proceso {
    /**
     * Field id, primary key.
     */
    private int idpk;
    /**
     * Field int_usuarios_id, foreign key of table int_usuarios
     */
    private int int_usuarios_id_fk;
    /**
     * Field int_proceso_id, foreign key of table int_proceso
     */
    private int int_proceso_id_fk;
    /**
     * 
     */
    private LocalDate fecha;
    /**
     * 
     */
    private LocalTime hora_inicio;
    /**
     * 
     */
    private LocalTime hora_fin;
    /**
     * 
     */
    private int hits;

    /**
     *
     * @param userId
     * @param processId
     */
    public int_usuarios_proceso(int userId, int processId) {
        this.int_usuarios_id_fk = userId;
        this.int_proceso_id_fk = processId;
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
    public int getUserId() {
        return this.int_usuarios_id_fk;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(int userId) {
        this.int_usuarios_id_fk = userId;
    }

    /**
     *
     * @return
     */
    public int getProcessId() {
        return this.int_proceso_id_fk;
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
    public LocalDate getDate() {
        return this.fecha;
    }

    /**
     *
     * @param date
     */
    public void setDate(LocalDate date) {
        this.fecha = date;
    }

    /**
     *
     * @return
     */
    public LocalTime getStartTime() {
        return this.hora_inicio;
    }

    /**
     *
     * @param startTime
     */
    public void setStartTime(LocalTime startTime) {
        this.hora_inicio = startTime;
    }

    /**
     *
     * @return
     */
    public LocalTime getEndTime() {
        return this.hora_fin;
    }

    /**
     *
     * @param endTime
     */
    public void setEndTime(LocalTime endTime) {
        this.hora_fin = endTime;
    }

    /**
     *
     * @return
     */
    public int getHits() {
        return this.hits;
    }

    /**
     *
     * @param hits
     */
    public void setHits(int hits) {
        this.hits = hits;
    }
    
}
