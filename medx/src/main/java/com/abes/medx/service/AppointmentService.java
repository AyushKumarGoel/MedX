package com.abes.medx.service;

import java.util.List;

import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.AppointmentException;
import com.abes.medx.exception.BookingException;

public interface AppointmentService {

    AppointmentDTO createAppointment(String id, String date, String time, PatientDTO patient, String doctorId, int toPay) throws AppointmentException;

    boolean bookAppointment(AppointmentDTO appointment) throws BookingException;

    boolean updateAppointment(AppointmentDTO appointment) throws AppointmentException;

    boolean cancelAppointment(String id) throws AppointmentException;

    boolean completeAppointment(String id) throws AppointmentException;

    AppointmentDTO getAppointmentById(String id) throws AppointmentException;

    List<AppointmentDTO> getAppointmentsByPatientId(String patientId) throws AppointmentException;

    List<AppointmentDTO> getAppointmentsByDoctorId(String doctorId) throws AppointmentException;

    List<AppointmentDTO> getAppointmentsByStatus(String status) throws AppointmentException;

    List<AppointmentDTO> getAllAppointments() throws AppointmentException;

    String getNextAppointmentId() throws AppointmentException;
}
