package com.abes.medx.dao;

import java.util.List;

import com.abes.medx.dto.AdminDTO;

/**
 * Data Access Object (DAO) interface for Admin-related operations.
 * Defines the contract for CRUD and authentication methods on Admin entities.
 */
public interface AdminDAO {

    /**
     * Registers a new admin in the system.
     * 
     * @param admin AdminDTO object containing the details of the admin to register
     * @return true if registration is successful, false otherwise
     */
    boolean register(AdminDTO admin);

    /**
     * Authenticates an admin using email and password.
     * 
     * @param email    Admin's email
     * @param password Admin's password
     * @return AdminDTO object if authentication is successful; null otherwise
     */
    AdminDTO authenticate(String email, String password);

    /**
     * Updates the profile details of an existing admin.
     * 
     * @param updatedAdmin AdminDTO object containing updated information
     * @return true if update is successful, false otherwise
     */
    boolean updateProfile(AdminDTO updatedAdmin);

    /**
     * Deletes an admin record identified by the email.
     * 
     * @param email Email of the admin to delete
     * @return true if deletion is successful, false otherwise
     */
    boolean delete(String email);

    /**
     * Retrieves an AdminDTO object by email.
     * 
     * @param email Email of the admin to retrieve
     * @return AdminDTO object if found; null otherwise
     */
    AdminDTO getAdminByEmail(String email);

    /**
     * Generates or retrieves the next unique admin ID for new admin registration.
     * 
     * @return Next admin ID as a String
     */
    String getNextAdminId();

    /**
     * Retrieves a list of all admins in the system.
     * 
     * @return List of AdminDTO objects
     */
    List<AdminDTO> getAllAdmins();
    
}
// Note: This interface is designed to be implemented by a class that interacts with the database or any data source.
// It provides methods for managing admin entities, including registration, authentication, profile updates, deletion, and retrieval of admin details.