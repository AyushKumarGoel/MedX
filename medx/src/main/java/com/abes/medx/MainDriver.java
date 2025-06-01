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
import com.abes.medx.service.AppointmentServiceImpl;
import com.abes.medx.service.UserServiceImpl;
import com.abes.medx.ui.UI;
import com.abes.medx.util.CollectionUtil;

public class MainDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Optional: Debugging keys loaded
        System.out.println("Preloaded Patient IDs: " + CollectionUtil.patientMap.keySet());
        System.out.println("Preloaded Doctor IDs: " + CollectionUtil.doctorMap.keySet());
        System.out.println("Preloaded Admin IDs: " + CollectionUtil.adminMap.keySet());

        System.out.println("Welcome to MedX, your healthcare management system!");
        System.out.println("Please select your role to continue:");

        AdminDAO adminDAO = new AdminDAOImpl();
        DoctorDAO doctorDAO = new DoctorDAOImpl();
        PatientDAO patientDAO = new PatientDAOImpl();
        AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

        UserServiceImpl userService = new UserServiceImpl(adminDAO, doctorDAO, patientDAO);
        AppointmentServiceImpl appointmentService = new AppointmentServiceImpl(appointmentDAO, doctorDAO);

        UI ui = new UI(scanner, userService, appointmentService);
        ui.start();

        scanner.close();
        System.out.println("Thank you for using MedX. Goodbye!");
    }
}
