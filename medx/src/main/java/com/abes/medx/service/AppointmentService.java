package com.abes.medx.service;

import java.util.List;

import com.abes.medx.dao.AppointmentDAO;
import com.abes.medx.dao.DoctorDAO;
import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;

public class AppointmentService {

    private final AppointmentDAO appointmentDAO;
    private final DoctorDAO doctorDAO;

    public AppointmentService(AppointmentDAO appointmentDAO, DoctorDAO doctorDAO) {
        this.appointmentDAO = appointmentDAO;
        this.doctorDAO = doctorDAO;
    }

    public AppointmentDTO createAppointment(String id, String date, String time, PatientDTO patient, String doctorId, int toPay) {
        if (appointmentDAO.getAppointmentById(id) != null) return null;

        DoctorDTO doctor = doctorDAO.getDoctorById(doctorId);
        if (doctor == null) return null;

        AppointmentDTO appointment = new AppointmentDTO(id, date, time, patient, doctor, toPay, "Scheduled");
        return appointment;
    }

    public boolean bookAppointment(AppointmentDTO appointment) {
        return appointmentDAO.bookAppointment(appointment);
    }

    public boolean updateAppointment(AppointmentDTO appointment) {
        return appointmentDAO.updateAppointment(appointment);
    }

    public boolean cancelAppointment(String id) {
        return appointmentDAO.cancelAppointment(id);
    }

    public boolean completeAppointment(String id) {
        AppointmentDTO a = appointmentDAO.getAppointmentById(id);
        if (a != null && !"Cancelled".equalsIgnoreCase(a.getStatus())) {
            a.setStatus("Completed");
            return appointmentDAO.updateAppointment(a);
        }
        return false;
    }

    public AppointmentDTO getAppointmentById(String id) {
        return appointmentDAO.getAppointmentById(id);
    }

    public List<AppointmentDTO> getAppointmentsByPatientId(String patientId) {
        return appointmentDAO.getAppointmentsByPatientId(patientId);
    }

    public List<AppointmentDTO> getAppointmentsByDoctorId(String doctorId) {
        return appointmentDAO.getAppointmentsByDoctorId(doctorId);
    }

    public List<AppointmentDTO> getAppointmentsByStatus(String status) {
        return appointmentDAO.getAppointmentsByStatus(status);
    }

    public List<AppointmentDTO> getAllAppointments() {
        return appointmentDAO.getAllAppointments();
    }

    public String getNextAppointmentId() {
        return appointmentDAO.getNextAppointmentId();
    }
}