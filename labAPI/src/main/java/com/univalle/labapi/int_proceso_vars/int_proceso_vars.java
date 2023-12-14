/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso_vars;

/**
 * Representa las variables asociadas a un proceso en el sistema.
 * Incluye detalles como el nombre, descripción, valores máximo y mínimo, y un indicador de estado.
 */

public class int_proceso_vars {
    
    /**
     * Constructor para crear una instancia de int_proceso_vars con valores iniciales.
     *
     * @param processId    Identificador del proceso asociado.
     * @param name         Nombre de la variable del proceso.
     * @param description  Descripción de la variable del proceso.
     * @param max          Valor máximo permitido para la variable.
     * @param min          Valor mínimo permitido para la variable.
     */
    
    private int idpk;
    private int int_proceso_id_fk;
    private String nombre;
    private String descripcion;
    private double max_2;
    private double min;
    private boolean flag;

    /**
     * Constructor completo para crear una instancia de int_proceso_vars con un indicador de estado.
     *
     * @param processId    Identificador del proceso asociado.
     * @param name         Nombre de la variable del proceso.
     * @param description  Descripción de la variable del proceso.
     * @param max          Valor máximo permitido para la variable.
     * @param min          Valor mínimo permitido para la variable.
     * @param flag         Indicador de estado o bandera para la variable.
     */
    
    public int_proceso_vars(int processId, String name, 
            String description, double max, double min) {    
        this.int_proceso_id_fk = processId;
        this.nombre = name;
        this.descripcion = description;
        this.max_2 = max;
        this.min = min;
        this.flag = false;
    }
    
    public int_proceso_vars(int processId, String name, 
            String description, double max, double min, boolean flag) {    
        this.int_proceso_id_fk = processId;
        this.nombre = name;
        this.descripcion = description;
        this.max_2 = max;
        this.min = min;
        this.flag = flag;
    }

     /**
     * Obtiene el identificador único de la variable del proceso.
     *
     * @return El identificador único.
     */
    
    public int getId() {
        return idpk;
    }

    /**
     * Establece el identificador único de la variable del proceso.
     *
     * @param idpk El nuevo identificador único.
     */
    public void setId(int idpk) {
        this.idpk = idpk;
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
     * Obtiene el nombre de la variable del proceso.
     *
     * @return El nombre de la variable.
     */
    
    public String getName() {
        return nombre;
    }

    /**
     * Establece el nombre de la variable del proceso.
     *
     * @param name El nuevo nombre de la variable.
     */
    public void setName(String name) {
        this.nombre = name;
    }


    /**
     * Obtiene la descripción de la variable del proceso.
     *
     * @return La descripción de la variable.
     */
    
    public String getDescription() {
        return descripcion;
    }

    /**
     * Establece la descripción de la variable del proceso.
     *
     * @param description La nueva descripción de la variable.
     */
    
    public void setDescription(String description) {
        this.descripcion = description;
    }

    /**
     * Obtiene el valor máximo permitido para la variable del proceso.
     *
     * @return El valor máximo.
     */
    public double getMax() {
        return max_2;
    }


    /**
     * Establece el valor máximo permitido para la variable del proceso.
     *
     * @param max El nuevo valor máximo.
     */
    
    public void setMax(double max) {
        this.max_2 = max;
    }

    /**
     * Obtiene el valor mínimo permitido para la variable del proceso.
     *
     * @return El valor mínimo.
     */
    
    public double getMin() {
        return min;
    }

    /**
     * Establece el valor mínimo permitido para la variable del proceso.
     *
     * @param min El nuevo valor mínimo.
     */
    
    public void setMin(double min) {
        this.min = min;
    }
    
    /**
     * Verifica si el indicador de estado o bandera está activo.
     *
     * @return El estado del indicador.
     */
    
    public boolean isFlag() {
        return flag;
    }

        /**
     * Establece el indicador de estado o bandera para la variable del proceso.
     *
     * @param flag El nuevo estado del indicador.
     */
    
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    @Override
    public String toString(){
        return "int_proceso_vars("
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
