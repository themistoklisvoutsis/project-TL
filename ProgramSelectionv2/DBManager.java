import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBManager {

    // Credentials βάσης δεδομένων.
    private static final String URL = "jdbc:mysql://localhost:3306/ProgramSelection";
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
}