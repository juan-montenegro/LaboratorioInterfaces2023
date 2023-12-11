/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso_vars;

/**
 *
 * @author juane
 */
public class int_proceso_vars {
    /**
    Field id, primary key.
    */
    private int idpk;
    private int int_proceso_id_fk;
    private String nombre;
    private String descripcion;
    private double max_2;
    private double min;

    public int_proceso_vars() {
    }

    public int getIdpk() {
        return idpk;
    }

    public void setIdpk(int idpk) {
        this.idpk = idpk;
    }

    public int getInt_proceso_id_fk() {
        return int_proceso_id_fk;
    }

    public void setInt_proceso_id_fk(int int_proceso_id_fk) {
        this.int_proceso_id_fk = int_proceso_id_fk;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMax_2() {
        return max_2;
    }

    public void setMax_2(double max_2) {
        this.max_2 = max_2;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }
    
    
}
