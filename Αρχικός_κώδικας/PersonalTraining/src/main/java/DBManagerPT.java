import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBManagerPT {
    private static final String URL = "jdbc:mysql://localhost:3306/GymNet";
    private static final String USER = "root";
    private static final String PASSWORD = "1234ceid";

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return connection;
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Σφάλμα κατά το κλείσιμο της σύνδεσης: " + e.getMessage());
        }
    }

    public static boolean storeSubscription(String fullName, int age, String gender,
                                      int height, int weight, boolean hasInjuries,
                                      String injuries, String trainerName, String programType,
                                      String startDate, int duration, double cost) {
    Connection conn = null;
    try {
        conn = getConnection();
        conn.setAutoCommit(false);  // Ξεκινάμε transaction

        // Εισαγωγή προσωπικών στοιχείων
        String personalInfoSql = "INSERT INTO PersonalInfo " +
                "(FullName, Age, Gender, Height, Weight, HasInjuries, Injuries) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstmt1 = conn.prepareStatement(personalInfoSql);
        pstmt1.setString(1, fullName);
        pstmt1.setInt(2, age);
        pstmt1.setString(3, gender);
        pstmt1.setInt(4, height);
        pstmt1.setInt(5, weight);
        pstmt1.setBoolean(6, hasInjuries);
        pstmt1.setString(7, injuries);

        int personalInfoResult = pstmt1.executeUpdate();
        pstmt1.close();

        // Εισαγωγή στοιχείων συνδρομής
        String subscriptionSql = "INSERT INTO PersonalTrainingSubscriptions " +
                "(CustomerName, TrainerName, ProgramType, StartDate, Duration, Cost) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement pstmt2 = conn.prepareStatement(subscriptionSql);
        pstmt2.setString(1, fullName);
        pstmt2.setString(2, trainerName);
        pstmt2.setString(3, programType);
        pstmt2.setString(4, startDate);
        pstmt2.setInt(5, duration);
        pstmt2.setDouble(6, cost);

        int subscriptionResult = pstmt2.executeUpdate();
        pstmt2.close();

        // Αν όλα πήγαν καλά, κάνουμε commit
        if (personalInfoResult > 0 && subscriptionResult > 0) {
            conn.commit();
            return true;
        } else {
            conn.rollback();
            return false;
        }

    } catch (SQLException e) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException ex) {
            System.out.println("Σφάλμα κατά το rollback: " + ex.getMessage());
        }
        System.out.println("Σφάλμα κατά την αποθήκευση: " + e.getMessage());
        return false;
    } finally {
        try {
            if (conn != null) {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println("Σφάλμα κατά την επαναφορά του autoCommit: " + e.getMessage());
        }
    }
}
}