package com.abes.medx.dao;

import com.abes.medx.dto.AdminDTO;

public interface AdminDAO {
    boolean register(AdminDTO admin);
    AdminDTO authenticate(String email, String password);
    boolean updateProfile(AdminDTO updatedAdmin);
    boolean delete(String email);
    AdminDTO getAdminByEmail(String email);
}