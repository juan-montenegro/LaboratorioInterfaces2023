/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univalle.labapi.int_usuarios_proceso;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author Juan Camilo Chavez
 * @author Juan Esteban Montenegro
 * @author Juan David Beltran
 */
public interface int_usuarios_procesoDAO {
    
        public List <int_usuarios_proceso> getAllProcessForUser(int usuario);
        public List <int_usuarios_proceso> getAllUserForProcess(int proceso);
        public List <int_usuarios_proceso> getUserForHits(int umbralHits);
        public List <int_usuarios_proceso> getProcessForDate(LocalDate fecha);
        public List <int_usuarios_proceso> getUserForDate(LocalDate fecha);
        
        public int_usuarios_proceso getHitsForDate(LocalDate fecha);
       
        public int insertNewRegister (int usuario, int proceso, LocalDate fecha, LocalTime hora_inicio, LocalTime hora_fin, int hits );
        public int updateHoraFin(LocalTime hora_fin);
        
        public int deleteRegister(int usario, int proceso, LocalDate fecha,LocalTime hora_inicio);
        public int deleteRegistersOfUser(int usuario);
        public int deleteRegistersOfProcess(int proceso);
        public int deleteRegistersOfDate (LocalDate fecha);       
}
