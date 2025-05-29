import java.util.Scanner;

public class CommentCriticsScreen {
    private String gymName;

    public CommentCriticsScreen(String gymName) {
        this.gymName = gymName;
    }

    public void showEvaluationScreen() {
        Scanner scanner = new Scanner(System.in);
        DBManagerGR dbManager = new DBManagerGR();

        System.out.print("Enter your client ID: ");
        String clientId = scanner.nextLine();

        System.out.println("Do you want to leave a comment or a rating? (comment/rating)");
        String choice = scanner.nextLine();

        ManageCommentsCritics manageCommentsCritics = new ManageCommentsCritics();
        if (choice.equalsIgnoreCase("comment")) {
            System.out.print("Enter your comment: ");
            String comment = scanner.nextLine();
            manageCommentsCritics.checkComment(clientId, gymName, comment);
        } else if (choice.equalsIgnoreCase("rating")) {
            System.out.print("Enter your rating (1 to 5): ");
            double rating = scanner.nextDouble();
            manageCommentsCritics.checkRating(clientId, gymName, rating);
        } else {
            System.out.println("Invalid choice.");
        }

        System.out.println("\nGym Feedback:");
        dbManager.showGymFeedback(gymName);
    }
}
