

import java.util.Date;
import java.util.List;

public class NoDataForPeriodScreen {
    public static void display(List<String> results, Date from, Date to) {
        if (results.isEmpty()) {
            results.add(" No Inputs From" + from + " To " + to);
        }
    }
}
