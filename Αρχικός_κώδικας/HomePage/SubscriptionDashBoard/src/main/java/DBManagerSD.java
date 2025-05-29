import java.util.Arrays;
import java.util.List;

public class DBManagerSD {

    public static List<String> querySubsPerProgram() {
        return Arrays.asList(
                "John / Statham / Yoga / 3 months",
                "Giorgos / Aslanidis / Cardio / 3 months",
                "Panagiotis / Apostolakis / Yoga / 6 months",
                "John / Panagiotopoulos / Weightlifting / 6 months",
                "Maria / Papadopoulou / Yoga / 3 months",
                "Ioannis / Tsigos / Cardio / 1 year",
                "John / Statham / Yoga / 3 months",
                "Spyros / Ioannou / Pilates / 3 months",
                "Panagiotis / Apostolopoulos / Weightlifting / 6 months",
                "John / Anderson / Yoga / 3 months",
                "Giorgos / Spyrou / Cardio / 3 months",
                "Spyros / Papadakis / Cardio / 6 months"
        );

    }

    public static List<String> queryCheckInsPerHour() {
        return Arrays.asList(
                "9:30 / 3",
                "10:00 / 2",
                "13:00 / 4 ",
                "14:00 / 1",
                "14:30 / 1",
                "18:30 / 1"
        );
    }
}
