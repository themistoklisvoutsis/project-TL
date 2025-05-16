import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SpecificProgramScreen {
    public static void showGymProgram(String[] programDetails){

        String selectedProgram = programDetails[0];
        int duration = Integer.parseInt(programDetails[1]);
        
        System.out.println("\n=== Η εγγραφή σας ολοκληρώθηκε ===");
        System.out.println("Πρόγραμμα: " + selectedProgram);
        System.out.println("Διάρκεια: " + (duration == 1 ? "1 μήνας" :
                duration == 12 ? "1 χρόνος" :
                        duration + " μήνες"));
    }

    // Μέθοδος ελέγχου ύπαρξης χρήστη
    private static boolean userExists(String firstname, String lastname, String phone) {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM clients WHERE firstname = ? AND lastname = ? AND phone = ?";
            
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, firstname);
                stmt.setString(2, lastname);
                stmt.setString(3, phone);
                
                ResultSet rs = stmt.executeQuery();
                return rs.next(); // Επιστρέφει true αν βρέθηκε χρήστης, false αν όχι
            }
        } catch (SQLException e) {
            System.out.println("Σφάλμα κατά τον έλεγχο ύπαρξης χρήστη: " + e.getMessage());
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
            System.out.println("Σφάλμα κατά την καταχώρηση των στοιχείων: " + e.getMessage());
            return false;
        }
    }

    public static boolean personalDataInsertion(Scanner scanner) {
        try {
            scanner.nextLine(); // Καθαρίζουμε το buffer
            System.out.println("\n=== Εισήγαγε τα στοιχεία σου ===");
            
            System.out.println("Όνομα :");
            String firstname = scanner.nextLine().trim();
            if (firstname.isEmpty()) {
               // System.out.println("\nΤο όνομα δεν μπορεί να είναι κενό!");
                return false;
            }
            
            System.out.println("Επίθετο :");
            String lastname = scanner.nextLine().trim();
            if (lastname.isEmpty()) {
               // System.out.println("\nΤο επίθετο δεν μπορεί να είναι κενό!");
                return false;
            }
            
            System.out.println("Διεύθυνση :");
            String address = scanner.nextLine().trim();
            if (address.isEmpty()) {
               // System.out.println("\nΗ διεύθυνση δεν μπορεί να είναι κενή!");
                return false;
            }
            
            System.out.println("Αριθμός Τηλεφώνου :");
            String phone = scanner.nextLine().trim();
            if (phone.isEmpty()) {
               // System.out.println("\nΟ αριθμός τηλεφώνου δεν μπορεί να είναι κενός!");
                return false;
            }

            // Έλεγχος αν ο χρήστης υπάρχει ήδη
            if (userExists(firstname, lastname, phone)) {
                System.out.println("\nΟ χρήστης υπάρχει ήδη στο σύστημα!");
                return false;
            }

            // Εισαγωγή νέου χρήστη
            if (storeSubscription(firstname, lastname, address, phone)) {
                return true;
            }
            return false;
            
        } catch (Exception e) {
            System.out.println("Σφάλμα: " + e.getMessage());
            return false;
        }
    }
}