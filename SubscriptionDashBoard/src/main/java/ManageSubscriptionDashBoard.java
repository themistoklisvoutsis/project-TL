import java.util.List;

public class ManageSubscriptionDashBoard {


    public static void searchSubPerProgram() {
        List<String> token1 = DBManager.querySubsPerProgram();
        List<String> token2 = DBManager.queryCheckInsPerHour();

        if (token1 != null && !token1.isEmpty()) {
            SubPerProgramScreen.setToken(token1);
            SubPerProgramScreen.display();

            PeakHourScreen.setToken(token2);
            PeakHourScreen.display();
        } else {
            NoSubscriptionScreen.display();
        }
    }
}