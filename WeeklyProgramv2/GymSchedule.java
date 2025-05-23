import java.util.*;


class GymHours {
    String day;
    String openTime;
    String closeTime;


    public GymHours(String day, String openTime, String closeTime) {
        this.day = day;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public void display() {
        System.out.println(day + ": " +  openTime + " - "  + closeTime);
    }
}

public class GymSchedule {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello Gym Owner! Let's shedule our gym hours for the week.");
        List<String> daysOfWeek = Arrays.asList(
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
        );
        List<GymHours> weeklySchedule = new ArrayList<>();

        for (String day : daysOfWeek) {
            System.out.println("\n Enter opening time for " + day + ": " );
            String openTime = scanner.nextLine();
            System.out.println(" Enter closing time for " + day + ": " );
            String closeTime = scanner.nextLine();
            weeklySchedule.add(new GymHours(day, openTime, closeTime));

        }
        System.out.println("\n Weekly Gym Schedule:");
        for (GymHours day : weeklySchedule) {
            day.display();
        }
        scanner.close();
        }
    }


