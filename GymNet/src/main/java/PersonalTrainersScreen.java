import java.util.Scanner;
public class PersonalTrainersScreen {
    static Scanner input = new Scanner(System.in);
    public static void choosesPersonalTraining() {

        showPersonalTrainers(); // show trainers
        String selected = selectsPersonalTrainer(); // select trainer
        ManagePersonalTrainingPrograms.searchPersonalTrainerSchedule(selected); // show programs
        PersonalProgramFilterScreen.adjustFilters(); // show filters
        PersonalInfoScreen.showPersonalInfoScreen(); // fill personal info
        PersonalTrainingConfirmationScreen.showconfScreen(); //DBManagerPT.storeSubscription();

    }
    public static void showPersonalTrainers() {

        System.out.println("\nPersonal Trainers:");
        System.out.println("----------------------------------------");
        System.out.println("1. Themis Papoglou");
        System.out.println("2. Kyriakos Voutis");
        System.out.println("3. Konstantis Vasileiou");

    }
    public static String selectsPersonalTrainer() {
        String trainer = "";
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("\nPlease select a trainer (1-3): ");
            try {
                int trainerChoice = input.nextInt();

                switch (trainerChoice) {
                    case 1:
                        trainer = "Themis Papoglou";
                        validChoice = true;
                        break;
                    case 2:
                        trainer = "Kyriakos Voutis";
                        validChoice = true;
                        break;
                    case 3:
                        trainer = "Konstantis Vasileiou";
                        validChoice = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a number between 1 and 3.");
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                input.nextLine(); // Clear buffer
            }
        }

        System.out.println("\nYou selected trainer: " + trainer);
        return trainer;
    }
}

