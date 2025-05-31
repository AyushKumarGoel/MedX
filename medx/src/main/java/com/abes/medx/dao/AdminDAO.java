package com.abes.medx.dao;

import java.util.List;

import com.abes.medx.dto.AdminDTO;

// DAO interface for Admin operations
public interface AdminDAO {
    boolean register(AdminDTO admin);
    AdminDTO authenticate(String email, String password);
    boolean updateProfile(AdminDTO updatedAdmin);
    boolean delete(String email);
    AdminDTO getAdminByEmail(String email);
    String getNextAdminId();
    List<AdminDTO> getAllAdmins();
    
}