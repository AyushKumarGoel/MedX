package com.abes.medx.ui;

import java.util.Scanner;

import com.abes.medx.dto.AdminDTO;
import com.abes.medx.dto.AppointmentDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.AppointmentException;
import com.abes.medx.exception.BookingException;
import com.abes.medx.exception.UserException;
import com.abes.medx.service.AppointmentServiceImpl;
import com.abes.medx.service.UserServiceImpl;


public class UI {
    private final Scanner scanner;
    private final UserServiceImpl userService;
    private final AppointmentServiceImpl appointmentService;

    public UI(Scanner scanner, UserServiceImpl userService, AppointmentServiceImpl appointmentService) {
        this.scanner = scanner;
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== Welcome to MedX System ===");
            System.out.println("1. Admin Login");
            System.out.println("2. Doctor Login");
            System.out.println("3. Patient Login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    AdminUI adminUI = new AdminUI(scanner, userService);
                    adminUI.handleAdmin();
                    break;
                case "2":
                    DoctorUI doctorUI = new DoctorUI(scanner, userService, appointmentService);
                    doctorUI.handleDoctor();
                    break;
                case "3":
                    PatientUI patientUI = new PatientUI(appointmentService, userService, scanner);
                    patientUI.handlePatient();
                    break;
                case "4":
                    System.out.println("Thank you for using MedX. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
