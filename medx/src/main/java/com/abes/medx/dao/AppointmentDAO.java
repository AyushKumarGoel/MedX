package com.abes.medx.dao;

import java.util.List;

import com.abes.medx.dto.AppointmentDTO;

/**
 * DAO interface for Appointment operations.
 * Defines methods to manage appointments in the system.
 */
public interface AppointmentDAO {

    /**
     * Books a new appointment.
     *
     * @param appointment AppointmentDTO object containing appointment details
     * @return true if booking is successful, false otherwise
     */
    boolean bookAppointment(AppointmentDTO appointment);

    /**
     * Updates an existing appointment with new details.
     *
     * @param updatedAppointment AppointmentDTO object containing updated appointment info
     * @return true if update is successful, false if appointment does not exist
     */
    boolean updateAppointment(AppointmentDTO updatedAppointment);

    /**
     * Cancels an appointment by its unique ID.
     *
     * @param appointmentId Unique identifier of the appointment to cancel
     * @return true if cancellation is successful, false if appointment not found
     */
    boolean cancelAppointment(String appointmentId);

    /**
     * Retrieves an appointment by its unique ID.
     *
     * @param appointmentId Unique identifier of the appointment
     * @return AppointmentDTO object if found, null otherwise
     */
    AppointmentDTO getAppointmentById(String appointmentId);

    /**
     * Generates the next unique appointment ID.
     *
     * @return String representing the next available appointment ID
     */
    String getNextAppointmentId();

    /**
     * Retrieves all appointments associated with a specific patient.
     *
     * @param patientId Unique identifier of the patient
     * @return List of AppointmentDTO objects for the given patient
     */
    List<AppointmentDTO> getAppointmentsByPatientId(String patientId);

    /**
     * Retrieves all appointments associated with a specific doctor.
     *
     * @param doctorId Unique identifier of the doctor
     * @return List of AppointmentDTO objects for the given doctor
     */
    List<AppointmentDTO> getAppointmentsByDoctorId(String doctorId);

    /**
     * Retrieves all appointments in the system.
     *
     * @return List of all AppointmentDTO objects
     */
    List<AppointmentDTO> getAllAppointments();

    /**
     * Retrieves appointments filtered by their status.
     * For example: "Scheduled", "Completed", "Cancelled".
     *
     * @param status The status filter for appointments
     * @return List of AppointmentDTO objects matching the given status
     */
    List<AppointmentDTO> getAppointmentsByStatus(String status);
}
