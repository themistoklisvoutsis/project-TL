package org.example;

import java.util.List;

public class FinancialDataScreen {

    public void display(List<String> results) {
        System.out.println("📊 Οικονομικά αποτελέσματα:");
        for (String row : results) {
            System.out.println(row);
        }
    }
}
