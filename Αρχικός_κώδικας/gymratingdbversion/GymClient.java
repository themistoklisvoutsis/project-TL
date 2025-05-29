import java.util.Scanner;

public class GymClient {
    public static void main(String[] args) {
        SearchResultsScreen searchResultsScreen = new SearchResultsScreen();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the gym name: ");
        String gymName = scanner.nextLine();

        searchResultsScreen.gymProfileSelect(gymName);
    }
}
