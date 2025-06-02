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

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(new AdminDAOImpl(), new DoctorDAOImpl(), new PatientDAOImpl());
    }

    // ---------------------- Admin Tests ----------------------

    @Test
    void testAdminLoginSuccess() throws UserException {
        AdminDTO admin = userService.adminLogin("ujj@gmail.com", "ujj");
        assertNotNull(admin);
        assertEquals("ADM1", admin.getAdminId());
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
        AdminDTO admin = userService.getAdminByEmail("ujj@gmail.com");
        assertNotNull(admin);
        assertEquals("ADM1", admin.getAdminId());
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
        assertTrue(doctors.size() >= 1);
    }

    @Test
    void testGetDoctorById() throws UserException {
        DoctorDTO doctor = userService.getDoctorById("D1");
        System.out.println(doctor);
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

    // ---------------------- Admin Registration/Update/Delete
    // ----------------------

    @Test
    void testRegisterAdminSuccess() throws UserException {
        AdminDTO admin = new AdminDTO("ADM100", "Alice", "alice@mail.com", "alice123", "9999999999", "35");
        assertTrue(userService.registerAdmin(admin));
    }

    @Test
    void testRegisterAdminFailure() {
        assertThrows(UserException.class, () -> userService.registerAdmin(null));
    }

    @Test
    void testUpdateAdminProfileSuccess() throws UserException {
        AdminDTO admin = userService.getAdminByEmail("admin@mail.com");
        admin.setName("Updated Name");
        assertTrue(userService.updateAdminProfile(admin));
    }

    @Test
    void testUpdateAdminProfileFailure() {
        assertThrows(UserException.class, () -> userService.updateAdminProfile(null));
    }

    @Test
    void testDeleteAdminSuccess() throws UserException {
        assertTrue(userService.deleteAdmin("admin@mail.com"));
    }

    @Test
    void testDeleteAdminFailure() {
        assertThrows(UserException.class, () -> userService.deleteAdmin(""));
    }

    // ---------------------- Doctor Registration/Update/Delete
    // ----------------------

    @Test
    void testRegisterDoctorSuccess() throws UserException {
        DoctorDTO doctor = new DoctorDTO("D100", "Alice", "alice@doc.com", "alice123", "9999999999", "40", "Cardiology",
                10);
        assertTrue(userService.registerDoctor(doctor));
    }

    @Test
    void testRegisterDoctorFailure() {
        assertThrows(UserException.class, () -> userService.registerDoctor(null));
    }

    @Test
    void testUpdateDoctorProfileSuccess() throws UserException {
        DoctorDTO doctor = userService.getDoctorById("D1");
        doctor.setName("Updated Name");
        assertTrue(userService.updateDoctorProfile(doctor));
    }

    @Test
    void testUpdateDoctorProfileFailure() {
        assertThrows(UserException.class, () -> userService.updateDoctorProfile(null));
    }

    @Test
    void testDeleteDoctorSuccess() throws UserException {
        assertTrue(userService.deleteDoctor("doctor@mail.com"));
    }

    @Test
    void testDeleteDoctorFailure() {
        assertThrows(UserException.class, () -> userService.deleteDoctor(""));
    }

    // ---------------------- Patient Registration/Update/Delete
    // ----------------------

    @Test
    void testRegisterPatientSuccess() throws UserException {
        PatientDTO patient = new PatientDTO("P100", "Alice", "alice@pat.com", "alice123", "8888888888", "28");
        assertTrue(userService.registerPatient(patient));
    }

    @Test
    void testRegisterPatientFailure() {
        assertThrows(UserException.class, () -> userService.registerPatient(null));
    }

    @Test
    void testUpdatePatientProfileSuccess() throws UserException {
        PatientDTO patient = userService.getPatientByEmail("raj@gmail.com");
        patient.setName("Updated Name");
        assertTrue(userService.updatePatientProfile(patient));
    }

    @Test
    void testUpdatePatientProfileFailure() {
        assertThrows(UserException.class, () -> userService.updatePatientProfile(null));
    }

    @Test
    void testDeletePatientSuccess() throws UserException {
        assertTrue(userService.deletePatient("raj@gmail.com"));
    }

    @Test
    void testDeletePatientFailure() {
        assertThrows(UserException.class, () -> userService.deletePatient(""));
    }
}
