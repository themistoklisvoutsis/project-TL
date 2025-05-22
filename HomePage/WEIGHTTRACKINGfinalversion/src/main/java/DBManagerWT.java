import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DBManagerWT {

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

    // Αποθήκευση BMI στην βάση δεδομένων.
    public static boolean storeResult(int userId, double bmi) {
        String query = "INSERT INTO bmi_results (user_id, bmi_value, measurement_date) VALUES (?, ?, NOW())";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            pstmt.setDouble(2, bmi);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            // System.out.println("Σφάλμα κατά την αποθήκευση του BMI: " + e.getMessage());
            return false;
        }
    }
    public static void bodyMassHistorySearch() {
        String query = "SELECT user_id, bmi_value, measurement_date FROM bmi_results ORDER BY measurement_date DESC";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (!rs.isBeforeFirst()) { // Έλεγχος για ύπαρξη περιεχομένου στην βάση.
                System.out.println("\nΔεν βρέθηκαν καταχωρήσεις BMI στη βάση δεδομένων.");
                return;
            }

            System.out.println("\nΙστορικό μετρήσεων BMI:");
            System.out.println("------------------------");

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                double bmiValue = rs.getDouble("bmi_value");
                java.sql.Timestamp measurementDate = rs.getTimestamp("measurement_date");

                System.out.printf("Χρήστης: %d | BMI: %.2f | Ημερομηνία: %s%n",
                        userId, bmiValue, measurementDate);
            }

        } catch (SQLException e) {
            System.out.println("Σφάλμα κατά την ανάκτηση του ιστορικού BMI: " + e.getMessage());
        }
    }


}