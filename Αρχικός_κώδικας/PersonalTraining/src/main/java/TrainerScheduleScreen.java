public class TrainerScheduleScreen {
    public static void showTrainersSchedule(String[] schedule, String[] filters) {
        // Καλούμε την showFilterScreen για να πάρουμε τα φίλτρα από τον χρήστη
        String selectedTrainer = PersonalTrainersScreen.getSelectedTrainer();
        String[] userFilters = PersonalProgramFilterScreen.showFilterScreen(selectedTrainer);
        
        // Αν ο χρήστης ακύρωσε την επιλογή φίλτρων, χρησιμοποιούμε τα προεπιλεγμένα
        if (userFilters == null) {
            userFilters = filters;
        }
        
        if (userFilters == null) {
            System.out.println("Σφάλμα: Δεν βρέθηκαν έγκυρα φίλτρα");
            return;
        }
        
        System.out.println("\nΠρόγραμμα γυμναστή:");
        System.out.println("----------------------------------------");
        
        // Εμφάνιση μόνο των προγραμμάτων που ταιριάζουν με τα φίλτρα
        for (String line : schedule) {
            boolean matchesFilters = true;
            
            // Έλεγχος για το είδος προπόνησης
            if (!userFilters[0].equals("Όλα") && !line.contains(userFilters[0])) {
                matchesFilters = false;
            }
            
            // Έλεγχος για την ημέρα
            if (!userFilters[1].equals("Όλες") && !line.contains(userFilters[1])) {
                matchesFilters = false;
            }
            
            // Έλεγχος για τη χρονική ζώνη
            if (!userFilters[2].equals("Όλες")) {
                String timeStr = line.split(":")[1].trim().split(" ")[0]; // Παίρνουμε το ωράριο
                String startTime = timeStr.split("-")[0];
                
                // Ελέγχουμε αν η ώρα έναρξης ανήκει στην επιλεγμένη ζώνη
                int hour = Integer.parseInt(startTime.split(":")[0]);
                
                if (userFilters[2].equals("Πρωί") && (hour < 8 || hour >= 12)) {
                    matchesFilters = false;
                } else if (userFilters[2].equals("Μεσημέρι") && (hour < 12 || hour >= 16)) {
                    matchesFilters = false;
                } else if (userFilters[2].equals("Απόγευμα") && (hour < 16 || hour >= 20)) {
                    matchesFilters = false;
                }
            }
            
            // Εμφάνιση του προγράμματος αν ταιριάζει με τα φίλτρα
            if (matchesFilters) {
                System.out.println(line);
            }
        }
    }
}