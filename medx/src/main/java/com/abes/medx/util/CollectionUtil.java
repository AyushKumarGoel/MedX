package com.abes.medx.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.abes.medx.dto.AdminDTO;
import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.AppointmentException;
import com.abes.medx.exception.UserException;

public class CollectionUtil {

    public static final Map<String, AdminDTO> adminMap = new HashMap<>();
    public static final Map<String, PatientDTO> patientMap = new HashMap<>();
    public static final Map<String, DoctorDTO> doctorMap = new HashMap<>();
    public static final Map<String, AppointmentDTO> appointmentMap = new HashMap<>();
    public static final Map<String, List<String>> patientAppointments = new HashMap<>();
    public static final Map<String, List<String>> doctorAppointments = new HashMap<>();

    static {
        try {
            // Static Admin Data
            validateAndPutAdmin("A001", new AdminDTO("A001", "Ujjwal Kumar", "ujj@gmail.com", "ujj", "1234567890", "30"));
            validateAndPutAdmin("A002", new AdminDTO("A002", "Admin User", "admin@mail.com", "admin123", "1234567890", "35"));

            // Static Patient Data
            try {
                PatientDTO patient1 = createPatient("P001", "RajVerma", "raj@gmail.com", "raj", "1234567890", "20");
                validateAndPutPatient("P001", patient1);
            } catch (UserException e) {
                System.err.println("Failed to initialize patient with ID P001: " + e.getMessage());
            }

            try {
                PatientDTO patient2 = createPatient("P002", "Yatharth", "yath@gmail.com", "yatharth", "1234599990", "21");
                validateAndPutPatient("P002", patient2);
            } catch (UserException e) {
                System.err.println("Failed to initialize patient with ID P002: " + e.getMessage());
            }

            // Static Doctor Data
            try {
                validateAndPutDoctor("D001", new DoctorDTO("D001", "Anjali Singh", "anjali@gmail.com", "anjali", "1234567890", "29", "Cardiologist", 10));
            } catch (UserException e) {
                System.err.println("Failed to initialize doctor with ID D001: " + e.getMessage());
            }

            try {
                validateAndPutDoctor("D002", new DoctorDTO("D002", "Rahul Sharma", "rahul@gmail.com", "rahul", "1234567890", "35", "Neurologist", 15));
            } catch (UserException e) {
                System.err.println("Failed to initialize doctor with ID D002: " + e.getMessage());
            }

            // Static Appointment Data
            if (patientMap.containsKey("P001") && doctorMap.containsKey("D001")) {
                String appointmentId1 = "AP001";
                validateAndPutAppointment(appointmentId1, new AppointmentDTO(appointmentId1, "2023-10-01", "10:00", patientMap.get("P001"), doctorMap.get("D001"), 500, "Scheduled"));
            } else {
                System.err.println("Skipping appointment AP001: Required patient or doctor not initialized.");
            }

            if (patientMap.containsKey("P002") && doctorMap.containsKey("D002")) {
                String appointmentId2 = "AP002";
                validateAndPutAppointment(appointmentId2, new AppointmentDTO(appointmentId2, "2023-10-02", "11:00", patientMap.get("P002"), doctorMap.get("D002"), 700, "Scheduled"));
            } else {
                System.err.println("Skipping appointment AP002: Required patient or doctor not initialized.");
            }
        } catch (Exception e) {
            System.err.println("Unexpected error during static initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static PatientDTO createPatient(String patientId, String name, String email, String password, String phoneNumber, String age) throws UserException {
        try {
            if (patientId == null || patientId.trim().isEmpty()) {
                throw new UserException("Patient ID cannot be null or empty.");
            }
            if (email == null || email.trim().isEmpty()) {
                throw new UserException("Email cannot be null or empty for patient ID: " + patientId);
            }
            PatientDTO patient = new PatientDTO(patientId, name, email, password, phoneNumber, age);
            System.out.println("Created PatientDTO for ID: " + patientId + ", Email: " + patient.getEmail() + ", PatientID: " + patient.getPatientId());
            return patient;
        } catch (UserException e) {
            throw new UserException("Failed to create PatientDTO for ID: " + patientId + ". Error: " + e.getMessage());
        }
    }

    private static void validateAndPutPatient(String id, PatientDTO patient) throws UserException {
        if (patient == null) {
            throw new UserException("Patient object is null for ID: " + id);
        }
        if (id == null) {
            throw new UserException("ID is null for patient.");
        }
        if (patient.getEmail() == null) {
            throw new UserException("Email is null for patient ID: " + id);
        }
        if (patient.getPatientId() == null) {
            throw new UserException("PatientID is null for patient ID: " + id);
        }
        patientMap.put(id, patient);
        System.out.println("Successfully added patient with ID: " + id);
    }

    private static void validateAndPutDoctor(String id, DoctorDTO doctor) throws UserException {
        if (doctor == null || id == null || doctor.getEmail() == null || doctor.getDoctorId() == null) {
            throw new UserException("Invalid doctor data for ID: " + id);
        }
        doctorMap.put(id, doctor);
    }

    private static void validateAndPutAdmin(String id, AdminDTO admin) throws UserException {
        if (admin == null || id == null || admin.getEmail() == null || admin.getAdminId() == null) {
            throw new UserException("Invalid admin data for ID: " + id);
        }
        adminMap.put(id, admin);
    }

    private static void validateAndPutAppointment(String id, AppointmentDTO appointment) throws AppointmentException {
        if (appointment == null || id == null || appointment.getPatient() == null || appointment.getDoctor() == null) {
            throw new AppointmentException("Invalid appointment data for ID: " + id);
        }
        appointmentMap.put(id, appointment);
    }
}