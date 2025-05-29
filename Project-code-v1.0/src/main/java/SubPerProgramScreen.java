import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubPerProgramScreen {

    private static List<String> token;

    public static void setToken(List<String> t) {
        token = t;
    }

    public static void display() {
        if (token == null || token.isEmpty()) {
            System.out.println("No subscriptions to display.");
            return;
        }

        Map<String, Integer> programCounts = new HashMap<>();

        for (String line : token) {
            String[] parts = line.split("/");
            String program = null;

            if (parts.length >= 3) {
                program = parts[2].trim();
            } else if (parts.length == 2) {
                program = parts[1].trim();
            } else if (parts.length == 1) {
                program = parts[0].trim();
            }

            if (program != null && !program.isEmpty()) {
                programCounts.put(program, programCounts.getOrDefault(program, 0) + 1);
            }
        }

        System.out.println("=== Subscriptions Per Program ===");
        for (Map.Entry<String, Integer> entry : programCounts.entrySet()) {
            System.out.printf("%-15s | %d%n", entry.getKey(), entry.getValue());
        }
    }
}
