package com.abes.medx.dao;

import java.util.List;
import java.util.stream.Collectors;

import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.util.CollectionUtil;

public class AppointmentDAOImpl implements AppointmentDAO {

    @Override
    public boolean bookAppointment(AppointmentDTO appointment) {
        // Check if appointment ID already exists
        if (CollectionUtil.appointmentMap.containsKey(appointment.getAppointmentId())) {
            return false;
        }

        // Conflict check: same doctor, same date, same time
        boolean conflict = CollectionUtil.appointmentMap.values().stream()
            .anyMatch(a -> a.getDoctor().getDoctorId().equals(appointment.getDoctor().getDoctorId())
                    && a.getAppointmentDate().equals(appointment.getAppointmentDate())
                    && a.getAppointmentTime().equals(appointment.getAppointmentTime())
                    && !"Cancelled".equalsIgnoreCase(a.getStatus()));

        if (conflict) {
            return false; // Conflict exists
        }

        // Set default status
        appointment.setStatus("Scheduled");

        CollectionUtil.appointmentMap.put(appointment.getAppointmentId(), appointment);
        return true;
    }

    @Override
    public boolean updateAppointment(AppointmentDTO updatedAppointment) {
        if (!CollectionUtil.appointmentMap.containsKey(updatedAppointment.getAppointmentId())) {
            return false;
        }
        CollectionUtil.appointmentMap.put(updatedAppointment.getAppointmentId(), updatedAppointment);
        return true;
    }

    @Override
    public boolean cancelAppointment(String appointmentId) {
        AppointmentDTO appointment = CollectionUtil.appointmentMap.get(appointmentId);
        if (appointment != null) {
            appointment.setStatus("Cancelled"); // Soft delete (mark as cancelled)
            return true;
        }
        return false;
    }

    @Override
    public AppointmentDTO getAppointmentById(String appointmentId) {
        return CollectionUtil.appointmentMap.get(appointmentId);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByPatientId(String patientId) {
        return CollectionUtil.appointmentMap.values().stream()
                .filter(a -> a.getPatient() != null && patientId.equals(a.getPatient().getPatientId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByDoctorId(String doctorId) {
        return CollectionUtil.appointmentMap.values().stream()
                .filter(a -> a.getDoctor() != null && doctorId.equals(a.getDoctor().getDoctorId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return CollectionUtil.appointmentMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByStatus(String status) {
        return CollectionUtil.appointmentMap.values().stream()
                .filter(a -> a.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

}
