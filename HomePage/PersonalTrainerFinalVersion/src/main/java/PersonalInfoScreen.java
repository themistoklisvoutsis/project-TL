import java.util.Scanner;
 public class PersonalInfoScreen {
     private static Scanner scanner = new Scanner(System.in);
     private static String fullName;
     private static String gender, injuries = "";
     private static int age, height, weight;
     private static boolean hasInjuries;

     public static boolean checkPersonalInfoFillIn() {
         try {
             System.out.print("Εισάγετε το ονοματεπώνυμό σας: ");
             fullName = scanner.nextLine();
             if (fullName.trim().isEmpty()) {
                 NotEnoughDataScreen.display1();
                 return false;
             }

             System.out.print("Εισάγετε την ηλικία σας: ");
             String ageInput = scanner.nextLine();
             if (ageInput.trim().isEmpty()) {
                 NotEnoughDataScreen.display1();
                 return false;
             }
             age = Integer.parseInt(ageInput);

             System.out.print("Εισάγετε το φύλο σας (A/Θ): ");
             gender = scanner.nextLine().toUpperCase();
             if (!gender.equals("Α") && !gender.equals("Θ")) {
                 NotEnoughDataScreen.display1();
                 return false;
             }

             System.out.print("Εισάγετε το ύψος σας (σε εκατοστά): ");
             String heightInput = scanner.nextLine();
             if (heightInput.trim().isEmpty()) {
                 NotEnoughDataScreen.display1();
                 return false;
             }
             height = Integer.parseInt(heightInput);

             System.out.print("Εισάγετε το βάρος σας (σε κιλά): ");
             String weightInput = scanner.nextLine();
             if (weightInput.trim().isEmpty()) {
                 NotEnoughDataScreen.display1();
                 return false;
             }
             weight = Integer.parseInt(weightInput);

             System.out.print("Έχετε κάποιο ιστορικό τραυματισμών; (Ν/Ο): ");
             String injuryInput = scanner.nextLine().toUpperCase();
             if (!injuryInput.equals("Ν") && !injuryInput.equals("Ο")) {
                 NotEnoughDataScreen.display1();
                 return false;
             }
             hasInjuries = injuryInput.equals("Ν");

             if (hasInjuries) {
                 System.out.print("Παρακαλώ περιγράψτε τους τραυματισμούς σας: ");
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
             System.out.println("Το ονοματεπώνυμο πρέπει να περιέχει όνομα και επώνυμο (τουλάχιστον 5 χαρακτήρες).");
             isValid = false;
         }

         if (age < 16 || age > 100) {
             System.out.println("Η ηλικία πρέπει να είναι μεταξύ 16 και 100 ετών.");
             isValid = false;
         }

         if (height < 140 || height > 220) {
             System.out.println("Το ύψος πρέπει να είναι μεταξύ 140 και 220 εκατοστών.");
             isValid = false;
         }

         if (weight < 40 || weight > 200) {
             System.out.println("Το βάρος πρέπει να είναι μεταξύ 40 και 200 κιλών.");
             isValid = false;
         }

         if (hasInjuries && (injuries == null || injuries.length() < 10)) {
             System.out.println("Παρακαλώ δώστε μια πιο λεπτομερή περιγραφή των τραυματισμών σας (τουλάχιστον 10 χαρακτήρες).");
             isValid = false;
         }

         return isValid;
     }

     public static void showPersonalInfoScreen() {
         System.out.println("\nΣυμπλήρωση Προσωπικών Στοιχείων");
         System.out.println("----------------------------------------");

         scanner.nextLine(); // Καθαρισμός buffer

         if (!checkPersonalInfoFillIn()) {
             System.out.println("Η συμπλήρωση των στοιχείων ακυρώθηκε.");
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