import java.util.Scanner;

public class GymClient {
    public static void main(String[] args) {
        DBManager dbManager = new DBManager();
        ManageCheckIn manageCheckIn = new ManageCheckIn(dbManager);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter your QR Code to check in (or type 'exit' to quit):");
            String qrCode = scanner.nextLine();

            if (qrCode.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the system. Goodbye!");
                break;
            }

            manageCheckIn.qrGenerate(qrCode);
        }

        scanner.close(); // Close scanner to release resources
    }
}
