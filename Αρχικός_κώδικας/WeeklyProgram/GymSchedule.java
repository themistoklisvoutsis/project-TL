import java.util.*;

class GymHours {
    private String day;
    private String opentime;
    private String closetime;
    private boolean isClosed;

    public GymHours(String day, String opentime, String closetime) {
        this.day = day;
        this.opentime = opentime;
        this.closetime = closetime;
        this.isClosed = false;
    }

    public GymHours(String day) {
        this.day = day;
        this.isClosed = true;
    }

    public void display() {
        if (isClosed) {
            System.out.println(day + ": Closed");
        } else {
            System.out.println(day + ": Opens at " + opentime + " until " + closetime);
        }
    }
}

public class GymSchedule {
    public static void main(String[] args) {
        List<GymHours> weeklySchedule = Arrays.asList(
                new GymHours("Monday", "8:00 AM", "10:00 PM"),
                new GymHours("Tuesday", "8:00 AM", "10:00 PM"),
                new GymHours("Wednesday", "8:00 AM", "10:00 PM"),
                new GymHours("Thursday", "8:00 AM", "10:00 PM"),
                new GymHours("Friday", "8:00 AM", "10:00 PM"),
                new GymHours("Saturday", "8:00 AM", "10:00 PM"),
                new GymHours("Sunday", "8:00 AM", "6:00 PM")
        );

        System.out.println("  Weekly Programme\n");
        for (GymHours day : weeklySchedule) {
            day.display();
        }
    }
}
