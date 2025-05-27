package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManagerFD {

    private static final String URL = "jdbc:mysql://localhost:3306/GymNet"
            + "?useSSL=false"
            + "&allowPublicKeyRetrieval=true"
            + "&serverTimezone=Europe/Athens";
    private static final String USER = "root";
    private static final String PASSWORD = "1234ceid";

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Επιτυχής σύνδεση με τη βάση GymNet!");
            }
            return connection;
        } catch (ClassNotFoundException e) {
            throw new SQLException("❌ Δεν βρέθηκε ο MySQL Driver: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        try {
            getConnection();
        } catch (SQLException e) {
            System.out.println("❌ Σφάλμα κατά τη σύνδεση: " + e.getMessage());
            e.printStackTrace();
        }
        NoDataForComparisonScreen.display();
        FinancialDashboardScreen.choosesTimeDomain();

    }
    public static List<String> searchForTimeDomain(Date from, Date to) {
        List<String> results = new ArrayList<>();
        String query = "SELECT * FROM financial_history WHERE check_date BETWEEN ? AND ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, from);
            stmt.setDate(2, to);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    long gymId = rs.getLong("Gym_Id");
                    double fdata = rs.getDouble("Fdata");
                    Date checkDate = rs.getDate("check_date");

                    String row = "📌 ID: " + id +
                            ", Gym ID: " + gymId +
                            ", Ποσό: " + fdata + "€" +
                            ", Ημερομηνία: " + checkDate;

                    results.add(row);
                }
            }

        } catch (SQLException e) {
            results.add("❌ Σφάλμα βάσης: " + e.getMessage());
        }

        return results;
    }



}

//searchForTimeDomain()
// searchForDifferentTimeDomain