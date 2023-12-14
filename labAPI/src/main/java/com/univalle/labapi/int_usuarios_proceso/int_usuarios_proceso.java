/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_usuarios_proceso;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representa una entidad de usuario en proceso, asociando un usuario con un proceso específico.
 * Incluye información sobre la fecha, hora de inicio, hora de finalización y la cantidad de 'hits' realizados en el proceso.
 */
public class int_usuarios_proceso {
    /**
     * / Campo id, clave primaria.
     */
    private int idpk;
    /**
    / * Campo int_usuarios_id, clave foránea de la tabla int_usuarios.
     */
    private int int_usuarios_id_fk;
    /**
    / Campo int_proceso_id, clave foránea de la tabla int_proceso.
    */
    private int int_proceso_id_fk;
    /**
     * Fecha del proceso.
     */
    private LocalDate fecha;
    /**
     * Hora de inicio del proceso.
     */
    private LocalTime hora_inicio;
    /**
     * Hora de finalización del proceso
     */
    private LocalTime hora_fin;
    /**
     * Cantidad de hits en el proceso.
     */
    private int hits;

    /**
     * Constructor de int_usuarios_proceso.
     * Inicializa una nueva instancia con los detalles especificados.
     *
     * @param userId Identificador del usuario asociado.
     * @param processId Identificador del proceso asociado.
     * @param date Fecha del proceso.
     * @param tiempoIni Hora de inicio del proceso.
     * @param tiempoFin Hora de finalización del proceso.
     * @param hits Cantidad de 'hits' realizados en el proceso.
     */
    
    public int_usuarios_proceso(int userId, int processId, Date date, Time tiempoIni, Time tiempoFin, int hits) {
        this.int_usuarios_id_fk = userId;
        this.int_proceso_id_fk = processId;
        this.fecha = date.toLocalDate();
        this.hora_inicio = tiempoIni.toLocalTime();
        this.hora_fin = tiempoFin.toLocalTime(); 
        this.hits = hits;
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

    @Override
    public String toString() {
        return "int_usuarios_proceso{" 
                + "idpk=" + idpk 
                + ", int_usuarios_id_fk=" + int_usuarios_id_fk 
                + ", int_proceso_id_fk=" + int_proceso_id_fk 
                + ", fecha=" + fecha 
                + ", hora_inicio=" + hora_inicio 
                + ", hora_fin=" + hora_fin 
                + ", hits=" + hits 
                + '}';
    }
    
    
}
