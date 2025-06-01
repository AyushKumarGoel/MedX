package com.abes.medx.dao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.UserException;
import com.abes.medx.util.CollectionUtil;

class PatientDAOImplTest {

    private PatientDAO patientDAO;
    private PatientDTO samplePatient;

    // Runs before each test case to initialize test data and reset storage
    @BeforeEach
    void setUp() throws UserException {
        patientDAO = new PatientDAOImpl();
        CollectionUtil.patientMap.clear(); // Clear map before each test
        samplePatient = new PatientDTO(
            "P10", "Kamal", "kamal@example.com", "pass123", "9876543210", "30"
        );
    }

    // Test: Registering a new patient should succeed
    @Test
    void testRegisterSuccess() {
        assertTrue(patientDAO.register(samplePatient));
        assertEquals(1, CollectionUtil.patientMap.size());
    }

    // Test: Registering a duplicate patient should fail
    @Test
    void testRegisterDuplicate() {
        patientDAO.register(samplePatient);
        assertFalse(patientDAO.register(samplePatient)); // Should not allow duplicate
    }

    // Test: Authenticating with correct credentials should return patient
    @Test
    void testAuthenticateSuccess() {
        patientDAO.register(samplePatient);
        PatientDTO result = patientDAO.authenticate("kamal@example.com", "pass123");
        assertNotNull(result);
        assertEquals("P10", result.getPatientId());
    }

    // Test: Authenticating with wrong password should return null
    @Test
    void testAuthenticateFailure() {
        patientDAO.register(samplePatient);
        PatientDTO result = patientDAO.authenticate("kamal@example.com", "wrongpass");
        assertNull(result);
    }

    // Test: Updating profile of existing patient should succeed
    @Test
    void testUpdateProfileSuccess() throws UserException {
        patientDAO.register(samplePatient);
        PatientDTO updated = new PatientDTO(
            "P10", "Kamal Updated", "kamal@example.com", "newpass", "0000000000", "31"
        );
        assertTrue(patientDAO.updateProfile(updated));
        assertEquals("Kamal Updated", CollectionUtil.patientMap.get("P10").getName());
    }

    // Test: Updating profile of unregistered patient should fail
    @Test
    void testUpdateProfileFailure() throws UserException {
        PatientDTO newPatient = new PatientDTO(
            "P11", "Ayushi", "ayushi@example.com", "pass456", "1111111111", "28"
        );
        assertFalse(patientDAO.updateProfile(newPatient));
    }

    // Test: Deleting a registered patient should succeed
    @Test
    void testDeleteSuccess() {
        patientDAO.register(samplePatient);
        assertTrue(patientDAO.delete("kamal@example.com"));
        assertEquals(0, CollectionUtil.patientMap.size());
    }

    // Test: Deleting a non-existent patient should fail
    @Test
    void testDeleteFailure() {
        assertFalse(patientDAO.delete("abc@example.com"));
    }

    // Test: Retrieving patient by valid email should return correct patient
    @Test
    void testGetPatientByEmailSuccess() {
        patientDAO.register(samplePatient);
        PatientDTO result = patientDAO.getPatientByEmail("kamal@example.com");
        assertNotNull(result);
        assertEquals("P10", result.getPatientId());
    }

    // Test: Retrieving patient by unknown email should return null
    @Test
    void testGetPatientByEmailFailure() {
        assertNull(patientDAO.getPatientByEmail("notfound@example.com"));
    }

    // Test: Should return all registered patients
    @Test
    void testGetAllPatients() throws UserException {
        patientDAO.register(samplePatient);
        patientDAO.register(new PatientDTO(
            "P11", "tanya", "tanya@example.com", "tanya123", "9999999999", "25"
        ));
        List<PatientDTO> list = patientDAO.getAllPatients();
        assertEquals(2, list.size());
    }
}
