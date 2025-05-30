package com.abes.medx.dao;

import com.abes.medx.dto.AppointmentDTO;

public class AppointmentDAOImpl implements AppointmentDAO {

    // Implementation for adding an appointment
    @Override
    public void addAppointment(AppointmentDTO appointment) {
    }
    
    // Implementation for canceling an appointment
    @Override
    public void cancelAppointment(String appointmentId) {
    }

    // Implementation for retrieving an appointment by ID
    @Override
    public AppointmentDTO getAppointmentById(String appointmentId) {
        return null;
    }

    // Implementation for updating an appointment
    @Override
    public void updateAppointment(AppointmentDTO appointment) {
    }

    // Implementation for showing all appointments
    @Override
    public void showAppointments() {
    }

    // Implementation for showing appointments by patient ID
    @Override
    public void showAppointmentsByPatient(String patientId) {
    }

    // Implementation for showing appointments by doctor ID
    @Override
    public void showAppointmentsByDoctor(String doctorId) {
    }

}
