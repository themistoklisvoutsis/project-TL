package org.example;

import org.example.DBManagerPS;
import org.example.FinancialDataScreen;

import java.sql.Date;
import java.util.List;

public class ManageFinancialDashboard {

    public static void searchFinancialData(Date from, Date to) {
        List<String> results = DBManagerPS.searchForTimeDomain(from, to);

        FinancialDataScreen screen = new FinancialDataScreen();
        screen.display(results); // Τώρα εμφανίζεις μέσω screen
    }
}
