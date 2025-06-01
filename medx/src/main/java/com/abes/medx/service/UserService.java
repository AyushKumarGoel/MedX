package com.abes.medx.service;

import java.util.List;

import com.abes.medx.dto.AdminDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.UserException;

public interface UserService {

    // ---------------- Admin operations ----------------

    /**
     * Authenticates an admin using email and password.
     *
     * @param email    Admin's email
     * @param password Admin's password
     * @return AdminDTO object if credentials are valid
     * @throws UserException if authentication fails
     */
    AdminDTO adminLogin(String email, String password) throws UserException;

    /**
     * Registers a new admin.
     *
     * @param admin AdminDTO object containing admin details
     * @return true if registration is successful
     * @throws UserException if registration fails
     */
    boolean registerAdmin(AdminDTO admin) throws UserException;

    /**
     * Updates an existing admin profile.
     *
     * @param admin AdminDTO with updated information
     * @return true if update is successful
     * @throws UserException if update fails
     */
    boolean updateAdminProfile(AdminDTO admin) throws UserException;

    /**
     * Deletes an admin by email.
     *
     * @param email Admin's email
     * @return true if deletion is successful
     * @throws UserException if deletion fails
     */
    boolean deleteAdmin(String email) throws UserException;

    /**
     * Retrieves an admin by email.
     *
     * @param email Admin's email
     * @return AdminDTO object
     * @throws UserException if admin is not found
     */
    AdminDTO getAdminByEmail(String email) throws UserException;

    /**
     * Retrieves a list of all admins.
     *
     * @return List of AdminDTO
     * @throws UserException if retrieval fails
     */
    List<AdminDTO> getAllAdmins() throws UserException;

    /**
     * Generates the next available admin ID.
     *
     * @return Next admin ID as a String
     * @throws UserException if generation fails
     */
    String getNextAdminId() throws UserException;

    // ---------------- Doctor operations ----------------

    /**
     * Authenticates a doctor using email and password.
     *
     * @param email    Doctor's email
     * @param password Doctor's password
     * @return DoctorDTO object if credentials are valid
     * @throws UserException if authentication fails
     */
    DoctorDTO doctorLogin(String email, String password) throws UserException;

    /**
     * Registers a new doctor.
     *
     * @param doctor DoctorDTO object containing doctor details
     * @return true if registration is successful
     * @throws UserException if registration fails
     */
    boolean registerDoctor(DoctorDTO doctor) throws UserException;

    /**
     * Updates an existing doctor profile.
     *
     * @param doctor DoctorDTO with updated information
     * @return true if update is successful
     * @throws UserException if update fails
     */
    boolean updateDoctorProfile(DoctorDTO doctor) throws UserException;

    /**
     * Deletes a doctor by email.
     *
     * @param email Doctor's email
     * @return true if deletion is successful
     * @throws UserException if deletion fails
     */
    boolean deleteDoctor(String email) throws UserException;

    /**
     * Retrieves all registered doctors.
     *
     * @return List of DoctorDTO
     * @throws UserException if retrieval fails
     */
    List<DoctorDTO> getAllDoctors() throws UserException;

    /**
     * Retrieves a doctor by ID.
     *
     * @param id Doctor's unique ID
     * @return DoctorDTO object
     * @throws UserException if doctor is not found
     */
    DoctorDTO getDoctorById(String id) throws UserException;

    /**
     * Retrieves a doctor by email.
     *
     * @param email Doctor's email
     * @return DoctorDTO object
     * @throws UserException if doctor is not found
     */
    DoctorDTO getDoctorByEmail(String email) throws UserException;

    /**
     * Generates the next available doctor ID.
     *
     * @return Next doctor ID as a String
     * @throws UserException if generation fails
     */
    String getNextDoctorId() throws UserException;

    // ---------------- Patient operations ----------------

    /**
     * Authenticates a patient using email and password.
     *
     * @param email    Patient's email
     * @param password Patient's password
     * @return PatientDTO object if credentials are valid
     * @throws UserException if authentication fails
     */
    PatientDTO patientLogin(String email, String password) throws UserException;

    /**
     * Registers a new patient.
     *
     * @param patient PatientDTO object containing patient details
     * @return true if registration is successful
     * @throws UserException if registration fails
     */
    boolean registerPatient(PatientDTO patient) throws UserException;

    /**
     * Updates an existing patient profile.
     *
     * @param patient PatientDTO with updated information
     * @return true if update is successful
     * @throws UserException if update fails
     */
    boolean updatePatientProfile(PatientDTO patient) throws UserException;

    /**
     * Deletes a patient by email.
     *
     * @param email Patient's email
     * @return true if deletion is successful
     * @throws UserException if deletion fails
     */
    boolean deletePatient(String email) throws UserException;

    /**
     * Retrieves all registered patients.
     *
     * @return List of PatientDTO
     * @throws UserException if retrieval fails
     */
    List<PatientDTO> getAllPatients() throws UserException;

    /**
     * Retrieves a patient by email.
     *
     * @param email Patient's email
     * @return PatientDTO object
     * @throws UserException if patient is not found
     */
    PatientDTO getPatientByEmail(String email) throws UserException;

    /**
     * Generates the next available patient ID.
     *
     * @return Next patient ID as a String
     * @throws UserException if generation fails
     */
    String getNextPatientId() throws UserException;
}
