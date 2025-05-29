import java.util.Scanner;

public class GymProgramsScreen {

    public static String[] GymProgramSelect(Scanner scanner){
        return showProgramResults(scanner);
    }

    public static String[] showProgramResults(Scanner scanner){
        String selectedProgram = "";
        int duration = 0;

        // Program selection.
        while (selectedProgram.isEmpty()) {
            System.out.println("\n=== GYM PROGRAMS ===");
            System.out.println("\nSelect a program:");
            System.out.println("1. Zumba");
            System.out.println("2. Yoga");
            System.out.println("3. CrossFit");
            System.out.println("4. Weights");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Thank you! Goodbye!");
                    return null;
                case 1:
                    selectedProgram = "Zumba";
                    break;
                case 2:
                    selectedProgram = "Yoga";
                    break;
                case 3:
                    selectedProgram = "CrossFit";
                    break;
                case 4:
                    selectedProgram = "Weights";
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        // Duration selection.
        while (duration == 0) {
            System.out.println("\nSelect subscription duration for the program " + selectedProgram + ":");
            System.out.println("1. 1 year");
            System.out.println("2. 1 month");
            System.out.println("3. 3 months");
            System.out.println("4. 6 months");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    duration = 12;
                    break;
                case 2:
                    duration = 1;
                    break;
                case 3:
                    duration = 3;
                    break;
                case 4:
                    duration = 6;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        return new String[]{selectedProgram, String.valueOf(duration)}; // Return values for display.
    }
}
