public class PersonalProgressScreen {
    public void showPersonalProgressScreen() {
        System.out.println("Welcome to your Personal Progress Screen!");
    }

    public void selectsTimePeriod(ManagePersonalProgress managePersonalProgress, int clientId, String timePeriod) {
        managePersonalProgress.searchCheckInAmount(clientId, timePeriod);
    }
}
