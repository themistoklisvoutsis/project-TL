import java.util.List;

public class InvalidFiltersScreen {
    public static boolean handle(List<?> results) {
        if (results.isEmpty()) {
            System.out.println("No matching gyms found");
            return true;
        }
        return false;
    }
}
