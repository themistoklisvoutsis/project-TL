package org.example;

import java.sql.Date;
import java.util.Scanner;

import static org.example.ManageFinancialDashboard.*;

public class FinancialDashboardScreen {
  //  showFinancialDashboardScreen();
   public static void choosesTimeDomain(){
       Scanner scanner = new Scanner(System.in);

       System.out.print("Εισάγετε ημερομηνία έναρξης (yyyy-MM-dd): ");
       String startInput = scanner.nextLine().trim();
       Date startDate = Date.valueOf(startInput);

       System.out.print("Εισάγετε ημερομηνία λήξης (yyyy-MM-dd): ");
       String endInput = scanner.nextLine().trim();
       Date endDate = Date.valueOf(endInput);

       System.out.println("Ημερομηνία Έναρξης: " + startDate);
       System.out.println("Ημερομηνία Λήξης: " + endDate);
       searchFinancialData(startDate,endDate);
   }

}
