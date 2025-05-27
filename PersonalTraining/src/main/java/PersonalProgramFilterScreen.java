import java.util.Scanner;
public class PersonalProgramFilterScreen {
    static Scanner scanner = new Scanner(System.in);
    
    private static final String[][] VALID_OPTIONS = {
        {"Προσωπικές προπονήσεις", "CrossFit", "Yoga", "Pilates", "TRX", "Λειτουργική προπόνηση", "Όλα"},
        {"Δευτέρα", "Τρίτη", "Τετάρτη", "Πέμπτη", "Παρασκευή", "Σάββατο", "Κυριακή", "Όλες"},
        {"Πρωί", "Μεσημέρι", "Απόγευμα", "Όλες"}
    };
    
    private static final String[] FILTER_NAMES = {
        "Είδος προπόνησης", "Ημέρα", "Χρονική ζώνη"
    };

    public static String[] showFilterScreen(String selectedTrainer) {
        String[] selectedFilters = new String[3];

        System.out.println("\nΕπιλογή Φίλτρων Αναζήτησης:");
        System.out.println("----------------------------------------");
        
        for (int i = 0; i < FILTER_NAMES.length; i++) {
            System.out.println("\n" + (i + 1) + ". " + FILTER_NAMES[i] + ":");
            for (int j = 0; j < VALID_OPTIONS[i].length; j++) {
                System.out.println("   " + (j + 1) + ". " + VALID_OPTIONS[i][j]);
            }
            System.out.println("Επιλέξτε " + FILTER_NAMES[i].toLowerCase() + " (1-" + VALID_OPTIONS[i].length + "): ");
            
            int choice = scanner.nextInt();
            if (choice > 0 && choice <= VALID_OPTIONS[i].length) {
                selectedFilters[i] = VALID_OPTIONS[i][choice - 1];
            } else {
                selectedFilters[i] = VALID_OPTIONS[i][VALID_OPTIONS[i].length - 1]; // Επιλογή "Όλα/Όλες" ως προεπιλογή
            }
        }
        
        System.out.println("\nΕπιλεγμένα φίλτρα:");
        for (int i = 0; i < selectedFilters.length; i++) {
            System.out.println(FILTER_NAMES[i] + ": " + selectedFilters[i]);
        }
        
        if (!checkFilters(selectedFilters)) {
            System.out.println("Παρακαλώ επιλέξτε έγκυρα φίλτρα.");
            return null;
        }
        
        // Καλούμε την showPersonalInfoScreen μετά την επιλογή των φίλτρων
        PersonalInfoScreen.showPersonalInfoScreen();
        
        return selectedFilters;
    }

    private static boolean checkFilters(String[] filters) {
        // Έλεγχος για null ή κενά φίλτρα
        if (filters == null || filters.length != 3) {
            return false;
        }

        // Έλεγχος κάθε φίλτρου αν είναι έγκυρη επιλογή
        for (int i = 0; i < filters.length; i++) {
            boolean isValid = false;
            for (String validOption : VALID_OPTIONS[i]) {
                if (validOption.equals(filters[i])) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                return false;
            }
        }

        return true;
    }
}