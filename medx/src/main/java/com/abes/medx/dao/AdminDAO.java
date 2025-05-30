package com.abes.medx.dao;

import java.util.List;

import com.abes.medx.dto.AdminDTO;

public interface AdminDAO {
    AdminDTO authenticate(String email, String password);
    boolean register(AdminDTO admin);
    AdminDTO getAdminByEmail(String email);
    boolean updateProfile(AdminDTO admin);
    boolean delete(String email);
    List<AdminDTO> getAllAdmins();
    String getNextAdminId();
}