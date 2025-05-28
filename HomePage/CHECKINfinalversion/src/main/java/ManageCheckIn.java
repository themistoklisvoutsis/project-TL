public class ManageCheckIn {
    private DBManagerCI dbManager;

    public ManageCheckIn(DBManagerCI dbManager) {
        this.dbManager = dbManager;
    }

    public void processCheckIn(long gymClientId) {
        if (!dbManager.isClientRegistered(gymClientId)) {
            System.out.println("Check-In Failed: Invalid GymClient ID.");
            return;
        }

        if (dbManager.hasCheckedInToday(gymClientId)) {
            System.out.println("Check-In Failed: Already checked in today.");
            return;
        }

        dbManager.updateCheckIn(gymClientId);
        System.out.println("Check-In Successful!");
    }
}

