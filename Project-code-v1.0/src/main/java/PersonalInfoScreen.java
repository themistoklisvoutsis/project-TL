import java.util.Scanner;
public class PersonalInfoScreen {
    private static Scanner scanner = new Scanner(System.in);
    private static String fullName;
    private static String gender, injuries = "";
    private static int age, height, weight;
    private static boolean hasInjuries;

    public static boolean checkPersonalInfoFillIn() {
        try {
            System.out.print("Insert your full name (Firstname Lastname) : ");
            fullName = scanner.nextLine();
            if (fullName.trim().isEmpty()) {
                NotEnoughDataScreen.display1();
                return false;
            }

            System.out.print("Insert your age: ");
            String ageInput = scanner.nextLine();
            if (ageInput.trim().isEmpty()) {
                NotEnoughDataScreen.display1();
                return false;
            }
            age = Integer.parseInt(ageInput);

            System.out.print("Insert your sex (Male/Female): ");
            gender = scanner.nextLine().toUpperCase();
            if (!gender.equals("Α") && !gender.equals("Θ")) {
                NotEnoughDataScreen.display1();
                return false;
            }

            System.out.print("Insert your height (in cm): ");
            String heightInput = scanner.nextLine();
            if (heightInput.trim().isEmpty()) {
                NotEnoughDataScreen.display1();
                return false;
            }
            height = Integer.parseInt(heightInput);

            System.out.print("Insert your weight (in kilos): ");
            String weightInput = scanner.nextLine();
            if (weightInput.trim().isEmpty()) {
                NotEnoughDataScreen.display1();
                return false;
            }
            weight = Integer.parseInt(weightInput);

            System.out.print("Have you got a history of serious injuries or rare diseases; (Ν/Ο): ");
            String injuryInput = scanner.nextLine().toUpperCase();
            if (!injuryInput.equals("Ν") && !injuryInput.equals("Ο")) {
                NotEnoughDataScreen.display1();
                return false;
            }
            hasInjuries = injuryInput.equals("Ν");

            if (hasInjuries) {
                System.out.print("Please describe your injuries (10 words maximum): ");
                injuries = scanner.nextLine();
                if (injuries.trim().isEmpty()) {
                    NotEnoughDataScreen.display1();
                    return false;
                }
            }

            return true;

        } catch (NumberFormatException e) {
            NotEnoughDataScreen.display1();
            return false;
        }
    }

    public static boolean checkPersonalInfoValidation() {
        boolean isValid = true;

        if (fullName.length() < 5 || !fullName.contains(" ")) {
            System.out.println("Your full name should consist of your first and last name  (at least 5 characters).");
            isValid = false;
        }

        if (age < 16 || age > 100) {
            System.out.println("Age must be between 16 and 100 years old.");
            isValid = false;
        }

        if (height < 140 || height > 220) {
            System.out.println("Height must be between 140 cm and 220 cm.");
            isValid = false;
        }

        if (weight < 40 || weight > 200) {
            System.out.println("Weight must be between 40 and 200 kilos.");
            isValid = false;
        }

        if (hasInjuries && (injuries == null || injuries.length() < 10)) {
            System.out.println("Please give a more analytical description of your disease/injury history (at least 10 characters).");
            isValid = false;
        }

        return isValid;
    }

    public static void showPersonalInfoScreen() {
        System.out.println("\nPersonal Data Insertion");
        System.out.println("----------------------------------------");

        scanner.nextLine(); // Καθαρισμός buffer

        if (!checkPersonalInfoFillIn()) {
            System.out.println("Data insertion has been cancelled.");
            return;
        }

        if (!checkPersonalInfoValidation()) {
            InvalidDataScreen.display3();
            return;
        }
    }

    public static String getFullName() {
        return fullName;
    }
}