/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso_refs;

/**
 *
 * @author juane
 */
public class int_proceso_refs {
    /**
     * Identificador único del registro, clave primaria.
     */
    private int idpk;
    /**
     * Identificador del proceso asociado, clave foránea.
     */
    private int int_proceso_id_fk;
    /**
     * Nombre del proceso.
     */
    private String nombre;
    /**
     * Descripción del proceso.
     */
    private String descripcion;
    /**
     * Valor máximo permitido para ciertos parámetros del proceso.
     */
    private double max_2;
    /**
     * Valor mínimo permitido para ciertos parámetros del proceso.
     */
    private double min;
     /**
     * Indicador de estado o condición (generalmente usado como un booleano).
     */
    private boolean flag;

    /**
     * Constructor para crear una referencia de proceso con valores iniciales.
     *
     * @param processId    Identificador del proceso.
     * @param name         Nombre del proceso.
     * @param description  Descripción del proceso.
     * @param max          Valor máximo para parámetros del proceso.
     * @param min          Valor mínimo para parámetros del proceso.
     */
    public int_proceso_refs(int processId, String name, 
            String description, double max, double min) {    
        this.int_proceso_id_fk = processId;
        this.nombre = name;
        this.descripcion = description;
        this.max_2 = max;
        this.min = min;
        this.flag = false;
    }
    
    /**
     * Constructor completo para crear una referencia de proceso.
     *
     * @param processId    Identificador del proceso.
     * @param name         Nombre del proceso.
     * @param description  Descripción del proceso.
     * @param max          Valor máximo para parámetros del proceso.
     * @param min          Valor mínimo para parámetros del proceso.
     * @param flag         Indicador de estado o condición.
     */
    public int_proceso_refs(int processId, String name, 
            String description, double max, double min, boolean  flag) {    
        this.int_proceso_id_fk = processId;
        this.nombre = name;
        this.descripcion = description;
        this.max_2 = max;
        this.min = min;
        this.flag = flag;
    }

    /**
     * Obtiene el identificador único del registro.
     *
     * @return El identificador único.
     */
    public int getId() {
        return idpk;
    }


    /**
     * Establece el identificador único del registro.
     *
     * @param id El nuevo identificador único.
     */
    public void setId(int id) {
        this.idpk = id;
    }

     /**
     * Obtiene el identificador del proceso asociado.
     *
     * @return El identificador del proceso.
     */
    public int getProcessId() {
        return int_proceso_id_fk;
    }

    /**
     * Establece el identificador del proceso asociado.
     *
     * @param processId El nuevo identificador del proceso.
     */
    public void setProcessId(int processId) {
        this.int_proceso_id_fk = processId;
    }

    /**
     * Obtiene el nombre del proceso.
     *
     * @return El nombre del proceso.
     */
    public String getName() {
        return nombre;
    }

      /**
     * Obtiene la descripción del proceso.
     *
     * @return La descripción del proceso.
     */
    public void setName(String name) {
        this.nombre = name;
    }

    /**
     * Establece la descripción del proceso.
     *
     * @param description La nueva descripción del proceso.
     */
    public String getDescription() {
        return descripcion;
    }

    /**
     * Establece la descripción del proceso.
     *
     * @param description La nueva descripción del proceso.
     */
    public void setDescription(String description) {
        this.descripcion = description;
    }

    /**
     * Obtiene el valor máximo para ciertos parámetros del proceso.
     *
     * @return El valor máximo.
     */
    public double getMax() {
        return max_2;
    }

     /**
     * Establece el valor máximo para ciertos parámetros del proceso.
     *
     * @param max El nuevo valor máximo.
     */
    public void setMax(double max) {
        this.max_2 = max;
    }

     /**
     * Obtiene el valor mínimo para ciertos parámetros del proceso.
     *
     * @return El valor mínimo.
     */
    public double getMin() {
        return min;
    }
    
    /**
     * Establece el valor mínimo para ciertos parámetros del proceso.
     *
     * @param min El nuevo valor mínimo.
     */
    public void setMin(double min) {
        this.min = min;
    }

    /**
     * Verifica si el indicador de estado o condición está activo.
     *
     * @return El estado del indicador.
     */
    public boolean isFlag() {
        return this.flag;
    }

    /**
     * Establece el indicador de estado o condición.
     *
     * @param flag El nuevo estado del indicador.
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    @Override
    public String toString(){
        return "int_proceso_refs("
                + this.idpk + ", "
                + this.int_proceso_id_fk + ", " 
                + this.nombre + ", "
                + this.descripcion + ", "
                + this.max_2 + ", "
                + this.min + ", "
                + this.flag
                + ")";
    }
    
}
