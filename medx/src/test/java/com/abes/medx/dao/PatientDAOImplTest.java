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

    @BeforeEach
    void setUp() {
        patientDAO = new PatientDAOImpl();
        try {
            samplePatient = new PatientDTO(
                    "John Doe", "john@example.com", "pass123", "9876543210", "30", "P001"
            );
        } catch (UserException e) {
            throw new RuntimeException("Failed to create sample patient", e);
        }
        CollectionUtil.patientMap.clear(); // Clear storage before each test
    }

    @Test
    void testRegisterSuccess() {
        assertTrue(patientDAO.register(samplePatient));
        assertEquals(1, CollectionUtil.patientMap.size());
    }

    @Test
    void testRegisterDuplicate() {
        patientDAO.register(samplePatient);
        assertFalse(patientDAO.register(samplePatient)); // Duplicate ID
    }

    @Test
    void testAuthenticateSuccess() {
        patientDAO.register(samplePatient);
        PatientDTO result = patientDAO.authenticate("john@example.com", "pass123");
        assertNotNull(result);
        assertEquals("P001", result.getPatientId());
    }

    @Test
    void testAuthenticateFailure() {
        patientDAO.register(samplePatient);
        PatientDTO result = patientDAO.authenticate("john@example.com", "wrongpass");
        assertNull(result);
    }

    @Test
    void testUpdateProfileSuccess() {
        patientDAO.register(samplePatient);
        PatientDTO updated = null;
        try {
            updated = new PatientDTO(
                    "John Updated", "john@example.com", "newpass", "0000000000", "31", "P001"
            );
        } catch (UserException e) {
            throw new RuntimeException("Failed to create updated patient", e);
        }
        assertTrue(patientDAO.updateProfile(updated));
        assertEquals("John Updated", CollectionUtil.patientMap.get("P001").getName());
    }

    @Test
    void testUpdateProfileFailure() {
        PatientDTO newPatient = null;
        try {
            newPatient = new PatientDTO(
                    "Jane Doe", "jane@example.com", "pass456", "1111111111", "28", "P999"
            );
        } catch (UserException e) {
            throw new RuntimeException("Failed to create new patient", e);
        }
        assertFalse(patientDAO.updateProfile(newPatient)); // Not yet registered
    }

    @Test
    void testDeleteSuccess() {
        patientDAO.register(samplePatient);
        assertTrue(patientDAO.delete("john@example.com"));
        assertEquals(0, CollectionUtil.patientMap.size());
    }

    @Test
    void testDeleteFailure() {
        assertFalse(patientDAO.delete("nonexistent@example.com"));
    }

    @Test
    void testGetPatientByEmailSuccess() {
        patientDAO.register(samplePatient);
        PatientDTO result = patientDAO.getPatientByEmail("john@example.com");
        assertNotNull(result);
        assertEquals("P001", result.getPatientId());
    }

    @Test
    void testGetPatientByEmailFailure() {
        assertNull(patientDAO.getPatientByEmail("notfound@example.com"));
    }

    @Test
    void testGetAllPatients() {
        patientDAO.register(samplePatient);
        try {
            patientDAO.register(new PatientDTO(
                    "Alice", "alice@example.com", "alice123", "9999999999", "25", "P002"
            ));
        } catch (UserException e) {
            throw new RuntimeException("Failed to create Alice patient", e);
        }
        List<PatientDTO> list = patientDAO.getAllPatients();
        assertEquals(2, list.size());
    }
}
