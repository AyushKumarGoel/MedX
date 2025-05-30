package com.abes.medx.dao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.UserException;
import com.abes.medx.util.CollectionUtil;

class AppointmentDAOImplTest {

    private AppointmentDAO appointmentDAO;
    private DoctorDTO doctor;
    private PatientDTO patient;

    @BeforeEach
    void setUp() throws UserException {
        appointmentDAO = new AppointmentDAOImpl();
        CollectionUtil.appointmentMap.clear();

        doctor = new DoctorDTO("D001", "Dr. Smith", "smith@doc.com", "doc123", "9999999999", "40", "Cardiology", 15);
        patient = new PatientDTO("John Doe", "john@pat.com", "pat123", "8888888888", "30", "P001");
    }

    AppointmentDTO createAppointment(String id, String date, String time) {
        return new AppointmentDTO(id, date, time, patient, doctor, 500, "Scheduled");
    }

    @Test
    void testBookAppointmentSuccess() {
        AppointmentDTO appt = createAppointment("AP001", "2025-06-01", "10:00 AM");
        assertTrue(appointmentDAO.bookAppointment(appt));
        assertEquals(1, CollectionUtil.appointmentMap.size());
    }

    @Test
    void testBookAppointmentDuplicateIdFails() {
        AppointmentDTO appt1 = createAppointment("AP001", "2025-06-01", "10:00 AM");
        AppointmentDTO appt2 = createAppointment("AP001", "2025-06-02", "11:00 AM");

        assertTrue(appointmentDAO.bookAppointment(appt1));
        assertFalse(appointmentDAO.bookAppointment(appt2)); // Duplicate ID
    }

    @Test
    void testBookAppointmentConflictFails() {
        AppointmentDTO appt1 = createAppointment("AP001", "2025-06-01", "10:00 AM");
        AppointmentDTO appt2 = createAppointment("AP002", "2025-06-01", "10:00 AM"); // Same doctor, date, time

        assertTrue(appointmentDAO.bookAppointment(appt1));
        assertFalse(appointmentDAO.bookAppointment(appt2)); // Conflict
    }

    @Test
    void testUpdateAppointmentSuccess() {
        AppointmentDTO appt = createAppointment("AP001", "2025-06-01", "10:00 AM");
        appointmentDAO.bookAppointment(appt);

        appt.setToPay(1000);
        assertTrue(appointmentDAO.updateAppointment(appt));
        assertEquals(1000, CollectionUtil.appointmentMap.get("AP001").getToPay());
    }

    @Test
    void testUpdateAppointmentFailsIfNotExist() {
        AppointmentDTO appt = createAppointment("AP001", "2025-06-01", "10:00 AM");
        assertFalse(appointmentDAO.updateAppointment(appt)); // Not in map
    }

    @Test
    void testCancelAppointmentSuccess() {
        AppointmentDTO appt = createAppointment("AP001", "2025-06-01", "10:00 AM");
        appointmentDAO.bookAppointment(appt);

        assertTrue(appointmentDAO.cancelAppointment("AP001"));
        assertEquals("Cancelled", CollectionUtil.appointmentMap.get("AP001").getStatus());
    }

    @Test
    void testCancelAppointmentFailsIfNotFound() {
        assertFalse(appointmentDAO.cancelAppointment("AP404"));
    }

    @Test
    void testGetAppointmentById() {
        AppointmentDTO appt = createAppointment("AP001", "2025-06-01", "10:00 AM");
        appointmentDAO.bookAppointment(appt);

        AppointmentDTO result = appointmentDAO.getAppointmentById("AP001");
        assertNotNull(result);
        assertEquals("AP001", result.getAppointmentId());
    }

    @Test
    void testGetAppointmentsByPatientId() {
        AppointmentDTO appt = createAppointment("AP001", "2025-06-01", "10:00 AM");
        appointmentDAO.bookAppointment(appt);

        List<AppointmentDTO> result = appointmentDAO.getAppointmentsByPatientId("P001");
        assertEquals(1, result.size());
    }

    @Test
    void testGetAppointmentsByDoctorId() {
        AppointmentDTO appt = createAppointment("AP001", "2025-06-01", "10:00 AM");
        appointmentDAO.bookAppointment(appt);

        List<AppointmentDTO> result = appointmentDAO.getAppointmentsByDoctorId("D001");
        assertEquals(1, result.size());
    }

    @Test
    void testGetAllAppointments() {
        appointmentDAO.bookAppointment(createAppointment("AP001", "2025-06-01", "10:00 AM"));
        appointmentDAO.bookAppointment(createAppointment("AP002", "2025-06-02", "11:00 AM"));

        List<AppointmentDTO> all = appointmentDAO.getAllAppointments();
        assertEquals(2, all.size());
    }

    @Test
    void testGetAppointmentsByStatus() {
        AppointmentDTO appt1 = createAppointment("AP001", "2025-06-01", "10:00 AM");
        AppointmentDTO appt2 = createAppointment("AP002", "2025-06-02", "11:00 AM");

        appointmentDAO.bookAppointment(appt1);
        appointmentDAO.bookAppointment(appt2);
        appointmentDAO.cancelAppointment("AP002");

        List<AppointmentDTO> scheduled = appointmentDAO.getAppointmentsByStatus("Scheduled");
        List<AppointmentDTO> cancelled = appointmentDAO.getAppointmentsByStatus("Cancelled");

        assertEquals(1, scheduled.size());
        assertEquals(1, cancelled.size());
    }

    @Test
    void testGetNextAppointmentId() {
        assertEquals("AP1", appointmentDAO.getNextAppointmentId());
        appointmentDAO.bookAppointment(createAppointment("AP1", "2025-06-01", "10:00 AM"));
        assertEquals("AP2", appointmentDAO.getNextAppointmentId());
    }
}
