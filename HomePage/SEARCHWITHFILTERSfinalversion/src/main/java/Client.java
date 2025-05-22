


import java.util.List;

public class Client {
    public static void searchWithFilterManager() {
        GymSearchScreen searchScreen = new GymSearchScreen();
        ManageFilters filters = new ManageFilters();
        DBManager dbManager = new DBManager();

        while (true) {
            searchScreen.showGymSearchScreen();
            int choice = searchScreen.getUserChoice();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
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
