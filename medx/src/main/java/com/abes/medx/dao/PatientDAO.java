package com.abes.medx.dao;

import java.util.List;

import com.abes.medx.dto.PatientDTO;

/**
 * DAO interface for Patient operations.
 * Defines methods for patient registration, authentication,
 * profile management, deletion, and retrieval.
 */
public interface PatientDAO {
    
    /**
     * Registers a new patient.
     * 
     * @param patient PatientDTO containing patient details
     * @return true if registration is successful, false if patient already exists
     */
    boolean register(PatientDTO patient);

    /**
     * Authenticates a patient using email and password.
     * 
     * @param email Patient's email
     * @param password Patient's password
     * @return PatientDTO if authentication succeeds, null otherwise
     */
    PatientDTO authenticate(String email, String password);

    /**
     * Updates the profile information of an existing patient.
     * 
     * @param updatedPatient PatientDTO containing updated patient details
     * @return true if update is successful, false if patient does not exist
     */
    boolean updateProfile(PatientDTO updatedPatient);

    /**
     * Deletes a patient identified by email.
     * 
     * @param email Email of the patient to delete
     * @return true if deletion is successful, false if patient not found
     */
    boolean delete(String email);

    /**
     * Retrieves a patient by their email.
     * 
     * @param email Email of the patient
     * @return PatientDTO if found, null otherwise
     */
    PatientDTO getPatientByEmail(String email);

    /**
     * Retrieves all registered patients.
     * 
     * @return List of PatientDTO objects
     */
    List<PatientDTO> getAllPatients();

    /**
     * Generates the next unique patient ID.
     * 
     * @return Next patient ID as a String
     */
    String getNextPatientId();
}
