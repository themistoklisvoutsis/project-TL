import java.util.Scanner;

public class PersonalProgramFilterScreen {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String[][] VALID_OPTIONS = {
            {"Personal Training", "CrossFit", "Yoga", "Pilates", "TRX", "Functional Training", "All"},
            {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "All"},
            {"Morning", "Noon", "Afternoon", "All"}
    };

    private static final String[] FILTER_NAMES = {
            "Training Type", "Day", "Time Zone"
    };

    public static String[] adjustFilters() {
        String[] selectedFilters = new String[3];

        System.out.println("\nFilter Settings:");
        System.out.println("----------------------------------------");

        for (int i = 0; i < FILTER_NAMES.length; i++) {
            System.out.println("\nSelect for " + FILTER_NAMES[i] + ":");

            // Show available options
            for (int j = 0; j < VALID_OPTIONS[i].length; j++) {
                System.out.println("   " + (j + 1) + ". " + VALID_OPTIONS[i][j]);
            }

            // Get user choice
            boolean validChoice = false;
            while (!validChoice) {
                System.out.print("Select (1-" + VALID_OPTIONS[i].length + "): ");
                try {
                    int choice = scanner.nextInt();
                    if (choice > 0 && choice <= VALID_OPTIONS[i].length) {
                        selectedFilters[i] = VALID_OPTIONS[i][choice - 1];
                        validChoice = true;
                    } else {
                        System.out.println("Please select a number between 1 and " + VALID_OPTIONS[i].length);
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a valid number");
                    scanner.nextLine(); // Clear buffer
                }
            }
        }

        // Show selected filters
        System.out.println("\nSelected Filters:");
        System.out.println("----------------------------------------");
        for (int i = 0; i < selectedFilters.length; i++) {
            System.out.println(FILTER_NAMES[i] + ": " + selectedFilters[i]);
        }

        return selectedFilters;
    }
    public static void showFilterScreen() {
        System.out.println("\nPersonal Training Program Filters:");
        System.out.println("----------------------------------------");

        // Show filters for training type
        System.out.println("\n1. Training Type:");
        System.out.println("   1. Personal Training");
        System.out.println("   2. CrossFit");
        System.out.println("   3. Yoga");
        System.out.println("   4. Pilates");
        System.out.println("   5. TRX");
        System.out.println("   6. Functional Training");
        System.out.println("   7. All");

        // Show filters for days
        System.out.println("\n2. Day:");
        System.out.println("   1. Monday");
        System.out.println("   2. Tuesday");
        System.out.println("   3. Wednesday");
        System.out.println("   4. Thursday");
        System.out.println("   5. Friday");
        System.out.println("   6. Saturday");
        System.out.println("   7. Sunday");
        System.out.println("   8. All");

        // Show filters for time zones
        System.out.println("\n3. Time Zone:");
        System.out.println("   1. Morning (08:00-12:00)");
        System.out.println("   2. Noon (12:00-16:00)");
        System.out.println("   3. Afternoon (16:00-20:00)");
        System.out.println("   4. All");
    }
    public static boolean checkFilters() {
        String[] currentFilters = adjustFilters();

        if (currentFilters == null || currentFilters.length != 3) {
            InvalidPersonalFilterScreen.display2();
            return false;
        }

        System.out.println("\nFilter Validity Check:");
        System.out.println("----------------------------------------");
        boolean allValid = true;

        // Check each filter
        for (int i = 0; i < currentFilters.length; i++) {
            boolean isValid = false;
            for (String validOption : VALID_OPTIONS[i]) {
                if (validOption.equals(currentFilters[i])) {
                    isValid = true;
                    break;
                }
            }

            if (!isValid) {
                allValid = false;
                break;
            }
        }

        if (!allValid) {
            InvalidPersonalFilterScreen.display2();
        }

        return allValid;
    }
}
