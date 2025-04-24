import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String URL = "jdbc:mysql://localhost:3306/gym";
    static final String USER = "root";
    static final String PASSWORD = "ceid123";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String gymname = null;
        String city = null;
        String type = null;
        Double rating = null;
        Boolean personaltrainer = null;
        Double monthlysubscription = null;

        while (true) {
            System.out.println("\n GYM SEARCH MENU ");
            System.out.println("1. Search by city");
            System.out.println("2. Search by type");
            System.out.println("3. Search by rating");
            System.out.println("4. Search by personal trainer availability");
            System.out.println("5. Search by monthly subscription max");
            System.out.println("6. View filtered gyms");
            System.out.println("7. Reset filters");
            System.out.println("8. Search by name");
            System.out.print("Choose a filter: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter city name (ATHENS, PATRA): ");
                    city = scanner.nextLine().toUpperCase();
                    break;
                case 2:
                    System.out.print("Enter gym type (BODYBUILDING, CROSSFIT, PILATES): ");
                    type = scanner.nextLine().toUpperCase();
                    break;
                case 3:
                    System.out.print("Enter minimum rating: ");
                    rating = scanner.nextDouble();
                    scanner.nextLine();  // consume newline
                    break;
                case 4:
                    System.out.print("Do you want a personal trainer? (Yes/No): ");
                    String personalTrainerInput = scanner.nextLine();
                    personaltrainer = personalTrainerInput.equalsIgnoreCase("Yes");
                    break;
                case 5:
                    System.out.print("Enter monthly subscription: ");
                    monthlysubscription = scanner.nextDouble();
                    scanner.nextLine();  // consume newline
                    break;
                case 6:
                    searchGyms(city, type, rating, personaltrainer, monthlysubscription, gymname);
                    break;
                case 7:
                    // Reset all filters
                    gymname = null;
                    city = null;
                    type = null;
                    rating = null;
                    personaltrainer = null;
                    monthlysubscription = null;
                    System.out.println("Filters reset.");
                    break;
                case 8:
                    System.out.println("Enter the gym name to search:");
                    gymname = scanner.nextLine();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void searchGyms(String city, String type, Double rating, Boolean personaltrainer, Double monthlysubscription, String gymname) {
        String query = "SELECT * FROM gymlist WHERE 1=1";

        if (city != null) {
            query += " AND city = ?";
        }
        if (type != null) {
            query += " AND type LIKE ?";
        }
        if (rating != null) {
            query += " AND rating >= ?";
        }
        if (personaltrainer != null) {
            query += " AND personaltrainer = ?";
        }
        if (monthlysubscription != null) {
            query += " AND monthlysubscription <= ?";
        }
        if (gymname != null) {
            query += " AND gymname LIKE ?";
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            int paramIndex = 1;

            if (city != null) {
                stmt.setString(paramIndex++, city);
            }
            if (type != null) {
                stmt.setString(paramIndex++, "%" + type + "%");
            }
            if (rating != null) {
                stmt.setDouble(paramIndex++, rating);
            }
            if (personaltrainer != null) {
                stmt.setInt(paramIndex++, personaltrainer ? 1 : 0);
            }
            if (monthlysubscription != null) {
                stmt.setDouble(paramIndex++, monthlysubscription);
            }
            if (gymname != null) {
                stmt.setString(paramIndex++, "%" + gymname + "%");
            }

            ResultSet rs = stmt.executeQuery();
            displayResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayResults(ResultSet rs) throws SQLException {
        System.out.println("\n-- Search Results --");
        boolean found = false;
        while (rs.next()) {
            found = true;
            System.out.printf("ID: %d | Name: %s | City: %s | Type: %s | Subscription: €%.2f | Rating: %.1f | PT: %s\n",
                    rs.getInt("id"),
                    rs.getString("gymname"),
                    rs.getString("city"),
                    rs.getString("type"),
                    rs.getDouble("monthlysubscription"),
                    rs.getDouble("rating"),
                    rs.getInt("personaltrainer") == 1 ? "Yes" : "No"
            );
        }
        if (!found) {
            System.out.println("No gyms found with the selected filters.");
        }
    }
}
