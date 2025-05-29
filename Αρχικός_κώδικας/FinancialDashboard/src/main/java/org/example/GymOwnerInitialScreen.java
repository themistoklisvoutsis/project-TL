package org.example;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class GymOwnerInitialScreen {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n====== 📊 GYMNET Οικονομικό Μενού ======");
            System.out.println("1. Προβολή Οικονομικών Δεδομένων");
            System.out.println("2. Προβολή Ιστορικού Οικονομικών");
            System.out.println("3. Σύγκριση Περιόδων");
            System.out.println("4. Εξαγωγή Δεδομένων");
            System.out.println("5. Έλεγχος αν υπάρχουν δεδομένα");
            System.out.println("6. Έξοδος");
            System.out.print("Επιλέξτε ενέργεια: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    FinancialDashboardScreen.choosesTimeDomain();
                    break;
                case "2":
                    showHistory();
                    break;
                case "3":
                    comparePeriods();
                    break;
                case "4":
                    FinancialProgressScreen.exportFile();
                    break;
                case "5":
                    NoDataForComparisonScreen.display();
                    break;
                case "6":
                    System.out.println("👋 Έξοδος από την εφαρμογή.");
                    exit = true;
                    break;
                default:
                    System.out.println("❌ Μη έγκυρη επιλογή. Προσπαθήστε ξανά.");
            }
        }
    }

    public static void showHistory() {
        System.out.println("📁 Εμφάνιση όλων των εγγραφών στο financial_history:");
        List<String> results = DBManagerPS.searchForTimeDomain(Date.valueOf("2000-01-01"), new Date(System.currentTimeMillis()));
        new FinancialDataScreen().display(results);
    }

    public static void comparePeriods() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n📅 Επιλογή ΤΡΕΧΟΥΣΑΣ περιόδου:");
        System.out.print("Έναρξη (yyyy-MM-dd): ");
        Date currentFrom = Date.valueOf(scanner.nextLine().trim());
        System.out.print("Λήξη (yyyy-MM-dd): ");
        Date currentTo = Date.valueOf(scanner.nextLine().trim());

        System.out.println("\n📅 Επιλογή ΠΡΟΗΓΟΥΜΕΝΗΣ περιόδου:");
        System.out.print("Έναρξη (yyyy-MM-dd): ");
        Date previousFrom = Date.valueOf(scanner.nextLine().trim());
        System.out.print("Λήξη (yyyy-MM-dd): ");
        Date previousTo = Date.valueOf(scanner.nextLine().trim());

        List<String> current = DBManagerPS.searchForTimeDomain(currentFrom, currentTo);
        List<String> previous = DBManagerPS.searchForTimeDomain(previousFrom, previousTo);

        FinancialProgressScreen.display(current, previous);
    }
}