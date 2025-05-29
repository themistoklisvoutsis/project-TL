package org.example;

import org.example.GymSearchScreen;

import java.sql.*;

public class ManageFilters {
    private String name;
    private String city;
    private String type;
    private Double minRating;
    private Boolean hasTrainer;
    private Double maxSubscription;

    public void adjustFilters(int choice, GymSearchScreen screen) {
        switch(choice) {
            case 1 -> name = screen.getInput("Enter gym name: ");
            case 2 -> city = screen.getInput("Enter city: ").toUpperCase();
            case 3 -> type = screen.getInput("Enter type: ").toUpperCase();
            case 4 -> minRating = Double.parseDouble(screen.getInput("Min rating: "));
            case 5 -> hasTrainer = screen.getInput("Need trainer? (y/n): ").equalsIgnoreCase("y");
            case 6 -> maxSubscription = Double.parseDouble(screen.getInput("Max subscription: "));
        }
    }

    public void resetFilters() {
        name = city = type = null;
        minRating = maxSubscription = null;
        hasTrainer = null;
    }

    /*  Μέθοδοι που λαμβάνουν όλα τα πεδία φίλτρου */
    public String getNameFilter() { return name; }
    public String getCityFilter() { return city; }
    public String getTypeFilter() { return type; }
    public Double getMinRating() { return minRating; }
    public Boolean hasTrainer() { return hasTrainer; }
    public Double getMaxSubscription() { return maxSubscription; }
}