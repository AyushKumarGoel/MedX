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
        CollectionUtil.doctorMap.clear(); // Reset storage before each test

        sampleDoctor = new DoctorDTO(
            "D10", "Raj", "raj@example.com", "pass123",
            "1234567890", "45", "Cardiology", 15
        );
    }

    @Test
    void testRegisterSuccess() {
        assertTrue(doctorDAO.register(sampleDoctor));
        assertEquals(1, CollectionUtil.doctorMap.size());
    }

    @Test
    void testRegisterDuplicate() {
        doctorDAO.register(sampleDoctor);
        assertFalse(doctorDAO.register(sampleDoctor)); // Duplicate ID
    }

    @Test
    void testAuthenticateSuccess() {
        doctorDAO.register(sampleDoctor);
        DoctorDTO result = doctorDAO.authenticate("raj@example.com", "pass123");
        assertNotNull(result);
        assertEquals("D10", result.getDoctorId());
    }

    @Test
    void testAuthenticateFailure() {
        doctorDAO.register(sampleDoctor);
        DoctorDTO result = doctorDAO.authenticate("raj@example.com", "wrongpass");
        assertNull(result);
    }

    @Test
    void testUpdateProfile() throws UserException {
        doctorDAO.register(sampleDoctor);
        DoctorDTO updated = new DoctorDTO(
            "D10", "Raja", "raj@example.com", "newpass",
            "9999999999", "46", "Neurology", 20
        );
        assertTrue(doctorDAO.updateProfile(updated));
        assertEquals("Raja", CollectionUtil.doctorMap.get("D10").getName());
    }

    @Test
    void testUpdateProfileFailure() throws UserException {
        DoctorDTO newDoc = new DoctorDTO(
            "D11", "Rajan", "new@example.com", "pass", "0000000000", "30", "Ortho", 5
        );
        assertFalse(doctorDAO.updateProfile(newDoc)); // Not registered
    }

    @Test
    void testDeleteSuccess() {
        doctorDAO.register(sampleDoctor);
        assertTrue(doctorDAO.delete("raj@example.com"));
        assertNull(CollectionUtil.doctorMap.get("D10"));
    }

    @Test
    void testDeleteFailure() {
        assertFalse(doctorDAO.delete("nonexistent@example.com"));
    }

    @Test
    void testGetDoctorByEmail() {
        doctorDAO.register(sampleDoctor);
        DoctorDTO result = doctorDAO.getDoctorByEmail("raj@example.com");
        assertNotNull(result);
        assertEquals("D10", result.getDoctorId());
    }

    @Test
    void testGetDoctorById() {
        doctorDAO.register(sampleDoctor);
        DoctorDTO result = doctorDAO.getDoctorById("D10");
        assertNotNull(result);
        assertEquals("Raj", result.getName());
    }

    @Test
    void testGetAllDoctors() throws UserException {
        doctorDAO.register(sampleDoctor);
        doctorDAO.register(new DoctorDTO(
            "D11", "Yash", "yash@example.com", "1234",
            "1111111111", "40", "ENT", 10
        ));
        List<DoctorDTO> list = doctorDAO.getAllDoctors();
        assertEquals(2, list.size());
    }
}
