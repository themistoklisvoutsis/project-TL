import java.util.Scanner;
public class PersonalTrainersScreen {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        showPersonalTrainers(); // εμφάνιση γυμναστών.
        String selected = selectsPersonalTrainer(); // Επιλογή γυμναστή.
        ManagePersonalTrainingPrograms.searchPersonalTrainerSchedule(selected); // Εμφάνιση προγραμμάτων.
        PersonalProgramFilterScreen.adjustFilters(); // Εμφάνιση φίλτρων.
        PersonalInfoScreen.showPersonalInfoScreen(); // Συμπλήρωση στοιχείων.
        PersonalTrainingConfirmationScreen.showconfScreen(); //DBManagerPT.storeSubscription();


    }
    public static void showPersonalTrainers() {

            System.out.println("\nΠροσωπικοί Γυμναστές:");
            System.out.println("----------------------------------------");
            System.out.println("1. Θέμης Παπόγλου");
            System.out.println("2. Κυριάκος Βούτης");
            System.out.println("3. Κωνσταντής Βασιλείου");

    }
    public static String selectsPersonalTrainer() {
        String trainer = "";
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("\nΠαρακαλώ επιλέξτε έναν γυμναστή (1-3): ");
            try {
                int trainerChoice = input.nextInt();

                switch (trainerChoice) {
                    case 1:
                        trainer = "Θέμης Παπόγλου";
                        validChoice = true;
                        break;
                    case 2:
                        trainer = "Κυριάκος Βούτης";
                        validChoice = true;
                        break;
                    case 3:
                        trainer = "Κωνσταντής Βασιλείου";
                        validChoice = true;
                        break;
                    default:
                        System.out.println("Μη έγκυρη επιλογή. Παρακαλώ επιλέξτε έναν αριθμό από το 1 έως το 3.");
                }
            } catch (Exception e) {
                System.out.println("Παρακαλώ εισάγετε έναν έγκυρο αριθμό.");
                input.nextLine(); // Καθαρισμός του buffer
            }
        }

        System.out.println("\nΕπιλέξατε τον γυμναστή: " + trainer);
        return trainer;
    }
}

