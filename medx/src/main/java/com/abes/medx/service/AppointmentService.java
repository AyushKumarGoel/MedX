package com.abes.medx.service;

import java.util.List;

import com.abes.medx.dao.AppointmentDAO;
import com.abes.medx.dao.DoctorDAO;
import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.AppointmentException;
import com.abes.medx.exception.BookingException;

public class AppointmentService {

    private final AppointmentDAO appointmentDAO;
    private final DoctorDAO doctorDAO;

    public AppointmentService(AppointmentDAO appointmentDAO, DoctorDAO doctorDAO) {
        this.appointmentDAO = appointmentDAO;
        this.doctorDAO = doctorDAO;
    }

    public AppointmentDTO createAppointment(String id, String date, String time, PatientDTO patient, String doctorId, int toPay) throws AppointmentException {
        try {
            if (id == null || id.trim().isEmpty() || date == null || time == null || patient == null || doctorId == null) {
                throw new AppointmentException("Invalid appointment data provided.");
            }
            if (toPay < 0) {
                throw new AppointmentException("Payment amount cannot be negative.");
            }
            if (appointmentDAO.getAppointmentById(id) != null) {
                throw new AppointmentException("Appointment ID already exists.");
            }

            DoctorDTO doctor = doctorDAO.getDoctorById(doctorId);
            if (doctor == null) {
                throw new AppointmentException("Doctor with ID " + doctorId + " not found.");
            }

            return new AppointmentDTO(id, date, time, patient, doctor, toPay, "Scheduled");
        } catch (Exception e) {
            throw new AppointmentException("Error creating appointment: " + e.getMessage());
        }
    }

    public boolean bookAppointment(AppointmentDTO appointment) throws BookingException {
        try {
            if (appointment == null || appointment.getAppointmentId() == null) {
                throw new BookingException("Invalid appointment data for booking.");
            }
            return appointmentDAO.bookAppointment(appointment);
        } catch (Exception e) {
            throw new BookingException("Error booking appointment: " + e.getMessage());
        }
    }

    public boolean updateAppointment(AppointmentDTO appointment) throws AppointmentException {
        try {
            if (appointment == null || appointment.getAppointmentId() == null) {
                throw new AppointmentException("Invalid appointment data for update.");
            }
            return appointmentDAO.updateAppointment(appointment);
        } catch (Exception e) {
            throw new AppointmentException("Error updating appointment: " + e.getMessage());
        }
    }

    public boolean cancelAppointment(String id) throws AppointmentException {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new AppointmentException("Appointment ID cannot be empty.");
            }
            return appointmentDAO.cancelAppointment(id);
        } catch (Exception e) {
            throw new AppointmentException("Error canceling appointment: " + e.getMessage());
        }
    }

    public boolean completeAppointment(String id) throws AppointmentException {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new AppointmentException("Appointment ID cannot be empty.");
            }
            AppointmentDTO a = appointmentDAO.getAppointmentById(id);
            if (a == null) {
                throw new AppointmentException("Appointment with ID " + id + " not found.");
            }
            if ("Cancelled".equalsIgnoreCase(a.getStatus())) {
                throw new AppointmentException("Cannot complete a cancelled appointment.");
            }
            a.setStatus("Completed");
            return appointmentDAO.updateAppointment(a);
        } catch (Exception e) {
            throw new AppointmentException("Error completing appointment: " + e.getMessage());
        }
    }

    public AppointmentDTO getAppointmentById(String id) throws AppointmentException {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new AppointmentException("Appointment ID cannot be empty.");
            }
            AppointmentDTO appointment = appointmentDAO.getAppointmentById(id);
            if (appointment == null) {
                throw new AppointmentException("Appointment with ID " + id + " not found.");
            }
            return appointment;
        } catch (Exception e) {
            throw new AppointmentException("Error retrieving appointment: " + e.getMessage());
        }
    }

    public List<AppointmentDTO> getAppointmentsByPatientId(String patientId) throws AppointmentException {
        try {
            if (patientId == null || patientId.trim().isEmpty()) {
                throw new AppointmentException("Patient ID cannot be empty.");
            }
            return appointmentDAO.getAppointmentsByPatientId(patientId);
        } catch (Exception e) {
            throw new AppointmentException("Error retrieving patient appointments: " + e.getMessage());
        }
    }

    public List<AppointmentDTO> getAppointmentsByDoctorId(String doctorId) throws AppointmentException {
        try {
            if (doctorId == null || doctorId.trim().isEmpty()) {
                throw new AppointmentException("Doctor ID cannot be empty.");
            }
            return appointmentDAO.getAppointmentsByDoctorId(doctorId);
        } catch (Exception e) {
            throw new AppointmentException("Error retrieving doctor appointments: " + e.getMessage());
        }
    }

    public List<AppointmentDTO> getAppointmentsByStatus(String status) throws AppointmentException {
        try {
            if (status == null || status.trim().isEmpty()) {
                throw new AppointmentException("Status cannot be empty.");
            }
            return appointmentDAO.getAppointmentsByStatus(status);
        } catch (Exception e) {
            throw new AppointmentException("Error retrieving appointments by status: " + e.getMessage());
        }
    }

    public List<AppointmentDTO> getAllAppointments() throws AppointmentException {
        try {
            return appointmentDAO.getAllAppointments();
        } catch (Exception e) {
            throw new AppointmentException("Error retrieving all appointments: " + e.getMessage());
        }
    }

    public String getNextAppointmentId() throws AppointmentException {
        try {
            String nextId = appointmentDAO.getNextAppointmentId();
            if (nextId == null || nextId.trim().isEmpty()) {
                throw new AppointmentException("Failed to generate next appointment ID.");
            }
            return nextId;
        } catch (Exception e) {
            throw new AppointmentException("Error generating next appointment ID: " + e.getMessage());
        }
    }
}