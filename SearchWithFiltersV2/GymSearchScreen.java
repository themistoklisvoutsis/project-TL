package org.example;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GymSearchScreen {
    private Scanner scanner = new Scanner(System.in);

/* Δημιουργία "μενού" φίλτρων */
    public void showGymSearchScreen() {
        System.out.println("\nGYM SEARCH MENU");
        System.out.println("1. Search by name");
        System.out.println("2. Search by city");
        System.out.println("3. Search by type");
        System.out.println("4. Search by rating");
        System.out.println("5. Search by personal trainer");
        System.out.println("6. Search by max subscription");
        System.out.println("7. Execute Search");
        System.out.println("8. Reset filters");
        System.out.println("9. Exit");
        System.out.print("Choose option: ");
    }
/* Αποθήκευση επιλογών που έδωσε ο χρήστης */
    public int getUserChoice() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        scanner.nextLine();
        return scanner.nextLine();
    }
    /* Εμφάνιση προφίλ γυμναστηρίου που ταιριάζει με τα φίλτρα που έδωσε ο χρήστης */
    public void showResults(List<GymProfile> results) {
        System.out.println("\n-- SEARCH RESULTS --");
        if(results.isEmpty()) {
            System.out.println("No matching gyms found");
            return;
        }
        results.forEach(this::displayGymProfile);
    }

    private void displayGymProfile(GymProfile gym) {
        System.out.printf("""
            ID: %d
            Name: %s
            City: %s
            Type: %s
            Subscription: €%.2f
            Rating: %.1f
            Personal Trainer: %s
            --------------------------
            """,
                gym.id(), gym.name(), gym.city(), gym.type(),
                gym.subscription(), gym.rating(), gym.personalTrainer() ? "Yes" : "No");
    }

    public void showError(String invalidChoice) {

    }
}