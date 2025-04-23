import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class WeightTrackingTerminal {
    public static void main(String args[]) {

        // Δήλωση Μεταβλητών.
        double height;
        double weight;
        double bmi;
        int choice;

        System.out.println("Για επιλογή υπολογισμού Δείκτη Μάζας Σώματος πάτα το 1.");
        System.out.println("Για επιλογή εμφάνισης Ιστορικού πάτα το 2.");

        Scanner inputchoice = new Scanner(System.in);
        System.out.println("Δώσε την επιλογή");
        choice = inputchoice.nextInt();

        if (choice == 1) {

            // Διάβασμα και εκτύπωση ύψους.
            Scanner input = new Scanner(System.in);
            System.out.println("Δώσε το ύψος");
            height = input.nextDouble();
            System.out.println("Height is " + height);

            // Διάβασμα και εκτύπωση βάρους.
            Scanner input1 = new Scanner(System.in);
            System.out.println("Δώσε το βάρος");
            weight = input1.nextDouble();
            System.out.println("Wight is " + weight);

            // Υπολογισμός Δείκτη Μάζας Σώματος.
            bmi = weight / (height * height);
            System.out.println("BMI is " + bmi);

        } else if(choice==2){
            printHistory();
        }
    }

    // Μέθοδος για αποθήκευση δεδομένων στη βάση
    public static void saveToDatabase(double height, double weight, double bmi) {
        // Σύνδεση στη βάση δεδομένων
        String url = "jdbc:mysql://localhost:3306/your_database_name";
        String username = "root";
        String password = "your_password";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Δημιουργία SQL query για εισαγωγή
            String query = "INSERT INTO weight_history (height, weight, bmi, date) VALUES (" +
                    height + ", " + weight + ", " + bmi + ", CURDATE())";

            // Εκτέλεση του query
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Τα δεδομένα αποθηκεύτηκαν επιτυχώς!");

        } catch (SQLException e) {
            System.out.println("Σφάλμα κατά την αποθήκευση στη βάση δεδομένων: " + e.getMessage());
        }
    }

    // Μέθοδος για εμφάνιση του ιστορικού από τη βάση δεδομένων
    public static void printHistory() {
        // Σύνδεση στη βάση δεδομένων
        String url = "jdbc:mysql://localhost:3306/WeightTracking";
        String username = "root";
        String password = "1234ceid";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Δημιουργία SQL query για ανάκτηση των δεδομένων
            String query = "SELECT * FROM gymclient";

            // Εκτέλεση του query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Εκτύπωση των αποτελεσμάτων
            while (rs.next()) {
                int id = rs.getInt("client_id");
                double height = rs.getDouble("client_height");
                double weight = rs.getDouble("client_weight");
                double bmi = rs.getDouble("bmi");
                String date = rs.getString("bmi_date");

                System.out.println("ID: " + id + " | Ύψος: " + height + " | Βάρος: " + weight +
                        " | BMI: " + bmi + " | Ημερομηνία: " + date);
            }

        } catch (SQLException e) {
            System.out.println("Σφάλμα κατά την ανάκτηση των δεδομένων: " + e.getMessage());
        }
    }
}