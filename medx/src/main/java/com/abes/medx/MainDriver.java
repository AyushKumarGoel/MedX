package com.abes.medx;

// Importing necessary classes
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
        // Scanner object to capture user input
        Scanner scanner = new Scanner(System.in);

        // Debug output: display preloaded IDs for testing purposes
        System.out.println("Preloaded Patient IDs: " + CollectionUtil.patientMap.keySet());
        System.out.println("Preloaded Doctor IDs: " + CollectionUtil.doctorMap.keySet());
        System.out.println("Preloaded Admin IDs: " + CollectionUtil.adminMap.keySet());

        // Greeting message to user
        System.out.println("Welcome to MedX, your healthcare management system!");
        System.out.println("Please select your role to continue:");

        // DAO instances to interact with data layer for different user types
        AdminDAO adminDAO = new AdminDAOImpl();
        DoctorDAO doctorDAO = new DoctorDAOImpl();
        PatientDAO patientDAO = new PatientDAOImpl();
        AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

        // Service instances to handle business logic for users and appointments
        UserServiceImpl userService = new UserServiceImpl(adminDAO, doctorDAO, patientDAO);
        AppointmentServiceImpl appointmentService = new AppointmentServiceImpl(appointmentDAO, doctorDAO);

        // UI instance to handle interaction with the user
        UI ui = new UI(scanner, userService, appointmentService);

        // Start the user interface
        ui.start();

        // Close the scanner resource
        scanner.close();

        // Farewell message
        System.out.println("Thank you for using MedX. Goodbye!");
    }
}
// End of MainDriver.java
// This code serves as the entry point for the MedX application, initializing necessary components and starting the user interface.