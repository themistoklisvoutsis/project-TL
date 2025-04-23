import java.util.Scanner;

public class WeightTrackingTerminal {
    public static void main(String args[]) {

        // Δήλωση Μεταβλητών.
        double height;
        double weight;
        double bmi;
        int choice;

        System.out.println("Για επιλογή υπολογισμού Δείκτη Μάζας Σώματος πάτα το 1.");
        System.out.println("Για επιλογή εμφάνισης Ιστορικού πάτα το 2.");

        Scanner inputchoice = new Scanner(System.in);
        System.out.println("Δώσε την επιλογή");
        choice = inputchoice.nextInt();

        if (choice == 1) {

            // Διάβασμα και εκτύπωση ύψους.
            Scanner input = new Scanner(System.in);
            System.out.println("Δώσε το ύψος");
            height = input.nextDouble();
            System.out.println("Height is " + height);

            // Διάβασμα και εκτύπωση βάρους.
            Scanner input1 = new Scanner(System.in);
            System.out.println("Δώσε το βάρος");
            weight = input1.nextDouble();
            System.out.println("Wight is " + weight);

            // Υπολογισμός Δείκτη Μάζας Σώματος.
            bmi = weight / (height * height);
            System.out.println("BMI is " + bmi);

        } else if(choice==2){
            System.out.println("Πλήρες Ιστορικό ");
        }
    }
}