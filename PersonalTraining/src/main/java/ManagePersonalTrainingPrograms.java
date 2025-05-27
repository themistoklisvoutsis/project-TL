public class ManagePersonalTrainingPrograms {
    public static String[] searchPersonalTrainerSchedule(String selectedTrainer) {
        String[] schedule;

        switch (selectedTrainer) {
            case "Θέμης Παπόγλου":
                schedule = new String[]{
                        "Δευτέρα: 09:00-17:00 - Προσωπικές προπονήσεις",
                        "Τρίτη: 10:00-18:00 - Μαθήματα CrossFit",
                        "Πέμπτη: 09:00-17:00 - Προσωπικές προπονήσεις",
                        "Παρασκευή: 11:00-19:00 - Μαθήματα δύναμης"
                };
                break;

            case "Κυριάκος Βούτης":
                schedule = new String[]{
                        "Δευτέρα: 08:00-16:00 - Μαθήματα Yoga",
                        "Τετάρτη: 09:00-17:00 - Προσωπικές προπονήσεις",
                        "Πέμπτη: 10:00-18:00 - Μαθήματα Pilates",
                        "Σάββατο: 10:00-14:00 - Ειδικά προγράμματα"
                };
                break;

            case "Κωνσταντής Βασιλείου":
                schedule = new String[]{
                        "Τρίτη: 08:00-12:00 - Προσωπικές προπονήσεις",
                        "Τετάρτη: 10:00-18:00 - Μαθήματα TRX",
                        "Παρασκευή: 09:00-17:00 - Λειτουργική προπόνηση",
                        "Κυριακή: 10:00-14:00 - Ειδικά προγράμματα"
                };
                break;

            default:
                schedule = new String[]{"Δεν βρέθηκε πρόγραμμα για τον επιλεγμένο γυμναστή."};
        }

        // Καλούμε την showTrainersSchedule με προεπιλεγμένα φίλτρα "Όλα"/"Όλες"
        String[] defaultFilters = {"Όλα", "Όλες", "Όλες"};
        TrainerScheduleScreen.showTrainersSchedule(schedule, defaultFilters);
        
        return schedule;
    }
}