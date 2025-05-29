
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class GymClientInitialScreen {
    public static void PersonalProgress() {
        System.out.println("Welcome to the Gym Management System!");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your ID number: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();

        GymClientInitialScreen initialScreen = new GymClientInitialScreen();
        PersonalProgressScreen personalProgressScreen = new PersonalProgressScreen();
        initialScreen.choosesPersonalProgress(personalProgressScreen);

        System.out.println("Please enter the time period in the format 'YYYY-MM-DD to YYYY-MM-DD': ");
        String timePeriod = scanner.nextLine();

        ManagePersonalProgress managePersonalProgress = new ManagePersonalProgress();
        personalProgressScreen.selectsTimePeriod(managePersonalProgress, clientId, timePeriod);

        scanner.close();
    }

    public void choosesPersonalProgress(PersonalProgressScreen personalProgressScreen) {
        System.out.println("Navigating to Personal Progress Screen...");
        personalProgressScreen.showPersonalProgressScreen();
    }

    public static void choosesGymSearch() {
        GymSearchScreen searchScreen = new GymSearchScreen();
        GymSearchScreen filters = new GymSearchScreen();
        DBManager dbManager = new DBManager();

        while (true) {

            int choice = GymSearchScreen.showGymSearchScreen();

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
                    List<GymProfileScreen.GymProfile> results = dbManager.filterSearchGymProfile(filters);
                    SearchResultScreen.showResults(results);
                    break;
                case 8:
                    filters.resetFilters();
                    break;
                case 9:
                    System.exit(0);
                default:
                    searchScreen.showGymSearchScreen("Invalid choice");
            }
        }
    }
}
