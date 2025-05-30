package com.abes.medx.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abes.medx.dao.AdminDAO;
import com.abes.medx.dao.AdminDAOImpl;
import com.abes.medx.dao.DoctorDAO;
import com.abes.medx.dao.DoctorDAOImpl;
import com.abes.medx.dao.PatientDAO;
import com.abes.medx.dao.PatientDAOImpl;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.UserException;
import com.abes.medx.util.CollectionUtil;

public class PatientServiceTest {

    private UserService userService;
    private PatientDAO patientDAO;

    @BeforeEach
    void setUp() {
        patientDAO = new PatientDAOImpl();
        AdminDAO adminDAO = new AdminDAOImpl();
        DoctorDAO doctorDAO = new DoctorDAOImpl();
        userService = new UserService(adminDAO, doctorDAO, patientDAO);
        CollectionUtil.patientMap.clear();
    }

    @Test
    void testRegisterPatientSuccess() throws UserException {
        PatientDTO patient = new PatientDTO("P001", "John Doe", "john@example.com", "pass123", "9876543210", "30");
        assertTrue(userService.registerPatient(patient));
        assertEquals(1, CollectionUtil.patientMap.size());
        assertEquals("John Doe", CollectionUtil.patientMap.get("P001").getName());
    }

    @Test
    void testRegisterPatientInvalidData() {
        assertThrows(UserException.class, () -> {
            PatientDTO patient = null;
            userService.registerPatient(patient);
        }, "Should throw UserException for null patient");
    }

    @Test
    void testPatientLoginSuccess() throws UserException {
        PatientDTO patient = new PatientDTO("P001", "John Doe", "john@example.com", "pass123", "9876543210", "30");
        userService.registerPatient(patient);
        PatientDTO result = userService.patientLogin("john@example.com", "pass123");
        assertNotNull(result);
        assertEquals("P001", result.getPatientId());
    }

    @Test
    void testPatientLoginFailure() throws UserException {
        PatientDTO patient = new PatientDTO("P001", "John Doe", "john@example.com", "pass123", "9876543210", "30");
        userService.registerPatient(patient);
        assertThrows(UserException.class, () -> {
            userService.patientLogin("john@example.com", "wrongpass");
        }, "Should throw UserException for wrong password");
    }

    @Test
    void testUpdatePatientProfileSuccess() throws UserException {
        PatientDTO patient = new PatientDTO("P001", "John Doe", "john@example.com", "pass123", "9876543210", "30");
        userService.registerPatient(patient);
        PatientDTO updated = new PatientDTO("P001", "John Updated", "john@example.com", "newpass", "1111111111", "31");
        assertTrue(userService.updatePatientProfile(updated));
        assertEquals("John Updated", CollectionUtil.patientMap.get("P001").getName());
    }

    @Test
    void testUpdatePatientProfileFailure() throws UserException {
        PatientDTO patient = new PatientDTO("P001", "John Doe", "john@example.com", "pass123", "9876543210", "30");
        assertFalse(userService.updatePatientProfile(patient), "Update should fail for unregistered patient");
    }

    @Test
    void testDeletePatientSuccess() throws UserException {
        PatientDTO patient = new PatientDTO("P001", "John Doe", "john@example.com", "pass123", "9876543210", "30");
        userService.registerPatient(patient);
        assertTrue(userService.deletePatient("john@example.com"));
        assertEquals(0, CollectionUtil.patientMap.size());
    }

    @Test
    void testDeletePatientFailure() {
        assertThrows(UserException.class, () -> {
            userService.deletePatient("");
        }, "Should throw UserException for empty email");
    }

    @Test
    void testGetPatientByEmailSuccess() throws UserException {
        PatientDTO patient = new PatientDTO("P001", "John Doe", "john@example.com", "pass123", "9876543210", "30");
        userService.registerPatient(patient);
        PatientDTO result = userService.getPatientByEmail("john@example.com");
        assertNotNull(result);
        assertEquals("P001", result.getPatientId());
    }

    @Test
    void testGetAllPatients() throws UserException {
        PatientDTO patient1 = new PatientDTO("P001", "John Doe", "john@example.com", "pass123", "9876543210", "30");
        PatientDTO patient2 = new PatientDTO("P002", "Jane Doe", "jane@example.com", "pass456", "1111111111", "25");
        userService.registerPatient(patient1);
        userService.registerPatient(patient2);
        List<PatientDTO> patients = userService.getAllPatients();
        assertEquals(2, patients.size());
    }

    @Test
    void testGetNextPatientId() throws UserException {
        assertEquals("P1", userService.getNextPatientId());
        PatientDTO patient = new PatientDTO("P1", "John Doe", "john@example.com", "pass123", "9876543210", "30");
        userService.registerPatient(patient);
        assertEquals("P2", userService.getNextPatientId());
    }
}