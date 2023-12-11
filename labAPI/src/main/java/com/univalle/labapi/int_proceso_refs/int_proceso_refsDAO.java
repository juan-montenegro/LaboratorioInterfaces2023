/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univalle.labapi.int_proceso_refs;

import java.util.List;

/**
 *
 * @author juane
 */
public interface int_proceso_refsDAO {
        public List<int_proceso_refs> getAllProcessRefs();
//        public List<int_proceso_refs> getProcessRefsFor(int processId);
        public int_proceso_refs getProcessRef(int processId);
        public int_proceso_refs getProcessRef(String name);
        public void insertProcessRef(int processId, String name, 
                String description, double max, double min);
        public void updateProcessRef(int_proceso_refs processRef);
}
