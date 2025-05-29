import java.util.Scanner;

public class FileConverter {

    public static void abort() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Are you sure you want to proceed? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("no")) {
            System.out.println("Operation aborted.");
            return;
        }

        exportFile();
    }

    public static void exportFile() {
        System.out.println("File exported.");
    }
}
