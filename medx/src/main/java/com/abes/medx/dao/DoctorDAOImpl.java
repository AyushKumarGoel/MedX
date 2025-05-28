package com.abes.medx.dao;

import com.abes.medx.dto.DoctorDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoctorDAOImpl implements DoctorDAO {

    private List<DoctorDTO> doctorList = new ArrayList<>();

    public void addDoctor(DoctorDTO doctor) {
        doctorList.add(doctor);
    }

    @Override
    public DoctorDTO getDoctorById(int id) {
        Optional<DoctorDTO> doctor = doctorList.stream().filter(d -> d.getId() == id).findFirst();
        return doctor.orElse(null);
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return new ArrayList<>(doctorList);
    }

    @Override
    public void updateDoctor(DoctorDTO updatedDoctor) {
        for(int index = 0; index < doctorList.size(); index++) {
            if(doctorList.get(index).getId() == updatedDoctor.getId()) {
                doctorList.remove(index);
                doctorList.add(updatedDoctor);
            }
        }
    }

    @Override
    public void deleteDoctor(int id) {
        doctorList.remove(doctorList.stream().filter(d -> d.getId() == id).findFirst().get());
    }

    public void showProfile(int id) {
        doctorList.stream().filter(d -> d.getId() == id).findFirst().ifPresent(doctorDTO -> System.out.println(doctorDTO.getName()));
    }
}
