import java.util.Scanner;

public class ManageTimeSchedule {
    private GymSchedule gymSchedule;
    private Scanner scanner;

    public ManageTimeSchedule(GymSchedule gymSchedule) {
        this.gymSchedule = gymSchedule;
        this.scanner = new Scanner(System.in);
    }


    public void promptForModifications() {
        System.out.print("\nDo you want to modify the schedule? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            modifySchedule();
            storeSchedule();
        }
    }


    private void modifySchedule() {
        boolean continueEditing = true;
        while (continueEditing) {
            System.out.print("\nEnter day to modify (e.g., Monday): ");
            String dayName = scanner.nextLine().trim();
            int dayIndex = GymSchedule.getDayIndex(dayName);

            if (dayIndex == -1) {
                System.out.println("Invalid day! Try again.");
            } else {
                refillDay(dayIndex);
            }

            System.out.print("Modify another day? (yes/no): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            continueEditing = choice.equals("yes");
        }
    }


    private void refillDay(int dayIndex) {
        System.out.println("\n=== Refilling " + GymSchedule.DAYS[dayIndex] + " ===");
        for (int slot = 0; slot < gymSchedule.getTimeSlotCount(); slot++) {
            System.out.print(gymSchedule.getTimeSlotLabel(slot) + ": ");
            String className = scanner.nextLine().trim();
            gymSchedule.addClass(dayIndex, slot, className.isEmpty() ? null : className);
        }
    }


    private void storeSchedule() {
        System.out.print("\nSave changes? (yes/no): ");
        String saveChoice = scanner.nextLine().trim().toLowerCase();
        if (saveChoice.equals("yes")) {
            System.out.println("Schedule updated successfully!");
            gymSchedule.printSchedule();
        } else {
            System.out.println("Changes discarded.");
        }
    }


}