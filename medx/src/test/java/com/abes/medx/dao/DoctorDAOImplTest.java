package com.abes.medx.dao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.exception.UserException;
import com.abes.medx.util.CollectionUtil;

class DoctorDAOImplTest {

    private DoctorDAO doctorDAO;
    private DoctorDTO sampleDoctor;

    @BeforeEach
    void setUp() throws UserException {
        doctorDAO = new DoctorDAOImpl();
        try {
            sampleDoctor = new DoctorDTO(
                    "D001", "Dr. Smith", "smith@example.com", "pass123",
                    "1234567890", "45", "Cardiology", 15
            );
        } catch (UserException e) {
            throw new RuntimeException("Failed to create sampleDoctor in setUp", e);
        }
        CollectionUtil.doctorMap.clear(); // Reset storage before each test
    }

    @Test
    void testRegisterSuccess() {
        assertTrue(doctorDAO.register(sampleDoctor));
        assertEquals(1, CollectionUtil.doctorMap.size());
    }

    @Test
    void testRegisterDuplicate() {
        doctorDAO.register(sampleDoctor);
        assertFalse(doctorDAO.register(sampleDoctor)); // Should fail due to duplicate ID
    }

    @Test
    void testAuthenticateSuccess() {
        doctorDAO.register(sampleDoctor);
        DoctorDTO result = doctorDAO.authenticate("smith@example.com", "pass123");
        assertNotNull(result);
        assertEquals("D001", result.getDoctorId());
    }

    @Test
    void testAuthenticateFailure() {
        doctorDAO.register(sampleDoctor);
        DoctorDTO result = doctorDAO.authenticate("smith@example.com", "wrongpass");
        assertNull(result);
    }

    @Test
    void testUpdateProfile() throws UserException {
        doctorDAO.register(sampleDoctor);
        DoctorDTO updated = null;
        try {
            updated = new DoctorDTO(
                    "D001", "Dr. Updated", "smith@example.com", "newpass",
                    "9999999999", "46", "Neurology", 20
            );
        } catch (UserException e) {
            throw new RuntimeException("Failed to create updated DoctorDTO in testUpdateProfile", e);
        }
        assertTrue(doctorDAO.updateProfile(updated));
        assertEquals("Dr. Updated", CollectionUtil.doctorMap.get("D001").getName());
    }

    @Test
    void testUpdateProfileFailure() {
        DoctorDTO newDoc = null;
        try {
            newDoc = new DoctorDTO(
                    "D002", "Dr. New", "new@example.com", "pass", "0000000000", "30", "Ortho", 5
            );
        } catch (UserException e) {
            throw new RuntimeException("Failed to create newDoc in testUpdateProfileFailure", e);
        }
        assertFalse(doctorDAO.updateProfile(newDoc));
    }

    @Test
    void testDeleteSuccess() {
        doctorDAO.register(sampleDoctor);
        assertTrue(doctorDAO.delete("smith@example.com"));
        assertNull(CollectionUtil.doctorMap.get("D001"));
    }

    @Test
    void testDeleteFailure() {
        assertFalse(doctorDAO.delete("nonexistent@example.com"));
    }

    @Test
    void testGetDoctorByEmail() {
        doctorDAO.register(sampleDoctor);
        DoctorDTO result = doctorDAO.getDoctorByEmail("smith@example.com");
        assertNotNull(result);
        assertEquals("D001", result.getDoctorId());
    }

    @Test
    void testGetDoctorById() {
        doctorDAO.register(sampleDoctor);
        DoctorDTO result = doctorDAO.getDoctorById("D001");
        assertNotNull(result);
        assertEquals("Dr. Smith", result.getName());
    }

    @Test
    void testGetAllDoctors() throws UserException {
        doctorDAO.register(sampleDoctor);
        try {
            doctorDAO.register(new DoctorDTO("D002", "Dr. Jane", "jane@example.com", "1234", "1111111111", "40", "ENT", 10));
        } catch (UserException e) {
            throw new RuntimeException("Failed to create DoctorDTO in testGetAllDoctors", e);
        }
        List<DoctorDTO> list = doctorDAO.getAllDoctors();
        assertEquals(2, list.size());
    }
}
