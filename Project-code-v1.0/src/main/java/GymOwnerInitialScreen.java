import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class GymOwnerInitialScreen {
    public static void selectsFinancialDashBoard() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n======  GYMNET Financial Menu ======");
            System.out.println("1. View Financial Data");
            System.out.println("2. View Financial History");
            System.out.println("3. Compare Periods");
            System.out.println("4. Export Data");
            System.out.println("5. Check Data Availability");
            System.out.println("6. Exit");
            System.out.print("Select action: ");

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
                    System.out.println(" Exiting application.");
                    exit = true;
                    break;
                default:
                    System.out.println(" Invalid choice. Please try again.");
            }
        }
    }

    public static void showHistory() {
        System.out.println("Displaying all records in financial_history:");
        List<String> results = DBManager.searchForTimeDomain(Date.valueOf("2000-01-01"), new Date(System.currentTimeMillis()));
        new FinancialDataScreen().display(results);
    }

    public static void comparePeriods() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n Select CURRENT period:");
        System.out.print("Start (yyyy-MM-dd): ");
        Date currentFrom = Date.valueOf(scanner.nextLine().trim());
        System.out.print("End (yyyy-MM-dd): ");
        Date currentTo = Date.valueOf(scanner.nextLine().trim());

        System.out.println("\n Select PREVIOUS period:");
        System.out.print("Start (yyyy-MM-dd): ");
        Date previousFrom = Date.valueOf(scanner.nextLine().trim());
        System.out.print("End (yyyy-MM-dd): ");
        Date previousTo = Date.valueOf(scanner.nextLine().trim());

        List<String> current = DBManager.searchForTimeDomain(currentFrom, currentTo);
        List<String> previous = DBManager.searchForTimeDomain(previousFrom, previousTo);

        FinancialProgressScreen.display(current, previous);
    }
}
