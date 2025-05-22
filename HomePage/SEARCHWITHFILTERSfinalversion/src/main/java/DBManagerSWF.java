

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/* Σύνδεση βάσης mySQLshell με τον κώδικα */
public class DBManagerSWF {
    private static final String URL = "jdbc:mysql://localhost:3306/GymNet";
    private static final String USER = "root";
    private static final String PASSWORD = "1234ceid";

    public List<GymProfile> filterSearchGymProfile(ManageFilters filters) {
        List<GymProfile> results = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM gymlist WHERE 1=1");
        List<Object> params = new ArrayList<>();

        prepareQuery(filters, query, params);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            setParameters(stmt, params);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                results.add(mapResultToGymProfile(rs));
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return results;
    }

    private void setParameters(PreparedStatement stmt, List<Object> params) throws SQLException {
        for (int i = 0; i < params.size(); i++) {
            stmt.setObject(i + 1, params.get(i));
        }
    }

    /* Σύγκριση επιλογών του χρήστη (φίλτρα που έδωσε) με το περιεχόμενο του πίνακα gymlist της βάσης mySQLshell */
    private void prepareQuery(ManageFilters filters, StringBuilder query, List<Object> params) {

        if(filters.getNameFilter() != null) {
            query.append(" AND gymname LIKE ?");
            params.add("%" + filters.getNameFilter() + "%");
        }


        if(filters.getCityFilter() != null) {
            query.append(" AND city = ?");
            params.add(filters.getCityFilter());
        }


        if(filters.getTypeFilter() != null) {
            query.append(" AND type LIKE ?");
            params.add("%" + filters.getTypeFilter() + "%");
        }


        if(filters.getMinRating() != null) {
            query.append(" AND rating >= ?");
            params.add(filters.getMinRating());
        }


        if(filters.hasTrainer() != null) {
            query.append(" AND personaltrainer = ?");
            params.add(filters.hasTrainer() ? 1 : 0);
        }


        if(filters.getMaxSubscription() != null) {
            query.append(" AND monthlysubscription <= ?");
            params.add(filters.getMaxSubscription());
        }
    }




    private GymProfile mapResultToGymProfile(ResultSet rs) throws SQLException {
        return new GymProfile(
                rs.getInt("id"),
                rs.getString("gymname"),
                rs.getString("city"),
                rs.getString("type"),
                rs.getDouble("monthlysubscription"),
                rs.getDouble("rating"),
                rs.getBoolean("personalTrainer")
        );
    }
}