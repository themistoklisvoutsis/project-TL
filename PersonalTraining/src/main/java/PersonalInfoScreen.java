import java.util.Scanner;

public class PersonalInfoScreen {
    private static Scanner scanner = new Scanner(System.in);
    private static String fullName; // βεβαιωθείτε ότι αυτή η μεταβλητή είναι static
    private static String gender, injuries = "";  // αρχικοποίηση ως κενό string
    private static int age, height, weight;
    private static boolean hasInjuries;

    public static void showPersonalInfoScreen() {
        System.out.println("\nΣυμπλήρωση Προσωπικών Στοιχείων");
        System.out.println("----------------------------------------");

        scanner.nextLine();

        if (!checkPersonalInfoFillIn()) {
            System.out.println("Η συμπλήρωση των στοιχείων ακυρώθηκε.");
            return;
        }

        if (!checkPersonalInfoValidation()) {
            System.out.println("Παρακαλώ διορθώστε τα μη έγκυρα στοιχεία.");
            return;
        }
    }

    // Προσθήκη νέας μεθόδου για την αποθήκευση συνδρομής
    public static boolean storeTrainerSubscription(String trainerName) {
        boolean stored = DBManagerPT.storeSubscription(
            fullName, age, gender, height, weight, hasInjuries,
            (injuries != null ? injuries : ""),
            trainerName,
            "Προσωπικές προπονήσεις",
            java.time.LocalDate.now().toString(),
            1,
            50.0
        );

        if (stored) {
            System.out.println("Τα στοιχεία καταχωρήθηκαν επιτυχώς!");
        } else {
            System.out.println("Υπήρξε πρόβλημα κατά την καταχώρηση των στοιχείων.");
        }
        return stored;
    }


    public static boolean checkPersonalInfoFillIn() {
        try {
            System.out.print("Εισάγετε το ονοματεπώνυμό σας: ");
            fullName = scanner.nextLine();
            if (fullName.trim().isEmpty()) {
                System.out.println("Το ονοματεπώνυμο είναι υποχρεωτικό πεδίο.");
                return false;
            }
            
            System.out.print("Εισάγετε την ηλικία σας: ");
            String ageInput = scanner.nextLine();
            if (ageInput.trim().isEmpty()) {
                System.out.println("Η ηλικία είναι υποχρεωτικό πεδίο.");
                return false;
            }
            age = Integer.parseInt(ageInput);
            
            System.out.print("Εισάγετε το φύλο σας (A/Θ): ");
            gender = scanner.nextLine().toUpperCase();
            if (!gender.equals("Α") && !gender.equals("Θ")) {
                System.out.println("Το φύλο πρέπει να είναι 'Α' ή 'Θ'.");
                return false;
            }
            
            System.out.print("Εισάγετε το ύψος σας (σε εκατοστά): ");
            String heightInput = scanner.nextLine();
            if (heightInput.trim().isEmpty()) {
                System.out.println("Το ύψος είναι υποχρεωτικό πεδίο.");
                return false;
            }
            height = Integer.parseInt(heightInput);
            
            System.out.print("Εισάγετε το βάρος σας (σε κιλά): ");
            String weightInput = scanner.nextLine();
            if (weightInput.trim().isEmpty()) {
                System.out.println("Το βάρος είναι υποχρεωτικό πεδίο.");
                return false;
            }
            weight = Integer.parseInt(weightInput);
            
            System.out.print("Έχετε κάποιο ιστορικό τραυματισμών; (Ν/Ο): ");
            String injuryInput = scanner.nextLine().toUpperCase();
            if (!injuryInput.equals("Ν") && !injuryInput.equals("Ο")) {
                System.out.println("Η απάντηση πρέπει να είναι 'Ν' ή 'Ο'.");
                return false;
            }
            hasInjuries = injuryInput.equals("Ν");
            
            if (hasInjuries) {
                System.out.print("Παρακαλώ περιγράψτε τους τραυματισμούς σας: ");
                injuries = scanner.nextLine();
                if (injuries.trim().isEmpty()) {
                    System.out.println("Η περιγραφή τραυματισμών είναι υποχρεωτική εφόσον έχετε ιστορικό.");
                    return false;
                }
            }
            
            return true;
            
        } catch (NumberFormatException e) {
            System.out.println("Παρακαλώ εισάγετε έγκυρους αριθμούς για ηλικία, ύψος και βάρος.");
            return false;
        }
    }
    
    public static boolean checkPersonalInfoValidation() {
        boolean isValid = true;
        
        // Έλεγχος μήκους και μορφής ονόματος
        if (fullName.length() < 5 || !fullName.contains(" ")) {
            System.out.println("Το ονοματεπώνυμο πρέπει να περιέχει όνομα και επώνυμο (τουλάχιστον 5 χαρακτήρες).");
            isValid = false;
        }
        
        // Έλεγχος ηλικίας
        if (age < 16 || age > 100) {
            System.out.println("Η ηλικία πρέπει να είναι μεταξύ 16 και 100 ετών.");
            isValid = false;
        }
        
        // Έλεγχος ύψους
        if (height < 140 || height > 220) {
            System.out.println("Το ύψος πρέπει να είναι μεταξύ 140 και 220 εκατοστών.");
            isValid = false;
        }
        
        // Έλεγχος βάρους
        if (weight < 40 || weight > 200) {
            System.out.println("Το βάρος πρέπει να είναι μεταξύ 40 και 200 κιλών.");
            isValid = false;
        }
        
        // Έλεγχος περιγραφής τραυματισμών
        if (hasInjuries && (injuries == null || injuries.length() < 10)) {
            System.out.println("Παρακαλώ δώστε μια πιο λεπτομερή περιγραφή των τραυματισμών σας (τουλάχιστον 10 χαρακτήρες).");
            isValid = false;
        }
        
        return isValid;
    }
    
    public static String getFullName() {
        return fullName;
    }
}