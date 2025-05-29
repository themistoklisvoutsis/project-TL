import java.util.Scanner;

public class RatingReviewsScreen {
    private String gymName;

    public RatingReviewsScreen(String gymName) {
        this.gymName = gymName;
    }

    public void showEvaluationScreen() {
        Scanner scanner = new Scanner(System.in);
        DBManager dbManager = new DBManager();
        ManageRatingsReviews manageRatingsReviews = new ManageRatingsReviews();

        System.out.print("Enter your client ID: ");
        String clientId = scanner.nextLine();

        System.out.print("Do you want to leave a comment or a rating? (comment/rating): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("comment")) {
            leaveComment(scanner, manageRatingsReviews, clientId, gymName);
        } else if (choice.equalsIgnoreCase("rating")) {
            System.out.print("Enter your rating (1 to 5): ");
            double rating = scanner.nextDouble();
            manageRatingsReviews.performRate(clientId, gymName, rating);
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.println("\nGym Feedback:");
        dbManager.showGymFeedback(gymName);
    }

    private void leaveComment(Scanner scanner, ManageRatingsReviews manageCommentsCritics, String clientId, String gymName) {
        System.out.print("Enter your comment: ");
        String comment = scanner.nextLine();
        manageCommentsCritics.checkComment(clientId, gymName, comment);
    }

}
