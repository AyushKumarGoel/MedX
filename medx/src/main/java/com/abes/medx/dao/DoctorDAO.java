package com.abes.medx.dao;

import java.util.List;

import com.abes.medx.dto.DoctorDTO;

/**
 * DAO interface for Doctor-related database operations.
 * Defines the contract for managing doctor data.
 */
public interface DoctorDAO {
    
    /**
     * Registers a new doctor.
     * 
     * @param doctor DoctorDTO object containing doctor details
     * @return true if registration is successful, false if doctor already exists
     */
    boolean register(DoctorDTO doctor);

    /**
     * Authenticates a doctor using email and password.
     * 
     * @param email Doctor's email
     * @param password Doctor's password
     * @return DoctorDTO if authentication is successful, null otherwise
     */
    DoctorDTO authenticate(String email, String password);

    /**
     * Updates profile details of an existing doctor.
     * 
     * @param updatedDoctor DoctorDTO with updated information
     * @return true if update is successful, false if doctor does not exist
     */
    boolean updateProfile(DoctorDTO updatedDoctor);

    /**
     * Deletes a doctor record identified by email.
     * 
     * @param email Doctor's email to identify the record to delete
     * @return true if deletion is successful, false if doctor not found
     */
    boolean delete(String email);

    /**
     * Retrieves a doctor by their email.
     * 
     * @param email Doctor's email
     * @return DoctorDTO if found, null otherwise
     */
    DoctorDTO getDoctorByEmail(String email);

    /**
     * Retrieves a doctor by their unique ID.
     * 
     * @param doctorId Unique doctor identifier
     * @return DoctorDTO if found, null otherwise
     */
    DoctorDTO getDoctorById(String doctorId);

    /**
     * Retrieves a list of all doctors.
     * 
     * @return List of DoctorDTO objects representing all doctors
     */
    List<DoctorDTO> getAllDoctors();

    /**
     * Generates the next unique doctor ID.
     * 
     * @return Next available doctor ID as a String
     */
    String getNextDoctorId();
}
