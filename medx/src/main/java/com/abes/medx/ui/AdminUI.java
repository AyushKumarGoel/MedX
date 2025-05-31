package com.abes.medx.ui;

import java.util.List;
import java.util.Scanner;

import com.abes.medx.dto.AdminDTO;
import com.abes.medx.dto.DoctorDTO;
import com.abes.medx.dto.PatientDTO;
import com.abes.medx.exception.UserException;
import com.abes.medx.service.UserService;

public class AdminUI {

    private final Scanner scanner;
    private final UserService userService;

    public AdminUI(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    public void handleAdmin() throws UserException {
        System.out.print("Admin Email: ");
        String email = scanner.nextLine();
        if (email.trim().isEmpty()) throw new UserException("Email cannot be empty.");

        System.out.print("Password: ");
        String password = scanner.nextLine();
        if (password.trim().isEmpty()) throw new UserException("Password cannot be empty.");

        AdminDTO admin = userService.adminLogin(email, password);
        adminMenu(admin);
    }

    private void adminMenu(AdminDTO admin) throws UserException {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View All Doctors");
            System.out.println("2. Register New Doctor");
            System.out.println("3. Update Doctor");
            System.out.println("4. Delete Doctor");
            System.out.println("5. View All Patients");
            System.out.println("6. Register New Patient");
            System.out.println("7. Delete Patient");
            System.out.println("8. View All Admins");
            System.out.println("9. Register New Admin");
            System.out.println("10. Logout");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    List<DoctorDTO> doctors = userService.getAllDoctors();
                    doctors.forEach(System.out::println);
                    break;
                case "2":
                    registerDoctor();
                    break;
                case "3":
                    updateDoctor();
                    break;
                case "4":
                    System.out.print("Enter doctor email to delete: ");
                    String docEmail = scanner.nextLine();
                    if (userService.deleteDoctor(docEmail)) {
                        System.out.println("Doctor deleted.");
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case "5":
                    List<PatientDTO> patients = userService.getAllPatients();
                    patients.forEach(System.out::println);
                    break;
                case "6":
                    registerPatient();
                    break;
                case "7":
                    System.out.print("Enter patient email to delete: ");
                    String patEmail = scanner.nextLine();
                    if (userService.deletePatient(patEmail)) {
                        System.out.println("Patient deleted.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case "8":
                    List<AdminDTO> admins = userService.getAllAdmins();
                    admins.forEach(System.out::println);
                    break;
                case "9":
                    registerAdmin();
                    break;
                case "10":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void registerDoctor() throws UserException, NumberFormatException {
        System.out.print("Doctor ID: ");
        String doctorId = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Age: ");
        String age = scanner.nextLine();
        System.out.print("Specialization: ");
        String specialization = scanner.nextLine();
        System.out.print("Years of Experience: ");
        int yearsOfExperience = Integer.parseInt(scanner.nextLine());

        DoctorDTO doctor = new DoctorDTO(doctorId, name, email, password, phoneNumber, age, specialization, yearsOfExperience);
        userService.registerDoctor(doctor);
        System.out.println("Doctor registered successfully.");
    }

    private void updateDoctor() throws UserException, NumberFormatException {
        System.out.print("Doctor ID: ");
        String doctorId = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Age: ");
        String age = scanner.nextLine();
        System.out.print("Specialization: ");
        String specialization = scanner.nextLine();
        System.out.print("Years of Experience: ");
        int yearsOfExperience = Integer.parseInt(scanner.nextLine());

        DoctorDTO doctor = new DoctorDTO(doctorId, name, email, password, phoneNumber, age, specialization, yearsOfExperience);
        userService.updateDoctorProfile(doctor);
        System.out.println("Doctor updated successfully.");
    }

    private void registerPatient() throws UserException {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            throw new UserException("Name cannot be empty.");
        }
        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (email.trim().isEmpty()) {
            throw new UserException("Email cannot be empty.");
        }
        System.out.print("Password: ");
        String password = scanner.nextLine();
        if (password.trim().isEmpty()) {
            throw new UserException("Password cannot be empty.");
        }
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Age: ");
        String age = scanner.nextLine();

        PatientDTO patient = new PatientDTO(userService.getNextPatientId(), name, email, password, phoneNumber, age);
        boolean success = userService.registerPatient(patient);
        if (success) {
            System.out.println("Patient registered successfully.");
        } else {
            System.out.println("Patient registration failed. ID or email may already exist.");
        }
    }

    private void registerAdmin() throws UserException {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            throw new UserException("Name cannot be empty.");
        }
        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (email.trim().isEmpty()) {
            throw new UserException("Email cannot be empty.");
        }
        System.out.print("Password: ");
        String password = scanner.nextLine();
        if (password.trim().isEmpty()) {
            throw new UserException("Password cannot be empty.");
        }
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Age: ");
        String age = scanner.nextLine();

        AdminDTO admin = new AdminDTO(userService.getNextAdminId(), name, email, password, phoneNumber, age);
        boolean success = userService.registerAdmin(admin);
        if (success) {
            System.out.println("Admin registered successfully.");
        } else {
            System.out.println("Admin registration failed. ID or email may already exist.");
        }
    }

}
