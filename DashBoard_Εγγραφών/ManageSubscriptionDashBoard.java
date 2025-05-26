import java.util.List;

public class ManageSubscriptionDashBoard {

    //static List<String> token;
    public static void searchSubPerProgram() {
        List<String> token1 = DBManager.querySubsPerProgram();
        List<String> token2 = DBManager.queryCheckInsPerHour();

        if (token1 != null && !token1.isEmpty()) {
            SubPerProgramScreen.setToken(token1);
            SubPerProgramScreen.display();

            PeakHourScreen.setToken(token2);  // <-- Set this!
            PeakHourScreen.display();
        } else {
            NoSubscriptionScreen.display();  // Just show the message
        }
    }
}