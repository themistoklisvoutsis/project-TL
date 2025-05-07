import java.util.Scanner;

public class ManageGymPrograms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] programDetails = selectGymPrograms(scanner);
        SpecificProgramScreen.showGymProgram(programDetails);
        SpecificProgramScreen.personalDataInsertion(scanner);
        scanner.close();
    }
    
    public static String[] selectGymPrograms(Scanner scanner) {
        return GymProgramsScreen.GymProgramSelect(scanner);
    }
}