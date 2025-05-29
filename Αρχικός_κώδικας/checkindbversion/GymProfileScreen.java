public class GymProfileScreen {
    private ManageCheckIn manageCheckIn;

    public GymProfileScreen(ManageCheckIn manageCheckIn) {
        this.manageCheckIn = manageCheckIn;
    }

    public void choosesCheckIn(String qrCode) {
        manageCheckIn.qrGenerate(qrCode);
    }
}

