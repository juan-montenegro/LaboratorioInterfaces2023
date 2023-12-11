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
    private int idpk;
    private int int_usuarios_id_fk;
    private int int_proceso_id_fk;
    
    private LocalDate fecha;
    private LocalTime hora_inicio;
    private LocalTime hora_fin;
    
    private int hits;

    /**
     *
     * @param int_usuarios_id_fk
     * @param int_proceso_id_fk
     */
    public int_usuarios_proceso(int int_usuarios_id_fk, int int_proceso_id_fk) {
        this.int_usuarios_id_fk = int_usuarios_id_fk;
        this.int_proceso_id_fk = int_proceso_id_fk;
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
    public int getUserId() {
        return int_usuarios_id_fk;
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
    public LocalDate getDate() {
        return fecha;
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
        return hora_inicio;
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
        return hora_fin;
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
        return hits;
    }

    /**
     *
     * @param hits
     */
    public void setHits(int hits) {
        this.hits = hits;
    }
    
}
