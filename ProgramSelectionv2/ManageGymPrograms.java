import java.util.Scanner;

public class ManageGymPrograms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] programDetails = selectGymPrograms(scanner);
        DBManager.storeSubscription(programDetails[0], Integer.parseInt(programDetails[1]));


            SpecificProgramScreen.showGymProgram(programDetails);
            boolean success = SpecificProgramScreen.personalDataInsertion(scanner);
            if (success) {
                GymProgramConfirmationScreen.displayConfirmation();
            } else {
                NotEnoughDataScreen.dislplay();
            }

        scanner.close();
    }
    
    public static String[] selectGymPrograms(Scanner scanner) {
        return GymProgramsScreen.GymProgramSelect(scanner);
    }
}