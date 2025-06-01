package com.abes.medx.ui;

import java.util.Scanner;

import com.abes.medx.service.AppointmentService;
import com.abes.medx.service.UserService;

public class UI {
    private final Scanner scanner;
    private final UserService userService;
    private final AppointmentService appointmentService;

    public UI(Scanner scanner, UserService userService, AppointmentService appointmentService) {
        this.scanner = scanner;
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    public void start() {
        boolean running = true;

        AdminUI adminUI = new AdminUI(scanner, userService);
        DoctorUI doctorUI = new DoctorUI(scanner, userService, appointmentService);
        PatientUI patientUI = new PatientUI(scanner, appointmentService, userService);

        while (running) {
            System.out.println("\n--- MedX System ---");
            System.out.println("Select your role:");
            System.out.println("1. Admin");
            System.out.println("2. Doctor");
            System.out.println("3. Patient");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    adminUI.handleAdmin();
                    break;
                case "2":
                    doctorUI.handleDoctor();
                    break;
                case "3":
                    patientUI.handlePatient();
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input. Please choose 1, 2, 3, or 4.");
            }
        }
    }
}
