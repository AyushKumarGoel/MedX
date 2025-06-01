package com.abes.medx.dao;

import java.util.ArrayList;
import java.util.List;

import com.abes.medx.dto.AdminDTO;
import com.abes.medx.util.CollectionUtil;

/**
 * Implementation of the AdminDAO interface.
 * Uses in-memory storage (CollectionUtil.adminMap) to manage AdminDTO objects.
 */
public class AdminDAOImpl implements AdminDAO {

    /**
     * Default constructor.
     */
    public AdminDAOImpl() {
    }

    /**
     * Registers a new admin if the adminId is not already present.
     *
     * @param admin AdminDTO object containing new admin details
     * @return true if registration is successful, false if admin already exists
     */
    @Override
    public boolean register(AdminDTO admin) {
        if (CollectionUtil.adminMap.containsKey(admin.getAdminId())) {
            return false; // admin already exists
        }
        CollectionUtil.adminMap.put(admin.getAdminId(), admin);
        return true;
    }

    /**
     * Authenticates an admin using email and password.
     * Performs case-insensitive email comparison.
     *
     * @param email    Admin's email
     * @param password Admin's password
     * @return AdminDTO if authentication succeeds, null otherwise
     */
    @Override
    public AdminDTO authenticate(String email, String password) {
        return CollectionUtil.adminMap.values().stream()
                .filter(admin -> admin.getEmail().equalsIgnoreCase(email)
                        && admin.getPassword().equals(password))
                .findFirst().orElse(null);
    }

    /**
     * Updates the profile information of an existing admin.
     * The admin must already exist (checked by adminId).
     *
     * @param updatedAdmin AdminDTO containing updated details
     * @return true if update is successful, false if admin does not exist
     */
    @Override
    public boolean updateProfile(AdminDTO updatedAdmin) {
        if (!CollectionUtil.adminMap.containsKey(updatedAdmin.getAdminId())) {
            return false; // admin does not exist
        }
        CollectionUtil.adminMap.put(updatedAdmin.getAdminId(), updatedAdmin);
        return true;
    }

    /**
     * Deletes an admin identified by the given email.
     * First retrieves admin by email, then removes from map by adminId.
     *
     * @param email Email of the admin to delete
     * @return true if deletion was successful, false if admin not found
     */
    @Override
    public boolean delete(String email) {
        AdminDTO adminToDelete = getAdminByEmail(email);
        if (adminToDelete != null) {
            CollectionUtil.adminMap.remove(adminToDelete.getAdminId());
            return true;
        }
        return false;
    }

    /**
     * Retrieves an AdminDTO by email with case-insensitive matching.
     *
     * @param email Email of the admin to find
     * @return AdminDTO if found, null otherwise
     */
    @Override
    public AdminDTO getAdminByEmail(String email) {
        return CollectionUtil.adminMap.values().stream()
                .filter(admin -> admin.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }


    /**
     * Generates the next unique admin ID.
     * Checks for IDs starting from "ADM1" upwards until an unused ID is found.
     *
     * @return next available admin ID as a String
     */
    @Override
    public String getNextAdminId() {
        int i = 1;
        // Fixed to check adminMap instead of appointmentMap for admin IDs
        while (CollectionUtil.adminMap.containsKey("ADM" + i)) {
            i++;
        }
        return "ADM" + i;
    }

    /**
     * Returns a list of all registered admins.
     *
     * @return List of AdminDTO objects
     */
    @Override
    public List<AdminDTO> getAllAdmins() {
        return new ArrayList<>(CollectionUtil.adminMap.values());
    }
}
// This class implements the AdminDAO interface, providing methods to manage admin data.
// It uses an in-memory map (CollectionUtil.adminMap) to store AdminDTO objects.