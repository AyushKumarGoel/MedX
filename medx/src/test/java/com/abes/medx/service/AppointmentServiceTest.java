package com.abes.medx.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abes.medx.dao.AppointmentDAO;
import com.abes.medx.dao.AppointmentDAOImpl;
import com.abes.medx.dao.DoctorDAO;
import com.abes.medx.dao.DoctorDAOImpl;
import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.AppointmentException;
import com.abes.medx.exception.BookingException;
import com.abes.medx.util.CollectionUtil;

class AppointmentServiceTest {

    private AppointmentService appointmentService;
    private AppointmentDAO appointmentDAO;
    private DoctorDAO doctorDAO;

    @BeforeEach
    public void setUp() {
        appointmentDAO = new AppointmentDAOImpl();
        doctorDAO = new DoctorDAOImpl();
        appointmentService = new AppointmentService(appointmentDAO, doctorDAO);
    }

    @Test
    void testCreateAppointment_Success() throws AppointmentException {
        String appointmentId = "AP1000";
        String date = "2025-06-01";
        String time = "10:00";
        PatientDTO patient = CollectionUtil.patientMap.get("P1");
        String doctorId = "D1";
        int toPay = 500;

        AppointmentDTO appointment = appointmentService.createAppointment(appointmentId, date, time, patient, doctorId, toPay);

        assertNotNull(appointment);
        assertEquals(appointmentId, appointment.getAppointmentId());
        assertEquals("Scheduled", appointment.getStatus());
    }

    @Test
    void testCreateAppointment_InvalidData() {
        assertThrows(AppointmentException.class, () -> {
            appointmentService.createAppointment(null, null, null, null, null, -100);
        });
    }

    @Test
    void testBookAppointment_Success() throws AppointmentException, BookingException {
        String appointmentId = "AP3";
        String date = "2025-06-02";
        String time = "11:00";
        PatientDTO patient = CollectionUtil.patientMap.get("P1");
        String doctorId = "D1";
        int toPay = 700;

        AppointmentDTO appointment = appointmentService.createAppointment(appointmentId, date, time, patient, doctorId, toPay);
        boolean booked = appointmentService.bookAppointment(appointment);

        assertTrue(booked);
    }

    @Test
    void testBookAppointment_NullAppointment() {
        assertThrows(BookingException.class, () -> {
            appointmentService.bookAppointment(null);
        });
    }

    @Test
    void testUpdateAppointment_InvalidData() {
        assertThrows(AppointmentException.class, () -> {
            appointmentService.updateAppointment(null);
        });
    }

    @Test
    void testCancelAppointment_Success() throws AppointmentException {
        boolean canceled = appointmentService.cancelAppointment("AP2");

        assertTrue(true);
        assertEquals("Cancelled", CollectionUtil.appointmentMap.get("AP2").getStatus());
    }

    @Test
    void testCancelAppointment_InvalidId() {
        assertThrows(AppointmentException.class, () -> {
            appointmentService.cancelAppointment("");
        });
    }

   

    @Test
    void testCompleteAppointment_Cancelled() throws AppointmentException {
        String appointmentId = "AP2";
        AppointmentDTO appointment = CollectionUtil.appointmentMap.get(appointmentId);
        appointment.setStatus("Cancelled");

        assertThrows(AppointmentException.class, () -> {
            appointmentService.completeAppointment(appointmentId);
        });
    }

    @Test
    void testGetAppointmentById_Success() throws AppointmentException {
        AppointmentDTO appointment = appointmentService.getAppointmentById("AP2");

        assertNotNull(appointment);
        assertEquals("AP2", appointment.getAppointmentId());
    }

    @Test
    void testGetAppointmentById_InvalidId() {
        assertThrows(AppointmentException.class, () -> {
            appointmentService.getAppointmentById("");
        });
    }

    @Test
    void testGetAppointmentsByPatientId_Success() throws AppointmentException {
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsByPatientId("P1");

        assertNotNull(appointments);
        assertFalse(appointments.isEmpty());
    }

    @Test
    void testGetAppointmentsByPatientId_InvalidId() {
        assertThrows(AppointmentException.class, () -> {
            appointmentService.getAppointmentsByPatientId("");
        });
    }

    @Test
    void testGetAppointmentsByDoctorId_Success() throws AppointmentException {
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsByDoctorId("D1");

        assertNotNull(appointments);
        assertFalse(appointments.isEmpty());
    }

    @Test
    void testGetAppointmentsByDoctorId_InvalidId() {
        assertThrows(AppointmentException.class, () -> {
            appointmentService.getAppointmentsByDoctorId("");
        });
    }

    @Test
    void testGetAppointmentsByStatus_Success() throws AppointmentException {
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsByStatus("Scheduled");

        assertNotNull(appointments);
        assertFalse(appointments.isEmpty());
    }

    @Test
    void testGetAppointmentsByStatus_InvalidStatus() {
        assertThrows(AppointmentException.class, () -> {
            appointmentService.getAppointmentsByStatus("");
        });
    }

    @Test
    void testGetAllAppointments() throws AppointmentException {
        List<AppointmentDTO> appointments = appointmentService.getAllAppointments();

        assertNotNull(appointments);
        assertFalse(appointments.isEmpty());
    }

    @Test
    void testGetNextAppointmentId() throws AppointmentException {
        String nextId = appointmentService.getNextAppointmentId();

        assertNotNull(nextId);
        assertFalse(nextId.trim().isEmpty());
    }
}
