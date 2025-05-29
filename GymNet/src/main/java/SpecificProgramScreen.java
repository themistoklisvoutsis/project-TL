import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SpecificProgramScreen {
    public static void showGymProgram(String[] programDetails){

        String selectedProgram = programDetails[0];
        int duration = Integer.parseInt(programDetails[1]);

        System.out.println("\n=== Your registration is complete ===");
        System.out.println("Program: " + selectedProgram);
        System.out.println("Duration: " + (duration == 1 ? "1 month" :
                duration == 12 ? "1 year" :
                        duration + " months"));
    }

    // Method to check if user exists
    private static boolean userExists(String firstname, String lastname, String phone) {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM clients WHERE firstname = ? AND lastname = ? AND phone = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, firstname);
                stmt.setString(2, lastname);
                stmt.setString(3, phone);

                ResultSet rs = stmt.executeQuery();
                return rs.next(); // Returns true if user found, false otherwise
            }
        } catch (SQLException e) {
            System.out.println("Error checking user existence: " + e.getMessage());
            return false;
        }
    }

    private static boolean storeSubscription(String firstname, String lastname, String address, String phone) {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "INSERT INTO clients (firstname, lastname, phone, address) VALUES (?, ?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, firstname);
                stmt.setString(2, lastname);
                stmt.setString(3, phone);
                stmt.setString(4, address);

                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error storing subscription data: " + e.getMessage());
            return false;
        }
    }

    public static boolean personalDataInsertion(Scanner scanner) {
        try {
            scanner.nextLine(); // Clear buffer
            System.out.println("\n=== Enter your details ===");

            System.out.println("First Name:");
            String firstname = scanner.nextLine().trim();
            if (firstname.isEmpty()) {
                // System.out.println("\nFirst name cannot be empty!");
                return false;
            }

            System.out.println("Last Name:");
            String lastname = scanner.nextLine().trim();
            if (lastname.isEmpty()) {
                // System.out.println("\nLast name cannot be empty!");
                return false;
            }

            System.out.println("Address:");
            String address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                // System.out.println("\nAddress cannot be empty!");
                return false;
            }

            System.out.println("Phone Number:");
            String phone = scanner.nextLine().trim();
            if (phone.isEmpty()) {
                // System.out.println("\nPhone number cannot be empty!");
                return false;
            }

            // Check if user already exists
            if (userExists(firstname, lastname, phone)) {
                System.out.println("\nUser already exists in the system!");
                return false;
            }

            // Insert new user
            if (storeSubscription(firstname, lastname, address, phone)) {
                return true;
            }
            return false;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
