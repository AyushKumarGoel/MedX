package com.abes.medx.ui;

import java.util.Scanner;

import com.abes.medx.service.AppointmentService;
import com.abes.medx.service.UserService;

/**
 * Main user interface entry point for the MedX system.
 * It allows users to choose their role and interact accordingly.
 */
public class UI {
    private final Scanner scanner;
    private final UserService userService;
    private final AppointmentService appointmentService;

    /**
     * Constructor to initialize UI with required services and scanner.
     *
     * @param scanner            Input scanner for user input.
     * @param userService        Service to handle user-related operations.
     * @param appointmentService Service to handle appointment-related operations.
     */
    public UI(Scanner scanner, UserService userService, AppointmentService appointmentService) {
        this.scanner = scanner;
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    /**
     * Starts the main application loop, allowing users to select their roles.
     */
    public void start() {
        boolean running = true;

        // Initialize UI handlers for each user role
        AdminUI adminUI = new AdminUI(scanner, userService);
        DoctorUI doctorUI = new DoctorUI(scanner, userService, appointmentService);
        PatientUI patientUI = new PatientUI(scanner, appointmentService, userService);

        // Main loop for user interaction
        while (running) {
            System.out.println("\n--- MedX System ---");
            System.out.println("Select your role:");
            System.out.println("1. Admin");
            System.out.println("2. Doctor");
            System.out.println("3. Patient");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            // Handle user input and invoke respective UI logic
            switch (choice) {
                case "1":
                    adminUI.handleAdmin(); // Admin login and actions
                    break;
                case "2":
                    doctorUI.handleDoctor(); // Doctor login and actions
                    break;
                case "3":
                    patientUI.handlePatient(); // Patient login and actions
                    break;
                case "4":
                    running = false; // Exit the system
                    break;
                default:
                    System.out.println("Invalid input. Please choose 1, 2, 3, or 4.");
            }
        }
    }
}
// End of UI.java
// This class serves as the main entry point for the user interface of the MedX system, allowing users to select their roles and interact with the application accordingly.