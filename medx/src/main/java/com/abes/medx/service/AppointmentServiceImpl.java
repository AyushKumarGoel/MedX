package com.abes.medx.service;

import java.util.List;

import com.abes.medx.dao.AppointmentDAO;
import com.abes.medx.dao.DoctorDAO;
import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.AppointmentException;
import com.abes.medx.exception.BookingException;

/**
 * Implementation of the AppointmentService interface.
 * Handles business logic related to appointments including creation, booking,
 * updating, cancellation, and completion of appointments.
 */
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentDAO appointmentDAO;
    private final DoctorDAO doctorDAO;

    // Constructor to initialize dependencies (DAO layer)
    public AppointmentServiceImpl(AppointmentDAO appointmentDAO, DoctorDAO doctorDAO) {
        this.appointmentDAO = appointmentDAO;
        this.doctorDAO = doctorDAO;
    }

    /**
     * Creates a new appointment after validating inputs.
     */
    @Override
    public AppointmentDTO createAppointment(String id, String date, String time, PatientDTO patient, String doctorId, int toPay) throws AppointmentException {
        if (date == null || time == null || patient == null) {
            throw new AppointmentException("Invalid appointment data provided.");
        }
        if (toPay < 0) {
            throw new AppointmentException("Payment amount cannot be negative.");
        }
        if (appointmentDAO.getAppointmentById(id) != null) {
            throw new AppointmentException("Appointment ID already exists.");
        }

        // Verify the doctor exists before assigning the appointment
        DoctorDTO doctor = doctorDAO.getDoctorById(doctorId);
        if (doctor == null) {
            throw new AppointmentException("Doctor with ID " + doctorId + " not found.");
        }

        // Return a new AppointmentDTO with initial status "Scheduled"
        return new AppointmentDTO(id, date, time, patient, doctor, toPay, "Scheduled");
    }

    /**
     * Books a valid appointment by delegating to the DAO.
     */
    @Override
    public boolean bookAppointment(AppointmentDTO appointment) throws BookingException {
        if (appointment == null || appointment.getAppointmentId() == null) {
            throw new BookingException("Invalid appointment data for booking.");
        }
        return appointmentDAO.bookAppointment(appointment);
    }

    /**
     * Updates an existing appointment after validating its ID.
     */
    @Override
    public boolean updateAppointment(AppointmentDTO appointment) throws AppointmentException {
        if (appointment == null || appointment.getAppointmentId() == null) {
            throw new AppointmentException("Invalid appointment data for update.");
        }
        return appointmentDAO.updateAppointment(appointment);
    }

    /**
     * Cancels an appointment using its ID after basic validation.
     */
    @Override
    public boolean cancelAppointment(String id) throws AppointmentException {
        if (id == null || id.trim().isEmpty()) {
            throw new AppointmentException("Appointment ID cannot be empty.");
        }
        return appointmentDAO.cancelAppointment(id);
    }

    /**
     * Marks an appointment as completed if it exists and isn’t cancelled.
     */
    @Override
    public boolean completeAppointment(String id) throws AppointmentException {
        if (id == null || id.trim().isEmpty()) {
            throw new AppointmentException("Appointment ID cannot be empty.");
        }

        // Retrieve the appointment to verify status
        AppointmentDTO a = appointmentDAO.getAppointmentById(id);
        if (a == null) {
            throw new AppointmentException("Appointment with ID " + id + " not found.");
        }
        if ("Cancelled".equalsIgnoreCase(a.getStatus())) {
            throw new AppointmentException("Cannot complete a cancelled appointment.");
        }

        // Update status to completed
        a.setStatus("Completed");
        return appointmentDAO.updateAppointment(a);
    }

    /**
     * Retrieves an appointment by ID after validating input.
     */
    @Override
    public AppointmentDTO getAppointmentById(String id) throws AppointmentException {
        if (id == null || id.trim().isEmpty()) {
            throw new AppointmentException("Appointment ID cannot be empty.");
        }

        AppointmentDTO appointment = appointmentDAO.getAppointmentById(id);
        if (appointment == null) {
            throw new AppointmentException("Appointment with ID " + id + " not found.");
        }

        return appointment;
    }

    /**
     * Retrieves all appointments associated with a specific patient.
     */
    @Override
    public List<AppointmentDTO> getAppointmentsByPatientId(String patientId) throws AppointmentException {
        if (patientId == null || patientId.trim().isEmpty()) {
            throw new AppointmentException("Patient ID cannot be empty.");
        }
        return appointmentDAO.getAppointmentsByPatientId(patientId);
    }

    /**
     * Retrieves all appointments associated with a specific doctor.
     */
    @Override
    public List<AppointmentDTO> getAppointmentsByDoctorId(String doctorId) throws AppointmentException {
        if (doctorId == null || doctorId.trim().isEmpty()) {
            throw new AppointmentException("Doctor ID cannot be empty.");
        }
        return appointmentDAO.getAppointmentsByDoctorId(doctorId);
    }

    /**
     * Retrieves appointments by their current status (e.g., Scheduled, Completed).
     */
    @Override
    public List<AppointmentDTO> getAppointmentsByStatus(String status) throws AppointmentException {
        if (status == null || status.trim().isEmpty()) {
            throw new AppointmentException("Status cannot be empty.");
        }
        return appointmentDAO.getAppointmentsByStatus(status);
    }

    /**
     * Retrieves all appointments in the system without filtering.
     */
    @Override
    public List<AppointmentDTO> getAllAppointments() throws AppointmentException {
        return appointmentDAO.getAllAppointments();
    }

    /**
     * Generates and retrieves the next unique appointment ID.
     */
    @Override
    public String getNextAppointmentId() throws AppointmentException {
        String nextId = appointmentDAO.getNextAppointmentId();
        if (nextId == null || nextId.trim().isEmpty()) {
            throw new AppointmentException("Failed to generate next appointment ID.");
        }
        return nextId;
    }
}
//     * @throws AppointmentException if time is null or in incorrect format.