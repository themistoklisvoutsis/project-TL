import java.util.Scanner;

public class WeightTrackingScreen{
    public static int categorySelection(){
        // Διάβασμα μεταβλητής για μετάβαση στην επόμενη λειτουργία.
        Scanner input=new Scanner(System.in);
        int choice= input.nextInt();

        return choice; // Επιστροφή μεταβλητής choice.
    }

    // Επιλογές χρήστη.
    public static void showWeightTrackingScreen(){
        System.out.println("1.Υπολογισμός Δείκτη Μάζας Σώματος.");
        System.out.println("2.Προβολή Ιστορικού.");
    }
}
