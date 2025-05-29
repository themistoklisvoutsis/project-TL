import java.util.Scanner;

public class WeightCalcScreen {
    public static double[] showWeightCalcScreen() {
        return dataInsertion();
    }

    public static double[] dataInsertion() {
        // Εισαγωγή Στοιχείων.
        Scanner input = new Scanner(System.in);
        System.out.println("Insert your weight (kg) :");
        double weight = input.nextDouble();
        System.out.println("Insert your height (cm) :");
        double height = input.nextDouble();
        // Έλεγχος δεδομένων.
        if (checkBodyData(weight, height)) {
            return new double[]{weight, height};
        } else {
            NotValidDataScreen.displayError();
            return null;
        }
    }

    // Μέθοδος ελέγχου εγκυρότητας δεδομένων.
    public static boolean checkBodyData(double weight, double height) {
        boolean validWeight = weight >= 30 && weight <= 300;
        boolean validHeight = height >= 1.00 && height <= 2.50;
        return validWeight && validHeight;
    }
}