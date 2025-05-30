package com.abes.medx.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abes.medx.dto.AdminDTO;
import com.abes.medx.exception.UserException;
import com.abes.medx.util.CollectionUtil;

class AdminDAOImplTest {

    private AdminDAO adminDAO;
    private AdminDTO sampleAdmin;

    @BeforeEach
    void setUp() throws UserException {
        adminDAO = new AdminDAOImpl();
        sampleAdmin = new AdminDTO("A001", "Alice Admin", "alice@admin.com", "admin123", "9876543210", "35");
        CollectionUtil.adminMap.clear(); // Reset state before each test
    }

    @Test
    void testRegisterSuccess() {
        assertTrue(adminDAO.register(sampleAdmin));
        assertEquals(1, CollectionUtil.adminMap.size());
    }

    @Test
    void testRegisterDuplicate() {
        adminDAO.register(sampleAdmin);
        assertFalse(adminDAO.register(sampleAdmin)); // Should fail for duplicate adminId
    }

    @Test
    void testAuthenticateSuccess() {
        adminDAO.register(sampleAdmin);
        AdminDTO result = adminDAO.authenticate("alice@admin.com", "admin123");
        assertNotNull(result);
        assertEquals("A001", result.getAdminId());
    }

    @Test
    void testAuthenticateFailureWrongPassword() {
        adminDAO.register(sampleAdmin);
        AdminDTO result = adminDAO.authenticate("alice@admin.com", "wrongpass");
        assertNull(result);
    }

    @Test
    void testUpdateProfileSuccess() {
        adminDAO.register(sampleAdmin);
        try {
            AdminDTO updatedAdmin = new AdminDTO("A001", "Alice Updated", "alice@admin.com", "newpass", "1234567890", "36");
            assertTrue(adminDAO.updateProfile(updatedAdmin));
            assertEquals("Alice Updated", CollectionUtil.adminMap.get("A001").getName());
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testUpdateProfileFailure() {
        try {
            AdminDTO unregisteredAdmin = new AdminDTO("A002", "Bob Admin", "bob@admin.com", "bob123", "9999999999", "40");
            assertFalse(adminDAO.updateProfile(unregisteredAdmin));
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testDeleteSuccess() {
        adminDAO.register(sampleAdmin);
        assertTrue(adminDAO.delete("alice@admin.com"));
        assertEquals(0, CollectionUtil.adminMap.size());
    }

    @Test
    void testDeleteFailure() {
        assertFalse(adminDAO.delete("nonexistent@admin.com"));
    }

    @Test
    void testGetAdminByEmailSuccess() {
        adminDAO.register(sampleAdmin);
        AdminDTO result = adminDAO.getAdminByEmail("alice@admin.com");
        assertNotNull(result);
        assertEquals("A001", result.getAdminId());
    }

    @Test
    void testGetAdminByEmailFailure() {
        AdminDTO result = adminDAO.getAdminByEmail("unknown@admin.com");
        assertNull(result);
    }
}
