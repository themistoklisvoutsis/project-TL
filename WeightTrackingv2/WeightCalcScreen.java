import java.util.Scanner;

public class WeightCalcScreen {
    public static double[] showWeightCalcScreen() {
        return dataInsertion();
    }

    public static double[] dataInsertion() {
        // Εισαγωγή Στοιχείων.
        Scanner input = new Scanner(System.in);
        System.out.println("Πληκτρολόγησε το βάρος σου");
        double weight = input.nextDouble();
        System.out.println("Πληκτρολόγησε το ύψος σου");
        double height = input.nextDouble();
        // Έλεγχος δεδομένων.
        if (checkBodyData(weight, height)) {
            return new double[]{weight, height};
        } else {
            NotValidData.displayError();
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