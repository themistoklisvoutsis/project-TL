public class QRGeneration {

    public static void qrGenerate(String qrCode) {
        if (!DBManager.isClientRegistered(qrCode)) {
          InvalidQRCodeScreen.display();
            return;
        }

        if (DBManager.hasCheckedInToday(qrCode)) {
           TooManyCheckInScreen.display();
            return;
        }

        DBManager.updateCheckIn(qrCode);
        CheckInSuccessScreen.display();
    }
}
