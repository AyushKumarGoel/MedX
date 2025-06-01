package com.abes.medx.dao;

import java.util.List;
import java.util.stream.Collectors;

import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.util.CollectionUtil;

/**
 * Implementation of AppointmentDAO interface.
 * Manages appointment data using an in-memory collection.
 */
public class AppointmentDAOImpl implements AppointmentDAO {

    /**
     * Books a new appointment if no conflict exists.
     * 
     * @param appointment AppointmentDTO containing appointment details
     * @return true if booking is successful, false if appointment ID exists or conflict occurs
     */
    @Override
    public boolean bookAppointment(AppointmentDTO appointment) {
        // Check if appointment ID already exists in the collection
        if (CollectionUtil.appointmentMap.containsKey(appointment.getAppointmentId())) {
            return false; // Duplicate appointment ID, booking fails
        }

        // Conflict check: Prevent double booking for the same doctor at the same date and time,
        // ignoring cancelled appointments
        boolean conflict = CollectionUtil.appointmentMap.values().stream()
            .anyMatch(a -> a.getDoctor().getDoctorId().equals(appointment.getDoctor().getDoctorId())
                    && a.getAppointmentDate().equals(appointment.getAppointmentDate())
                    && a.getAppointmentTime().equals(appointment.getAppointmentTime())
                    && !"Cancelled".equalsIgnoreCase(a.getStatus()));

        if (conflict) {
            return false; // Conflict exists, booking fails
        }

        // Set default status to "Scheduled" when booking a new appointment
        appointment.setStatus("Scheduled");

        // Add appointment to the collection
        CollectionUtil.appointmentMap.put(appointment.getAppointmentId(), appointment);
        return true;
    }

    /**
     * Updates details of an existing appointment.
     * 
     * @param updatedAppointment AppointmentDTO with updated details
     * @return true if update is successful, false if appointment does not exist
     */
    @Override
    public boolean updateAppointment(AppointmentDTO updatedAppointment) {
        if (!CollectionUtil.appointmentMap.containsKey(updatedAppointment.getAppointmentId())) {
            return false; // Appointment does not exist
        }
        CollectionUtil.appointmentMap.put(updatedAppointment.getAppointmentId(), updatedAppointment);
        return true;
    }

    /**
     * Cancels an appointment by setting its status to "Cancelled".
     * 
     * @param appointmentId Unique ID of the appointment to cancel
     * @return true if cancellation is successful, false if appointment not found
     */
    @Override
    public boolean cancelAppointment(String appointmentId) {
        AppointmentDTO appointment = CollectionUtil.appointmentMap.get(appointmentId);
        if (appointment != null) {
            // Soft delete by marking appointment as cancelled
            appointment.setStatus("Cancelled");
            return true;
        }
        return false; // Appointment not found
    }

    /**
     * Retrieves an appointment by its unique ID.
     * 
     * @param appointmentId Unique appointment identifier
     * @return AppointmentDTO if found, null otherwise
     */
    @Override
    public AppointmentDTO getAppointmentById(String appointmentId) {
        return CollectionUtil.appointmentMap.get(appointmentId);
    }

    /**
     * Retrieves all appointments associated with a specific patient ID.
     * 
     * @param patientId Unique patient identifier
     * @return List of appointments for the given patient
     */
    @Override
    public List<AppointmentDTO> getAppointmentsByPatientId(String patientId) {
        return CollectionUtil.appointmentMap.values().stream()
                .filter(a -> a.getPatient() != null && patientId.equals(a.getPatient().getPatientId()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all appointments associated with a specific doctor ID.
     * 
     * @param doctorId Unique doctor identifier
     * @return List of appointments for the given doctor
     */
    @Override
    public List<AppointmentDTO> getAppointmentsByDoctorId(String doctorId) {
        return CollectionUtil.appointmentMap.values().stream()
                .filter(a -> a.getDoctor() != null && doctorId.equals(a.getDoctor().getDoctorId()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all appointments stored in the system.
     * 
     * @return List of all appointments
     */
    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return CollectionUtil.appointmentMap.values().stream().collect(Collectors.toList());
    }

    /**
     * Retrieves appointments filtered by their status (e.g., "Scheduled", "Cancelled").
     * 
     * @param status Status string to filter appointments
     * @return List of appointments matching the given status
     */
    @Override
    public List<AppointmentDTO> getAppointmentsByStatus(String status) {
        return CollectionUtil.appointmentMap.values().stream()
                .filter(a -> a.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    /**
     * Generates the next unique appointment ID in the format "AP" + number.
     * 
     * @return Next available appointment ID
     */
    @Override
    public String getNextAppointmentId() {
        int i = 1;
        // Increment until an unused ID is found
        while (CollectionUtil.appointmentMap.containsKey("AP" + i)) {
            i++;
        }
        return "AP" + i;
    }
    
}
