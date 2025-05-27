
/* Δήλωση φίλτρων */
public record GymProfile(
        int id,
        String name,
        String city,
        String type,
        double subscription,
        double rating,
        boolean personalTrainer
) { }