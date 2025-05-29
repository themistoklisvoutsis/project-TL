import java.util.List;
import java.util.Scanner;

public class SearchResultScreen {
    public static class SearchResultsScreen {
        public void gymProfileSelect(String gymName) {
            GymProfileScreen gymProfileScreen = new GymProfileScreen(gymName);
            gymProfileScreen.showGymProfile();
        }
    }
    public static void showResults(List<GymProfileScreen.GymProfile> results) {
        System.out.println("\n-- SEARCH RESULTS --");
        if (results.isEmpty()) {
            System.out.println("No matching gyms found");
            return;
        }
        for (GymProfileScreen.GymProfile gym : results) {
            System.out.printf("""
                ID: %d
                Name: %s
                City: %s
                Type: %s
                Subscription: €%.2f
                Rating: %.1f
                Personal Trainer: %s
                --------------------------
                """,
                    gym.id(), gym.name(), gym.city(), gym.type(),
                    gym.subscription(), gym.rating(), gym.personalTrainer() ? "Yes" : "No");
        }
    }
    public static void gymProfileSelect() {
        SearchResultsScreen searchResultsScreen = new SearchResultsScreen();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the gym name: ");
        String gymName = scanner.nextLine();

        searchResultsScreen.gymProfileSelect(gymName);
    }

}
