import java.util.Scanner;

public class GymClientCI {
    public static void main(String[] args) {
        DBManagerCI dbManager = new DBManagerCI();
        ManageCheckIn manageCheckIn = new ManageCheckIn(dbManager);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter your GymClient ID to check in (or type 'exit' to quit):");
            String clientId = scanner.nextLine();

            if (clientId.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the system. Goodbye!");
                break;
            }

            try {
                long gymClientId = Long.parseLong(clientId);
                manageCheckIn.processCheckIn(gymClientId);
            } catch (NumberFormatException e) {
                System.out.println("Invalid GymClient ID. Please enter a numeric value.");
            }
        }

        scanner.close();
    }
}
