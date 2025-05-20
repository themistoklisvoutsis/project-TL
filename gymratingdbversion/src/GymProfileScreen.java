public class GymProfileScreen {
    private String gymName;

    public GymProfileScreen(String gymName) {
        this.gymName = gymName;
    }

    public void showGymProfileScreen() {
        CommentCriticsScreen commentsCriticsScreen = new CommentCriticsScreen(gymName);
        commentsCriticsScreen.showEvaluationScreen();
    }
}

