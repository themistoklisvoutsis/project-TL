import java.util.Scanner;

public class PersonalProgramFilterScreen {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String[][] VALID_OPTIONS = {
            {"Προσωπικές προπονήσεις", "CrossFit", "Yoga", "Pilates", "TRX", "Λειτουργική προπόνηση", "Όλα"},
            {"Δευτέρα", "Τρίτη", "Τετάρτη", "Πέμπτη", "Παρασκευή", "Σάββατο", "Κυριακή", "Όλες"},
            {"Πρωί", "Μεσημέρι", "Απόγευμα", "Όλες"}
    };

    private static final String[] FILTER_NAMES = {
            "Είδος προπόνησης", "Ημέρα", "Χρονική ζώνη"
    };

    public static String[] adjustFilters() {
        String[] selectedFilters = new String[3];

        System.out.println("\nΡύθμιση Φίλτρων:");
        System.out.println("----------------------------------------");

        for (int i = 0; i < FILTER_NAMES.length; i++) {
            System.out.println("\nΕπιλογή για " + FILTER_NAMES[i] + ":");

            // Εμφάνιση διαθέσιμων επιλογών
            for (int j = 0; j < VALID_OPTIONS[i].length; j++) {
                System.out.println("   " + (j + 1) + ". " + VALID_OPTIONS[i][j]);
            }

            // Λήψη επιλογής χρήστη
            boolean validChoice = false;
            while (!validChoice) {
                System.out.print("Επιλέξτε (1-" + VALID_OPTIONS[i].length + "): ");
                try {
                    int choice = scanner.nextInt();
                    if (choice > 0 && choice <= VALID_OPTIONS[i].length) {
                        selectedFilters[i] = VALID_OPTIONS[i][choice - 1];
                        validChoice = true;
                    } else {
                        System.out.println("Παρακαλώ επιλέξτε έναν αριθμό από το 1 έως το " + VALID_OPTIONS[i].length);
                    }
                } catch (Exception e) {
                    System.out.println("Παρακαλώ εισάγετε έναν έγκυρο αριθμό");
                    scanner.nextLine(); // Καθαρισμός buffer
                }
            }
        }

        // Εμφάνιση επιλεγμένων φίλτρων
        System.out.println("\nΕπιλεγμένα φίλτρα:");
        System.out.println("----------------------------------------");
        for (int i = 0; i < selectedFilters.length; i++) {
            System.out.println(FILTER_NAMES[i] + ": " + selectedFilters[i]);
        }

        return selectedFilters;
    }
    public static void showFilterScreen() {
        System.out.println("\nΦίλτρα Προγράμματος Personal Training:");
        System.out.println("----------------------------------------");

        // Εμφάνιση φίλτρων για είδος προπόνησης
        System.out.println("\n1. Είδος προπόνησης:");
        System.out.println("   1. Προσωπικές προπονήσεις");
        System.out.println("   2. CrossFit");
        System.out.println("   3. Yoga");
        System.out.println("   4. Pilates");
        System.out.println("   5. TRX");
        System.out.println("   6. Λειτουργική προπόνηση");
        System.out.println("   7. Όλα");

        // Εμφάνιση φίλτρων για ημέρες
        System.out.println("\n2. Ημέρα:");
        System.out.println("   1. Δευτέρα");
        System.out.println("   2. Τρίτη");
        System.out.println("   3. Τετάρτη");
        System.out.println("   4. Πέμπτη");
        System.out.println("   5. Παρασκευή");
        System.out.println("   6. Σάββατο");
        System.out.println("   7. Κυριακή");
        System.out.println("   8. Όλες");

        // Εμφάνιση φίλτρων για χρονικές ζώνες
        System.out.println("\n3. Χρονική ζώνη:");
        System.out.println("   1. Πρωί (08:00-12:00)");
        System.out.println("   2. Μεσημέρι (12:00-16:00)");
        System.out.println("   3. Απόγευμα (16:00-20:00)");
        System.out.println("   4. Όλες");
    }
    public static boolean checkFilters() {
        String[] currentFilters = adjustFilters();

        if (currentFilters == null || currentFilters.length != 3) {
            InvalidPersonalFilterScreen.display2();
            return false;
        }

        System.out.println("\nΈλεγχος εγκυρότητας φίλτρων:");
        System.out.println("----------------------------------------");
        boolean allValid = true;

        // Έλεγχος κάθε φίλτρου
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