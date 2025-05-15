import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SpecificProgramScreen {
    public static void showGymProgram(String[] programDetails){
        if (programDetails == null) return;
        
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
            String sql = "SELECT * FROM gym_client WHERE firstname = ? AND lastname = ? AND phone = ?";
            
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

    // Μέθοδος εισαγωγής νέου χρήστη
    private static boolean storeSubscription(String firstname, String lastname, String address, String phone) {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "INSERT INTO gym_client (firstname, lastname, phone, address) VALUES (?, ?, ?, ?)";
            
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

    public static void personalDataInsertion(Scanner scanner) {
        try {
            scanner.nextLine(); // Καθαρίζουμε το buffer
            System.out.println("\n=== Εισήγαγε τα στοιχεία σου ===");
            
            System.out.println("Όνομα :");
            String firstname = scanner.nextLine();
            
            System.out.println("Επίθετο :");
            String lastname = scanner.nextLine();
            
            System.out.println("Διεύθυνση :");
            String address = scanner.nextLine();
            
            System.out.println("Αριθμός Τηλεφώνου :");
            String phone = scanner.nextLine();

            // Έλεγχος αν ο χρήστης υπάρχει ήδη
            if (userExists(firstname, lastname, phone)) {
                System.out.println("\nΟ χρήστης υπάρχει ήδη στο σύστημα!");
                return;
            }

            // Εισαγωγή νέου χρήστη
            if (storeSubscription(firstname, lastname, address, phone)) {
                System.out.println("\nΗ εγγραφή των στοιχείων σας ολοκληρώθηκε με επιτυχία!");
            }
            
        } catch (Exception e) {
            System.out.println("Σφάλμα: " + e.getMessage());
        }
    }
}