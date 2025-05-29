public class ManageWeightTracking {
    private static final int userId = 1; // Προσωρινή σταθερή τιμή για τον χρήστη.

    public static void choosesWeightTracking() {

        // Επιλογή Υπολογισμού Δείκτη Μάζας Σώματος.
        WeightTrackingScreen.showWeightTrackingScreen();
        int choice = WeightTrackingScreen.categorySelection();

        if (choice == 1) {
            // Υπολογισμός BMI.
            double[] data = WeightCalcScreen.showWeightCalcScreen();
            if (data != null) {
                double bmi = WeightCalc.performCalc(data[0], data[1]);
                WeightResultScreen.showWeightResult(bmi);
                // Αποθήκευση BMI στην βάση δεδομένων.
                boolean success = DBManager.storeResult(userId, bmi);
                if (success) {
                    System.out.println("BMI has successfully been stored!");
                } else {
                    System.out.println("There has been an issue with BMI storage.");
                }
            }
        } else if (choice == 2) { // Προβολή Ιστορικού.
            WeightHistoryScreen.display(); // Προβολή οθόνης Ιστορικού.
            DBManager.bodyMassHistorySearch(); // Εμφάνιση μετρήσεων bmi.
        }
    }
}