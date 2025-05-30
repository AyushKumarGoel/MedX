package com.abes.medx.dao;

import java.util.List;

import com.abes.medx.dto.AppointmentDTO;

public interface AppointmentDAO {
    boolean bookAppointment(AppointmentDTO appointment);
    boolean updateAppointment(AppointmentDTO updatedAppointment);
    boolean cancelAppointment(String appointmentId);
    AppointmentDTO getAppointmentById(String appointmentId);
    List<AppointmentDTO> getAppointmentsByPatientId(String patientId);
    List<AppointmentDTO> getAppointmentsByDoctorId(String doctorId);
    List<AppointmentDTO> getAllAppointments();
    List<AppointmentDTO> getAppointmentsByStatus(String status);

}

