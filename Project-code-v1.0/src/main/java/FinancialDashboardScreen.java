

import java.sql.Date;
import java.util.Scanner;

public class FinancialDashboardScreen {
    //  showFinancialDashboardScreen();
    public static void choosesTimeDomain(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Start Date(yyyy-MM-dd): ");
        String startInput = scanner.nextLine().trim();
        Date startDate = Date.valueOf(startInput);

        System.out.print("Enter End Date(yyyy-MM-dd): ");
        String endInput = scanner.nextLine().trim();
        Date endDate = Date.valueOf(endInput);

        System.out.println("Start Date: " + startDate);
        System.out.println("EndDate: " + endDate);
        ManageFinancialDashboard.searchFinancialData(startDate,endDate);
    }

}
