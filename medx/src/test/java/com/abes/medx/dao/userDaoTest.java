package com.abes.medx.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abes.medx.dto.AdminDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.UserException;
import com.abes.medx.util.CollectionUtil;

class userDaoTest {

    private PatientDAO patientDAO;
    private DoctorDAO doctorDAO;
    private AdminDAO adminDAO;

    @BeforeEach
    void setUp() {
        patientDAO = new PatientDAOImpl();
        doctorDAO = new DoctorDAOImpl();
        adminDAO = new AdminDAOImpl();
        // Clear all maps to ensure a clean state for each test
        CollectionUtil.patientMap.clear();
        CollectionUtil.doctorMap.clear();
        CollectionUtil.adminMap.clear();
    }

    @Test
    void testDuplicateEmailAcrossUserTypes() throws UserException {
        // Register a patient
        PatientDTO patient = new PatientDTO("P001", "John Doe", "user@example.com", "pass123", "9876543210", "30");
        assertTrue(patientDAO.register(patient));

        // Try registering a doctor with the same email
        DoctorDTO doctor = new DoctorDTO("D001", "Dr. Smith", "user@example.com", "pass123", "1234567890", "40", "Cardiology", 15);
        assertFalse(doctorDAO.register(doctor), "Doctor registration should fail due to duplicate email");

        // Try registering an admin with the same email
        AdminDTO admin = new AdminDTO("A001", "Alice Admin", "user@example.com", "admin123", "1111111111", "35");
        assertFalse(adminDAO.register(admin), "Admin registration should fail due to duplicate email");
    }

    @Test
    void testNextIdGenerationConsistency() throws UserException {
        // Test patient ID generation
        assertEquals("P1", patientDAO.getNextPatientId());
        PatientDTO patient = new PatientDTO("P1", "John Doe", "john@example.com", "pass123", "9876543210", "30");
        patientDAO.register(patient);
        assertEquals("P2", patientDAO.getNextPatientId());

        // Test doctor ID generation
        assertEquals("D1", doctorDAO.getNextDoctorId());
        DoctorDTO doctor = new DoctorDTO("D1", "Dr. Smith", "smith@example.com", "pass123", "1234567890", "40", "Cardiology", 15);
        doctorDAO.register(doctor);
        assertEquals("D2", doctorDAO.getNextDoctorId());

        // Test admin ID generation
        assertEquals("ADM1", adminDAO.getNextAdminId());
        AdminDTO admin = new AdminDTO("ADM1", "Alice Admin", "alice@admin.com", "admin123", "1111111111", "35");
        adminDAO.register(admin);
        assertEquals("ADM2", adminDAO.getNextAdminId());
    }

    @Test
    void testCrossUserAuthenticationFailure() throws UserException {
        // Register a patient
        PatientDTO patient = new PatientDTO("P001", "John Doe", "user@example.com", "pass123", "9876543210", "30");
        patientDAO.register(patient);

        // Try authenticating as a doctor with patient credentials
        assertNull(doctorDAO.authenticate("user@example.com", "pass123"), "Doctor authentication should fail with patient credentials");

        // Try authenticating as an admin with patient credentials
        assertNull(adminDAO.authenticate("user@example.com", "pass123"), "Admin authentication should fail with patient credentials");
    }

    @Test
    void testInvalidEmailValidation() {
        // Test registration with invalid email across all user types
        assertThrows(UserException.class, () -> {
            PatientDTO patient = new PatientDTO("P001", "John Doe", "invalid-email", "pass123", "9876543210", "30");
            patientDAO.register(patient);
        }, "Patient registration should fail with invalid email");

        assertThrows(UserException.class, () -> {
            DoctorDTO doctor = new DoctorDTO("D001", "Dr. Smith", "invalid-email", "pass123", "1234567890", "40", "Cardiology", 15);
            doctorDAO.register(doctor);
        }, "Doctor registration should fail with invalid email");

        assertThrows(UserException.class, () -> {
            AdminDTO admin = new AdminDTO("A001", "Alice Admin", "invalid-email", "admin123", "1111111111", "35");
            adminDAO.register(admin);
        }, "Admin registration should fail with invalid email");
    }
}