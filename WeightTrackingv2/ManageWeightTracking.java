public class ManageWeightTracking {
    private static final int userId = 1; // Προσωρινή σταθερή τιμή για τον χρήστη.
    
    public static void main(String[] args) {

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
                    System.out.println("Το BMI αποθηκεύτηκε επιτυχώς!");
                } else {
                    System.out.println("Υπήρξε πρόβλημα κατά την αποθήκευση του BMI.");
                }
            }
        } else if (choice == 2) { // Προβολή Ιστορικού.
            WeightHistoryScreen.display(); // Προβολή οθόνης Ιστορικού.
            DBManager.bodyMassHistorySearch(); // Εμφάνιση μετρήσεων bmi.
        }
    }
}