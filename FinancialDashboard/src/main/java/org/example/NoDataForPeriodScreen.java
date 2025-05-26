package org.example;

import java.util.Date;
import java.util.List;

public class NoDataForPeriodScreen {
    public static void display(List<String> results, Date from, Date to) {
        if (results.isEmpty()) {
            results.add("⚠️ Δεν βρέθηκαν εγγραφές για το διάστημα " + from + " έως " + to);
        }
    }
}
