public class GymProfileScreen {
    private ManageCheckIn manageCheckIn;

    public GymProfileScreen(ManageCheckIn manageCheckIn) {
        this.manageCheckIn = manageCheckIn;
    }

    public void choosesCheckIn(long gymClientId) {
        manageCheckIn.processCheckIn(gymClientId); // Updated to use processCheckIn
    }
}
