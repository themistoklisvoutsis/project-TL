import java.util.InputMismatchException;
import java.util.Scanner;

public class GymSearchScreen {
    private static Scanner scanner = new Scanner(System.in);
    private String name;
    private String city;
    private String type;
    private Double minRating;
    private Boolean hasTrainer;
    private Double maxSubscription;
    /* Δημιουργία "μενού" φίλτρων και ανάγνωση επιλογής */
    public static int showGymSearchScreen() {
        System.out.println("\nGYM SEARCH MENU");
        System.out.println("1. Search by name");
        System.out.println("2. Search by city");
        System.out.println("3. Search by type");
        System.out.println("4. Search by rating");
        System.out.println("5. Search by personal trainer");
        System.out.println("6. Search by max subscription");
        System.out.println("7. Execute Search");
        System.out.println("8. Reset filters");
        System.out.println("9. Exit");
        System.out.print("Choose option: ");

        int choice;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            choice = -1;
        }
        scanner.nextLine(); // consume leftover newline
        return choice;
    }

    /**
     * Prompts the user with the given message and returns their input.
     * This replaces the previous getInput method.
     */
    public String showGymSearchScreen(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void adjustFilters(int choice, GymSearchScreen screen) {
        switch(choice) {
            case 1 -> name = screen.showGymSearchScreen("Enter gym name: ");
            case 2 -> city = screen.showGymSearchScreen("Enter city: ").toUpperCase();
            case 3 -> type = screen.showGymSearchScreen("Enter type: ").toUpperCase();
            case 4 -> minRating = Double.parseDouble(screen.showGymSearchScreen("Min rating: "));
            case 5 -> hasTrainer = screen.showGymSearchScreen("Need trainer? (y/n): ").equalsIgnoreCase("y");
            case 6 -> maxSubscription = Double.parseDouble(screen.showGymSearchScreen("Max subscription: "));
        }
    }

    public void resetFilters() {
        name = city = type = null;
        minRating = maxSubscription = null;
        hasTrainer = null;
    }

    /*  Μέθοδοι που λαμβάνουν όλα τα πεδία φίλτρου */
    public String getNameFilter() { return name; }
    public String getCityFilter() { return city; }
    public String getTypeFilter() { return type; }
    public Double getMinRating() { return minRating; }
    public Boolean hasTrainer() { return hasTrainer; }
    public Double getMaxSubscription() { return maxSubscription; }
}
