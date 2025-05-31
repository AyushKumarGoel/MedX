package com.abes.medx.dao;

import java.util.ArrayList;
import java.util.List;

import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.util.CollectionUtil;

public class DoctorDAOImpl implements DoctorDAO {

    @Override
    public boolean register(DoctorDTO doctor) {
        if (CollectionUtil.doctorMap.containsKey(doctor.getDoctorId())) return false;
        CollectionUtil.doctorMap.put(doctor.getDoctorId(), doctor);
        return true;
    }

    @Override
    public DoctorDTO authenticate(String email, String password) {
        for (DoctorDTO doctor : CollectionUtil.doctorMap.values()) {
            if (doctor.getEmail().equalsIgnoreCase(email) && doctor.getPassword().equals(password)) {
                return doctor;
            }
        }
        return null;
    }

    @Override
    public boolean updateProfile(DoctorDTO updatedDoctor) {
        if (!CollectionUtil.doctorMap.containsKey(updatedDoctor.getDoctorId())) return false;
        CollectionUtil.doctorMap.put(updatedDoctor.getDoctorId(), updatedDoctor);
        return true;
    }

    @Override
    public boolean delete(String email) {
        DoctorDTO toDelete = getDoctorByEmail(email);
        if (toDelete != null) {
            CollectionUtil.doctorMap.remove(toDelete.getDoctorId());
            return true;
        }
        return false;
    }

    @Override
    public DoctorDTO getDoctorByEmail(String email) {
        return CollectionUtil.doctorMap.values().stream()
                .filter(d -> d.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public DoctorDTO getDoctorById(String doctorId) {
        return CollectionUtil.doctorMap.get(doctorId);
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return new ArrayList<>(CollectionUtil.doctorMap.values());
    }

    @Override
    public String getNextDoctorId() {
        int i = 1;
        while (CollectionUtil.doctorMap.containsKey("D" + String.format("%03d", i))) {
            i++;
        }
        return "D" + i;      
    }
}