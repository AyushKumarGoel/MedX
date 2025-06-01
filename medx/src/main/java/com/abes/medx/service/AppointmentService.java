package com.abes.medx.service;

import java.util.List;

import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.AppointmentException;
import com.abes.medx.exception.BookingException;

/**
 * Service interface for managing appointment-related operations in the MedX system.
 * It defines methods for creating, booking, updating, and retrieving appointments.
 */
public interface AppointmentService {

    /**
     * Creates a new appointment with the specified details.
     *
     * @param id        Unique appointment ID.
     * @param date      Appointment date.
     * @param time      Appointment time.
     * @param patient   Patient object containing patient details.
     * @param doctorId  ID of the doctor for the appointment.
     * @param toPay     Payment amount for the appointment.
     * @return AppointmentDTO object with the created appointment details.
     * @throws AppointmentException if appointment creation fails.
     */
    AppointmentDTO createAppointment(String id, String date, String time, PatientDTO patient, String doctorId, int toPay) throws AppointmentException;

    /**
     * Books an existing appointment.
     *
     * @param appointment Appointment to be booked.
     * @return true if booking was successful, false otherwise.
     * @throws BookingException if booking fails.
     */
    boolean bookAppointment(AppointmentDTO appointment) throws BookingException;

    /**
     * Updates an existing appointment.
     *
     * @param appointment Updated appointment details.
     * @return true if update was successful.
     * @throws AppointmentException if update fails.
     */
    boolean updateAppointment(AppointmentDTO appointment) throws AppointmentException;

    /**
     * Cancels an appointment by ID.
     *
     * @param id ID of the appointment to cancel.
     * @return true if cancellation was successful.
     * @throws AppointmentException if cancellation fails.
     */
    boolean cancelAppointment(String id) throws AppointmentException;

    /**
     * Marks an appointment as completed by ID.
     *
     * @param id ID of the completed appointment.
     * @return true if completion was successful.
     * @throws AppointmentException if operation fails.
     */
    boolean completeAppointment(String id) throws AppointmentException;

    /**
     * Retrieves an appointment by its ID.
     *
     * @param id Appointment ID.
     * @return AppointmentDTO object with appointment details.
     * @throws AppointmentException if retrieval fails.
     */
    AppointmentDTO getAppointmentById(String id) throws AppointmentException;

    /**
     * Retrieves all appointments for a specific patient.
     *
     * @param patientId ID of the patient.
     * @return List of AppointmentDTOs for the patient.
     * @throws AppointmentException if retrieval fails.
     */
    List<AppointmentDTO> getAppointmentsByPatientId(String patientId) throws AppointmentException;

    /**
     * Retrieves all appointments for a specific doctor.
     *
     * @param doctorId ID of the doctor.
     * @return List of AppointmentDTOs for the doctor.
     * @throws AppointmentException if retrieval fails.
     */
    List<AppointmentDTO> getAppointmentsByDoctorId(String doctorId) throws AppointmentException;

    /**
     * Retrieves appointments filtered by their status (e.g., booked, completed, cancelled).
     *
     * @param status Status to filter appointments by.
     * @return List of AppointmentDTOs matching the status.
     * @throws AppointmentException if retrieval fails.
     */
    List<AppointmentDTO> getAppointmentsByStatus(String status) throws AppointmentException;

    /**
     * Retrieves all appointments in the system.
     *
     * @return List of all AppointmentDTOs.
     * @throws AppointmentException if retrieval fails.
     */
    List<AppointmentDTO> getAllAppointments() throws AppointmentException;

    /**
     * Generates or retrieves the next available appointment ID.
     *
     * @return A new unique appointment ID.
     * @throws AppointmentException if ID generation fails.
     */
    String getNextAppointmentId() throws AppointmentException;
}
