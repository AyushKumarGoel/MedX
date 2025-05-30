package com.abes.medx.dao;

import java.util.ArrayList;
import java.util.List;

import com.abes.medx.dto.AdminDTO;
import com.abes.medx.util.CollectionUtil;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public boolean register(AdminDTO admin) {
        if (CollectionUtil.adminMap.containsKey(admin.getAdminId())) {
            return false; // admin already exists
        }
        CollectionUtil.adminMap.put(admin.getAdminId(), admin);
        return true;
    }

    @Override
    public AdminDTO authenticate(String email, String password) {
        return CollectionUtil.adminMap.values().stream()
                .filter(admin -> admin.getEmail().equalsIgnoreCase(email)
                        && admin.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateProfile(AdminDTO updatedAdmin) {
        if (!CollectionUtil.adminMap.containsKey(updatedAdmin.getAdminId())) {
            return false; // admin does not exist
        }
        CollectionUtil.adminMap.put(updatedAdmin.getAdminId(), updatedAdmin);
        return true;
    }

    @Override
    public boolean delete(String email) {
        AdminDTO adminToDelete = getAdminByEmail(email);
        if (adminToDelete != null) {
            CollectionUtil.adminMap.remove(adminToDelete.getAdminId());
            return true;
        }
        return false;
    }

    @Override
    public AdminDTO getAdminByEmail(String email) {
        return CollectionUtil.adminMap.values().stream()
                .filter(admin -> admin.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String getNextAdminId() {
        return "ADM" + (CollectionUtil.adminMap.size() + 1);
    }

    @Override
    public List<AdminDTO> getAllAdmins() {
        return new ArrayList<>(CollectionUtil.adminMap.values());
    }
}