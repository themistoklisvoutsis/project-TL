import java.io.IOException;
import java.util.Scanner;

public class GymOwnerInitialScreen {
    private GymSchedule schedule;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new GymOwnerInitialScreen().selectsTimeSchedule();
    }

    public void selectsTimeSchedule() {
        while (true) {
            System.out.println("\n=== Gym Schedule System ===");
            System.out.println("1. Fill Schedule");
            System.out.println("2. Modify Schedule");
            System.out.println("3. Choose Day to View");
            System.out.println("4. Show Full Schedule");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    fillSchedule();
                    break;
                case 2:
                    modifySchedule();
                    break;
                case 3:
                    chooseDay();
                    break;
                case 4:
                    showSchedule();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }


    private void fillSchedule() {
        TimeScheduleScreen screen = new TimeScheduleScreen();
        screen.fillSchedule();
        schedule = screen.getFilledSchedule();
        System.out.println("\nSchedule created successfully!");
    }


    private void modifySchedule() {
        if (schedule == null) {
            System.out.println("Create a schedule first!");
            return;
        }
        new ManageTimeSchedule(schedule).promptForModifications();
    }


    private void chooseDay() {
        if (schedule == null) {
            System.out.println("No schedule to view!");
            return;
        }

        System.out.print("Enter day name (e.g. Monday): ");
        String dayName = scanner.nextLine();
        int dayIndex = GymSchedule.getDayIndex(dayName);

        if (dayIndex == -1) {
            System.out.println("Invalid day!");
        } else {
            schedule.printDay(dayIndex);
        }
    }


    private void showSchedule() {
        if (schedule == null) {
            System.out.println("No schedule to display!");
            return;
        }
        schedule.printSchedule();
    }
}