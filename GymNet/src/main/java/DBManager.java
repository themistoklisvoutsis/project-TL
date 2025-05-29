import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/GymNet?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Athens";

    private static final String USER = "root";
    private static final String PASSWORD = "1234ceid";

    // Connection instance
    private static Connection connection = null;

    /**
     * Legacy check-in manager (CI)
     */
    public DBManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found: " + e.getMessage());
        }
    }


    public static boolean isClientRegistered(String qrCode) {
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

    public static boolean hasCheckedInToday(String qrCode) {
        String query = "SELECT last_check_in FROM clients WHERE qr_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, qrCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Timestamp lastCheckIn = rs.getTimestamp("last_check_in");
                return isSameDay(new java.util.Date(), lastCheckIn);
            }
        } catch (SQLException e) {
            System.err.println("Error checking last check-i: " + e.getMessage());
        }
        return false;
    }

    public static void updateCheckIn(String qrCode) {
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

    private static boolean isSameDay(java.util.Date date, Timestamp timestamp) {
        java.util.Calendar cal1 = java.util.Calendar.getInstance();
        java.util.Calendar cal2 = java.util.Calendar.getInstance();
        cal1.setTime(date);
        cal2.setTime(timestamp);

        return cal1.get(java.util.Calendar.YEAR) == cal2.get(java.util.Calendar.YEAR)
                && cal1.get(java.util.Calendar.DAY_OF_YEAR) == cal2.get(java.util.Calendar.DAY_OF_YEAR);
    }

    /**
     * Shared connection access (FD, PT, PS, WT)
     */
    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return connection;
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found: " + e.getMessage(), e);
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

    /**
     * Financial history search
     */
    public static List<String> searchForTimeDomain(Date from, Date to) {
        List<String> results = new ArrayList<>();
        String query = "SELECT * FROM financial_history WHERE check_date BETWEEN ? AND ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, from);
            stmt.setDate(2, to);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String row = "📌 ID: " + rs.getLong("id") +
                            ", Gym ID: " + rs.getLong("Gym_Id") +
                            ", Ποσό: " + rs.getDouble("Fdata") + "€" +
                            ", Ημερομηνία: " + rs.getDate("check_date");
                    results.add(row);
                }
            }

        } catch (SQLException e) {
            results.add("❌ Σφάλμα βάσης: " + e.getMessage());
        }

        return results;
    }

    /**
     * Membership & feedback
     */
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

    /**
     * Report & query utilities
     */
    public String queryCheckIn(int clientId, String timePeriod) {
        StringBuilder results = new StringBuilder();
        String query = "SELECT check_in_time FROM check_ins WHERE client_id = ? AND DATE(check_in_time) BETWEEN ? AND ?";
        String[] dates = timePeriod.split(" to ");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, clientId);
            stmt.setString(2, dates[0]);
            stmt.setString(3, dates[1]);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                results.append(resultSet.getString("check_in_time")).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results.toString();
    }

    public static void queryPersonalTrainerSchedule(String trainerName) {
        try (Connection conn = getConnection()) {
            String sql = "SELECT DayOfWeek, StartTime, EndTime " +
                    "FROM TrainerSchedule " +
                    "WHERE TrainerName = ? AND IsAvailable = true " +
                    "ORDER BY FIELD(DayOfWeek, 'Δευτέρα', 'Τρίτη', 'Τετάρτη', 'Πέμπτη', 'Παρασκευή', 'Σάββατο', 'Κυριακή'), StartTime";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, trainerName);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("\nΠρόγραμμα γυμναστή: " + trainerName);
            System.out.println("----------------------------------------");
            while (rs.next()) {
                System.out.printf("%s: %s - %s\n",
                        rs.getString("DayOfWeek"), rs.getString("StartTime"), rs.getString("EndTime"));
            }
        } catch (SQLException e) {
            System.out.println("Σφάλμα κατά την αναζήτηση προγράμματος: " + e.getMessage());
        }
    }

    public static boolean storeSubscription(String fullName, int age, String gender,
                                            int height, int weight, boolean hasInjuries,
                                            String injuries, String trainerName, String programType,
                                            String startDate, int duration, double cost) {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            String personalSql = "INSERT INTO PersonalInfo (FullName, Age, Gender, Height, Weight, HasInjuries, Injuries) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt1 = conn.prepareStatement(personalSql);
            pstmt1.setString(1, fullName);
            pstmt1.setInt(2, age);
            pstmt1.setString(3, gender);
            pstmt1.setInt(4, height);
            pstmt1.setInt(5, weight);
            pstmt1.setBoolean(6, hasInjuries);
            pstmt1.setString(7, injuries);
            int res1 = pstmt1.executeUpdate();
            pstmt1.close();

            String subSql = "INSERT INTO PersonalTrainingSubscriptions (CustomerName, TrainerName, ProgramType, StartDate, Duration, Cost) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt2 = conn.prepareStatement(subSql);
            pstmt2.setString(1, fullName);
            pstmt2.setString(2, trainerName);
            pstmt2.setString(3, programType);
            pstmt2.setString(4, startDate);
            pstmt2.setInt(5, duration);
            pstmt2.setDouble(6, cost);
            int res2 = pstmt2.executeUpdate();
            pstmt2.close();

            if (res1 > 0 && res2 > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
        } catch (SQLException e) {
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) { System.out.println("Rollback error: " + ex.getMessage()); }
            System.out.println("Σφάλμα κατά την αποθήκευση: " + e.getMessage());
            return false;
        } finally {
            try { if (conn != null) conn.setAutoCommit(true); } catch (SQLException e) { System.out.println("AutoCommit reset error: " + e.getMessage()); }
        }
    }

    public static boolean storeSubscription(String programType, int months) {
        if (!programType.equals("Ζούμπα") && !programType.equals("Γιόγκα") && !programType.equals("Κροσφιτ") && !programType.equals("Βάρη")) {
            System.out.println("Μη έγκυρο πρόγραμμα!");
            return false;
        }
        if (months != 1 && months != 3 && months != 6 && months != 12) {
            System.out.println("Μη έγκυρη διάρκεια συνδρομής!");
            return false;
        }
        String sql = "INSERT INTO subscriptions (program_type, duration_months) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, programType);
            stmt.setInt(2, months);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Σφάλμα κατά την αποθήκευση της συνδρομής: " + e.getMessage());
            return false;
        }
    }

    /**
     * Gym profile filters
     */
    public List<GymProfileScreen.GymProfile> filterSearchGymProfile(GymSearchScreen filters) {
        List<GymProfileScreen.GymProfile> results = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM gymlist WHERE 1=1");
        List<Object> params = new ArrayList<>();
        prepareQuery(filters, query, params);
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {
            setParameters(stmt, params);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(mapResultToGymProfile(rs));
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return results;
    }

    private void prepareQuery(GymSearchScreen filters, StringBuilder query, List<Object> params) {
        if (filters.getNameFilter() != null) {
            query.append(" AND gymname LIKE ?");
            params.add("%" + filters.getNameFilter() + "%");
        }
        if (filters.getCityFilter() != null) {
            query.append(" AND city = ?");
            params.add(filters.getCityFilter());
        }
        if (filters.getTypeFilter() != null) {
            query.append(" AND type LIKE ?");
            params.add("%" + filters.getTypeFilter() + "%");
        }
        if (filters.getMinRating() != null) {
            query.append(" AND rating >= ?");
            params.add(filters.getMinRating());
        }
        if (filters.hasTrainer() != null) {
            query.append(" AND personaltrainer = ?");
            params.add(filters.hasTrainer() ? 1 : 0);
        }
        if (filters.getMaxSubscription() != null) {
            query.append(" AND monthlysubscription <= ?");
            params.add(filters.getMaxSubscription());
        }
    }

    private void setParameters(PreparedStatement stmt, List<Object> params) throws SQLException {
        for (int i = 0; i < params.size(); i++) {
            stmt.setObject(i + 1, params.get(i));
        }
    }

    private GymProfileScreen.GymProfile mapResultToGymProfile(ResultSet rs) throws SQLException {
        return new GymProfileScreen.GymProfile(
                rs.getInt("id"),
                rs.getString("gymname"),
                rs.getString("city"),
                rs.getString("type"),
                rs.getDouble("monthlysubscription"),
                rs.getDouble("rating"),
                rs.getBoolean("personalTrainer")
        );
    }

    /**
     * Static datasets
     */
    public static List<String> querySubsPerProgram() {
        return java.util.Arrays.asList(
                "John / Statham / Yoga / 3 months",
                "Giorgos / Aslanidis / Cardio / 3 months",
                "Panagiotis / Apostolakis / Yoga / 6 months",
                "John / Panagiotopoulos / Weightlifting / 6 months",
                "Maria / Papadopoulou / Yoga / 3 months",
                "Ioannis / Tsigos / Cardio / 1 year",
                "John / Statham / Yoga / 3 months",
                "Spyros / Ioannou / Pilates / 3 months",
                "Panagiotis / Apostolopoulos / Weightlifting / 6 months",
                "John / Anderson / Yoga / 3 months",
                "Giorgos / Spyrou / Cardio / 3 months",
                "Spyros / Papadakis / Cardio / 6 months"
        );
    }

    public static List<String> queryCheckInsPerHour() {
        return java.util.Arrays.asList(
                "9:30 / 3",
                "10:00 / 2",
                "13:00 / 4 ",
                "14:00 / 1",
                "14:30 / 1",
                "18:30 / 1"
        );
    }

    /**
     * BMI storage & history
     */
    public static boolean storeResult(int userId, double bmi) {
        String query = "INSERT INTO Bmi_results (userId, Bmi_value, Measurement_Date) VALUES (?, ?, NOW())";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.setDouble(2, bmi);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void bodyMassHistorySearch() {
        String query = "SELECT userId, bmi_value, measurement_date FROM bmi_results ORDER BY measurement_date DESC";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                System.out.println("\nΔεν βρέθηκαν καταχωρήσεις BMI στη βάση δεδομένων.");
                return;
            }

            System.out.println("\nΙστορικό μετρήσεων BMI:");
            System.out.println("------------------------");

            while (rs.next()) {
                System.out.printf("Χρήστης: %d | BMI: %.2f | Ημερομηνία: %s%n",
                        rs.getInt("Client_Id"), rs.getDouble("Bmi_value"), rs.getTimestamp("Measurement_Date"));
            }
        } catch (SQLException e) {
            System.out.println("Σφάλμα κατά την ανάκτηση του ιστορικού BMI: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            getConnection();
            // Example invocation
        } catch (SQLException e) {
            System.out.println("❌ Σφάλμα κατά τη σύνδεση: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
