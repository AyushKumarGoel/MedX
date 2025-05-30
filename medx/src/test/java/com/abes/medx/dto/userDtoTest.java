package com.abes.medx.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.abes.medx.exception.UserException;

public class userDtoTest {

    @Test
    void testUserDTOValidConstruction() throws UserException {
        UserDTO user = new UserDTO("John Doe", "john@example.com", "pass123", "9876543210", "30");
        assertEquals("John Doe", user.getName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("pass123", user.getPassword());
        assertEquals("9876543210", user.getPhoneNumber());
        assertEquals("30", user.getAge());
    }

    @Test
    void testUserDTOInvalidEmail() {
        assertThrows(UserException.class, () -> {
            new UserDTO("John Doe", "invalid-email", "pass123", "9876543210", "30");
        }, "Should throw UserException for invalid email");
    }

    @Test
    void testUserDTOInvalidPhoneNumber() {
        assertThrows(UserException.class, () -> {
            new UserDTO("John Doe", "john@example.com", "pass123", "12345", "30");
        }, "Should throw UserException for invalid phone number");
    }

    @Test
    void testUserDTOInvalidAge() {
        assertThrows(UserException.class, () -> {
            new UserDTO("John Doe", "john@example.com", "pass123", "9876543210", "150");
        }, "Should throw UserException for invalid age");
    }

    @Test
    void testPatientDTOEquality() throws UserException {
        PatientDTO patient1 = new PatientDTO("P001", "John Doe", "john@example.com", "pass123", "9876543210", "30");
        PatientDTO patient2 = new PatientDTO("P001", "John Doe", "john@example.com", "pass123", "9876543210", "30");
        PatientDTO patient3 = new PatientDTO("P002", "Jane Doe", "jane@example.com", "pass456", "1111111111", "25");

        assertEquals(patient1, patient2, "Patients with same patientId should be equal");
        assertNotEquals(patient1, patient3, "Patients with different patientId should not be equal");
    }

    @Test
    void testDoctorDTOEquality() throws UserException {
        DoctorDTO doctor1 = new DoctorDTO("D001", "Dr. Smith", "smith@example.com", "pass123", "1234567890", "40", "Cardiology", 15);
        DoctorDTO doctor2 = new DoctorDTO("D001", "Dr. Smith", "smith@example.com", "pass123", "1234567890", "40", "Cardiology", 15);
        DoctorDTO doctor3 = new DoctorDTO("D002", "Dr. Jane", "jane@example.com", "pass456", "1111111111", "35", "Neurology", 10);

        assertEquals(doctor1, doctor2, "Doctors with same doctorId and fields should be equal");
        assertNotEquals(doctor1, doctor3, "Doctors with different doctorId should not be equal");
    }

    @Test
    void testAdminDTOShowProfile() throws UserException {
        AdminDTO admin = new AdminDTO("A001", "Alice Admin", "alice@admin.com", "admin123", "9876543210", "35");
        // Redirect System.out to capture output for testing
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));
        admin.showProfile();
        String output = outContent.toString();
        assertTrue(output.contains("Admin ID: A001"));
        assertTrue(output.contains("Name: Alice Admin"));
        assertTrue(output.contains("Email: alice@admin.com"));
        System.setOut(System.out); // Reset System.out
    }
}