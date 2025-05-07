import java.util.Scanner;

public class GymProgramsScreen {
    
    public static String[] GymProgramSelect(Scanner scanner){
        return showProgramResults(scanner);
    }

    public static String[] showProgramResults(Scanner scanner){
        String selectedProgram = "";
        int duration = 0;

        // Επιλογή προγράμματος.
        while (selectedProgram.isEmpty()) {
            System.out.println("\n=== ΠΡΟΓΡΑΜΜΑΤΑ ΓΥΜΝΑΣΤΗΡΙΟΥ ===");
            System.out.println("\nΕπιλέξτε πρόγραμμα:");
            System.out.println("1. Ζούμπα");
            System.out.println("2. Γιόγκα");
            System.out.println("3. Κροσφιτ");
            System.out.println("4. Βάρη");
            System.out.println("0. Έξοδος");

            System.out.print("\nΕισάγετε την επιλογή σας: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Ευχαριστούμε! Αντίο!");
                    return null;
                case 1:
                    selectedProgram = "Ζούμπα";
                    break;
                case 2:
                    selectedProgram = "Γιόγκα";
                    break;
                case 3:
                    selectedProgram = "Κροσφιτ";
                    break;
                case 4:
                    selectedProgram = "Βάρη";
                    break;
                default:
                    System.out.println("Μη έγκυρη επιλογή! Παρακαλώ προσπαθήστε ξανά.");
            }
        }

        // Επιλογή διάρκειας.
        while (duration == 0) {
            System.out.println("\nΕπιλέξτε διάρκεια συνδρομής για το πρόγραμμα " + selectedProgram + ":");
            System.out.println("1. 1 χρόνος");
            System.out.println("2. 1 μήνας");
            System.out.println("3. 3 μήνες");
            System.out.println("4. 6 μήνες");

            System.out.print("\nΕισάγετε την επιλογή σας: ");
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
                    System.out.println("Μη έγκυρη επιλογή! Παρακαλώ προσπαθήστε ξανά.");
            }
        }

        return new String[]{selectedProgram, String.valueOf(duration)}; // Επιστροφή τιμών για εμφάνιση.
    }
}