import org.example.DBManager;
import org.example.GymProfile;
import org.example.GymSearchScreen;
import org.example.ManageFilters;

import java.sql.*;
import java.util.List;

public class GymClient {
    public static void main(String[] args) {
        GymSearchScreen searchScreen = new GymSearchScreen();
        ManageFilters filters = new ManageFilters();
        DBManager dbManager = new DBManager();

        while(true) {
            searchScreen.showGymSearchScreen();
            int choice = searchScreen.getUserChoice();

            switch(choice) {
                case 1: case 2: case 3: case 4: case 5: case 6:
                    filters.adjustFilters(choice, searchScreen);
                    break;
                case 7:
                    List<GymProfile> results = dbManager.filterSearchGymProfile(filters);
                    searchScreen.showResults(results);
                    break;
                case 8:
                    filters.resetFilters();
                    break;
                case 9:
                    System.exit(0);
                default:
                    searchScreen.showError("Invalid choice");
            }
        }
    }
}