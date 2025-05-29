import java.sql.*;

public class DBManager {
    private Connection connection;

    public DBManager() {
        try {
            String url = "jdbc:mysql://localhost:3306/gymdb";
            String user = "root";
            String password = "giorgos2004";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    public boolean searchMembership(String clientId) {
        String query = "SELECT has_participated FROM clients WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, clientId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("has_participated");
            }
        } catch (SQLException e) {
            System.err.println("Error checking membership: " + e.getMessage());
        }
        return false;
    }

    public void uploadComment(String clientId, String gymName, String comment) {
        String query = "INSERT INTO comments (gym_name, client_id, comment) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, gymName);
            stmt.setString(2, clientId);
            stmt.setString(3, comment);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error uploading comment: " + e.getMessage());
        }
    }

    public void uploadRating(String clientId, String gymName, double rating) {
        String query = "INSERT INTO ratings (gym_name, client_id, rating) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, gymName);
            stmt.setString(2, clientId);
            stmt.setDouble(3, rating);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error uploading rating: " + e.getMessage());
        }
    }

    public void showGymFeedback(String gymName) {
        String commentsQuery = "SELECT comment FROM comments WHERE gym_name = ?";
        String ratingsQuery = "SELECT AVG(rating) AS average_rating FROM ratings WHERE gym_name = ?";

        try (PreparedStatement commentsStmt = connection.prepareStatement(commentsQuery);
             PreparedStatement ratingsStmt = connection.prepareStatement(ratingsQuery)) {

            commentsStmt.setString(1, gymName);
            ResultSet commentsRs = commentsStmt.executeQuery();
            System.out.println("Comments for " + gymName + ":");
            while (commentsRs.next()) {
                System.out.println("- " + commentsRs.getString("comment"));
            }

            ratingsStmt.setString(1, gymName);
            ResultSet ratingsRs = ratingsStmt.executeQuery();
            if (ratingsRs.next()) {
                System.out.println("Average Rating: " + ratingsRs.getDouble("average_rating") + " stars");
            }

        } catch (SQLException e) {
            System.err.println("Error displaying gym feedback: " + e.getMessage());
        }
    }
}
