import java.sql.*;

public class DBManagerCI {
    private Connection connection;

    public void DBManager() {
        try {
            String url = "jdbc:mysql://localhost:3306/GymNet";
            String user = "root";
            String password = "1234ceid";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    public boolean isClientRegistered(String qrCode) {
        String query = "SELECT id FROM clients WHERE qr_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, qrCode);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error checking client registration: " + e.getMessage());
        }
        return false;
    }

    public boolean hasCheckedInToday(String qrCode) {
        String query = "SELECT last_check_in FROM clients WHERE qr_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, qrCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Timestamp lastCheckIn = rs.getTimestamp("last_check_in");
                return isSameDay(new java.util.Date(), lastCheckIn);
            }
        } catch (SQLException e) {
            System.err.println("Error checking last check-in: " + e.getMessage());
        }
        return false;
    }

    public void updateCheckIn(String qrCode) {
        String query = "UPDATE clients SET last_check_in = ? WHERE qr_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            stmt.setString(2, qrCode);
            stmt.executeUpdate();
            System.out.println("Check-in updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error updating check-in: " + e.getMessage());
        }
    }

    private boolean isSameDay(java.util.Date date, Timestamp timestamp) {
        java.util.Calendar cal1 = java.util.Calendar.getInstance();
        java.util.Calendar cal2 = java.util.Calendar.getInstance();
        cal1.setTime(date);
        cal2.setTime(timestamp);

        return cal1.get(java.util.Calendar.YEAR) == cal2.get(java.util.Calendar.YEAR)
                && cal1.get(java.util.Calendar.DAY_OF_YEAR) == cal2.get(java.util.Calendar.DAY_OF_YEAR);
    }
}


