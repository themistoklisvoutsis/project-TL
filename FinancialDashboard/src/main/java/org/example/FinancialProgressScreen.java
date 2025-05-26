package org.example;
import java.util.List;
public class FinancialProgressScreen {

    public static void display(List<String> currentResults, List<String> previousResults) {
        System.out.println("📊 Οικονομικά Δεδομένα Τρέχουσας Περιόδου:");
        for (String row : currentResults) {
            System.out.println(row);
        }

            System.out.println("\n📊 Οικονομικά Δεδομένα Προηγούμενης Περιόδου:");
            for (String row : previousResults) {
                System.out.println(row);
            }

            System.out.println("\n📈 Σύγκριση:");

            double currentSum = extractSum(currentResults);
            double previousSum = extractSum(previousResults);

            System.out.printf("🔹 Τρέχον Σύνολο: %.2f€\n", currentSum);
            System.out.printf("🔹 Προηγούμενο Σύνολο: %.2f€\n", previousSum);

            if (currentSum > previousSum) {
                System.out.printf("✅ Υπάρχει αύξηση εσόδων κατά %.2f€\n", currentSum - previousSum);
            } else if (currentSum < previousSum) {
                System.out.printf("⚠️ Υπάρχει μείωση εσόδων κατά %.2f€\n", previousSum - currentSum);
            } else {
                System.out.println("➖ Δεν υπήρξε διαφορά στα έσοδα.");
            }
        }

        private static double extractSum(List<String> data) {
            double sum = 0.0;
            for (String row : data) {
                try {
                    if (row.startsWith("📌")) {
                        int start = row.indexOf("Ποσό: ") + 6;
                        int end = row.indexOf("€", start);
                        String amountStr = row.substring(start, end).trim();
                        sum += Double.parseDouble(amountStr);
                    }
                } catch (Exception e) {
                    // Αν υπάρξει parsing error, το αγνοούμε
                }
            }
            return sum;
        }

    public static void exportFile(){
            System.out.println("File Exported!");
    }
    public static void abort(){
        System.out.println("Exporting Aborted");
    }
}
