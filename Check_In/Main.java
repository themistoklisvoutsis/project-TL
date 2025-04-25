import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GymSystem gymSystem = new GymSystem();

        GymClient client1 = new GymClient("001", "QR12345");
        GymClient client2 = new GymClient("002", "QR67890");
        gymSystem.registerClient(client1);
        gymSystem.registerClient(client2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter your QR Code to check in (or type 'exit' to quit):");
            String qrCode = scanner.nextLine();

            if (qrCode.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the system. Goodbye!");
                break;
            }

            String result = gymSystem.checkIn(qrCode);
            System.out.println(result);
        }

        scanner.close();
    }
}