package com.abes.medx.dao;

import java.util.ArrayList;
import java.util.List;

import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.util.CollectionUtil;

/**
 * Implementation of the DoctorDAO interface.
 * Uses an in-memory map (CollectionUtil.doctorMap) to store doctor records.
 */
public class DoctorDAOImpl implements DoctorDAO {

    /**
     * Default constructor.
     */
    public DoctorDAOImpl() {
    }

    /**
     * Registers a new doctor if the doctor ID does not already exist.
     * 
     * @param doctor DoctorDTO containing doctor details
     * @return true if registration succeeds, false if doctor ID already exists
     */
    @Override
    public boolean register(DoctorDTO doctor) {
        if (CollectionUtil.doctorMap.containsKey(doctor.getDoctorId())) 
            return false;  // Doctor already exists
        CollectionUtil.doctorMap.put(doctor.getDoctorId(), doctor);
        return true;
    }

    /**
     * Authenticates a doctor by matching email and password.
     * 
     * @param email Doctor's email
     * @param password Doctor's password
     * @return DoctorDTO if credentials match, null otherwise
     */
    @Override
    public DoctorDTO authenticate(String email, String password) {
        for (DoctorDTO doctor : CollectionUtil.doctorMap.values()) {
            if (doctor.getEmail().equalsIgnoreCase(email) && doctor.getPassword().equals(password)) {
                return doctor;
            }
        }
        return null; // No matching credentials found
    }

    /**
     * Updates the profile information of an existing doctor.
     * 
     * @param updatedDoctor DoctorDTO with updated details
     * @return true if update is successful, false if doctor not found
     */
    @Override
    public boolean updateProfile(DoctorDTO updatedDoctor) {
        if (!CollectionUtil.doctorMap.containsKey(updatedDoctor.getDoctorId())) 
            return false;  // Doctor does not exist
        CollectionUtil.doctorMap.put(updatedDoctor.getDoctorId(), updatedDoctor);
        return true;
    }

    /**
     * Deletes a doctor identified by email.
     * 
     * @param email Doctor's email to identify the doctor record
     * @return true if deletion is successful, false if doctor not found
     */
    @Override
    public boolean delete(String email) {
        DoctorDTO toDelete = getDoctorByEmail(email);
        if (toDelete != null) {
            CollectionUtil.doctorMap.remove(toDelete.getDoctorId());
            return true;
        }
        return false;
    }

    /**
     * Retrieves a doctor by their email.
     * 
     * @param email Email of the doctor
     * @return DoctorDTO if found, null otherwise
     */
    @Override
    public DoctorDTO getDoctorByEmail(String email) {
        return CollectionUtil.doctorMap.values().stream()
                .filter(d -> d.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves a doctor by their unique doctor ID.
     * 
     * @param doctorId Unique ID of the doctor
     * @return DoctorDTO if found, null otherwise
     */
    @Override
    public DoctorDTO getDoctorById(String doctorId) {
        return CollectionUtil.doctorMap.get(doctorId);
    }

    /**
     * Retrieves a list of all registered doctors.
     * 
     * @return List of DoctorDTO objects
     */
    @Override
    public List<DoctorDTO> getAllDoctors() {
        return new ArrayList<>(CollectionUtil.doctorMap.values());
    }

    /**
     * Generates the next available unique doctor ID.
     * 
     * @return Next doctor ID in the format "D" + number (e.g., D1, D2, ...)
     */
    @Override
    public String getNextDoctorId() {
        int i = 1;
        while (CollectionUtil.doctorMap.containsKey("D" + i)) {
            i++;
        }
        return "D" + i;      
    }
}
// End of DoctorDAOImpl.java
// This class implements the DoctorDAO interface, providing methods to manage doctor records in an in-memory map.