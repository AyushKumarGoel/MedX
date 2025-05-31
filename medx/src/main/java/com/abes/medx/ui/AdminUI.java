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

    public void handleAdmin() {
        try {
            System.out.print("Admin Email: ");
            String email = scanner.nextLine();
            if (email.trim().isEmpty()) throw new UserException("Email cannot be empty.");

            System.out.print("Password: ");
            String password = scanner.nextLine();
            if (password.trim().isEmpty()) throw new UserException("Password cannot be empty.");

            AdminDTO admin = userService.adminLogin(email, password);
            adminMenu(admin);
        } catch (UserException e) {
            System.out.println("Admin login failed: " + e.getMessage());
        }
    }

    private void adminMenu(AdminDTO admin) {
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
            System.out.println("10. Update Admin");
            System.out.println("11. Delete Admin");
            System.out.println("12. Logout");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        List<DoctorDTO> doctors = userService.getAllDoctors();
                        doctors.forEach(System.out::println);
                        break;
                    case "2":
                        // call your registerDoctor() method
                        break;
                    case "3":
                        // call your updateDoctor() method
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
                        // call your registerPatient() method
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
                        // call your registerAdmin() method
                        break;
                    case "10":
                        // call your updateAdmin() method
                        break;
                    case "11":
                        System.out.print("Enter admin email to delete: ");
                        String admEmail = scanner.nextLine();
                        if (admEmail.equals(admin.getEmail())) {
                            throw new UserException("Cannot delete yourself while logged in.");
                        }
                        if (userService.deleteAdmin(admEmail)) {
                            System.out.println("Admin deleted.");
                        } else {
                            System.out.println("Admin not found.");
                        }
                        break;
                    case "12":
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (UserException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
