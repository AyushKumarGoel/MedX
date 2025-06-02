package com.abes.medx.ui;

import java.util.Scanner;

import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.UserException;
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
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Doctor");
            System.out.println("3. Login as Patient");
            System.out.println("4. Register as Patient");
            System.out.println("5. Exit");
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
                    registerPatient(); // Register a new patient
                    break;
                case "5":
                    running = false; // Exit the system
                    break;
                default:
                    System.out.println("Invalid input. Please choose 1, 2, 3, or 4.");
            }
        }

    }

    private void registerPatient() {
        try {
            System.out.print("Enter patient name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Enter patient email: ");
            String email = scanner.nextLine().trim();
            System.out.print("Enter patient password: ");
            String password = scanner.nextLine().trim();
            System.out.print("Enter patient phone number: ");
            String phoneNumber = scanner.nextLine().trim();
            System.out.print("Enter patient age: ");
            String age = scanner.nextLine().trim();

            PatientDTO patient = new PatientDTO(userService.getNextPatientId(), name, email, password, phoneNumber, age);
            boolean success = userService.registerPatient(patient);
            if (success) {
                System.out.println("Patient registered successfully.");
            } else {
                System.out.println("Registration failed: Patient ID already exists.");
            }
        } catch (UserException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }
}
// End of UI.java
// This class serves as the main entry point for the user interface of the MedX
// system, allowing users to select their roles and interact with the
// application accordingly.