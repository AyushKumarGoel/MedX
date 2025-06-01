package com.abes.medx.dao;

import java.util.ArrayList;
import java.util.List;

import com.abes.medx.dto.PatientDTO;
import com.abes.medx.util.CollectionUtil;

/**
 * Implementation of PatientDAO interface using in-memory data structure.
 * Performs CRUD operations on PatientDTO objects stored in CollectionUtil.patientMap.
 */
public class PatientDAOImpl implements PatientDAO {

    /**
     * Default constructor.
     */
    public PatientDAOImpl() {
    }

    /**
     * Registers a new patient if the patient ID does not already exist.
     * 
     * @param patient PatientDTO containing patient details
     * @return true if registration is successful, false if patient ID already exists
     */
    @Override
    public boolean register(PatientDTO patient) {
        if (CollectionUtil.patientMap.containsKey(patient.getPatientId())) return false;
        CollectionUtil.patientMap.put(patient.getPatientId(), patient);
        return true;
    }

    /**
     * Authenticates a patient using email and password by searching through patientMap.
     * 
     * @param email Patient's email
     * @param password Patient's password
     * @return PatientDTO if authentication is successful, null otherwise
     */
    @Override
    public PatientDTO authenticate(String email, String password) {
        for (PatientDTO patient : CollectionUtil.patientMap.values()) {
            if (patient.getEmail().equalsIgnoreCase(email) && patient.getPassword().equals(password)) {
                return patient;
            }
        }
        return null;
    }

    /**
     * Updates the profile of an existing patient identified by patient ID.
     * 
     * @param updatedPatient PatientDTO containing updated patient details
     * @return true if update succeeds, false if patient does not exist
     */
    @Override
    public boolean updateProfile(PatientDTO updatedPatient) {
        if (!CollectionUtil.patientMap.containsKey(updatedPatient.getPatientId())) return false;
        CollectionUtil.patientMap.put(updatedPatient.getPatientId(), updatedPatient);
        return true;
    }

    /**
     * Deletes a patient from the map based on email.
     * 
     * @param email Email of the patient to delete
     * @return true if deletion is successful, false if patient not found
     */
    @Override
    public boolean delete(String email) {
        PatientDTO toDelete = getPatientByEmail(email);
        if (toDelete != null) {
            CollectionUtil.patientMap.remove(toDelete.getPatientId());
            return true;
        }
        return false;
    }

    /**
     * Retrieves a patient by email by searching through the patientMap values.
     * 
     * @param email Email of the patient to find
     * @return PatientDTO if found, null otherwise
     */
    @Override
    public PatientDTO getPatientByEmail(String email) {
        return CollectionUtil.patientMap.values().stream()
                .filter(p -> p.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns a list of all patients stored in the patientMap.
     * 
     * @return List of PatientDTO objects
     */
    @Override
    public List<PatientDTO> getAllPatients() {
        return new ArrayList<>(CollectionUtil.patientMap.values());
    }

    /**
     * Generates the next unique patient ID in the format "P" + number.
     * It increments the number until an unused ID is found.
     * 
     * @return The next available patient ID as a String
     */
    @Override
    public String getNextPatientId() {
        int i = 1;
        while (CollectionUtil.patientMap.containsKey("P" + i)) {
            i++;
        }
        return "P" + i;
    }

}
