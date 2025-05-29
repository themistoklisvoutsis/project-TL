import java.util.Scanner;

public class GymProfileScreen {


    public GymProfileScreen(String gymName) {
    }

    /* Δήλωση φίλτρων */
    public record GymProfile(
            int id,
            String name,
            String city,
            String type,
            double subscription,
            double rating,
            boolean personalTrainer
    ) { }
    private ManageCheckIn manageCheckIn;
    private SearchResultScreen gymName;

    public GymProfileScreen(ManageCheckIn manageCheckIn, SearchResultScreen gymName) {
        this.manageCheckIn = manageCheckIn;
        this.gymName = gymName;
    }

    public static void choosesCheckIn(String qrCode) {
        QRGeneration.qrGenerate(qrCode);
    }
    public void showGymProfile() {
        RatingReviewsScreen ratingReviewsScreen = new RatingReviewsScreen(gymName.toString());
        ratingReviewsScreen.showEvaluationScreen();
    }
    public static void choosesGymProgram() {
        Scanner scanner = new Scanner(System.in);
        String[] programDetails = ManageGymPrograms.selectGymPrograms(scanner);
        DBManager.storeSubscription(programDetails[0], Integer.parseInt(programDetails[1]));


        SpecificProgramScreen.showGymProgram(programDetails);
        boolean success = SpecificProgramScreen.personalDataInsertion(scanner);
        if (success) {
            GymProgramConfirmationScreen.display();
        } else {
            NotEnoughDataScreen.dislplay();
        }

        scanner.close();
    }
}
