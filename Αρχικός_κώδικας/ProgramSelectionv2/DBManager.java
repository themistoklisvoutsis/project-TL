import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBManager {

    // Credentials βάσης δεδομένων.
    private static final String URL = "jdbc:mysql://localhost:3306/GymNet";
    private static final String USER = "root";
    private static final String PASSWORD = "1234ceid";

    private static Connection connection = null;

    // Δημιουργία σύνδεσης
    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                // System.out.println("Επιτυχής σύνδεση στη βάση δεδομένων!");
            }
            return connection;
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found: " + e.getMessage());
        }
    }

    // Κλείσιμο της σύνδεσης
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Η σύνδεση με τη βάση δεδομένων έκλεισε.");
            }
        } catch (SQLException e) {
            System.out.println("Σφάλμα κατά το κλείσιμο της σύνδεσης: " + e.getMessage());
        }
    }
    public static boolean storeSubscription(String programType, int months) {
        try {
            Connection connection = getConnection();
            // Έλεγχος εγκυρότητας προγράμματος
            if (!programType.equals("Ζούμπα") && 
                !programType.equals("Γιόγκα") && 
                !programType.equals("Κροσφιτ") && 
                !programType.equals("Βάρη")) {
                System.out.println("Μη έγκυρο πρόγραμμα!");
                return false;
            }
            
            // Έλεγχος εγκυρότητας διάρκειας
            if (months != 1 && months != 3 && months != 6 && months != 12) {
                System.out.println("Μη έγκυρη διάρκεια συνδρομής!");
                return false;
            }
            
            String sql = "INSERT INTO subscriptions (program_type, duration_months) VALUES (?, ?)";
            
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, programType);
                stmt.setInt(2, months);
                
                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Σφάλμα κατά την αποθήκευση της συνδρομής: " + e.getMessage());
            return false;
        }
    }
}