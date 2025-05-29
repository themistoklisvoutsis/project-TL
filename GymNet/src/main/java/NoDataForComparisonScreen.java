
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NoDataForComparisonScreen {
    public static void display() {
        String query = "SELECT COUNT(*) AS total FROM financial_history";

        try (Connection connection = DBManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                int count = rs.getInt("total");

                if (count == 0) {
                    System.out.println("️ No History of Financial Data.");
                } else {

                }
            }

        } catch (SQLException e) {
            System.out.println(" Error!!." + e.getMessage());
        }
    }
}
