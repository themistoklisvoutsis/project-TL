import java.util.List;

public class PeakHourScreen {

    private static List<String> token;

    public static void setToken(List<String> t) {
        token = t;
    }

    public static void display() {
        if (token == null || token.isEmpty()) {
            System.out.println("No check-in data available.");
            return;
        }

        System.out.println("== Check Ins Per Hour ==");
        for (String entry : token) {
            String[] parts = entry.split(" / ");
            if (parts.length == 2) {
                String time = parts[0].trim();
                String count = parts[1].trim();
                System.out.printf("%-5s | %s%n", time, count);
            } else {

                System.out.println(entry);
            }
        }
    }

}
