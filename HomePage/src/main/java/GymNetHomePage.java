
import org.example.GymOwnerInitialScreenFD;

import java.util.Scanner;

public class GymNetHomePage {

    static Scanner scanner = new Scanner(System.in);

    public static void main (String[]args){
        StartingMenu();
        int atrbt = scanner.nextInt(); // Διάβασμα επιλογής attribute.

        if (atrbt == 1) {
            GymOwner();
        } else if (atrbt == 2) {
            GymClients();
        }
    }

    public static void GymOwner(){
        System.out.println("You are a Gym Owner!"); // Λειτουργία ως Gym Owner.

        System.out.println("Please select your option:  ");
        // Μενού λειτουργιών.
        System.out.println("1. Financial Dashboard"); // Κλήση Financial Dashboard Feature.
        System.out.println("2. Time Schedule"); // Κλήση Time Schedule Feature.
        System.out.println("3. Subscription Dasboard"); // Κλήση Subscription Dashboard Feature.
        System.out.println("4. Exit");

        int choiceGO = scanner.nextInt(); // Μεταβλητή αποθήκευσης επιλογής λειτουργίας.

        switch (choiceGO){
            case 1:
                GymOwnerInitialScreenFD.selectsFinancialDashBoard();
                break;
            case 2:
                GymOwnerInitialScreenS.selectsTimeScheduleS();
                break;
            case 3:
                SubDashBoardGeneral.selectsSubscriptionDashboard();
                break;
            default:
                System.out.println("Thank you for using GymNet!");
                break;
        }
    }
    public static void GymClients(){
        System.out.println("You are a Gym Client!");// Λειτουργία ως Gym Client.

        System.out.println("Please select your option:  ");
        // Μενού λειτουργιών.
        System.out.println("1. Search for a Gym");
        System.out.println("2. Weight Tracking");
        System.out.println("3. Check In");
        System.out.println("4. Program Selection");
        System.out.println("5. Gym Rating");
        System.out.println("6. Personal Progress");
        System.out.println("7. Personal Training");
        System.out.println("8. Exit");

        int choiceGC = scanner.nextInt(); // Μεταβλητή αποθήκευσης επιλογής λειτουργίας.

        switch (choiceGC) {
            case 1:
                GymClientSWF.choosesGymSearch(); // Κλήση Search With filters Feature.
                break;
            case 2:
                ManageWeightTracking.choosesWeightTracking(); // Κλήση Weight Tracking Feature.
                break;
            case 3:
                GymClientCI.choosesCheckIn(); // Κλήση CheckIn Feature.
                break;
            case 4:
                ManageGymPrograms.choosesGymProgram(); // Κλήση Program Selection Feature.
                break;
            case 5:
                GymClientGR.gymProfileSelect(); // Κλήση Rating Feature.
                break;
            case 6:
                 GymClientInitialScreenPP.PersonalProgress();// Κλήση Personal Progress Feature.
                break;
            case 7:
                PersonalTrainersScreen.choosesPersonalTraining();// Κλήση Personal Trainer Feature.
                break;
            default:
                System.out.println("Thank you for using GymNet!");
                break;

        }
    }
    public static void StartingMenu(){
        // Αρχικοί Τίτλοι & Επιλογή attribute.
        System.out.println("Welcome to GymNet!");
        System.out.println("Please select your attribute:  ");
        System.out.println("1. Gym Owner");
        System.out.println("2. Gym Client");
    }
}
