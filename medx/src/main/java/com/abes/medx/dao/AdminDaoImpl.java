package com.abes.medx.dao;

import com.abes.medx.dto.AdminDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.util.CollectionUtil;

public class AdminDaoImpl implements AdminDAO {

    @Override
    public boolean login(String adminId, String password) {
        // Fetch from CollectionUtil.adminMap
        PatientDTO admin = CollectionUtil.adminMap.get(adminId);
        return admin != null && admin.getPassword().equals(password);
    }

    @Override
    public void addDoctor(String doctorId, String name, String specialization, int yearsOfExperience) {
        int id = Integer.parseInt(doctorId);
        DoctorDTO doctor = new DoctorDTO(id, name, specialization, yearsOfExperience);
        CollectionUtil.doctorMap.put(doctorId, doctor);
    }

    @Override
    public void removeDoctor(String doctorId) {
        CollectionUtil.doctorMap.remove(doctorId);
    }

    @Override
    public String viewAllDoctors() {
        if (CollectionUtil.doctorMap.isEmpty()) return "No doctors available.";
        StringBuilder sb = new StringBuilder("Doctors List:\n");
        for (DoctorDTO doc : CollectionUtil.doctorMap.values()) {
            sb.append("ID: ").append(doc.getId())
              .append(", Name: ").append(doc.getName())
              .append(", Specialization: ").append(doc.getSpecialization())
              .append(", Experience: ").append(doc.getYearsOfExperience()).append(" years")
              .append("\n");
        }
        return sb.toString();
    }

    @Override
    public String viewAllPatients() {
        if (CollectionUtil.patientMap.isEmpty()) return "No patients available.";
        StringBuilder sb = new StringBuilder("Patients List:\n");
        for (PatientDTO pat : CollectionUtil.patientMap.values()) {
            sb.append("ID: ").append(pat.getPatientId())
              .append(", Name: ").append(pat.getFirstName()).append(" ").append(pat.getLastName() != null ? pat.getLastName() : "")
              .append(", Email: ").append(pat.getEmail())
              .append(", Phone: ").append(pat.getPhoneNumber())
              .append(", Age: ").append(pat.getAge())
              .append("\n");
        }
        return sb.toString();
    }
}
