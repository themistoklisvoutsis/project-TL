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


            Scanner input = new Scanner(System.in);
            System.out.println("Δώσε το ύψος");
            height = input.nextDouble();
            System.out.println("Height is " + height);


            Scanner input1 = new Scanner(System.in);
            System.out.println("Δώσε το βάρος");
            weight = input1.nextDouble();
            System.out.println("Wight is " + weight);


            bmi = weight / (height * height);
            System.out.println("BMI is " + bmi);


            saveToDatabase(height, weight, bmi);

        } else if (choice == 2) {
            Scanner clientInput = new Scanner(System.in);
            System.out.println("Δώσε το ID του πελάτη για εμφάνιση ιστορικού:");
            int clientId = clientInput.nextInt();
            printHistory(clientId);
        }
    }


    public static void saveToDatabase(double height, double weight, double bmi) {

        String url = "jdbc:mysql://localhost:3306/WeightTRacking";
        String username = "root";
        String password = "1234ceid";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String query = "INSERT INTO gymclient (client_height, client_weight, bmi, bmi_date) VALUES (" +
                    height + ", " + weight + ", " + bmi + ", CURDATE())";


            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Τα δεδομένα αποθηκεύτηκαν επιτυχώς!");

        } catch (SQLException e) {
            System.out.println("Σφάλμα κατά την αποθήκευση στη βάση δεδομένων: " + e.getMessage());
        }
    }


    public static void printHistory(int clientId) {

        String url = "jdbc:mysql://localhost:3306/WeightTracking";
        String username = "root";
        String password = "1234ceid";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String query = "SELECT * FROM gymclient WHERE client_id = " + clientId;

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            boolean found = false;
            while (rs.next()) {
                found = true;
                double height = rs.getDouble("client_height");
                double weight = rs.getDouble("client_weight");
                double bmi = rs.getDouble("bmi");
                String date = rs.getString("bmi_date");

                System.out.println("Ύψος: " + height + " | Βάρος: " + weight +
                        " | BMI: " + bmi + " | Ημερομηνία: " + date);
            }

            if (!found) {
                System.out.println("Δεν βρέθηκε ιστορικό για αυτό το client ID.");
            }

        } catch (SQLException e) {
            System.out.println("Σφάλμα κατά την ανάκτηση των δεδομένων: " + e.getMessage());
        }
    }

}
