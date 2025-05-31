package com.abes.medx.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abes.medx.dao.AdminDAOImpl;
import com.abes.medx.dao.DoctorDAOImpl;
import com.abes.medx.dao.PatientDAOImpl;
import com.abes.medx.dto.AdminDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.UserException;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(new AdminDAOImpl(), new DoctorDAOImpl(), new PatientDAOImpl());
    }

    // ---------------------- Admin Tests ----------------------

    @Test
    void testAdminLoginSuccess() throws UserException {
        AdminDTO admin = userService.adminLogin("admin@mail.com", "admin123");
        assertNotNull(admin);
        assertEquals("ADM2", admin.getAdminId());
    }

    @Test
    void testAdminLoginFailure() {
        assertThrows(UserException.class, () -> userService.adminLogin("invalid@abes.com", "wrong"));
    }

    @Test
    void testGetAllAdmins() throws UserException {
        List<AdminDTO> admins = userService.getAllAdmins();
        assertNotNull(admins);
        assertTrue(admins.size() >= 2);
    }

    @Test
    void testGetAdminByEmail() throws UserException {
        AdminDTO admin = userService.getAdminByEmail("admin@mail.com");
        assertNotNull(admin);
        assertEquals("ADM2", admin.getAdminId());
    }

    // ---------------------- Doctor Tests ----------------------

    @Test
    void testDoctorLoginSuccess() throws UserException {
        DoctorDTO doctor = userService.doctorLogin("rahul@gmail.com", "rahul");
        assertNotNull(doctor);
        assertEquals("D2", doctor.getDoctorId());
    }

    @Test
    void testDoctorLoginFailure() {
        assertThrows(UserException.class, () -> userService.doctorLogin("invalid@abes.com", "wrong"));
    }

    @Test
    void testGetAllDoctors() throws UserException {
        List<DoctorDTO> doctors = userService.getAllDoctors();
        assertNotNull(doctors);
        assertTrue(doctors.size() >= 2);
    }

    @Test
    void testGetDoctorById() throws UserException {
        DoctorDTO doctor = userService.getDoctorById("D1");
        assertNotNull(doctor);
        assertEquals("doctor@mail.com", doctor.getEmail());
    }

    @Test
    void testGetDoctorByEmail() throws UserException {
        DoctorDTO doctor = userService.getDoctorByEmail("doctor@mail.com");
        assertNotNull(doctor);
        assertEquals("D1", doctor.getDoctorId());
    }

    // ---------------------- Patient Tests ----------------------

    @Test
    void testPatientLoginSuccess() throws UserException {
        PatientDTO patient = userService.patientLogin("raj@gmail.com", "raj");
        assertNotNull(patient);
        assertEquals("P1", patient.getPatientId());
    }

    @Test
    void testPatientLoginFailure() {
        assertThrows(UserException.class, () -> userService.patientLogin("invalid@abes.com", "wrong"));
    }

    @Test
    void testGetAllPatients() throws UserException {
        List<PatientDTO> patients = userService.getAllPatients();
        assertNotNull(patients);
        assertTrue(patients.size() >= 2);
    }

    @Test
    void testGetPatientByEmail() throws UserException {
        PatientDTO patient = userService.getPatientByEmail("raj@gmail.com");
        assertNotNull(patient);
        assertEquals("P1", patient.getPatientId());
    }

    // ---------------------- ID Generators ----------------------

    @Test
    void testGetNextAdminId() throws UserException {
        String nextId = userService.getNextAdminId();
        assertTrue(nextId.startsWith("A"));
    }

    @Test
    void testGetNextDoctorId() throws UserException {
        String nextId = userService.getNextDoctorId();
        assertTrue(nextId.startsWith("D"));
    }

    @Test
    void testGetNextPatientId() throws UserException {
        String nextId = userService.getNextPatientId();
        assertTrue(nextId.startsWith("P"));
    }
}
