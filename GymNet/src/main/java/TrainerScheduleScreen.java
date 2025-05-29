public class TrainerScheduleScreen {
    public static void showTrainersSchedule(String selected){
        DBManager.queryPersonalTrainerSchedule(selected);
        PersonalProgramFilterScreen.showFilterScreen();
    }
}
