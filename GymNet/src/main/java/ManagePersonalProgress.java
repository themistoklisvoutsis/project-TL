public class ManagePersonalProgress {
    public void searchCheckInAmount(int clientId, String timePeriod) {
        DBManager dbManager = new DBManager();
        String results = dbManager.queryCheckIn(clientId, timePeriod);

        if (results.isEmpty()) {
            NoProgressScreen noProgressScreen = new NoProgressScreen();
            noProgressScreen.display();
        } else {
            ProgressHistoryScreen progressHistoryScreen = new ProgressHistoryScreen();
            progressHistoryScreen.display(results);
            checkWorkOutGoal();
            int maxWorkOuts = maximumSeqWorkOuts();
            searchPreviousCheck(results, maxWorkOuts);
        }
    }

    public void checkWorkOutGoal() {
        System.out.println("Checking workout goals...");
    }

    public int maximumSeqWorkOuts() {
        System.out.println("Calculating maximum sequential workouts...");
        return 0;
    }

    public void searchPreviousCheck(String results, int maxWorkOuts) {
        ProgressComparisonScreen progressComparisonScreen = new ProgressComparisonScreen();
        progressComparisonScreen.progressComparisonAndDisplay(results, maxWorkOuts);
    }
}


