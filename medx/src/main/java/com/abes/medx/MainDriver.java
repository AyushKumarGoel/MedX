package com.abes.medx;


import java.util.Scanner;

import com.abes.medx.dao.AdminDAO;
import com.abes.medx.dao.AdminDAOImpl;
import com.abes.medx.dao.AppointmentDAO;
import com.abes.medx.dao.AppointmentDAOImpl;
import com.abes.medx.dao.DoctorDAO;
import com.abes.medx.dao.DoctorDAOImpl;
import com.abes.medx.dao.PatientDAO;
import com.abes.medx.dao.PatientDAOImpl;
import com.abes.medx.service.AppointmentService;
import com.abes.medx.service.UserService;
import com.abes.medx.ui.UI;
import com.abes.medx.util.CollectionUtil;

public class MainDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(CollectionUtil.patientMap.keySet());

        // Instantiate DAO implementations
        AdminDAO adminDAO = new AdminDAOImpl();
        DoctorDAO doctorDAO = new DoctorDAOImpl();
        PatientDAO patientDAO = new PatientDAOImpl();
        AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

        UserService userService = new UserService(adminDAO, doctorDAO, patientDAO);
        AppointmentService appointmentService = new AppointmentService(appointmentDAO, doctorDAO);

        // Launch the UI
        UI ui = new UI(scanner, userService, appointmentService);
        ui.start();

    }
}