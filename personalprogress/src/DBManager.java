import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBManager {
    private final String DB_URL = "jdbc:mysql://localhost:3306/gym_database";
    private final String USER = "root";
    private final String PASSWORD = "giorgos2004";

    public String queryCheckIn(int clientId, String timePeriod) {
        StringBuilder results = new StringBuilder();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "SELECT check_in_time FROM check_ins WHERE client_id = ? AND DATE(check_in_time) BETWEEN ? AND ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            String[] dates = timePeriod.split(" to ");
            preparedStatement.setInt(1, clientId);
            preparedStatement.setString(2, dates[0]);
            preparedStatement.setString(3, dates[1]);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                results.append(resultSet.getString("check_in_time")).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results.toString();
    }
}
