package com.abes.medx.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abes.medx.dto.AdminDTO;
import com.abes.medx.exception.UserException;
import com.abes.medx.util.CollectionUtil;

class AdminDAOImplTest {

    private AdminDAO adminDAO;
    private AdminDTO sampleAdmin;

    /**
     * Sets up a fresh DAO instance and sample admin before each test.
     *
     * @throws UserException if initialization fails
     */
    @BeforeEach
    void setUp() throws UserException {
        adminDAO = new AdminDAOImpl();
        sampleAdmin = new AdminDTO(
                "A001",               // adminId
                "Alice Admin",        // name
                "alice@admin.com",    // email
                "admin123",           // password
                "9876543210",         // phoneNumber
                "35"                  // age
        );
        CollectionUtil.adminMap.clear();
    }

    /**
     * Should register a new admin successfully.
     */
    @Test
    void testRegisterSuccess() {
        assertTrue(adminDAO.register(sampleAdmin));
        assertEquals(1, CollectionUtil.adminMap.size());
    }

    /**
     * Should fail to register a duplicate admin.
     */
    @Test
    void testRegisterDuplicate() {
        adminDAO.register(sampleAdmin);
        assertFalse(adminDAO.register(sampleAdmin));
    }

    /**
     * Should authenticate with correct email and password.
     */
    @Test
    void testAuthenticateSuccess() {
        adminDAO.register(sampleAdmin);
        AdminDTO result = adminDAO.authenticate("alice@admin.com", "admin123");
        assertNotNull(result);
        assertEquals("A001", result.getAdminId());
    }

    /**
     * Should return null for incorrect password.
     */
    @Test
    void testAuthenticateFailureWrongPassword() {
        adminDAO.register(sampleAdmin);
        AdminDTO result = adminDAO.authenticate("alice@admin.com", "wrongpass");
        assertNull(result);
    }

    /**
     * Should successfully update an existing admin profile.
     *
     * @throws UserException if update fails
     */
    @Test
    void testUpdateProfileSuccess() throws UserException {
        adminDAO.register(sampleAdmin);
        AdminDTO updatedAdmin = new AdminDTO(
                "A001",               // adminId
                "Alice Updated",      // name
                "alice@admin.com",    // email
                "newpass",            // password
                "1234567890",         // phoneNumber
                "36"                  // age
        );
        assertTrue(adminDAO.updateProfile(updatedAdmin));
        assertEquals("Alice Updated", CollectionUtil.adminMap.get("A001").getName());
    }

    /*
    @Test
    void testUpdateProfileFailure() {
        AdminDTO unregisteredAdmin = new AdminDTO("A002", "Bob Admin", "bob@admin.com", "bob123", "9999999999", "40");
        assertFalse(adminDAO.updateProfile(unregisteredAdmin));
    }
    */

    /**
     * Should delete an existing admin by email.
     */
    @Test
    void testDeleteSuccess() {
        adminDAO.register(sampleAdmin);
        assertTrue(adminDAO.delete("alice@admin.com"));
        assertEquals(0, CollectionUtil.adminMap.size());
    }

    /**
     * Should return false when trying to delete a non-existent admin.
     */
    @Test
    void testDeleteFailure() {
        assertFalse(adminDAO.delete("nonexistent@admin.com"));
    }

    /**
     * Should fetch admin details by valid email.
     */
    @Test
    void testGetAdminByEmailSuccess() {
        adminDAO.register(sampleAdmin);
        AdminDTO result = adminDAO.getAdminByEmail("alice@admin.com");
        assertNotNull(result);
        assertEquals("A001", result.getAdminId());
    }

    /**
     * Should return null for invalid email lookup.
     */
    @Test
    void testGetAdminByEmailFailure() {
        AdminDTO result = adminDAO.getAdminByEmail("unknown@admin.com");
        assertNull(result);
    }
}
