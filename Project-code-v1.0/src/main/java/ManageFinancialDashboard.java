
import java.sql.Date;
import java.util.List;

public class ManageFinancialDashboard {

    public static void searchFinancialData(Date from, Date to) {
        List<String> results = DBManager.searchForTimeDomain(from, to);

        FinancialDataScreen screen = new FinancialDataScreen();
        screen.display(results); // Τώρα εμφανίζεις μέσω screen
    }
}
