package com.abes.medx.dao;

import com.abes.medx.dto.AppointmentDTO;

public interface AppointmentDAO {

    public void addAppointment(AppointmentDTO appointment) ;

    public void cancelAppointment(String appointmentId) ;

    public AppointmentDTO getAppointmentById(String appointmentId) ;  

    public void updateAppointment(AppointmentDTO appointment) ;    
    
    public void showAppointments() ;    
    
    public void showAppointmentsByPatient(String patientId) ;    
    
    public void showAppointmentsByDoctor(String doctorId) ;    
        
}
