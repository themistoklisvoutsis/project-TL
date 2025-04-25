import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

class GymClient {
    private String id;
    private boolean hasParticipated;

    public GymClient(String id, boolean hasParticipated) {
        this.id = id;
        this.hasParticipated = hasParticipated;
    }

    public String getId() {
        return id;
    }

    public boolean hasParticipated() {
        return hasParticipated;
    }
}

class Gym {
    private String name;
    private List<String> comments;
    private List<Double> ratings;

    public Gym(String name) {
        this.name = name;
        this.comments = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public void addRating(double rating) {
        ratings.add(rating);
    }

    public void displayCommentsAndRatings() {
        System.out.println("Comments for " + name + ":");
        for (String comment : comments) {
            System.out.println("- " + comment);
        }

        if (!ratings.isEmpty()) {
            double averageRating = ratings.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            System.out.println("Average Rating: " + averageRating + " stars");
        } else {
            System.out.println("No ratings available.");
        }
    }
}

class GymSystem {
    private Map<String, GymClient> clients;
    private Map<String, Gym> gyms;

    public GymSystem() {
        clients = new HashMap<>();
        gyms = new HashMap<>();
    }

    public void registerClient(GymClient client) {
        clients.put(client.getId(), client);
    }

    public void registerGym(Gym gym) {
        gyms.put(gym.getName(), gym);
    }

    public String submitComment(String clientId, String gymName, String comment) {
        if (!clients.containsKey(clientId)) {
            return "Client not found.";
        }

        if (!gyms.containsKey(gymName)) {
            return "Gym not found.";
        }

        GymClient client = clients.get(clientId);
        if (!client.hasParticipated()) {
            return "You cannot leave a comment without participating in a gym program.";
        }

        gyms.get(gymName).addComment(comment);
        return "Comment submitted successfully.";
    }

    public String submitRating(String clientId, String gymName, double rating) {
        if (!clients.containsKey(clientId)) {
            return "Client not found.";
        }

        if (!gyms.containsKey(gymName)) {
            return "Gym not found.";
        }

        if (rating < 1.0 || rating > 5.0) {
            return "Rating must be between 1 and 5 stars.";
        }

        GymClient client = clients.get(clientId);
        if (!client.hasParticipated()) {
            return "You cannot leave a rating without participating in a gym program.";
        }

        gyms.get(gymName).addRating(rating);
        return "Rating submitted successfully.";
    }

    public void displayGymFeedback(String gymName) {
        if (gyms.containsKey(gymName)) {
            gyms.get(gymName).displayCommentsAndRatings();
        } else {
            System.out.println("Gym not found.");
        }
    }
}

public class GymRating {
    public static void main(String[] args) {
        GymSystem gymSystem = new GymSystem();

        Gym gym1 = new Gym("Fitness Club");
        gymSystem.registerGym(gym1);

        GymClient client1 = new GymClient("001", true);
        GymClient client2 = new GymClient("002", false);
        gymSystem.registerClient(client1);
        gymSystem.registerClient(client2);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your client ID: ");
        String clientId = scanner.nextLine();

        System.out.print("Enter the gym name: ");
        String gymName = scanner.nextLine();

        System.out.println("Do you want to leave a comment or a rating? (comment/rating)");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("comment")) {
            System.out.print("Enter your comment: ");
            String comment = scanner.nextLine();
            System.out.println(gymSystem.submitComment(clientId, gymName, comment));
        } else if (choice.equalsIgnoreCase("rating")) {
            System.out.print("Enter your rating (1 to 5): ");
            double rating = scanner.nextDouble();
            System.out.println(gymSystem.submitRating(clientId, gymName, rating));
        } else {
            System.out.println("Invalid choice.");
        }

        System.out.println("\nGym Feedback:");
        gymSystem.displayGymFeedback(gymName);

        scanner.close();
    }
}
