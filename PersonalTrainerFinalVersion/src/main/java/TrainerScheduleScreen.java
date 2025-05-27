public class TrainerScheduleScreen {
    public static void showTrainersSchedule(String selected){
        DBManagerPT.queryPersonalTrainerSchedule(selected);  // Περνάμε το selected ως παράμετρο
        PersonalProgramFilterScreen.showFilterScreen();
    }
}
