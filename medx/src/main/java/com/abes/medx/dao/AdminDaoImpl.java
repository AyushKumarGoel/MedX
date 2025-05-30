package com.abes.medx.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.abes.medx.dto.AdminDTO;
import com.abes.medx.util.CollectionUtil;

public class AdminDaoImpl implements AdminDAO {

    private final Map<String, AdminDTO> adminMap = CollectionUtil.adminMap;

    @Override
    public AdminDTO authenticate(String email, String password) {
        for (AdminDTO admin : adminMap.values()) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    @Override
    public boolean register(AdminDTO admin) {
        if (adminMap.containsKey(admin.getAdminId()) || getAdminByEmail(admin.getEmail()) != null) {
            return false;
        }
        adminMap.put(admin.getAdminId(), admin);
        return true;
    }

    @Override
    public AdminDTO getAdminByEmail(String email) {
        for (AdminDTO admin : adminMap.values()) {
            if (admin.getEmail().equals(email)) {
                return admin;
            }
        }
        return null;
    }

    @Override
    public boolean updateProfile(AdminDTO admin) {
        if (adminMap.containsKey(admin.getAdminId())) {
            adminMap.put(admin.getAdminId(), admin);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String email) {
        AdminDTO admin = getAdminByEmail(email);
        if (admin != null) {
            adminMap.remove(admin.getAdminId());
            return true;
        }
        return false;
    }

    @Override
    public List<AdminDTO> getAllAdmins() {
        return new ArrayList<>(adminMap.values());
    }

    @Override
    public String getNextAdminId() {
        return "A" + (adminMap.size() + 1);
    }
}