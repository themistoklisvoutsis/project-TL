
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
        System.out.println("1. Create a new Gym");
        System.out.println("2. View Gyms");
        System.out.println("3. Exit");
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
        System.out.println("6. Exit");

        int choice = scanner.nextInt(); // Μεταβλητή αποθήκευσης επιλογής λειτουργίας.

        switch (choice) {
            case 1:
                Client.searchWithFilterManager(); // Κλήση Search With filters Feature.
                break;
            case 2:
                ManageWeightTracking.WeightTrackingManager(); // Κλήση Weight Tracking Feature.
                break;
            case 3:
                GymClient2.CheckInManager(); // Κλήση CheckIn Feature.
                break;
            case 4:
                ManageGymPrograms.GymProgramsManager(); // Κλήση Program Selection Feature.
                break;
            case 5:
                GymClient.GymRatingsManager(); // Κλήση Rating Feature.
            default:
                System.out.println("Ευχαριστούμε που χρησιμοποιήσατε το GymNet!");
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
