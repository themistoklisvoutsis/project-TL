package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.DBManagerFD.getConnection;

public class NoDataForComparisonScreen {
    public static void display() {
        String query = "SELECT COUNT(*) AS total FROM financial_history";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                int count = rs.getInt("total");

                if (count == 0) {
                    System.out.println("⚠️ Δεν υπάρχει ιστορικό οικονομικών.");
                } else {
                    //System.out.println("✅ Υπάρχουν " + count + " εγγραφές στο οικονομικό ιστορικό.");
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Σφάλμα κατά τον έλεγχο." + e.getMessage());
        }
    }
}
