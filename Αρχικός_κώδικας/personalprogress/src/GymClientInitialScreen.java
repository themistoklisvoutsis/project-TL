import java.util.Scanner;

public class GymClientInitialScreen {
    public static void main(String[] args) {
        System.out.println("Welcome to the Gym Management System!");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your Client ID: ");
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
}
