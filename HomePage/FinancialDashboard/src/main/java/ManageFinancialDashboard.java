package org.example;

import java.sql.Date;
import java.util.List;

public class ManageFinancialDashboard {

    public static void searchFinancialData(Date from, Date to) {
        List<String> results = DBManagerFD.searchForTimeDomain(from, to);

        FinancialDataScreen screen = new FinancialDataScreen();
        screen.display(results); // Τώρα εμφανίζεις μέσω screen
    }
}
