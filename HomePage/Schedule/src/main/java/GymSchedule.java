public class GymSchedule {
    private String[][] schedule;
    private final String[] timeSlots;
    public static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public GymSchedule() {
        schedule = new String[7][16];
        timeSlots = new String[]{
                "8:00am-9:00am",
                "9:00am-10:00am",
                "10:00am-11:00am",
                "11:00am-12:00pm",
                "12:00pm-13:00pm",
                "13:00pm-14:00pm",
                "14:00pm-15:00pm",
                "15:00pm-16:00pm",
                "16:00pm-17:00pm",
                "17:00pm-18:00pm",
                "18:00pm-19:00pm",
                "19:00pm-20:00pm",
                "20:00pm-21:00pm",
                "21:00pm-22:00pm",
                "22:00pm-23:00pm",
                "23:00pm-00:00am"
        };
    }

    public void addClass(int day, int timeSlot, String className) {
        validateDay(day);
        validateTimeSlot(timeSlot);
        schedule[day][timeSlot] = className;
    }

    public void printDay(int dayIndex) {
        validateDay(dayIndex);
        System.out.println("\n" + DAYS[dayIndex] + ":");
        System.out.println("----------------------------------");
        for (int slot = 0; slot < timeSlots.length; slot++) {
            String session = schedule[dayIndex][slot];
            String displaySession = (session == null) ? "[Available]" : session;
            System.out.printf("%-18s | %s\n", timeSlots[slot], displaySession);
        }
    }

    public void printSchedule() {
        for (int day = 0; day < DAYS.length; day++) {
            printDay(day);
        }
    }


    private void validateDay(int day) {
        if (day < 0 || day >= DAYS.length) {
            throw new IllegalArgumentException("Invalid day index!");
        }
    }

    private void validateTimeSlot(int timeSlot) {
        if (timeSlot < 0 || timeSlot >= timeSlots.length) {
            throw new IllegalArgumentException("Invalid time slot!");
        }
    }

    public static int getDayIndex(String dayName) {
        for (int i = 0; i < DAYS.length; i++) {
            if (DAYS[i].equalsIgnoreCase(dayName.trim())) {
                return i;
            }
        }
        return -1;
    }

    public String getTimeSlotLabel(int index) {
        return timeSlots[index];
    }

    public int getTimeSlotCount() {
        return timeSlots.length;
    }
}