package com.abes.medx.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.util.CollectionUtil;

public class DoctorDAOImpl implements DoctorDAO {

    private final Map<String, DoctorDTO> doctorMap = CollectionUtil.doctorMap;

    @Override
    public DoctorDTO authenticate(String email, String password) {
        for (DoctorDTO doctor : doctorMap.values()) {
            if (doctor.getEmail().equals(email) && doctor.getPassword().equals(password)) {
                return doctor;
            }
        }
        return null;
    }

    @Override
    public boolean register(DoctorDTO doctor) {
        if (doctorMap.containsKey(doctor.getDoctorId()) || getDoctorByEmail(doctor.getEmail()) != null) {
            return false;
        }
        doctorMap.put(doctor.getDoctorId(), doctor);
        return true;
    }

    @Override
    public boolean delete(String email) {
        DoctorDTO doctor = getDoctorByEmail(email);
        if (doctor != null) {
            doctorMap.remove(doctor.getDoctorId());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProfile(DoctorDTO doctor) {
        if (doctorMap.containsKey(doctor.getDoctorId())) {
            doctorMap.put(doctor.getDoctorId(), doctor);
            return true;
        }
        return false;
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return new ArrayList<>(doctorMap.values());
    }

    @Override
    public DoctorDTO getDoctorById(String id) {
        return doctorMap.get(id);
    }

    @Override
    public DoctorDTO getDoctorByEmail(String email) {
        for (DoctorDTO doctor : doctorMap.values()) {
            if (doctor.getEmail().equals(email)) {
                return doctor;
            }
        }
        return null;
    }

    @Override
    public String getNextDoctorId() {
        int nextId = doctorMap.size() + 1; // Simple increment logic for demo purposes
        return "DOC" + nextId; // Assuming doctor IDs are prefixed with "DOC"
    }
}