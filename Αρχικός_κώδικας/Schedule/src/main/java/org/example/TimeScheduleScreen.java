import java.util.Scanner;

public class TimeScheduleScreen {
    private GymSchedule gymSchedule;
    private Scanner scanner;

    public TimeScheduleScreen() {
        gymSchedule = new GymSchedule();
        scanner = new Scanner(System.in);
    }

    public void fillSchedule() {
        System.out.println("=== Gym Schedule Setup ===");
        for (int day = 0; day < GymSchedule.DAYS.length; day++) {
            System.out.println("\n=== " + GymSchedule.DAYS[day] + " ===");
            for (int slot = 0; slot < gymSchedule.getTimeSlotCount(); slot++) {
                System.out.print(gymSchedule.getTimeSlotLabel(slot) + ": ");
                String className = scanner.nextLine().trim();
                if (!className.isEmpty()) {
                    gymSchedule.addClass(day, slot, className);
                }
            }
        }
    }

    public void chooseDay() {
        System.out.print("\nEnter a day to view (e.g., Monday): ");
        String dayName = scanner.nextLine().trim();
        int dayIndex = GymSchedule.getDayIndex(dayName);

        if (dayIndex == -1) {
            System.out.println("Invalid day! Please try again.");
        } else {
            gymSchedule.printDay(dayIndex);
        }
    }
    public void startWorkflow() {
        fillSchedule();
        new ManageTimeSchedule(this.gymSchedule).promptForModifications();
    }

    public GymSchedule getFilledSchedule() {
        return gymSchedule;
    }


}