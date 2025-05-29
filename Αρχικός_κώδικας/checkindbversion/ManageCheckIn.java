public class ManageCheckIn {
    private DBManager dbManager;

    public ManageCheckIn(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void qrGenerate(String qrCode) {
        if (!dbManager.isClientRegistered(qrCode)) {
            System.out.println("Check-In Failed: Invalid QR Code.");
            return;
        }

        if (dbManager.hasCheckedInToday(qrCode)) {
            System.out.println("Check-In Failed: Already checked in today.");
            return;
        }

        dbManager.updateCheckIn(qrCode);
        System.out.println("Check-In Successful!");
    }
}

